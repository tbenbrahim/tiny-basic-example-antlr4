package com.example.tinybasic.model.expressions;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.ValueType;

import java.util.List;

public class FunctionExpression implements Expression {

    private final List<Expression> argumentExpressions;
    private final String name;

    public FunctionExpression(String name, List<Expression> argumentExpressions){
        this.name=name;
        this.argumentExpressions=argumentExpressions;
    }

    @Override
    public ValueType evaluate(ExecutionContext executionContext) {
        Object[] arguments=argumentExpressions.stream()
                .map(expr->expr.evaluate(executionContext).getValue(executionContext))
                .toArray();
        return executionContext.executeFunction(name, arguments);
    }
}
