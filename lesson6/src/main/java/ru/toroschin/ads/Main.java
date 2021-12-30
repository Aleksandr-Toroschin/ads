package ru.toroschin.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int countTrees = 20;
    private static final int leftRange = -25;
    private static final int rightRange = 25;
    private static final int maxLevel = 4;
    private static final Random random = new Random();

    public static void main(String[] args) {

        List<TreeImpl<Integer>> trees = new ArrayList<>();
        for (int i = 0; i < countTrees; i++) {
            trees.add(makeRandomTree());
        }

        int balanced = 0;
        int nonBalanced = 0;
        for (TreeImpl<Integer> tree : trees) {
            if (tree.isBalanced()) {
                balanced++;
            } else {
                nonBalanced++;
            }
            tree.display();
        }

        System.out.printf("Сбалансированы: %d%nНе сбалансированы: %d%nСбалансировано %.2f%%", balanced, nonBalanced, (balanced * 100f / countTrees));
    }

    public static TreeImpl<Integer> makeRandomTree() {
        TreeImpl<Integer> tree = new TreeImpl<>(maxLevel);
        boolean successAdd = true;
        while (successAdd) {
            successAdd = tree.add(random.nextInt(rightRange + Math.abs(leftRange)) + leftRange);
        }
        return tree;
    }

}
