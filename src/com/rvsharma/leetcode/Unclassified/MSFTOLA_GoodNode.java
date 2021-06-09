package com.rvsharma.leetcode.Unclassified;

import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

public class MSFTOLA_GoodNode {

    /**
     * Perform DFS on tree and store the maximum value seen till this nodes' parent.
     * Compare the value and increment the count if the value is larger than values seen till
     * the nodes' parent, and change the max. Recurse for children.
     * @param root current root in the DFS
     * @param max max value seen till the nodes' parent
     * @return return the count of the good nodes
     */
    private static int goodNodes(TreeNode root, int max){
        // base case when reaching leaf nodes
        if (root == null) {
            return 0;
        }
        int count = 0;
        // see if current value is more than parent node val
        if (root.val >= max) {
            count++; // include in the count if it is a good node
            max = root.val; // reset the max value
        }
        // recurse for child nodes
        return count + goodNodes(root.left, max) + goodNodes(root.right, max);
    }
    public static void main(String[] args) {

        String[] input = {"1"};
        TreeNode root = TreeNode.createTree(input);
        int numOfGoodNodes = goodNodes(root, Integer.MIN_VALUE);
        System.out.println(numOfGoodNodes);
    }
}
