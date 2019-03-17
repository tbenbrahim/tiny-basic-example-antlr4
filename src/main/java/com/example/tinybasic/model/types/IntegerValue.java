package com.example.tinybasic.model.types;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.ValueType;

public class IntegerValue implements ValueType {
    private final Integer value;

    public IntegerValue(Integer value) {
        this.value=value;
    }

    @Override
    public Object getValue(ExecutionContext executionContext) {
        return value;
    }
}
