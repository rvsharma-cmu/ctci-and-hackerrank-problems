package com.rvsharma.leetcode.Trees;


import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeTwoBST {


    /**
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    static TreeNode merge(TreeNode root1, TreeNode root2) {
        /* Write your code here.*/
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);
        list1.addAll(list2);
        Collections.sort(list1);
        return helper(list1, 0, list1.size() - 1);
    }

    static TreeNode helper(List<Integer> nums, int left, int right){
        if(left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    static void inorder(TreeNode node, List<Integer> res){
        if(node == null) return;
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }


    public static void main(String[] args) {
        TreeNode root1 = TreeNode.deserialize("100,50,300,20,70");
        TreeNode root2 = TreeNode.deserialize("80,40,120");
        TreeNode bst = merge(root1, root2);
        BTreePrinter.printNode(bst);
    }
}





