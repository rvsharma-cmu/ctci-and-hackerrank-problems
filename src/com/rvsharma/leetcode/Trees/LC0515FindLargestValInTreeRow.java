package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * You need to find the largest value in each row of a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * Output: [1, 3, 9]
 */
public class LC0515FindLargestValInTreeRow {

    // do a simple level BFS checking the highest value in each level
    public static List<Integer> largestValues(TreeNode root) {

        if(root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                max = Math.max(max, curr.val);
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            res.add(max);
        }
        return res;
    }

    // can also be done in DFS pre-order way
    public static List<Integer> largestValues_DFS(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        helper(root, res, 0);
        return res;
    }

    private static void helper(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        if (res.size() == level){
            res.add(Integer.MIN_VALUE);
        }

        if (root.val > res.get(level)){
            res.set(level, Math.max(root.val, res.get(level)));
        }
        helper(root.left, res, level + 1);
        helper(root.right, res, level + 1);
    }

    public static int sumOfLeftLeaves(TreeNode root){
        return helper_sum(root,0);
    }

    private static int helper_sum(TreeNode node, int sumSoFar){
        if (node == null) return sumSoFar;
        if(node.left != null){
            sumSoFar += node.left.val;
        }
        return helper_sum(node.left, sumSoFar) + helper_sum(node.right, sumSoFar);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7");
//        System.out.println(largestValues_DFS(root));
        System.out.println(sumOfLeftLeaves(root));
    }
}
