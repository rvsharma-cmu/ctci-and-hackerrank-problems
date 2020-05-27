package com.rvsharma.leetcode.Arrays;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class LC1296DivideArrayConsecutiveK {

    public boolean isPossibleDivide(int[] A, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        Queue<Integer> start = new LinkedList<>();
        int lastChecked = -1, opened = 0;
        for (int i : map.keySet()) {
            int frequency = map.get(i);
            if (opened > 0 && i > lastChecked + 1 || opened > frequency) {
                return false;
            }
            start.add(frequency - opened);
            lastChecked = i;
            opened = frequency;
            if (start.size() == k) {
                int close = start.remove();
                opened -= close;
            }
        }
        return opened == 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,2,3,4,3,4,5,9,10,11};
        int[] nums2 = {1, 2, 3, 4};

        /*
            [16,5,15,15,20,16,20,14,21,20,19,20,12,17,13,15,11,17,18,18,11,13,13,14,14,9,20,18,10,4,4,6,15,19,8,15,7,
            17,15,9,24,2,23,22,26,8,21,22,14,13,16,2,25,23,17,19,17,3,22,23,19,12,21,12,16,27,28,10,13,8,24,3,22,6,10,
            9,14,7,11,22,11,5,16,19,21,2,8,24,16,21,7,29,18,9,10,18,6,17,21,20]
            k = 10
         */
        int[] nums3 = {16,5,15,15,20,16,20,14,21,20,19,20,12,17,13,15,11,17,18,18,11,13,13,14,14,9,20,18,10,4,4,6,15,19
                ,8,15,7,17,15,9,24,2,23,22,26,8,21,22,14,13,16,2,25,23,17,19,17,3,22,23,19,12,21,12,16,27,28,10,13,8,
                24,3,22,6,10,9,14,7,11,22,11,5,16,19,21,2,8,24,16,21,7,29,18,9,10,18,6,17,21,20};
        LC1296DivideArrayConsecutiveK sol = new LC1296DivideArrayConsecutiveK();
        boolean test = sol.isPossibleDivide(nums1, 3);
        assert test == true : "False for nums1";
        test = sol.isPossibleDivide(nums2, 3);
        assert test == false : "True for nums2!";
        test = sol.isPossibleDivide(nums3, 10);
        assert test == false : "True for nums3";
    }
}
