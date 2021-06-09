package com.rvsharma.leetcode.Trees;


import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 *
 * Example 1:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * The following are two duplicate subtrees:
 *
 *       2
 *      /
 *     4
 * and
 *
 *     4
 * Therefore, you need to return above trees' root in the form of a list.
 */
public class LC0652FindDuplicateSubtrees {
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Map<String, Integer> map = new HashMap<>();
        postOrder(root, res, map);
        return res;
    }

    private static String postOrder(TreeNode node, List<TreeNode> res, Map<String, Integer> map) {
        if(node == null) return "#";
        String left = postOrder(node.left, res, map);
        String right = postOrder(node.right, res, map);
        String search = node.val + "," + left + "," + right;
        if (map.getOrDefault(search, 0) == 1) res.add(node);
        map.put(search, map.getOrDefault(search, 0) + 1);
        return search;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("1,2,3,4,null,2,4,null,null,4");
        List<TreeNode> res = findDuplicateSubtrees(root);
        for (TreeNode tree : res) {
            BTreePrinter.printNode(tree);
        }
    }
}
