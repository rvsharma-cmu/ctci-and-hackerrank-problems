package com.rvsharma.leetcode.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class LC0091DecodeWays {
    private static Map<Integer, Integer> memo = new HashMap<>();
    public static int numDecodings_topDown(String s) {
        if(s == null || s.length() == 0) return 0;
        return helper(0,s);
    }
    private static int helper(int idx, String str){
        if(idx == str.length()){
            return 1;
        }
        if(str.charAt(idx) == '0') return 0;
        if(idx == str.length() - 1) return 1;
        if(memo.containsKey(idx)) return memo.get(idx);

        int ans = helper(idx+1, str);
        if(Integer.parseInt(str.substring(idx, idx+2)) <= 26)
            ans += helper(idx+2, str);
        memo.put(idx, ans);
        return ans;
    }

     public static int numDecodings(String s){
        if(s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i < dp.length; i++){
            if(s.charAt(i-1) != '0')
                dp[i] += dp[i-1];
            int twoNums = Integer.parseInt(s.substring(i-2, i));
            if(twoNums >= 10 && twoNums <= 26)
                dp[i] += dp[i-2];
        }
        return dp[s.length()];
     }

    public static void main(String[] args) {
        String str = "226";
        System.out.println("Number of ways to decode \"" + str + "\" is : " + numDecodings("226"));
    }
}
