package com.rvsharma.leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/*
    Given a string, find the length of the longest substring without repeating characters.

    Example 1:

    Input: "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
                 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LC0003LongestSubstrWORepeating {
    public static int lengthOfLongestSubstring(String s) {
        int start = 0, maxLen = 0;
        Map<Character, Integer> charToNext = new HashMap<>();
        for(int end = 0; end < s.length(); end++){
            if(charToNext.containsKey(s.charAt(end))){
                start = Math.max(start, charToNext.get(s.charAt(end)));
            }
            maxLen = Math.max(maxLen, end - start + 1);
            charToNext.put(s.charAt(end), end + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
