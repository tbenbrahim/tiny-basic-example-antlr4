package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Statement;

public class GotoStatemnt implements Statement {
    private final int lineNumber;

    public GotoStatemnt(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.gotoLine(lineNumber);
    }
}
