package com.rvsharma.leetcode.MiniMax;

import java.util.Arrays;

public class LC0877StoneGame {

	public boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][][] memo = new int[n+1][n+1][2];
		for(int[][] d : memo){
			for(int[] a : d){
				Arrays.fill(a, -1);
			}
		}
		int score = helper(0, piles.length - 1, piles, 0, memo);
		return score >= 0;
	}

	private int helper(int l, int r, int[] piles, int id, int[][][] memo){
		if(r < l){
			return 0;
		}

		if(memo[l][r][id] != -1){
			return memo[l][r][id];
		}

		int next = Math.abs(id - 1);
		if(next == 1){
			memo[l][r][id] = Math.max(piles[l] + helper(l + 1, r, piles, next, memo), piles[r] + helper(l, r - 1, piles, next, memo));
		} else {
			memo[l][r][id] =  Math.min(-piles[l] + helper(l + 1, r, piles, next, memo), -piles[r] + helper(l, r - 1, piles, next, memo));
		}
		return memo[l][r][id];
	}

	public static void main(String[] args) {
		int[] piles = {7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,
				38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24};
		System.out.println(new LC0877StoneGame().stoneGame(piles));
	}
}
