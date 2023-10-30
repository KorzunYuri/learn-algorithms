package com.yurykorzun.learn.algorithms.java.strings;

import com.yurykorzun.learn.algorithms.java.Task;
import com.yurykorzun.learn.algorithms.java.TaskChecker;
import com.yurykorzun.learn.algorithms.java.TaskInput;
import com.yurykorzun.learn.algorithms.java.strings.charsetwindow.Solver1;
import com.yurykorzun.learn.algorithms.java.strings.charsetwindow.Solver2;

/**
 *  input - array of TWO strings:
 *      args[0] - string of length N
 *      args[1] - string of length K <= N
 *  output:
 *      first shortest substring of args[0] containing all letters from args[1], otherwise blank string
 *      if the letter repeats in args[1] then the substring must contain exactly at least that amount of repeats of the letter
 */
public class FindShortestSubstringContainingSetOfLetters {

    public static void main(String[] args) {
        TaskChecker checker = new TaskChecker();

        //  provide some tests
        String sepWhiteSpace = " ";
        String sepSemiColon = ";";
        checker.add(new Task(new TaskInput("avaajaav aav",              sepWhiteSpace), "ava"));
        checker.add(new Task(new TaskInput("abcdefja abca",             sepWhiteSpace), "abcdefja"));
        checker.add(new Task(new TaskInput("abcabc acb",                sepWhiteSpace), "abc"));
        checker.add(new Task(new TaskInput("aavvaabbaavvbb abvabva",    sepWhiteSpace), "avvaabb"));
        checker.add(new Task(new TaskInput("abcabcabcabcaaabbba aaabbb",sepWhiteSpace), "aaabbb"));
        checker.add(new Task(new TaskInput("abcde edcba",               sepWhiteSpace), "abcde"));

        checker.checkAll(new Solver1());
        checker.checkAll(new Solver2());
    }


}


