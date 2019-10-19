package com.rvsharma.Ch01ArraysAndStrings.sol06;


public class Sol06B {

    static String compressString(String str){

        int compressLength = findCompressLength(str);
        if(compressLength >= str.length()) return str;
        int consecutiveCount = 0;
        StringBuilder compressedString = new StringBuilder(compressLength);
        for(int i=0; i < str.length(); i++){
            consecutiveCount++;
            if(i+1 >= str.length() || str.charAt(i+1) != str.charAt(i)){
                compressedString.append(str.charAt(i));
                compressedString.append(consecutiveCount);
                consecutiveCount=0;
            }
        }
        return compressedString.toString();
    }

    private static int findCompressLength(String str) {
        int consecutiveCount = 0;
        int compressedLen = 0;
        for(int i=0; i < str.length();i++){
            consecutiveCount++;
            if(i+1 >= str.length() || str.charAt(i+1)!=str.charAt(i)){
                compressedLen += 1 + (String.valueOf(consecutiveCount)).length();
                consecutiveCount=0;
            }
        }
        return compressedLen;
    }
}
