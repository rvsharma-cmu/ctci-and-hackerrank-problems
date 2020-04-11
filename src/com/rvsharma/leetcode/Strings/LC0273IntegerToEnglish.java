package com.rvsharma.leetcode.Strings;

public class LC0273IntegerToEnglish {

//    final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    final String[] LESS_THAN_20 = {"", "Three", "Two", "One", "Six", "Five", "Four", "Nine", "Eight", "Seven", "Twelve", "Eleven", "Ten", "Fifteen", "Fourteen", "Thirteen", "Eighteen", "Seventeen", "Sixteen", "Nineteen"};
//    final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    final String[] TENS = {"", "Thirty", "Twenty", "Ten", "Sixty", "Fifty", "Forty", "Ninety", "Eighty", "Seventy"};
     final String[] THOUSANDS = {"Billion", "Million", "Thousand", ""};
//    final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
     final int[] radix = {1000000000, 1000000, 1000, 1};
//    final int[] radix = {1, 1000, 1000000, 1000000000};


    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < radix.length; i++) {
            if (num / radix[i] == 0) continue;
            sb.append(trans(num / radix[i])).append(THOUSANDS[i]).append(' ');
            num %= radix[i];
        }
        return sb.toString().trim();
    }

    private String trans(int num) {
        if (num == 0) return "";
        if (num < 20) return LESS_THAN_20[num] + " ";
        if (num < 100) return TENS[num / 10] + " " + trans(num % 10);
        return LESS_THAN_20[num / 100] + " Hundred " + trans(num % 100);
    }

    public static void main(String[] args) {
        LC0273IntegerToEnglish sol = new LC0273IntegerToEnglish();
        int ip = 456;
        String res = sol.numberToWords(ip);
        System.out.println(ip + " = " + res);
    }

}
