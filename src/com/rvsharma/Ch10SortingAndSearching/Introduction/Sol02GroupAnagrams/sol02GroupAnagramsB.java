package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol02GroupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class sol02GroupAnagramsB {

    static void groupAnagrams(String[] arrays){

        Map<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();

        for(String str : arrays){
            String keySet = sortString(str);
            ArrayList<String> list = hashMap.getOrDefault(keySet, new ArrayList<>());
            list.add(str);
            hashMap.put(keySet, list);
        }

        int index = 0;
        for(String str : hashMap.keySet()){

            ArrayList<String> groupOfString = hashMap.get(str);
            for(String string : groupOfString){
                arrays[index++] = string;
            }
        }
    }

    private static String sortString(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return Arrays.toString(arr);
    }
}
