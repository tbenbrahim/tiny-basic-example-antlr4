package com.example.tinybasic.visitors;

import com.example.parser.TinyBasicBaseVisitor;
import com.example.parser.TinyBasicParser;
import com.example.tinybasic.model.ValueType;
import com.example.tinybasic.model.types.VariableReference;

public class VarArgumentVisitor extends TinyBasicBaseVisitor<ValueType> {
    @Override
    public ValueType visitVar(TinyBasicParser.VarContext ctx) {
        return new VariableReference(ctx.VAR().getText());
    }
}
