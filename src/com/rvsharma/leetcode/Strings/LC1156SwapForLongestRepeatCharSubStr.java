package com.rvsharma.leetcode.Strings;

import java.util.ArrayList;
import java.util.List;

public class LC1156SwapForLongestRepeatCharSubStr {
	public int maxRepOpt1(String text) {

		List<Integer>[] memo = new ArrayList[26];
		int ans = 1;
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if(memo[c - 'a'] == null){
				memo[c - 'a'] = new ArrayList<>();
			}
			memo[c - 'a'].add(i);
		}
		for(int i = 0; i < 26; i++) {
			if (memo[i] != null) {
				int count = 1;
				int count1 = 0;
				int max = 0;
				for (int j = 1; j < memo[i].size(); j++) {
					if (memo[i].get(j) == memo[i].get(j - 1) + 1) {
						count++;
					} else {
						count1 = (memo[i].get(j) == memo[i].get(j - 1) + 2 ? count : 0);
						count = 1;
					}
					max = Math.max(max, count + count1);
				}
				ans = Math.max(ans, max + (memo[i].size() > max ? 1 : 0));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		String text = "ababa";
		LC1156SwapForLongestRepeatCharSubStr swap = new LC1156SwapForLongestRepeatCharSubStr();
		System.out.println(swap.maxRepOpt1(text));
	}

}
