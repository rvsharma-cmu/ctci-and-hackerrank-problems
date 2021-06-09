package com.rvsharma.leetcode.HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC0451SortCharacterByFreq {
    public static String frequencySort(String s){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> (a.getValue().equals(b.getValue()) ? a.getKey() - b.getKey() : b.getValue() - a.getValue()));
        for(Map.Entry<Character, Integer> e : map.entrySet()){
            pq.offer(e);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Map.Entry<Character, Integer> e = pq.poll();
            for(int i = 0; i < e.getValue(); i++){
                sb.append(e.getKey());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
        System.out.println(frequencySort("Aabb"));
    }

}
