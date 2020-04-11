package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LC0297SerializeDeserializeTree {

    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val).append(" ");
            q.add(node.left);
            q.add(node.right);
        }
        int i = res.length() - 1;
        while(res.charAt(i) == 'n' || res.charAt(i) == ' ') res.deleteCharAt(i--);
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if ((i+1) < values.length && !values[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        LC0297SerializeDeserializeTree sol = new LC0297SerializeDeserializeTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
        String serialString = sol.serialize(root);
        System.out.println(serialString);
        TreeNode node = sol.deserialize(serialString);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
