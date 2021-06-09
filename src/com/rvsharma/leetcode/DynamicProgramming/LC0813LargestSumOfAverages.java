package com.rvsharma.leetcode.DynamicProgramming;

public class LC0813LargestSumOfAverages {

	public double largestSumOfAverages(int[] arr, int K) {
		int[] sum = new int[arr.length];
		for (int i = 0;i < arr.length; i++) sum[i] = arr[i] + (i > 0 ? sum[i-1] : 0);
		return dfs(arr, K, sum, 0);
	}

	public double dfs(int[] arr, int k, int[] prefixSum, int startIdx) {
		if (k == 1) return ((double)(prefixSum[arr.length -1] - prefixSum[startIdx] + arr[startIdx]) / (arr.length -startIdx));
		double largest = 0;
		for (int i = startIdx; i + k <= arr.length; i++) {
			double current = (double) (prefixSum[i] - prefixSum[startIdx] + arr[startIdx]) / (i - startIdx + 1)
					+ dfs(arr, k - 1, prefixSum, i + 1);
			largest = Math.max(largest, current);
		}
		return largest;
	}

	public static void main(String[] args) {
		int[] array = new int[]{9,1,2,3,9};
		int k = 3;
		LC0813LargestSumOfAverages sol = new LC0813LargestSumOfAverages();
		System.out.println(sol.largestSumOfAverages(array, k));
	}
}