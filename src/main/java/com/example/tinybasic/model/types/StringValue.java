package com.example.tinybasic.model.types;

import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.ValueType;

public class StringValue implements ValueType<String> {
    private final String text;

    public StringValue(String text) {
        this.text = text;
    }

    @Override
    public String getValue(ExecutionContext executionContext) {
        return text;
    }
}
