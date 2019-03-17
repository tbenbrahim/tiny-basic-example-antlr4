package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.Statement;

import java.util.List;

public class PrintStatement implements Statement {
    private final List<Expression> expressions;
    private final boolean noNewline;

    public PrintStatement(List<Expression> expressions, boolean noNewline) {
        this.expressions = expressions;
        this.noNewline = noNewline;
    }

    @Override
    public void execute(ExecutionContext executionContext) {
        expressions.stream().map(expression -> expression.evaluate(executionContext).getValue(executionContext))
                .forEach(value -> System.out.print(value.toString()));
        if (!noNewline) {
            System.out.println();
        }
    }
}
