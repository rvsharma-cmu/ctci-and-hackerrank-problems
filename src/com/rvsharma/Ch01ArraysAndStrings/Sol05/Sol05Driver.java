package com.rvsharma.Ch01ArraysAndStrings.Sol05;

public class Sol05Driver {
    public static void main(String[] args) {
        String a = "pae";
        String b = "pale";
        boolean isOneEdit = sol05A.oneEditAway(a, b);
        System.out.println(a + ", " + b + ": " + isOneEdit);
    }
}
