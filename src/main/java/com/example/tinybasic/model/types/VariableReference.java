package com.example.tinybasic.model.types;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.ValueType;

public class VariableReference implements ValueType<ValueType<?>> {
    private final String name;

    public VariableReference(String name) {
        this.name=name;
    }

    @Override
    public ValueType<?> getValue(ExecutionContext executionContext) {
        return executionContext.getVariableValue(name);
    }

    public void setValue(ExecutionContext executionContext, ValueType valueType){
        executionContext.setVariableValue(name, valueType);
    }
}
