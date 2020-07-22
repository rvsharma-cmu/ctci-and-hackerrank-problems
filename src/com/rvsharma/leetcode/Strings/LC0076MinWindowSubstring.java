package com.rvsharma.leetcode.Strings;

public class LC0076MinWindowSubstring {
    public static String minWindow(String s, String t){
        int[] hist = new int[128];

        for(char c : t.toCharArray()){
            hist[c]++;
        }
        int remaining = t.length();
        int left = 0, right = 0, minStart = 0, minlen = Integer.MAX_VALUE;
        while(right < s.length()){
            if(--hist[s.charAt(right++)] >= 0) remaining--;
            while(remaining == 0){
                if(right - left < minlen){
                    minlen = right - left;
                    minStart = left;
                }
                if(++hist[s.charAt(left++)] > 0) remaining++;
            }
        }
        return minlen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minlen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
