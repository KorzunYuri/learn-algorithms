package com.yurykorzun.learn.algorithms.java.strings.charsetwindow;

import com.yurykorzun.learn.algorithms.java.TaskSolver;

/**
 *  solution is based on arrays, where we store how many times each letter is presented in a window
 *  on each iteration we may 'make it' or 'break it' for a single letter, reaching the target repeats of falling under
 *  we store, how many letters have reached the target amount, so we don`t need to look at other letters` counters -
 *  we always know, are we good or not
 */
public class Solver2_1 implements TaskSolver {

    @Override
    public String solve(String[] args) {

        int actionsCounter = 0;

        String input = args[0];
        String charSet = args[1];
        String output = null;

        Solver2_InputInfo inputInfo = new Solver2_InputInfo();

        int charCode;
        int charCodeWithShift;

        int[][] arrays = initCountersArrays(inputInfo, input, charSet);

        //  fill target counters
        int[] CHARS_COUNT_TARGET    = arrays[0];    //  constant
        int[] charsCountCurrent     = arrays[1];
        int COVERED_CHARS_TARGET    = 0;            //  constant
        int coveredCharsCurrent     = 0;            //  should increment every time a single char condition is met, and vise versa

        for (int i = 0; i < charSet.length(); i++) {
            charCode = charSet.charAt(i);
            charCodeWithShift = getCharCodeWithShift(inputInfo, charCode);
            CHARS_COUNT_TARGET[charCodeWithShift]++;
            if (CHARS_COUNT_TARGET[charCodeWithShift] == 1) COVERED_CHARS_TARGET++;
        }

        //  move along the input string, minimizing the window
        int firstPos = 0;   //  window first letter pointer
        int lastPos  = 0;   //  window last  letter pointer
        int curCandidateLength = Integer.MAX_VALUE;
        while (lastPos < input.length()) {
            charCode = input.charAt(lastPos);
            charCodeWithShift = getCharCodeWithShift(inputInfo, charCode);
            // System.out.printf("+ %s = %s%n", Character.toString(charCode), input.substring(firstPos, lastPos + 1));
            //  if new letter fulfils the required amount - increment letter counter
            if (isCharInCharSet(inputInfo, CHARS_COUNT_TARGET, charCodeWithShift)) {
                charsCountCurrent[charCodeWithShift]++;
                if ((CHARS_COUNT_TARGET[charCodeWithShift] == charsCountCurrent[charCodeWithShift])) {
                    coveredCharsCurrent++;
                }
            }

            //  cut off char, if char window is not shorter than the charset
            while (firstPos <= lastPos && ((lastPos - firstPos) >= curCandidateLength - 1 || coveredCharsCurrent == COVERED_CHARS_TARGET)) {
                charCode = input.charAt(firstPos);
                charCodeWithShift = getCharCodeWithShift(inputInfo, charCode);
                //  if it`s a meaningful char
                if (isCharInCharSet(inputInfo, CHARS_COUNT_TARGET, charCodeWithShift)) {
                    //  if we are about to find new candidate - don`t cut off
                    if (    coveredCharsCurrent == COVERED_CHARS_TARGET
                        &&  charsCountCurrent[charCodeWithShift] == CHARS_COUNT_TARGET[charCodeWithShift]
                        &&  (lastPos - firstPos) < curCandidateLength
                    ) {
                        break;
                    } else {
                        // System.out.printf("- %s = %s%n", Character.toString(charCode), input.substring(firstPos + 1, lastPos + 1));
                        //  if by cutting the letter we will break its fulfilment - decrease letters counter
                        if (charsCountCurrent[charCodeWithShift] == CHARS_COUNT_TARGET[charCodeWithShift]) {
                            coveredCharsCurrent--;
                        }
                        charsCountCurrent[charCodeWithShift]--;
                    }
                }
                firstPos++;
                actionsCounter++;
            }

            //  check for candidate
            if (coveredCharsCurrent == COVERED_CHARS_TARGET) {
                String candidate = input.substring(firstPos, lastPos + 1);
                if (candidate.length() < curCandidateLength) {
                    // System.out.println("New candidate! " + candidate);
                    output = candidate;
                    curCandidateLength = candidate.length();
                    if (curCandidateLength == charSet.length()) {
                        System.out.printf("%s iterations %n", actionsCounter);
                        return output;
                    }
                }
            }

            //  move forward
            lastPos++;
            actionsCounter++;
        }

        System.out.printf("%s iterations %n", actionsCounter);

        return output;
    }

    /*
     *  the methods below, being far from best code practices,
     *  allow me to reuse the solution in Solver2_2. with reduced counters array size
     */
    protected int[][] initCountersArrays(Solver2_InputInfo info, String input, String charSet) {
        //  z char code is 172
        return new int[2][173];
    }

    protected int getCharCodeWithShift(Solver2_InputInfo info, int charCode) {
        return charCode;
    }

    protected boolean isCharInCharSet(Solver2_InputInfo info, int[] counters, int charCodeWithShift) {
        return counters[charCodeWithShift] > 0;
    }

}

