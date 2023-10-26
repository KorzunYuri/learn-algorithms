package com.yurykorzun.learn.algorithms.java;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;

@AllArgsConstructor
public class TaskChecker {
    TaskInput input;
    private String expectedOutput;

    public void check(TaskSolver solver) {
        String output = solver.solve(input.getArgs());
        Assertions.assertEquals(expectedOutput, output);
        System.out.printf("All good:\nclass:  %s\ninput:  '%s'\noutput: '%s'\n", solver.getClass().getSimpleName(), input.getInputString(), output);
    }
}
