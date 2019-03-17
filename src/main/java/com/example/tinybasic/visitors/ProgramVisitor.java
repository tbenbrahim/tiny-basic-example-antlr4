package com.example.tinybasic.visitors;

import com.example.parser.TinyBasicBaseVisitor;
import com.example.parser.TinyBasicParser;
import com.example.tinybasic.model.ExecutionContext;
import com.example.tinybasic.model.ProgramLine;

public class ProgramVisitor extends TinyBasicBaseVisitor<Void> {

    private final ExecutionContext executionContext;

    public ProgramVisitor(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    @Override
    public Void visitProgramLine(TinyBasicParser.ProgramLineContext ctx) {
        TinyBasicParser.NumberContext c = ctx.number();
        int lineNumber=c.accept(new NumberVisitor());
        System.out.println("line "+lineNumber);
        executionContext.addProgramLine(
                new ProgramLine(ctx.number().accept(new NumberVisitor()), ctx.statement().accept(new StatementVisitor())));
        return null;
    }

}
