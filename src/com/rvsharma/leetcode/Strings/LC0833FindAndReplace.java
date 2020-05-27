package com.rvsharma.leetcode.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LC0833FindAndReplace {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for(int i = 0; i < indexes.length; i++){
            sorted.add(new int[]{indexes[i], i});
        }
        Collections.sort(sorted, Comparator.comparing(i -> -i[0]));

        for(int[] a : sorted){
            int i = a[0], j = a[1];
            String s = sources[j], t = targets[j];
            if(S.substring(i, i + s.length()).equals(s)){
                S = S.substring(0, i) + t + S.substring(i + s.length());
            }
        }

        return S;
    }
}
