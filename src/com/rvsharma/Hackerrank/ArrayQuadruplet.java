package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;


/**
 * Given an unsorted array of integers arr and a number s,
 * write a function findArrayQuadruplet that finds four numbers (quadruplet) in arr that sum up to s.
 * Your function should return an array of these numbers in an ascending order.
 * If such a quadruplet doesn’t exist, return an empty array.
 *
 * Note that there may be more than one quadruplet in arr whose sum is s.
 * You’re asked to return the first one you encounter (considering the results are sorted).
 *
 * Explain and code the most efficient solution possible, and analyze its time and space complexities.
 *
 * Example:
 *
 * input:  arr = [2, 7, 4, 0, 9, 5, 1, 3], s = 20
 *
 * output: [0, 4, 7, 9] # The ordered quadruplet of (7, 4, 0, 9)
 *                      # whose sum is 20. Notice that there
 *                      # are two other quadruplets whose sum is 20:
 *                      # (7, 9, 1, 3) and (2, 4, 9, 5), but again you’re
 *                      # asked to return the just one quadruplet (in an
 *                      # ascending order)
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.integer arr
 *
 * 1 ≤ arr.length ≤ 100
 * [input] integer s
 *
 * [output] array.integer
 *
 * 7 - [2,4,0,9,5,1,3], 13
 * 4 - [2,0,9,5,1,3], 9
 *
 *
 *
 */
public class ArrayQuadruplet {

    static int[] findArrayQuadruplet(int[] arr, int s) {
        // your code goes here
        boolean found = false;
        int[] output = new int[4];
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        for (int x : arr){
            list.add(x);
        }
        for(int i = 0; i < arr.length; i++){
            for (int j = i + 1; j < arr.length; j++){
                int first = list.get(i);
                int second = list.get(j);
                list.remove(j);
                list.remove(i);
                int[] pairs = pairFound(list, s - first - second);
                if(pairs != null){
                    found = true;
                    int[] res = {first, second, pairs[0], pairs[1]};
                    Arrays.sort(res);
                    int num = 0;
                    for (int x : res){
                        num = num * 10 + x;
                    }
                    if(num < min){
                        output = res;
                        min = num;
                    }
                }
                list.add(i, first);
                list.add(j, second);
            }
        }
        return found ? output : new int[0];
    }

    static int[] pairFound(List<Integer> list, int target){
        Set<Integer> complement = new HashSet<>();

        for (int i = 0; i < list.size(); i++){
            int num = list.get(i);
            if(complement.contains(target - num)){
                return new int[]{num, target - num};
            }
            complement.add(num);
        }

        return null;
    }

    static int[] findQuad(int[] arr, int s){
        int n = arr.length;

        if(n < 4) return new int[0];

        Arrays.sort(arr);
        for (int i = 0; i <= n - 4; i++){
            for (int j = i + 1; j <= n - 3; j++){
                int sum = s - (arr[i] + arr[j]);

                int low = j + 1;
                int high = n - 1;
                while (low < high){
                    if(arr[low] + arr[high]  < sum)
                        low++;
                    else if(arr[low] + arr[high] > sum) {
                        high--;
                    } else {
                        return new int[]{arr[i], arr[j], arr[low], arr[high]};
                    }
                }
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] in = new int[]{2, 7, 4, 0, 9, 5, 1, 3};
        int x = 40;
        System.out.println(Arrays.toString(findQuad(in, x)));
    }

}