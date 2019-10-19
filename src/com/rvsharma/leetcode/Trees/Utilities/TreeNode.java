package com.rvsharma.leetcode.Trees.Utilities;

public class TreeNode {

    int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int nodeValue){
        this.val = nodeValue;
    }

    public static void printTreeInorder(TreeNode root){

        if(root == null){

            return;
        }

        printTreeInorder(root.left);
        System.out.println(root.val);
        printTreeInorder(root.right);
    }

    public void printTreePreOrder(TreeNode root){

        if(root == null){
            System.out.println();
            return;
        }

        System.out.print(root.val);
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);
    }

    public void printTreePostOrder(TreeNode root){

        if(root == null){
            System.out.println();
            return;
        }

        printTreePostOrder(root.left);
        printTreePostOrder(root.right);
        System.out.print(root.val);
    }
}
