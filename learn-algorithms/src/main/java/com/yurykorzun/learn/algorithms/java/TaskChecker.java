package com.yurykorzun.learn.algorithms.java;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TaskChecker {

    private List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public void checkAll(TaskSolver solver) {
        System.out.println("======================");
        System.out.printf("%s%n", solver.getClass().getName());
        tasks.forEach(task -> {
            System.out.println("----------------------");
            check(solver, task);
        });
        System.out.println("======================");
    }

    public void check(TaskSolver solver, Task check) {
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
