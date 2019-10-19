package com.rvsharma.Hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HRStemmer {

    static String stemmer(String inputTerms) {
        if(inputTerms == null || inputTerms.isEmpty())
            return "";
        String[] strs = inputTerms.trim().split("\\s+");
        String[] suffixes = {"\\w+'s", "\\w+s'", "\\w+ed","\\w+ing","\\w+ness", "\\w+e",
                "\\w+?(:s|es)", "\\w+n",
                "\\w+er", "\\w+est",
                "\\w+ly", "\\w+th" };
        List<Pattern> suffixPatterns = new ArrayList<>();
        String[] irregVerbs = {"did", "does", "doing", "done", "went", "going", "gone"};
        Set<String> irregularVerbs = new HashSet<>(Arrays.asList(irregVerbs));
        String prefixes = "^(non|pre|un|ir).*$";
        Pattern prefixPattern = Pattern.compile(prefixes);
        for(String suffix : suffixes) {
            suffixPatterns.add(Pattern.compile(suffix));
        }
        String result = "";
        boolean matchedOnce = false;
        for(String str : strs){
            if(irregularVerbs.contains(str)){
                if(str.equals("did") || str.equals("does") || str.equals("doing")
                        || str.equals("done")){
                    result = "do";
                }

                else if(str.equals("went") || str.equals("going") || str.equals("gone")) {
                    result = "go";
                }
                matchedOnce = true;
                continue;
            }
            String newString = "";
            if (prefixPattern.matcher(str).matches()) {
                matchedOnce = true;
                if(str.startsWith("non")) newString = str.substring("non".length());
                else if(str.startsWith("pre")) newString = str.substring("pre".length());
                else if(str.startsWith("un")) newString = str.substring("un".length());
                else newString = str.substring("ir".length());
                result = newString;
            }
            str = newString.isEmpty() ? str : newString;
            for(Pattern pattern : suffixPatterns){

                if(pattern.matcher(str).matches()){
                    int suffLen = pattern.toString().length() - 3;
                    // if(str.length() - suffLen <=0) {
                    //     result = str;
                    //     System.out.println("Less than");
                    //     break;
                    // }
                    String substring = str.substring(0, str.length() - suffLen);
                    if(result.isEmpty())
                        result = substring;
                    if(substring.endsWith("e") || str.endsWith("e")){
                        result = str;
                    }
                    else if(substring.length() != 0 && substring.length() <= result.length())
                        result = substring;
                    matchedOnce = true;
                    break;
                }
            }
        }
        if(!matchedOnce) result = strs[0];
        return result;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        String _inputTerms;
        try {
            _inputTerms = in.nextLine();
        } catch (Exception e) {
            _inputTerms = null;
        }
        System.err.close();
        String res = stemmer(_inputTerms);
        System.out.println("Result:"+res);
    }

}
