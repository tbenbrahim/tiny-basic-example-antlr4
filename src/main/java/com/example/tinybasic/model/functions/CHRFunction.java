package com.example.tinybasic.model.functions;

import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.StringValue;

public class CHRFunction extends AbstractOneNumberFunction{
    @Override
    public ValueType invoke(Object... args) {
        return new StringValue((char) ((Integer) args[0]).intValue() + "");
    }

}
