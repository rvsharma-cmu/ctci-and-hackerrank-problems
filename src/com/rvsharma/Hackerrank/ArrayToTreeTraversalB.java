package com.rvsharma.Hackerrank;

import java.util.LinkedList;
import java.util.Queue;

public class ArrayToTreeTraversalB {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    private static TreeNode createTree(int[] arr){

        TreeNode root = null;
        Queue<TreeNode> queue = new LinkedList<>();

        for (int value : arr) {
            root = insertValue(root, value, queue);
        }
        return root;
    }

    private static TreeNode insertValue(TreeNode root, int value, Queue<TreeNode> q){

        TreeNode node = new TreeNode(value);

        if(root == null)
            root = node;
        else {
            TreeNode firstNode = q.peek();
            if (firstNode!=null && firstNode.left == null) {
                firstNode.left = node;
            } else {
                if(firstNode==null) return root;
                firstNode.right = node;
                q.poll();
            }
        }
        q.offer(node);
        return root;
    }

    private static void levelOrderTraversal(TreeNode root){

        if(root == null)
            return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            System.out.print(q.peek().val + " ");
            if (q.peek().left != null) {
                q.offer(q.peek().left);
            }
            if (q.peek().right != null) {
                q.offer(q.peek().right);
            }
            q.poll();
        }
    }

    private static void preOrderTraversal(TreeNode root){

        if(root == null) return;

        System.out.println(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    private static void postOrderTraversal(TreeNode root){

        if(root == null) return;

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.val);
    }

    public static void main(String[] args){

        int[] arr = new int[]{10, 20, 30, 40, 50, 60};

        TreeNode root = createTree(arr);
        System.out.println("Level order traversal is ");
        levelOrderTraversal(root);
        System.out.println("Pre order traversal is ");
        preOrderTraversal(root);
        System.out.println("Post order traversal is ");
        postOrderTraversal(root);
    }
}
