package com.yurykorzun.learn.algorithms.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;

@AllArgsConstructor
@Getter
public class TaskCheck {
    TaskInput input;
    private String expectedOutput;
}
