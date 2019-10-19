package com.rvsharma.Hackerrank;

import java.util.*;

public class ApproximateMatchingB {

    static String calculateScore(String text, String prefix, String suffix) {

        String result = "";
        List<String> substrings = new ArrayList<>();
        Map<String, Integer> mapSubstrCount = new HashMap<>();

        for(int i = 0; i< text.length(); i++)
            for (int j = i + 1; j <= text.length(); j++)
                substrings.add(text.substring(i, j));

        for(String subStr : substrings){
            int i = 0;
            int suffixIndex = 0;
            while (i < subStr.length()) {
                int startingPos = i + 1;
                if(prefix.length() > i &&
                        subStr.substring(0, startingPos).equals(prefix.substring(prefix.length() - startingPos))) {
                    if(startingPos > suffixIndex) {
                        suffixIndex = startingPos;
                    }
                }
                i++;
            }
            int j = 0;
            int prefixIndex = 0;
            while (j < subStr.length()) {
                if(suffix.length() + j >= subStr.length() && subStr.substring(j).equals(suffix.substring(0, subStr.length() -j))) {
                    if(subStr.length() > prefixIndex + j)
                        prefixIndex = subStr.length() - j;
                }
                j++;
            }
            int getValue = mapSubstrCount.getOrDefault(subStr, prefixIndex + suffixIndex);
            mapSubstrCount.put(subStr, getValue);
        }

        List<String> output = new ArrayList<>(mapSubstrCount.keySet());
        Collections.sort(output);
        int mapCount = Integer.MIN_VALUE;
        for (String str : output) {
            int count = mapSubstrCount.get(str);
            if (count > mapCount) {
                result = str;
                mapCount = count;
            }
        }

        return result;
    }

    public static void main(String[] args){
//        String text = "ab";
//        String prefix = "b";
//        String suffix = "a";

        String text = "nothing";
        String prefix = "bruno";
        String suffix = "ingenious";

        String output = calculateScore(text, prefix, suffix);
        System.out.println(output);
    }
}
