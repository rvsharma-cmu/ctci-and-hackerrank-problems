package com.rvsharma.leetcode.Trees;

import com.rvsharma.leetcode.Trees.Utilities.BTreePrinter;
import com.rvsharma.leetcode.Trees.Utilities.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class L0652FindDuplicateTrees {
	int curr = 1;
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

		Map<String, Integer> serialtoid = new HashMap<>();
		Map<Integer, Integer> idtocount = new HashMap<>();
		List<TreeNode> res = new LinkedList<>();
		postOrder(root, serialtoid, idtocount, res);
		return res;
	}

	private int postOrder(TreeNode root, Map<String, Integer> serialtoid, Map<Integer, Integer> idtocount, List<TreeNode> res){
		if(root == null) return -1;
		int leftId = postOrder(root.left, serialtoid, idtocount, res);
		int rightId = postOrder(root.right, serialtoid, idtocount, res);
		String serialStr = leftId + "," + root.val + "," + rightId;
		int serialId = serialtoid.getOrDefault(serialStr, curr);
		if(serialId == curr)
			curr++;
		serialtoid.put(serialStr, serialId);
		int count = idtocount.getOrDefault(serialId, 0)+1;
		idtocount.put(serialId, count);
		if(idtocount.get(serialId) == 2)
			res.add(root);
		return serialId;
	}

	public List<TreeNode> findDuplicateSubtrees_On2(TreeNode root) {

		List<TreeNode> list = new LinkedList<>();
		postOrder(root, new HashMap<>(), list);
		return list;
	}

	private String postOrder(TreeNode root, Map<String, Integer> map, List<TreeNode> list){

		if(root == null){
			return "n";
		}

		String curr = root.val + "," + postOrder(root.left, map, list) + "," + postOrder(root.right, map, list);
		map.put(curr, map.getOrDefault(curr, 0) + 1);
		if(map.get(curr) == 2) list.add(root);
		return curr;
	}

	public static void main(String[] args) {
		String serializedTree = "1,2,3,4,null,2,4,null,null,4";
		TreeNode root = TreeNode.deserialize(serializedTree);
		L0652FindDuplicateTrees l0652FindDuplicateTrees = new L0652FindDuplicateTrees();
		List<TreeNode> duplicateSubtrees = l0652FindDuplicateTrees.findDuplicateSubtrees(root);
		for (TreeNode tree1 : duplicateSubtrees){
			BTreePrinter.printNode(tree1);
		}
	}
}
