package com.rvsharma.leetcode.DepthFirstSearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1146ReorderRoutes {
	Map<Integer, Set<Integer>> in = new HashMap<>();
	Map<Integer, Set<Integer>> out = new HashMap<>();
	int count = 0;
	public int minReorder(int n, int[][] connections) {

		for(int[] edge : connections){
			Set<Integer> set = in.getOrDefault(edge[1], new HashSet<>());
			set.add(edge[0]);
			in.put(edge[1], set);
			set = out.getOrDefault(edge[0], new HashSet<>());
			set.add(edge[1]);
			out.put(edge[0], set);
		}
		dfs(0);
		return count;
	}

	private void dfs(int node){

		// starting from 0, change the edge direction
		// if the edge is outgoing from the node
		// reverse it, and increment the count

		if(out.containsKey(node)){
			for(int x : out.get(node)){
				// node --> x
				count++;
				// delete it (node) from the in map for x
				Set<Integer> set = in.get(x);
				set.remove(node);
				// recurse for the neighbors
				in.put(x,set);
				dfs(x);
			}
		}


		// similarly for neighbors that are incoming to node, erase
		// so we don't count it as incorrect when we are visiting the x
		if(in.containsKey(node)){
			for(int x : in.get(node)){
				// x --> node
				Set<Integer> set = out.get(x);
				set.remove(node);
				out.put(x,set);
				dfs(x);
			}
		}

	}

	public static void main(String[] args) {
		int[][] sol = new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}};
		LC1146ReorderRoutes solution = new LC1146ReorderRoutes();
		System.out.println(solution.minReorder(0, sol));
	}
}
