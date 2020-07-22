package com.rvsharma.Hackerrank.CodePath.CoreDS;


import java.util.Arrays;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicString {

    public static boolean isIsomorphicString(String s, String t) {
        int[] hash1 = new int[256], hash2 = new int[256];
        Arrays.fill(hash1, -1);
        Arrays.fill(hash2, -1);
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(hash1[s.charAt(i)] != hash2[t.charAt(i)])
                return false;
            hash1[s.charAt(i)] = i;
            hash2[t.charAt(i)] = i;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "add";
        String t = "egg";
        System.out.println(isIsomorphicString(s, t));
        s = "foo";
        t = "bar";
        System.out.println(isIsomorphicString(s, t));
        s = "paper";
        t = "title";
        System.out.println(isIsomorphicString(s, t));
    }
}
