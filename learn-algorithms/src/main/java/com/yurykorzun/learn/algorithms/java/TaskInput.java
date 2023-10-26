package com.yurykorzun.learn.algorithms.java;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskInput {
    private String inputString;
    private String argsSeparator;

    public String[] getArgs() {
        return inputString.split(argsSeparator);
    }
}
