package com.example.tinybasic.visitors;

import com.example.parser.TinyBasicBaseVisitor;
import com.example.parser.TinyBasicParser;

public class NumberVisitor extends TinyBasicBaseVisitor<Integer> {

    @Override
    public Integer visitNumber(TinyBasicParser.NumberContext ctx) {
        return Integer.parseInt(ctx.getText());
    }
}
