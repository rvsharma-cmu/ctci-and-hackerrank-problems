package com.rvsharma.leetcode.Strings;

public class LC0214ShortestPalindrome {

    // two implementation 1(active). KMP 2. BruteForce
    /**
     *  Taken from Tushar Roy's wiki
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/string/SubstringSearch.java
     *  Explanation video of KMP at
     *  https://www.youtube.com/watch?v=GTJr8OvyEVQ
     */
    private int[] buildKMPTable(char[] pattern){
        int[] temp = new int[pattern.length];
        int j = 0, i = 1;
        while(i < pattern.length) {
            if(pattern[i] == pattern[j]) {
                temp[i] = j + 1;
                i++;
                j++;
            } else {
                // depends on j
                if(j != 0) // its moved since start; some prefix == suffix
                    j = temp[j-1];
                else{
                    temp[i] = 0;
                    i++;
                }
            }
        }
        return temp;
    }

    // https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation

    public String shortestPalindrome2(String s) {

        // build a string s + "#" + reverse(s);
        // and find out its KMP
        String reverse = new StringBuilder(s).reverse().toString();
        String sTemp = s + "#" + reverse;
        int[] temp = buildKMPTable(sTemp.toCharArray());

        int common = temp[temp.length - 1];
        String afterCommon = new StringBuilder(s.substring(common)).reverse().toString();
        return afterCommon + s;
    }

    // brute force implementation
    public String shortestPalindrome(String s) {

        // from 0 find the longest palindromic substring and
        // reverse the remaining substring
        // append this in front of the original substring
        // to get the shortest palindrome
        int n = s.length();
        String rev = reverse(s.toCharArray(), 0, n - 1);
        for(int i = 0; i < n; i++){
            if(s.substring(0, n - i).equals(rev.substring(i))){
                return rev.substring(0, i) + s;
            }
        }
        return "";
    }
    // reverse a given string from index x to y
    private String reverse(char[] str, int x, int y){
        if(y>str.length-1) return "";
        while(x < y){
            char temp = str[x];
            str[x] = str[y];
            str[y] = temp;
            x++;
            y--;
        }
        return String.valueOf(str);
    }

    public static void main(String[] args){
        String str = "abcdcbay";
        LC0214ShortestPalindrome ss = new LC0214ShortestPalindrome();
        String result = ss.shortestPalindrome(str);
        System.out.print(result);
    }

}
