package com.rvsharma.Ch01ArraysAndStrings.sol01;

public class Sol01 {

    public static boolean isUniqueChars(String str) {
        if(str.length() > 128 )
            return false;
        boolean[] charSet = new boolean[128];
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(charSet[c]) return false;
            charSet[c] = true;
        }
        return true;
    }
}
