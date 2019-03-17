package com.example.tinybasic.model.statements;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.Statement;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.IntegerValue;
import com.example.tinybasic.model.types.StringValue;
import com.example.tinybasic.model.types.VariableReference;

import java.util.List;
import java.util.Scanner;

public class InputStatement implements Statement {
    private final List<ValueType> variables;

    public InputStatement(List<ValueType> variables) {
        this.variables = variables;
    }

    @Override
    public void execute(ExecutionContext executionContext) {
        Scanner in = new Scanner(System.in);
        for (ValueType valueType : variables) {
            String input = in.nextLine();
            try {
                int intValue = Integer.parseInt(input);
                ((VariableReference) valueType).setValue(executionContext, new IntegerValue(intValue));
            } catch (NumberFormatException e) {
                ((VariableReference) valueType).setValue(executionContext, new StringValue(input));
            }
        }
    }
}
