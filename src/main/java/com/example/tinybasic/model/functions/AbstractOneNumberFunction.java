package com.example.tinybasic.model.functions;

import com.example.tinybasic.model.FunctionDefinition;

public abstract class AbstractOneNumberFunction implements FunctionDefinition {
    @Override
    public boolean acceptsArguments(Object... args) {
        return args.length == 1 && args[0] instanceof Integer;
    }

    @Override
    public String validationError(String name, Object... args) {
        if (args.length != 1) {
            return "Expected 1 argument in call to " + name;
        }
        if (!(args[0] instanceof Integer)) {
            return "Expected a number in call to " + name;
        }
        throw new IllegalStateException("Unexpected call to validationError");
    }
}
