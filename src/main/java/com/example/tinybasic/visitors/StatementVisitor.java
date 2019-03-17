package com.example.tinybasic.visitors;

import com.example.parser.TinyBasicBaseVisitor;
import com.example.parser.TinyBasicParser;
import com.example.tinybasic.model.Statement;
import com.example.tinybasic.model.statements.*;

import java.util.stream.Collectors;

public class StatementVisitor extends TinyBasicBaseVisitor<Statement> {

    @Override
    public Statement visitPrint(TinyBasicParser.PrintContext ctx) {
        return new PrintStatement(ctx.exprlist().expression().stream()
                .map(expr -> expr.accept(new ExpressionVisitor()))
                .collect(Collectors.toList()),
                ctx.nonl != null);
    }

    @Override
    public Statement visitIf(TinyBasicParser.IfContext ctx) {
        return new IfStatement(new Condition(ctx.expression(0).accept(new ExpressionVisitor()),
                ctx.relop().getText(),
                ctx.expression(1).accept(new ExpressionVisitor())),
                ctx.statement().accept(new StatementVisitor()));
    }

    @Override
    public Statement visitGoto(TinyBasicParser.GotoContext ctx) {
        return new GotoStatemnt(ctx.number().accept(new NumberVisitor()));
    }

    @Override
    public Statement visitInput(TinyBasicParser.InputContext ctx) {
        return new InputStatement(ctx.varlist().var().stream()
                .map(var -> var.accept(new VarArgumentVisitor()))
                .collect(Collectors.toList()));
    }

    @Override
    public Statement visitAssignment(TinyBasicParser.AssignmentContext ctx) {
        return new AssignmentStatement(ctx.var().accept(new VarArgumentVisitor()),
                ctx.expression().accept(new ExpressionVisitor()));
    }

    @Override
    public Statement visitGosub(TinyBasicParser.GosubContext ctx) {
        return new GosubStatemnet(ctx.number().accept(new NumberVisitor()));
    }

    @Override
    public Statement visitReturn(TinyBasicParser.ReturnContext ctx) {
        return new ReturnStatement();
    }

    @Override
    public Statement visitEnd(TinyBasicParser.EndContext ctx) {
        return new EndStatement();
    }
}
