package com.example.tinybasic.model.statements;

import com.example.tinybasic.TinyBasicError;
import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;

public class Condition {

    private final Expression expression1;
    private final String relop;
    private final Expression expression2;

    public Condition(Expression expression1, String relop, Expression expression2) {
        this.expression1 = expression1;
        this.relop = relop;
        this.expression2 = expression2;
    }

    private <T extends Comparable<T>> boolean compare(String relop, T value1, T value2) {
        switch (relop) {
            case "<":
                return value1.compareTo(value2) < 0;
            case "<=":
                return value1.compareTo(value2) <= 0;
            case "<>":
                return value1.compareTo(value2) != 0;
            case ">":
                return value1.compareTo(value2) > 0;
            case ">=":
                return value1.compareTo(value2) >= 0;
            case "=":
                return value1.compareTo(value2) == 0;
            default:
                throw new IllegalStateException("Unhandled relational operator in IF statement");
        }
    }


    public boolean evaluate(ExecutionContext executionContext) {
        Object value1 = expression1.evaluate(executionContext).getValue(executionContext);
        Object value2 = expression2.evaluate(executionContext).getValue(executionContext);
        if (value1 instanceof String && value2 instanceof String) {
            return compare(relop, (String) value1, (String) value2);
        } else if (value1 instanceof Integer && value2 instanceof Integer) {
            return compare(relop, (Integer) value1, (Integer) value2);
        } else {
            throw new TinyBasicError("Incompatible types in IF statement comparison");
        }
    }
}
