package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC0314BinaryVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root){
        List<List<int[]>> list = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        minMax(root, 0);
        for (int i = 0; i < Math.abs(leftMost) + rightMost + 1; i++){
            list.add(new ArrayList<>());
        }
        helper(root, list, Math.abs(leftMost), 0);

        List<List<Integer>> res = new ArrayList<>();
        for (List<int[]> l : list) {
            List<Integer> temp = new ArrayList<>();
            for (int[] e : l){
                temp.add(e[0]);
            }
            res.add(temp);
        }
        return res;
    }

    static int leftMost = 0, rightMost = 0;

    private static void minMax(TreeNode root, int x){
        if (root == null) return;
        if (x < leftMost)
            leftMost = x;
        if (x > rightMost)
            rightMost = x;
        minMax(root.left, x - 1);
        minMax(root.right, x + 1);
    }

    private static void helper(TreeNode curr, List<List<int[]>> res, int x, int y){
        if (curr == null) return;
        if (res.get(x) == null) res.add(x, new ArrayList<>());
        res.get(x).add(new int[]{curr.val, x, y});
        res.get(x).sort((o1, o2) -> o1[2] == o2[2] ? o1[1] - o2[1] : o2[2] - o1[2]);
        helper(curr.left, res, x - 1, y - 1);
        helper(curr.right, res, x + 1, y - 1);
    }

    public static void main(String[] args) {
        String input = "1";
        TreeNode root = TreeNode.deserialize(input);
        LC0314BinaryVerticalOrderTraversal sol = new LC0314BinaryVerticalOrderTraversal();
        List<List<Integer>> lists = sol.verticalOrder(root);
        System.out.println(lists);
    }
}
