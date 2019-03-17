package com.example.tinybasic.model.expressions;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.StringValue;

public class StringExpression implements Expression {

    private final StringValue value;

    public StringExpression(String value){
        this.value=new StringValue(value.substring(1, value.length()-1));
    }
    @Override
    public ValueType evaluate(ExecutionContext executionContext) {
        return value;
    }
}
