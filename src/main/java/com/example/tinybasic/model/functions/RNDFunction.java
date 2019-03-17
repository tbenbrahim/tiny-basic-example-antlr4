package com.example.tinybasic.model.functions;

import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.IntegerValue;

import java.util.Random;

public class RNDFunction extends AbstractOneNumberFunction {

    @Override
    public ValueType invoke(Object... args) {
        int bound = (Integer) args[0];
        int value = new Random().nextInt(bound);
        return new IntegerValue(value);
    }

}
