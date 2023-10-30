package com.yurykorzun.learn.algorithms.java.strings.charsetwindow;

import com.yurykorzun.learn.algorithms.java.TaskSolver;

import java.util.HashMap;
import java.util.Map;

/**
 *  solution is optimized - we always decrease window size, and we stop searching when solution is of minimal possible size
 */
public class Solver2 implements TaskSolver {

    @Override
    public String solve(String[] args) {

        int actionsCounter = 0;

        String input = args[0];
        String charSet = args[1];
        String output = null;
        
        int charCode;

        Map<Integer, Integer> CHARS_COUNT_TARGET    = new HashMap<>();  // for each letter, number of repeats required
        Map<Integer, Integer> charsCountCurrent     = new HashMap<>();  // for each letter, number of repeats in current window
        int COVERED_CHARS_TARGET    = 0;            //  constant
        int coveredCharsCurrent     = 0;            //  should increment every time a single char condition is met, and vise versa

        //  calculate target number for every letter and initialize window counter
        for (int i = 0; i < charSet.length(); i++) {
            charCode = charSet.charAt(i);
            CHARS_COUNT_TARGET.put(charCode, CHARS_COUNT_TARGET.getOrDefault(charCode, 0) + 1);
            if (CHARS_COUNT_TARGET.get(charCode).equals(1)) COVERED_CHARS_TARGET++;
            charsCountCurrent.put(charCode, 0);
        }

        //  move along the input string, minimizing the window
        int firstPos = 0;   //  window first letter pointer
        int lastPos  = 0;   //  window last  letter pointer
        int curCandidateLength = Integer.MAX_VALUE;
        while (lastPos < input.length()) {
            charCode = input.charAt(lastPos);
            //System.out.printf("+ %s = %s%n", Character.toString(charCode), input.substring(firstPos, lastPos + 1));
            //  if new letter reaches the required number of repeats - increment letter counter
            if (CHARS_COUNT_TARGET.containsKey(charCode)) {
                charsCountCurrent.put(charCode, charsCountCurrent.get(charCode) + 1);
                if (CHARS_COUNT_TARGET.get(charCode).equals(charsCountCurrent.get(charCode))) {
                    coveredCharsCurrent++;
                }
            }

            //  cut off char, if char window is not shorter than the charset
            while (firstPos <= lastPos && ((lastPos - firstPos) >= curCandidateLength - 1 || coveredCharsCurrent == COVERED_CHARS_TARGET)) {
                charCode = input.charAt(firstPos);
                //  if it`s a meaningful char
                if (CHARS_COUNT_TARGET.containsKey(charCode)) {
                    //  if we are about to find new candidate - don`t cut off
                    if (    coveredCharsCurrent == COVERED_CHARS_TARGET
                        &&  CHARS_COUNT_TARGET.get(charCode).equals(charsCountCurrent.get(charCode))
                        &&  (lastPos - firstPos) < curCandidateLength
                    ) {
                        break;
                    } else {
                        //System.out.printf("- %s = %s%n", Character.toString(charCode), input.substring(firstPos + 1, lastPos + 1));
                        //  if by cutting the letter we will break its fulfilment - decrease letters counter
                        if (CHARS_COUNT_TARGET.get(charCode).equals(charsCountCurrent.get(charCode))) {
                            coveredCharsCurrent--;
                        }
                        charsCountCurrent.put(charCode, charsCountCurrent.get(charCode) - 1);
                    }
                }
                firstPos++;
                actionsCounter++;
            }

            //  check for candidate
            if (coveredCharsCurrent == COVERED_CHARS_TARGET) {
                String candidate = input.substring(firstPos, lastPos + 1);
                if (candidate.length() < curCandidateLength) {
                    //System.out.println("New candidate! " + candidate);
                    output = candidate;
                    curCandidateLength = candidate.length();
                    if (curCandidateLength == charSet.length()) {
                        //System.out.printf("%s iterations %n", actionsCounter);
                        return output;
                    }
                }
            }

            //  move forward
            lastPos++;
            actionsCounter++;
        }

        //System.out.printf("%s iterations %n", actionsCounter);

        return output;
    }

}

