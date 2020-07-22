package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class Test {

    static boolean isSpecial(String text){
        String temp = alret(text);
        return text.equals(temp);
    }

    static String alret(String text){
        if(text == null || text.isEmpty()){
            return text;
        }
        return text.charAt(text.length() - 1) +
                alret(text.substring(0, text.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(isSpecial("abcdef"));
    }
}
