package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MultiMaps {
    public Map<Integer, Set<String>> frequencyMap(Map<String, Integer> input){

        if(input == null || input.size() == 0)
            return new HashMap<>();

        Map<Integer, Set<String>> res = new HashMap<>();
        for(String str : input.keySet()){
            Set<String> temp = res.get(input.get(str));
            if(temp == null){
                temp = new HashSet<>();
            }
            temp.add(str);
            res.put(input.get(str), temp);
        }

        return res;
    }

    public Map<String, Integer> wordCount(String input){
        Map<String, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        input = input.toLowerCase();
        while(j < input.length()){
            char c = input.charAt(j);
            if(c >= 'a' && c <= 'z'){
                i = j;
                while(j < input.length() && input.charAt(j) >= 'a' && input.charAt(j) <= 'z') j++;
                String key = input.substring(i, j);
                map.put(key, map.getOrDefault(key, 0) + 1);
                continue;
            }
            j++;
        }
        return map;
    }

    public static void main(String[] args) {
        MultiMaps sol = new MultiMaps();
        Map<String, Integer> stringIntegerMap = sol.wordCount("To be or not to be, that is the question.");
        for(String str : stringIntegerMap.keySet()){
            System.out.println("\"" + str + "\"" + "\t\t-> " + stringIntegerMap.get(str));
        }
        Map<Integer, Set<String>> integerSetMap = sol.frequencyMap(stringIntegerMap);
        for(Map.Entry<Integer, Set<String>> e : integerSetMap.entrySet()){
            System.out.print(e.getKey() + "\t--> ");
            System.out.println(e.getValue());
        }
    }
}
