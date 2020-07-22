package com.rvsharma.Hackerrank.CodePath.CoreDS;

public class StonesAndJewels {
    public static int stonesAndJewels(String J, String S) {
        int[] hash = new int[256];
        for(char s : S.toCharArray()){
            hash[s]++;
        }
        int res = 0;
        for(char j : J.toCharArray()){
            res += hash[j];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(stonesAndJewels("aA", "aAAbbbb"));
    }
}
