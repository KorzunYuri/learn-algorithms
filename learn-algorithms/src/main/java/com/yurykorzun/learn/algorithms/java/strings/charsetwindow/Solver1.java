package com.yurykorzun.learn.algorithms.java.strings.charsetwindow;

import com.yurykorzun.learn.algorithms.java.utils.base.TaskSolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solver1 implements TaskSolver<String, String> {

    @Override
    public String solve(String[] args) {

        int actionsCounter = 0;

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
            int lastLetterCode = input.charAt(lastLetterPos);
            if (tokenCountAim.containsKey(lastLetterCode)) {
                tokenCountWindow.put(lastLetterCode, tokenCountWindow.get(lastLetterCode) + 1);
            }

            if (tokenCountWindow.entrySet().stream().allMatch(e -> e.getValue() >= tokenCountAim.get(e.getKey()))) {

                //  cut off the tail
                int firstLetterCode = input.charAt(firstLetterPos);
                while (firstLetterPos < lastLetterPos
                        && (      !tokenCountWindow.containsKey(firstLetterCode)
                        || tokenCountWindow.get(firstLetterCode) > tokenCountAim.get(firstLetterCode))
                ) {

                    // System.out.printf("Current window: %s%n", input.substring(firstLetterPos, lastLetterPos + 1));
                    if (tokenCountWindow.containsKey(firstLetterCode)) {
                        tokenCountWindow.put(firstLetterCode, tokenCountWindow.get(firstLetterCode) - 1);
                    }
                    firstLetterPos++;
                    actionsCounter++;
                    firstLetterCode = input.charAt(firstLetterPos);
                }

                //  present a candidate
                String candidate = input.substring(firstLetterPos, lastLetterPos + 1);
                if (candidate.length() < curMinLength) {
                    curMinLength = candidate.length();
                    output = candidate;
                }

            }
            lastLetterPos++;
            actionsCounter++;
        }

        System.out.printf("%s iterations%n", actionsCounter);

        return output;
    }

}
