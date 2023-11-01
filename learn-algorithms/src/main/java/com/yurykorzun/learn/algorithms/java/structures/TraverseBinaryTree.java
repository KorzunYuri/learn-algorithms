package com.yurykorzun.learn.algorithms.java.structures;

import com.yurykorzun.learn.algorithms.java.utils.base.Task;
import com.yurykorzun.learn.algorithms.java.utils.base.TaskChecker;
import com.yurykorzun.learn.algorithms.java.utils.base.TaskSolver;
import com.yurykorzun.learn.algorithms.java.utils.input.StringTaskInput;

public class TraverseBinaryTree implements TaskSolver<String, String> {

    @Override
    public String solve(String[] tree) {
        StringBuilder builder = new StringBuilder();
        traverse(builder, tree, 1, 1);
        return builder.toString();
    }

    private int pow(int i, int p) {
        return (int) Math.pow(i, p);
    }

    private int getElementIndex(int level, int element) {
        int base = pow(2, level - 1) - 1;
        // System.out.println("Base: " + base + ", shift: " + element);
        return base + element - 1;
    }

    private void traverse(StringBuilder builder, String[] tree, int level, int element) {
        int index = getElementIndex(level, element);
        String node = tree[index];
        // System.out.println("level " + level + " element " + element + " index " + index + " value " + node);
        if (!"#".equals(node)) builder.append(node);
        if (pow(2, level + 1) <= tree.length + 1) {
            traverse(builder, tree, level + 1, 2 * (element - 1) + 1);
            traverse(builder, tree, level + 1, 2 * (element - 1) + 2);
        }
    }

    public static void main(String[] args) {
        TaskChecker<String, String> checker = new TaskChecker<>();

        //  provide some tests
        String sepWhiteSpace = " ";
        String sepSemiColon = ";";
        checker.add(new Task<>(new StringTaskInput("6 2 8 1 5 9 # # # 4 # # # # #", sepWhiteSpace), "6215489"));
        checker.add(new Task<>(new StringTaskInput("1 2 9 3 6 0 3 4 5 7 8 1 2 4 5", sepWhiteSpace), "123456789012345"));

        TaskSolver<String, String> solver = new TraverseBinaryTree();
        checker.checkAll(solver);
    }
}
