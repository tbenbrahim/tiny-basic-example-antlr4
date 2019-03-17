package com.example.tinybasic.model.expressions;

import com.example.tinybasic.TinyBasicError;
import com.example.tinybasic.model.Expression;

public class DivisionExpression extends AbstractArithmeticExpression {
    public DivisionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected Integer evaluate(Integer value1, Integer value2) {
        if (value2 == 0) {
            throw new TinyBasicError("Divsion by zero");
        }
        return value1 / value2;
    }
}
