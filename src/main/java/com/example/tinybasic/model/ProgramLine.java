package com.example.tinybasic.model;

public class ProgramLine implements Comparable<ProgramLine> {

    private int lineNumber;
    private Statement statement;

    public ProgramLine(int lineNumber, Statement statement) {
        this.lineNumber = lineNumber;
        this.statement = statement;
    }

    public int compareTo(ProgramLine other) {
        return lineNumber - other.lineNumber;
    }

    public void execute(ExecutionContext executionContext) {
        statement.execute(executionContext);
    }
}
