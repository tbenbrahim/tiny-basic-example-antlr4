package com.example.tinybasic;

import com.example.tinybasic.model.ExecutionContext;

import java.io.IOException;

public class MazeTest extends AbstractProgramTest {

    public void testRun() throws IOException {
        ExecutionContext executionContext = loadProgram("/maze.bas");
        executionContext.run();
    }

    public static void main(String[] args) throws IOException {
        new MazeTest().testRun();
    }

}
