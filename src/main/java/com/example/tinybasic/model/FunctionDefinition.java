package com.example.tinybasic.model;

public interface FunctionDefinition {

    ValueType invoke(Object... args);

    boolean acceptsArguments(Object...args);

    String validationError(String name, Object...args);
}
