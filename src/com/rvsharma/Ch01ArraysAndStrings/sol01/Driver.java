package com.rvsharma.Ch01ArraysAndStrings.sol01;

public class Driver {

    public static void main(String[] args){
        String str = "riugbq49gc984onwgcgn3oign3gn";

        boolean uniqueChars = Sol01A.isUniqueChars(str);
        System.out.println(str + ": " + uniqueChars);
    }
}
