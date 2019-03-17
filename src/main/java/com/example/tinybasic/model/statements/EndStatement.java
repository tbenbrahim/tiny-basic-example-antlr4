package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Statement;

public class EndStatement implements Statement {
    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.terminate();
    }
}
