package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Statement;

public class IfStatement implements Statement {
    private final Statement statement;
    private final Condition condition;

    public IfStatement(Condition condition, Statement statement) {
        this.condition=condition;
        this.statement = statement;
    }

    @Override
    public void execute(ExecutionContext executionContext) {
        if (condition.evaluate(executionContext)) {
            statement.execute(executionContext);
        }
    }
}