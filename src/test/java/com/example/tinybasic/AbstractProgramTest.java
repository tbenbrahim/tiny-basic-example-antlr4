package com.example.tinybasic;

import com.example.parser.TinyBasicLexer;
import com.example.parser.TinyBasicParser;
import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.visitors.ProgramVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public abstract class AbstractProgramTest {

    protected ExecutionContext loadProgram(String name) {
        String program = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(name), StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\r\n"));
        ExecutionContext executionContext = new ExecutionContext();
        ProgramVisitor programVisitor = new ProgramVisitor(executionContext);
        TinyBasicLexer lexer = new TinyBasicLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TinyBasicParser parser = new TinyBasicParser(tokens);
        ParseTree tree = parser.program();
        programVisitor.visit(tree);
        return executionContext;
    }
}
