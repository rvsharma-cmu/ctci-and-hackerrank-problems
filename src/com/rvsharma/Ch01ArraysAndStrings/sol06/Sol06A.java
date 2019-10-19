package com.rvsharma.Ch01ArraysAndStrings.sol06;

public class Sol06A {

    static String stringCompression(String a){
        int consecutiveCount = 0;
        StringBuilder compressedBuilder = new StringBuilder();
        for(int i=0; i<a.length();i++){
            consecutiveCount++;
            // i+1 == a.length() is the last character of the string
            if(i+1 >= a.length() || a.charAt(i)!= a.charAt(i+1)){
                compressedBuilder.append(a.charAt(i));
                compressedBuilder.append(consecutiveCount);
                consecutiveCount=0;
            }
        }
        return compressedBuilder.length() < a.length() ? compressedBuilder.toString() : a;
    }
}
