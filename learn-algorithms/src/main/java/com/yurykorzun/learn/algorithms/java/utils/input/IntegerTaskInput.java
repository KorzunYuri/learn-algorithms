package com.yurykorzun.learn.algorithms.java.utils.input;

import com.yurykorzun.learn.algorithms.java.utils.base.TaskInput;

public class IntegerTaskInput extends TaskInput<Integer> {

    private Integer[] args;

    public IntegerTaskInput(int[] input) {
        this.args = new Integer[input.length];
        for (int i = 0; i < input.length; i++) {
            this.args[i] = input[i];
        }
    }

    public IntegerTaskInput(String input, String separator) {
        String[] strArgs = input.split(separator);
        this.args = new Integer[strArgs.length];
        for (int i = 0; i < strArgs.length; i++) {
            this.args[i] = Integer.parseInt(strArgs[i]);
        }
    }

    @Override
    public Integer[] getArgs() {
        return args;
    }

    @Override
    public String inputToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]).append(",");
        }
        return builder.toString();
    }
}
