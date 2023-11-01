package com.yurykorzun.learn.algorithms.java.utils.input;

import com.yurykorzun.learn.algorithms.java.utils.base.TaskInput;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StringTaskInput extends TaskInput<String> {
    private String inputString;
    private String argsSeparator;

    @Override
    public String[] getArgs() {
        return this.getInputString().split(this.getArgsSeparator());
    }

    @Override
    public String inputToString() {
        return inputString;
    }
}
