package com.rvsharma.Ch01ArraysAndStrings.sol04;

public class Sol04A {

    private static int[] charFreq(String string){
        int[] table = new int[26];
        char[] arr = string.toCharArray();
        for(char c : arr){
            if(c >= 'a' && c <= 'z')
                table[c - 'a']++;
            if(c >= 'A' && c <= 'Z')
                table[c - 'A']++;
        }
        return table;
    }

    public static boolean isPaliPermutation(String str){

        if(str.isEmpty()) return false;
        boolean oddFlag = false;
        int[] table = charFreq(str);
        for(int freq : table){

            if(freq % 2 != 0) {
                if(oddFlag) return false;
                oddFlag = true;
            }
        }
        return true;
    }
}
