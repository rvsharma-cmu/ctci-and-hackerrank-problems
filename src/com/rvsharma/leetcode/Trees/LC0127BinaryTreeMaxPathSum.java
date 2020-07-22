package com.rvsharma.leetcode.Trees;
import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class LC0127BinaryTreeMaxPathSum {

    private static int maxSum = 0;
    public static int maxPathSum(TreeNode root){
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(TreeNode root) {
        if(root == null) return 0;

        int lefGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);
        int cost = root.val + lefGain + rightGain;
        if (maxSum < cost) {
            maxSum = cost;
        }
        return root.val + Math.max(lefGain, rightGain);
    }

    public static void main(String[] args) {
        String str = "-10,9,20,n,n,15,7";
        TreeNode root = TreeNode.deserialize(str);
        BTreePrinter.printNode(root);
        String str2 = "5, 14, 23, 68, 70, 71, 79, 83, 87, 96, 98, 119";
        root = TreeNode.deserialize(str2);
        System.out.println(maxPathSum(root));
    }
}
