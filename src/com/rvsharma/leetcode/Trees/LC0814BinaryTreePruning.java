package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC0814BinaryTreePruning {


    public static TreeNode pruneTree(TreeNode root) {
        if(!containsOne(root)) return null;

        // do a pre order traversal
        Deque<TreeNode> st = new ArrayDeque<>();
        st.addLast(root);
        while (!st.isEmpty()){
            TreeNode n = st.removeLast();
            if (!containsOne(n.left)){
                n.left = null;
            }
            if (!containsOne(n.right)){
                n.right = null;
            }
            if(n.left != null) st.addLast(n.left);
            if(n.right != null) st.addLast(n.right);
        }

        return root;
    }

    private static boolean containsOne(TreeNode node){
        if(node == null) return false;
        if(node.val == 1) return true;
        return containsOne(node.left) || containsOne(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("0,null,0,0,0");
        System.out.println("Input: ");
        BTreePrinter.printNode(root);
        System.out.println("Output: ");
        BTreePrinter.printNode(pruneTree(root));
    }
}
