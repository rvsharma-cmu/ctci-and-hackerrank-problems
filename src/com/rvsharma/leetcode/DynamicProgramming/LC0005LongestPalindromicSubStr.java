package com.rvsharma.leetcode.DynamicProgramming;

public class LC0005LongestPalindromicSubStr {

	/**
	 * Using 2D array bottom up dynamic programming
	 * dp[i][j]
	 * @param s
	 * @return
	 */
	public String longestPalindrome_2DArray(String s) {
		int len = s.length();

		boolean[][] dp = new boolean[len][len];
		String result = s.charAt(0) + ""; // base case as the first character

		// dp[i][i] is always true;
		for(int i = 0; i < len - 1; i++){
			dp[i][i] = true;
		}
		for(int i = len - 1; i >= 0; i--){
			for(int j = i; j < len; j++) {
				if(i != j){
					dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i+1][j-1]);
				}
				if(dp[i][j] && (j - i + 1 > result.length())){
					result = s.substring(i, j+1);
				}
			}
		}

		return result;
	}


	int start = 0, max = 1;
	public String longestPalindrome(String s){

		if(s == null || s.length() < 2)
			return s;
		for(int i = 0; i < s.length(); i++){
			expandAlongCenter(s, i, i);
			expandAlongCenter(s, i, i+1);
		}

		return s.substring(start, start+max);
	}

	private void expandAlongCenter(String s, int i, int j){

		while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
			i--;
			j++;
		}
		if(j - i - 1 > max){
			max = j - i - 1;
			start = i + 1;
		}
	}

	public static void main(String[] args) {
		String input = "abaab";
		LC0005LongestPalindromicSubStr sol = new LC0005LongestPalindromicSubStr();
		System.out.println(sol.longestPalindrome(input));
	}
}
