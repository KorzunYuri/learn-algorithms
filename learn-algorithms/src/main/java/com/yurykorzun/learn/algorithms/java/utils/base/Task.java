package com.yurykorzun.learn.algorithms.java.utils.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Task<I, R> {
    TaskInput<I> input;
    private R expectedOutput;
}
