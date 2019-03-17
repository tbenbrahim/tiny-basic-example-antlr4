package com.example.tinybasic.visitors;

import com.example.parser.TinyBasicBaseVisitor;
import com.example.parser.TinyBasicParser;
import com.example.tinybasic.model.Expression;
import com.example.tinybasic.model.expressions.*;

import java.util.stream.Collectors;

public class ExpressionVisitor extends TinyBasicBaseVisitor<Expression> {

    @Override
    public Expression visitParentheses(TinyBasicParser.ParenthesesContext ctx) {
        return ctx.expression().accept(new ExpressionVisitor());
    }

    @Override
    public Expression visitMultiplyDivide(TinyBasicParser.MultiplyDivideContext ctx) {
        switch (ctx.op.getText()) {
            case "*":
                return new MultiplicationExpression(ctx.expression(0).accept(new ExpressionVisitor()),
                        ctx.expression(1).accept(new ExpressionVisitor()));
            case "/":
                return new DivisionExpression(ctx.expression(0).accept(new ExpressionVisitor()),
                        ctx.expression(1).accept(new ExpressionVisitor()));
            default:
                throw new IllegalStateException("Unhandled operator in MultiplyDivde");
        }
    }

    @Override
    public Expression visitAddSubtract(TinyBasicParser.AddSubtractContext ctx) {
        switch (ctx.op.getText()) {
            case "+":
                return new AdditionExpression(ctx.expression(0).accept(new ExpressionVisitor()),
                        ctx.expression(1).accept(new ExpressionVisitor()));
            case "-":
                return new SubtractionExpression(ctx.expression(0).accept(new ExpressionVisitor()),
                        ctx.expression(1).accept(new ExpressionVisitor()));
            default:
                throw new IllegalStateException("Unhandled operator in AddSubtract");
        }
    }

    @Override
    public Expression visitFunctionCall(TinyBasicParser.FunctionCallContext ctx) {
        return new FunctionExpression(ctx.name.getText(),
                ctx.expression().stream()
                        .map(expressionContext -> expressionContext.accept(new ExpressionVisitor()))
                        .collect(Collectors.toList()));
    }

    @Override
    public Expression visitSign(TinyBasicParser.SignContext ctx) {
        return "-".equals(ctx.sign.getText()) ? new NegativeExpression(ctx.expression().accept(new ExpressionVisitor())) :
                ctx.expression().accept(new ExpressionVisitor());
    }

    @Override
    public Expression visitVariable(TinyBasicParser.VariableContext ctx) {
        return new VariableExpression(ctx.var().VAR().getSymbol().getText());
    }

    @Override
    public Expression visitString(TinyBasicParser.StringContext ctx) {
        return new StringExpression(ctx.STRING().getSymbol().getText());
    }

    @Override
    public Expression visitNum(TinyBasicParser.NumContext ctx) {
        return new IntegerExpression(Integer.parseInt(ctx.number().getText()));
    }
}
