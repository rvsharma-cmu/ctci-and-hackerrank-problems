package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.Arrays;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class LC0099RecoverBinarySearchTree {
	public void recoverTree(TreeNode root) {

		int[] swap = new int[2];
		Arrays.fill(swap, -1);
		dfs(root, null, null, swap);
		if(swap[0] != -1){
			binarySearch(root, swap[1], swap[0]);
		}

	}

	private void binarySearch(TreeNode root, int toSwap, int value){
		if(root.val == toSwap){
			root.val = value;
			return;
		}
		if(root.val > toSwap){
			binarySearch(root.left, toSwap, value);
		} else {
			binarySearch(root.right, toSwap, value);
		}
	}

	private void dfs(TreeNode root, Integer min, Integer max, int[] swap){
		if(root == null)
			return;

		if((min!=null && root.val < min) || (max != null && root.val > max)) {
			if (min!=null && root.val < min) {
				swap[0] = root.val;
				swap[1] = min;
				root.val = swap[1];
				return;
			} else {
				swap[0] = root.val;
				swap[1] = max;
				root.val = swap[1];
				return;
			}
		}
		dfs(root.left, min, root.val, swap);
		dfs(root.right, root.val, max, swap);
	}

	public static void main(String[] args) {
		String[] values = new String[]{"2", "3", "1"};
		TreeNode root = TreeNode.createTree(values);
		BTreePrinter.printNode(root);
		LC0099RecoverBinarySearchTree sol = new LC0099RecoverBinarySearchTree();
		sol.recoverTree(root);
		BTreePrinter.printNode(root);
	}
}
