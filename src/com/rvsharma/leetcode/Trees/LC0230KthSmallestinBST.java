package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.*;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 *
 *
 *
 * Constraints:
 *
 * The number of elements of the BST is between 1 to 10^4.
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
public class LC0230KthSmallestinBST {

    // inorder traversal in a BST traverses the elements in ascending order
    public static int kthSmallest(TreeNode root, int k){
        if(root == null) return 0;
        Deque<TreeNode> st = new ArrayDeque<>();
        st.addLast(root);
        while (root != null || !st.isEmpty()){
            if(root != null){
                st.addLast(root);
                root = root.left;
            } else {
                TreeNode node = st.removeLast();
                if(--k == 0) return node.val;
                root = node.right;
            }
        }
        return 0;
    }

    static class TreeNodeWithCount {
        int val;
        TreeNodeWithCount left, right;
        int leftCount;
        TreeNodeWithCount(int val){
            this.val = val;
            leftCount = 0;
        }
    }

    public static int kthSmallest_freq_helper(TreeNodeWithCount root, int k) {

        if(root == null) return 0;
        int count = root.leftCount + 1;
        if (k == count) return root.val;

        if (k < count) {
            return kthSmallest_freq_helper(root.left, k);
        }

        return kthSmallest_freq_helper(root.right, k - count);
    }

    // follow up : What if the BST is modified (insert/delete operations) often and you need to find the kth
    // smallest frequently? How would you optimize the kthSmallest routine?
    public static int kthSmallest_freq(TreeNode root, int k){
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                list.add(curr.val);
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }
        TreeNodeWithCount rootCount = null;
        for (int x : list) {
            rootCount = insert(rootCount, x);
        }

        return kthSmallest_freq_helper(rootCount, k);
    }

    private static TreeNodeWithCount insert(TreeNodeWithCount rootCount, int x) {
        if(rootCount == null) return new TreeNodeWithCount(x);

        if(x < rootCount.val){
            rootCount.left = insert(rootCount.left, x);
            rootCount.leftCount++;
        } else {
            rootCount.right = insert(rootCount.right, x);
        }
        return rootCount;
    }


    public static void main(String[] args) {
        TreeNode node = TreeNode.deserialize("3,1,4,null,2");
        System.out.println(kthSmallest(node, 3));
        node = TreeNode.deserialize("20,8,22,4,12,null,null,null,null,10,14");
        BTreePrinter.printNode(node);
        System.out.println(kthSmallest_freq(node, 5));
    }
}
