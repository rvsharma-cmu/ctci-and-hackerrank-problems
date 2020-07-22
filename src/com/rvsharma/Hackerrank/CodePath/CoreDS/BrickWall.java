package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {

    /*
        [1, 3, 5]
        [3, 4]
        [1, 4]
        [2]
        [3, 4]
        [1, 4, 5]
        1 -> 2
        2 -> 1
        3 -> 3
        4 -> 4
        5 -> 2
    */
    public static int leastBricks(List<List<Integer>> wall) {
        List<int[]> list = new ArrayList<>();
        for(List<Integer> row : wall){
            int[] temp = new int[row.size() - 1];
            int sum = 0;
            for(int i = 0; i < row.size() - 1; i++){
                sum += row.get(i);
                temp[i] = sum;
            }
            list.add(temp);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] row : list){
            for(int x : row){
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }
        int maxValue = Integer.MIN_VALUE, maxKey = -1;
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(e.getValue() > maxValue){
                maxValue = e.getValue();
                maxKey = e.getKey();
            }
        }
        int res = 0;
        if(maxKey == -1) return list.size();
        for(int[] row : list){
            boolean found = false;
            for(int x : row){
                if(x == maxKey){
                    found = true;
                    break;
                }
            }
            if(!found) res++;
        }
        return res;
    }

    public static int leastBricks_official(List<List<Integer>> wall){
        Map<Integer, Integer> map = new HashMap<>();
        for(List<Integer> row : wall){
            int sum = 0;
            for(int i = 0; i < row.size() - 1; i++){
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = Integer.MAX_VALUE;
        for(int key : map.keySet()){
            res = Math.min(res, wall.size() - map.get(key));
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        int[][] parse = new int[][]{{1}, {1}, {1}};
        for (int[] ints : parse) {
            List<Integer> temp = new ArrayList<>();
            for (int anInt : ints) {
                temp.add(anInt);
            }
            input.add(temp);
        }
        System.out.println(BrickWall.leastBricks(input));
    }
}
