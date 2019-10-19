package com.rvsharma.Hackerrank;

import java.util.*;
import java.util.regex.*;

public class ApproximateMatchingA {

    public static String findHighestPattern(String text, String suffix, String prefix){

        ArrayList<String> prefixes = new ArrayList<>();
        ArrayList<String> suffixes = new ArrayList<>();
        List<String> substrings = new ArrayList<>();
        for(int i = 0; i < text.length(); i++) {
            for(int j = i +  1; j <=text.length(); j++){
                substrings.add(text.substring(i, j));
            }
        }
        Collections.sort(substrings);
        System.out.println(substrings.toString());
        int length = text.length();
        int i = 1;
        while(i <= length){
            ((List<String>) prefixes).add(text.substring(0, i));
            i++;
        }

        while(length-- > 0){
            ((List<String>) suffixes).add(text.substring(length));
        }

        int suffixIdx = 0;
        String suffixString = "";

        for(String x : suffixes){

            Pattern pattern = Pattern.compile(x+"(.*)");
            Matcher matcher = pattern.matcher(suffix);

            if(matcher.matches()){
                int suffixMaxLen = x.length();
                if (suffixMaxLen > suffixIdx) {
                    suffixIdx = suffixMaxLen;
                    suffixString = x;
                    System.out.println("group 0 " + matcher.group(0) + " str:" + x);
                }
            }
        }
        String finalResult = "";
        int resultLen = 0;
        for(String str : substrings){
            if(suffixString.length()!=0 && str.endsWith(suffixString) && (str.length() > finalResult.length())){
                finalResult = str;
            }
        }

        int prefixIdx = 0;

        String prefixString = "";
        for(String x : prefixes){

            Pattern pattern = Pattern.compile("(.*)" + x);
            Matcher matcher = pattern.matcher(prefix);
            if(matcher.matches()){
                int prefixMaxLen = x.length();
                if(prefixMaxLen > prefixIdx){
                    prefixIdx = x.length();
                    prefixString += x;
                    System.out.println("group 0 " + matcher.group(0) + " str:" + x);
                }
            }
        }
        for(String str : substrings){
            if(prefixString.length() !=0 && str.startsWith(prefixString) && (str.length() > finalResult.length())){
                finalResult = str;
            }
        }
        System.out.println("finalString = " + finalResult + " Length = " + finalResult.length());
        String result = prefixString + suffixString;
        if(finalResult.length() == 0){
            suffixes.addAll(prefixes);
            Collections.sort(suffixes);
            finalResult = suffixes.get(0);
        }
        return finalResult;
    }

    public static void main(String[] args){
        String text = "ab";
        String prefix = "b";
        String suffix = "a";

//        String text = "nothing";
//        String prefix = "bruno";
//        String suffix = "ingenious";

        String output = findHighestPattern(text, suffix, prefix);
        System.out.println(output);
    }

}

