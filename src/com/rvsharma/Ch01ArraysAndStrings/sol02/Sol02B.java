package com.rvsharma.Ch01ArraysAndStrings.sol02;

public class Sol02B {

    public static boolean isPermutation(String a, String b) {

        if(a.length() != b.length())
            return false;

        int[] charCount = new int[128];
        for(int i = 0; i < a.length(); i++){
            charCount[a.charAt(i)]++;
        }
        for(int i = 0; i < b.length(); i++){
            charCount[b.charAt(i)]--;
            if(charCount[b.charAt(i)] < 0)
                return false;
        }
        return true;
    }
}
