package com.yurykorzun.learn.algorithms.java.strings.charsetwindow;

/**
 *  optimizes size of letter counters array, but has to check, whether char code is in range
 */
public class Solver2_2 extends Solver2_1 {

    @Override
    protected int[][] initCountersArrays(Solver2_InputInfo info, String input, String charSet) {
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;
        for (int i = 0; i < charSet.length(); i++) {
            int charCode = input.charAt(i);
            if (charCode < minPos) minPos = charCode;
            if (charCode > maxPos) maxPos = charCode;
        }
        info.shift = minPos;
        info.charCodeMax = maxPos - info.shift;
        return new int[2][info.charCodeMax + 1];
    }

    @Override
    protected int getCharCodeWithShift(Solver2_InputInfo info, int charCode) {
        return charCode - info.shift;
    }

    @Override
    protected boolean isCharInCharSet(Solver2_InputInfo info, int[] counters, int charCodeWithShift) {
        return charCodeWithShift <= info.charCodeMax && counters[charCodeWithShift] > 0;
    }

}
