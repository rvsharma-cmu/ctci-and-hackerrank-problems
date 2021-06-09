package com.rvsharma.leetcode.Trees;


import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.*;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2
 * Input:
 *
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class LC0508MostFrequentSubtreeSum {

    private static int maxFreq = Integer.MIN_VALUE;

    public static int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();

        helper(root, map);
        List<Integer> list = new ArrayList<>();
        for (int x : map.keySet()){
            if (maxFreq == map.get(x))
                list.add(x);
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

    private static int helper(TreeNode root, Map<Integer, Integer> map){
        if (root == null) return 0;
        int leftSubSum = helper(root.left, map);
        int rightSumSum = helper(root.right, map);
        int sum = root.val + leftSubSum + rightSumSum;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        maxFreq = Math.max(maxFreq, map.get(sum));
        return sum;
    }


    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("5,2,-3");
        System.out.println(Arrays.toString(findFrequentTreeSum(root)));
    }
}
