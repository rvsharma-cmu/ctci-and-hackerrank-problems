package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Leetcode Question 104
 * Tree
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 */
public class LC0104MaximumDepth {
    public static int maxDepth(TreeNode root){

        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        int level = 0;
        stack.push(root);
        depths.push(1);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            int currLevel = depths.pop();
            level = Math.max(level, currLevel);
            if(node.right != null){
                stack.push(node.right);
                depths.push(currLevel+1);
            }
            if(node.left!=null){
                stack.push(node.left);
                depths.push(currLevel+1);
            }
        }
        return level;
    }

    public static void main(String[] args){

        TreeNode root = TreeNode.deserialize("3,9,20,n,n,15,7,3,6");
        BTreePrinter.printNode(root);
        int maxDepth = maxDepth(root);
        System.out.println("Maximum depth of the tree is " + maxDepth);
    }
}
