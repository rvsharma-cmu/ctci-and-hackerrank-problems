package com.rvsharma.Ch01ArraysAndStrings.sol06;

public class Sol06Driver {
    public static void main(String[] args) {
        String str = "aaaaabbbbaaaabbddc";
        System.out.println(str);
        System.out.println(Sol06A.stringCompression(str));
        System.out.println(Sol06B.compressString(str));
    }
}
