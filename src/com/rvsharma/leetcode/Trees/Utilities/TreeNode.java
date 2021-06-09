package com.rvsharma.leetcode.Trees.Utilities;

import java.util.*;

public class TreeNode {

    public int val;
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
        System.out.print(root.val + " ");
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

    public static TreeNode createTree(String[] arr){
        if(arr == null || arr.length == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        q.offer(root);
        for(int i = 1; i < arr.length; i++) {
            TreeNode parent = q.poll();
            if(!("null".equalsIgnoreCase(arr[i]))){
                TreeNode left = new TreeNode(Integer.parseInt(arr[i]));
                parent.left = left;
                q.offer(left);
            }
            if((i+1)<arr.length && !("null".equalsIgnoreCase(arr[++i]))){
                TreeNode right = new TreeNode(Integer.parseInt(arr[i]));
                parent.right = right;
                q.offer(right);
            }
        }
        return root;
    }

    /**
     * Method to deserialze a serialized string representing a binary tree
     *
     * @param data Serialized string having comma separated values of trees. Null nodes are represented by n
     * @return returns the root of the de-serialized binary tree
     */
    public static TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0].trim()));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i].trim()));
                parent.left = left;
                q.add(left);
            }
            if ((i+1) < values.length && !values[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i].trim()));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    @Deprecated
    public String toString() {
        StringBuilder builder = new StringBuilder(50);
        List<TreeNode> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        print(builder, "", "", children);
        return builder.toString();
    }
    @Deprecated
    private void print(StringBuilder builder, String prefix, String childrenPrefix, List<TreeNode> children) {
        builder.append(prefix);
        builder.append(val);
        builder.append('\n');
        for (Iterator<TreeNode> it = children.iterator(); it.hasNext();) {
            TreeNode next = it.next();
            if (it.hasNext()) {
                next.print(builder, childrenPrefix + "├── ", childrenPrefix + "│   ", children);
            } else {
                next.print(builder, childrenPrefix + "└── ", childrenPrefix + "    ", children);
            }
        }
    }
}
