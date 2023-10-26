package com.yurykorzun.learn.algorithms.java.strings;

import com.yurykorzun.learn.algorithms.java.Task;
import com.yurykorzun.learn.algorithms.java.TaskChecker;
import com.yurykorzun.learn.algorithms.java.TaskInput;
import com.yurykorzun.learn.algorithms.java.TaskSolver;

import java.util.*;

/**
 *  input - array of TWO strings:
 *      args[0] - string of length N
 *      args[1] - string of length K <= N
 *  output:
 *      first shortest substring of args[0] containing all letters from args[1], otherwise blank string
 *      if the letter repeats in args[1] then the substring must contain exactly at least that amount of repeats of the letter
 */
public class FindShortestSubstringContainingSetOfLetters implements TaskSolver {

    @Override
    public String solve(String[] args) {

        String input = args[0];
        String tokens = args[1];

        Map<Integer, Integer> tmp = new HashMap<>();
        Map<Integer, Integer> tokenCountWindow = new HashMap<>();
        for (int i = 0; i < tokens.length(); i++) {
            Integer charCode = (int) tokens.charAt(i);
            if (!tmp.containsKey(charCode)) tmp.put(charCode, 0);
            tmp.put(charCode, tmp.get(charCode) + 1);
            tokenCountWindow.put(charCode, 0);
        }
        Map<Integer, Integer> tokenCountAim = Collections.unmodifiableMap(tmp);

        String output = "";
        int curMinLength = Integer.MAX_VALUE;

        int firstLetterPos = 0;
        int lastLetterPos = 0;

        while (lastLetterPos < input.length()) {
            Integer lastLetterCode = (int) input.charAt(lastLetterPos);
            if (tokenCountAim.containsKey(lastLetterCode)) {
                tokenCountWindow.put(lastLetterCode, tokenCountWindow.get(lastLetterCode) + 1);
            }

            if (tokenCountWindow.entrySet().stream().allMatch(e -> e.getValue() >= tokenCountAim.get(e.getKey()))) {

                //  cut off the tail
                Integer firstLetterCode = (int) input.charAt(firstLetterPos);
                while (firstLetterPos < lastLetterPos
                        && (      !tokenCountWindow.containsKey(firstLetterCode)
                                || tokenCountWindow.get(firstLetterCode) > tokenCountAim.get(firstLetterCode))
                ) {

                    if (tokenCountWindow.containsKey(firstLetterCode)) {
                        tokenCountWindow.put(firstLetterCode, tokenCountWindow.get(firstLetterCode) - 1);
                    }
                    firstLetterPos += 1;
                }

                //  present a candidate
                String candidate = input.substring(firstLetterPos, lastLetterPos + 1);
                if (candidate.length() < curMinLength) {
                    curMinLength = candidate.length();
                    output = candidate;
                }

            }
            lastLetterPos += 1;
        }

        return output;
    }

    public static void main(String[] args) {
        TaskChecker checker = new TaskChecker();

        //  provide some tests
        String sepWhiteSpace = " ";
        String sepSemiColon = ";";
        checker.add(new Task(new TaskInput("avaajaav aav",              sepWhiteSpace), "ava"));
        checker.add(new Task(new TaskInput("abcdefja abca",             sepWhiteSpace), "abcdefja"));
        checker.add(new Task(new TaskInput("abcabc acb",                sepWhiteSpace), "abc"));
        checker.add(new Task(new TaskInput("aavvaabbaavvbb abvabva",    sepWhiteSpace), "avvaabb"));

        TaskSolver solver = new FindShortestSubstringContainingSetOfLetters();
        checker.checkAll(solver);
    }

}
