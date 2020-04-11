package com.rvsharma.leetcode.Strings;

/**
 * This solution is for strings longest Palindromic substring;
 * we expand from the middle out and see if the string is still a
 * palindrome; and we update the longest palindrome and its start and
 * end accordingly
 */
public class LC005LongestPalindromicSubstring {

    int start = 0, maxLen = 0;
    public String longestPalindrome(String s) {

        // corner case
        if (s.length() < 2) {
            return s;
        }
        // we got till the second last letter in the string
        for(int i = 0; i < s.length() - 1; i++) {
            expandString(s, i, i); // considering odd length of palindrome
            expandString(s, i, i+1); // considering even length of palindrome
        }

        return s.substring(start, start + maxLen);
    }

    private void expandString(String s, int left, int right) {
        /*
             keep expanding from the center as long as you can; provided it is
             still a palindrome
         */
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        /*
            Now check if the maxLen is smaller than current calculation of
            substring with left, right as centers; if yes, update the maxLen
            and Low values of the string
         */
        if(maxLen < right - left - 1) {
            start = left + 1;
            maxLen = right - left - 1;
        }
    }
}
