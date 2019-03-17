package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.Statement;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.VariableReference;

public class AssignmentStatement implements Statement {
    private final ValueType variable;
    private final Expression expression;

    public AssignmentStatement(ValueType variable, Expression expression) {
        this.variable=variable;
        this.expression=expression;
    }

    @Override
    public void execute(ExecutionContext executionContext) {
        ValueType value = expression.evaluate(executionContext);
        if (value instanceof VariableReference){
            value=((VariableReference)value).getValue(executionContext);
        }
        ((VariableReference)variable).setValue(executionContext, value);
    }
}
