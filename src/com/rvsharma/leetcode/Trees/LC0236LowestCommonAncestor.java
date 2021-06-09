package com.rvsharma.leetcode.Trees;


import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.ArrayList;
import java.lang.Long;
import java.util.List;

public class LC0236LowestCommonAncestor {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null || root == p || root == q) return root;
        boolean pOnLeft = covers(root.left, p);
        boolean qOnLeft = covers(root.left, q);
        if(pOnLeft != qOnLeft){
            return root;
        }
        TreeNode childSide = pOnLeft ? root.left : root.right;
        return lowestCommonAncestor(childSide, p, q);
    }


    private static boolean covers(TreeNode root, TreeNode p){
        if(root == null) return false;
        if(root == p) return true;
        return covers(root.left, p) || covers(root.right, p);
    }

    public static void main(String[] args) {
        String serialised = "3,5,1,6,2,0,8,n,n,7,4";
        TreeNode root = TreeNode.deserialize(serialised);
        BTreePrinter.printNode(root);
    }


}
