package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Statement;

public class GosubStatemnet implements Statement {
    private final int lineNumber;

    public GosubStatemnet(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.pushSubroutine(lineNumber);
    }
}
