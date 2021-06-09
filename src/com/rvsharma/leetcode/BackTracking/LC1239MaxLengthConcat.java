package com.rvsharma.leetcode.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1239MaxLengthConcat {

    private static boolean isUnique(String s){
        if (s.length() > 26) return false;
        if (s.length() == 0) return true;
        boolean[] hash = new boolean[26];
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if(hash[c - 'a']) return false;
            hash[c - 'a'] = true;
        }
        return true;
    }

    /*
        This solution is performing backtracking, an alternate approach is to use bitmask
     */
    public static int maxLength(List<String> arr) {
        List<String> res = new ArrayList<>();
        res.add("");
        int maxLen = 0;
        for(String str : arr){
            if(!isUnique(str)) continue;
            List<String> temp = new ArrayList<>();
            for(String s : res){
                String t = s + str;
                if(isUnique(t)) {
                    temp.add(t);
                    maxLen = Math.max(maxLen, t.length());
                }
            }
            res.addAll(temp);
        }
        return maxLen;
    }

    /**
     * This approach uses bitmask to identify the representation of the string and check if the
     * string is unique or not. Uses Integer.bitCount to find out the length of a unique concatenation of the
     * input strings. Finds out the maximum length in iteration.
     * @param arr Input array that has each string in each index
     * @return Maximum length
     */
    public static int maxLength_bitMask(List<String> arr){
        int result = 0;
        List<Integer> uniqCharLen = new ArrayList<>();
        uniqCharLen.add(0); // base representation of empty string i.e. ""
        // iterate on each string
        for (String inputString : arr) {
            if(!isUnique(inputString)) continue;
            // bit representation of the input string
            int strBitRepresentation = 0;
            for (char ch : inputString.toCharArray()) {
                strBitRepresentation |= 1 << (ch - 'a');    // build up the representation character by character
            }
            for (int i = 0; i < uniqCharLen.size(); i++){
                int candidateRep = uniqCharLen.get(i);
                if ((candidateRep & strBitRepresentation) == 0){ // if the concatenation is a unique string
                    // add the unique concatenation to the list
                    uniqCharLen.add(candidateRep | strBitRepresentation);
                    // and update the max concat length
                    result = Math.max(result, Integer.bitCount(candidateRep | strBitRepresentation));
                }
            }
        }
        return result;
    }

    /*
        Assuming the average length of string as m
        and n strings in the input
     */

    public static void main(String[] args) {
        String[] input = new String[]{"cha","r","act","ers"};
        List<String> arr = Arrays.asList(input.clone());
        System.out.println(maxLength_bitMask(arr));
    }
}
