package com.rvsharma.leetcode.Trees;


import com.rvsharma.Ch02LinkedList.Introduction.LLNode;
import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class LC0114FlattenBinaryTreeToLinkedList {

    public static void flatten(TreeNode root){
        if(root == null) return;
        Deque<TreeNode> st = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                // only add right if its not null
                if (curr.right != null)
                    st.addLast(curr.right);
                curr.right = curr.left;
                curr.left = null;
            } else if (curr.right == null && !st.isEmpty()){
                curr.right = st.removeLast();
            }
            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("1,2");
        BTreePrinter.printNode(root);
        flatten(root);
        BTreePrinter.printNode(root);
    }
}
