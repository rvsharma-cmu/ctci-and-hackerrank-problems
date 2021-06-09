package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BSTIterator {
    Deque<TreeNode> st = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        inorder(root);
    }

    private void inorder(TreeNode curr){
        while(curr != null){
            st.addLast(curr);
            curr = curr.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return st.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = st.pollLast();
        if(node.right != null) {
            inorder(node.right);
        }
        return node.val;
    }

    static void inorder(TreeNode node, List<Integer> res){
        if(node == null) return;
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.deserialize("3,1,5,null,2,4");
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        System.out.println(list);
        BTreePrinter.printNode(root);
        BSTIterator iter = new BSTIterator(root);
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
    }
}
