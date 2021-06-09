package com.rvsharma.leetcode.Trees;


import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 *
 *          3
 *        /   \
 *       9    20
 *           /  \
 *          15  7
 *
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * Example 2:
 *
 *              1
 *            /   \
 *           /     \
 *          2       3
 *         / \     / \
 *        4  5    6   7
 *
 *
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 */
public class LC0987VerticalOrderTraversal {

    public static List<List<Integer>> verticalTraversal(TreeNode root){
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
        res.get(x).add(new int[]{curr.val, y});
        res.get(x).sort((o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
        helper(curr.left, res, x - 1, y - 1);
        helper(curr.right, res, x + 1, y - 1);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7");
        List<List<Integer>> res = verticalTraversal(root);
        System.out.println(res);
    }
}
