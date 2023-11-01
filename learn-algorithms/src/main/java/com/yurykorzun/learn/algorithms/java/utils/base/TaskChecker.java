package com.yurykorzun.learn.algorithms.java.utils.base;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TaskChecker<I, R> {

    private List<Task<I, R>> tasks = new ArrayList<>();

    public void add(Task<I, R> task) {
        tasks.add(task);
    }

    public void checkAll(TaskSolver<I, R> solver) {
        System.out.println("======================");
        System.out.printf("%s%n", solver.getClass().getName());
        tasks.forEach(task -> {
            System.out.println("----------------------");
            check(solver, task);
        });
        System.out.println("======================");
    }

    public void check(TaskSolver<I, R> solver, Task<I, R> check) {
        R output = solver.solve(check.getInput().getArgs());
        Assertions.assertEquals(check.getExpectedOutput(), output);
        System.out.printf(
                "All good:" +
                        "\nclass:  %s" +
                        "\ninput:  '%s'" +
                        "\noutput: '%s'" +
                        "\n",
                solver.getClass().getSimpleName(),
                check.getInput().inputToString(),
                output);
    }

}
