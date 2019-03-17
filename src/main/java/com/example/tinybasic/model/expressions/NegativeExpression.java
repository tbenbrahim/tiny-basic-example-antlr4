package com.example.tinybasic.model.expressions;

import com.example.tinybasic.TinyBasicError;
import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.IntegerValue;

public class NegativeExpression implements Expression {
    private final Expression expression;

    public NegativeExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ValueType evaluate(ExecutionContext executionContext) {
        Object value = expression.evaluate(executionContext).getValue(executionContext);
        if (value instanceof Integer) {
            return new IntegerValue(-(Integer) value);
        }
        throw new TinyBasicError("Invalid type in sign expression");
    }
}
