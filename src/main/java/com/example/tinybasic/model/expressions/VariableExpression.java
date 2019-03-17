package com.example.tinybasic.model.expressions;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.ValueType;

public class VariableExpression implements Expression {

    private final String name;

    public VariableExpression(String name){
        this.name=name;
    }

    @Override
    public ValueType evaluate(ExecutionContext executionContext) {
        return executionContext.getVariableValue(name);
    }
}
