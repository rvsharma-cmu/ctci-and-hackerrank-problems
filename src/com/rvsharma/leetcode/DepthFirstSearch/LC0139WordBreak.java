package com.rvsharma.leetcode.DepthFirstSearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class LC0139WordBreak {
    public static boolean wordBreak(String str, List<String> wordDict) {
        if(str == null) return false;
        Set<String> dict = new HashSet<>(wordDict);
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if(dp[j] == 1 && dict.contains(str.substring(j, i))){
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[str.length()] == 1;
    }

    public static void main(String[] args) {
        String[] wordDict = {"leet", "code"};
        System.out.println("Possible to break the word? " + wordBreak("leetcode", Arrays.asList(wordDict)));
    }
}
