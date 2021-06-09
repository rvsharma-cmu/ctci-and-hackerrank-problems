package com.rvsharma.leetcode.Trees;


import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class LC0107BinaryTreeLevelOrderTraversalII {

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {

        if(root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                temp.add(node.val);
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            res.add(0, temp);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7");
        BTreePrinter.printNode(root);
        System.out.println(levelOrderBottom(root));
    }
}
