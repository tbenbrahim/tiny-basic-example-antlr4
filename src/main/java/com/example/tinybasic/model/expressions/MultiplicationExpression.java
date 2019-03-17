package com.example.tinybasic.model.expressions;

import com.example.tinybasic.model.Expression;

public class MultiplicationExpression extends AbstractArithmeticExpression {

    public MultiplicationExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected Integer evaluate(Integer value1, Integer value2) {
        return value1 * value2;
    }
}
