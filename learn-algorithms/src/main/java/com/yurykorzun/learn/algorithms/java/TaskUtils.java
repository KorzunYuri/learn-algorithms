package com.yurykorzun.learn.algorithms.java;

import org.junit.jupiter.api.Assertions;

public abstract class TaskUtils {

    public static void check(TaskSolver solver, TaskCheck check) {
        String output = solver.solve(check.getInput().getArgs());
        Assertions.assertEquals(check.getExpectedOutput(), output);
        System.out.printf(
            "All good:" +
                "\nclass:  %s" +
                "\ninput:  '%s'" +
                "\noutput: '%s'" +
                "\n",
            solver.getClass().getSimpleName(),
            check.getInput().getInputString(),
            output);
    }

}
