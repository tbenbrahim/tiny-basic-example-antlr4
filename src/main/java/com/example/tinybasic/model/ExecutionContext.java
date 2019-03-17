package com.example.tinybasic.model;

import com.example.tinybasic.TinyBasicError;
import com.example.tinybasic.model.functions.CHRFunction;
import com.example.tinybasic.model.functions.RNDFunction;

import java.util.*;

public class ExecutionContext {

    private final List<ProgramLine> programLines = new ArrayList<>();
    private boolean terminated;
    private Map<Integer, Integer> lineNumberToIndexMap = new HashMap<>();
    private Map<String, ValueType> variables = new HashMap<>();
    private int lineIndex;
    private Deque<Integer> subroutineStack = new ArrayDeque<>();
    private Map<String, FunctionDefinition> functions = new HashMap<>();

    public ExecutionContext() {
        functions.put("RND", new RNDFunction());
        functions.put("CHR", new CHRFunction());
    }

    public ValueType<?> getVariableValue(String name) {
        ValueType value = variables.get(name);
        if (value == null) {
            throw new TinyBasicError("Undefined variable " + name);
        }
        return value;
    }

    public void setVariableValue(String name, ValueType value) {
        variables.put(name, value);
    }

    public void addProgramLine(ProgramLine programLine) {
        programLines.add(programLine);
        reset();
    }

    private void reset() {
        this.lineNumberToIndexMap.clear();
        this.variables.clear();
        this.subroutineStack.clear();
        this.lineIndex = 0;
        this.terminated = false;
    }

    public void terminate() {
        this.terminated = true;
    }

    public void gotoLine(int lineNumber) {
        this.lineIndex = lineNumberToIndexMap.computeIfAbsent(lineNumber, this::getLineIndex) - 1;
    }

    public void pushSubroutine(int lineNumber) {
        subroutineStack.push(this.lineIndex);
        this.lineIndex = lineNumberToIndexMap.computeIfAbsent(lineNumber, this::getLineIndex);
    }

    public void popSubroutine() {
        try {
            this.lineIndex = subroutineStack.pop();
        } catch (NoSuchElementException e) {
            throw new TinyBasicError("Mismatched RETURN without GOSUB");
        }
    }

    public ValueType executeFunction(String name, Object... arguments) {
        FunctionDefinition functionDefinition = functions.get(name);
        if (functionDefinition == null) {
            throw new TinyBasicError("Unknown function " + name);
        }
        if (functionDefinition.acceptsArguments(arguments)) {
            return functionDefinition.invoke(arguments);
        }
        throw new TinyBasicError(functionDefinition.validationError(name, arguments));
    }

    public void run() {
        int numLines = programLines.size();
        programLines.sort(ProgramLine::compareTo);
        while (!terminated && lineIndex < numLines) {
            programLines.get(lineIndex).execute(this);
            ++lineIndex;
        }
    }

    private Integer getLineIndex(Integer lineNumber) {
        int result = Collections.binarySearch(programLines, new ProgramLine(lineNumber, null));
        if (result < 0) {
            throw new TinyBasicError("Invalid line number " + lineNumber);
        }
        return result;
    }
}
