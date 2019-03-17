package com.example.tinybasic.model.expressions;

import com.example.tinybasic.TinyBasicError;
import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.IntegerValue;

public abstract class AbstractArithmeticExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public AbstractArithmeticExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public final ValueType evaluate(ExecutionContext executionContext) {
        Object value1 = left.evaluate(executionContext).getValue(executionContext);
        Object value2 = right.evaluate(executionContext).getValue(executionContext);
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return new IntegerValue(evaluate((Integer) value1, (Integer) value2));
        }
        throw new TinyBasicError("Invalid type for arithmetic operation");
    }

    protected abstract Integer evaluate(Integer value1, Integer value2);
}
