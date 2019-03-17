package com.example.tinybasic.model.expressions;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.IntegerValue;

public class IntegerExpression implements Expression {

    private final IntegerValue value;

    public IntegerExpression(Integer value){
        this.value=new IntegerValue(value);
    }

    @Override
    public ValueType evaluate(ExecutionContext executionContext) {
        return value;
    }
}
