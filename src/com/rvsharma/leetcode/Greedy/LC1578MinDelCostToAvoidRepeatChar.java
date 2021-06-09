package com.rvsharma.leetcode.Greedy;

public class LC1578MinDelCostToAvoidRepeatChar {

	public static int minCost(String s, int[] cost) {

		int res = 0;

		for(int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i+1)) {
				int end = i + 1;
				while(end < s.length() && s.charAt(i) == s.charAt(end)) {
					end++;
				}
				end--;
				res += findMax(i, end, cost);
				i = end;
			}
		}
		return res;
	}

	private static int findMax(int start, int end, int[] cost) {
		int total = 0, max = start;

		for(int i = start; i <= end; i++) {
			if(cost[i] > cost[max]) {
				max = i;
			}
			total += cost[i];
		}

		return total - cost[max];
	}

	public static int minCost_oneSweep(String s, int[] cost){
		int res = 0, minCost = 0;
		for(int i = 0; i < s.length(); i++) {
			res += cost[i];
			if(i > 0 && s.charAt(i) != s.charAt(i-1)){
				res -= minCost;
				minCost = 0;
			}
			minCost = Math.max(minCost, cost[i]);
		}

		return res - minCost;
	}

	public static void main(String[] args) {
		String s = "bbbaaa";
		int[] cost = new int[]{4,9,3,8,8,9};
		System.out.println(minCost_oneSweep(s, cost));
	}
}
