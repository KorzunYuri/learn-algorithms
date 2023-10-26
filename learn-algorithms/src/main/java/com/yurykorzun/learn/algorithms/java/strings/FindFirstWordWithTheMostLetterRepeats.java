package com.yurykorzun.learn.algorithms.java.strings;

import com.yurykorzun.learn.algorithms.java.TaskChecker;
import com.yurykorzun.learn.algorithms.java.TaskInput;
import com.yurykorzun.learn.algorithms.java.TaskSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  input: sequence of words separated by whitespaces
 *  output: first word containing the most single digit repeats
 *      if there are no words containing repeating digits - return blank string
 */
public class FindFirstWordWithTheMostLetterRepeats implements TaskSolver {

    @Override
    public String solve(String[] args) {

        String input = args[0];
        String output = "";

        //  for iterating over input words
        int firstLetterPos = 0;
        int lastLetterPos = 0;
        final char sep = ' ';

        //  for iterating over word letters
        Map<Integer, Integer> letterCount = new HashMap<>();
        String curWord;
        int curWordMostRepeats = 0;
        int curRepeats = 0;
        int mostRepeats = 0;

        while (lastLetterPos <= input.length()) {
            if (lastLetterPos == input.length() || input.charAt(lastLetterPos) == sep) {
                curWord = input.substring(firstLetterPos, lastLetterPos);
                firstLetterPos = lastLetterPos + 1;
                if (curWord.length() > 0) {
                    letterCount.clear();
                    curWordMostRepeats = 0;
                    for (int j = 0; j < curWord.length(); j++) {
                        Integer charCode = (int) input.charAt(j);
                        if (!letterCount.containsKey(charCode)) letterCount.put(charCode, 0);
                        curRepeats = letterCount.get(charCode) + 1;
                        letterCount.put(charCode, curRepeats);
                        if (curRepeats > curWordMostRepeats) curWordMostRepeats = curRepeats;
                    }
                    if (curWordMostRepeats > mostRepeats) {
                        mostRepeats = curWordMostRepeats;
                        output = curWord;
                    }
                }
            }
            lastLetterPos += 1;
        }
        return output;
    }

    public static void main(String[] args) {

        TaskSolver solver = new FindFirstWordWithTheMostLetterRepeats();
        String sepWhiteSpace = " ";
        String sepSemiColon = ";";

        //  provide some tests
        List<TaskChecker> tasks = new ArrayList<>();
        tasks.add(new TaskChecker(new TaskInput("words with no repeats ups!", sepSemiColon), "repeats"));
        tasks.add(new TaskChecker(new TaskInput(" 111 222 333 ", sepSemiColon), "111"));
        tasks.add(new TaskChecker(new TaskInput("     ", sepSemiColon), ""));

        tasks.forEach(t -> t.check(solver));
    }
}
