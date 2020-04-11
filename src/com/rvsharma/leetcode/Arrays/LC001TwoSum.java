package com.rvsharma.leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * given an array and a target sum, find two numbers that add up to the given
 * target number given
 */
public class LC001TwoSum {

    /**
     * brute force
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumBrute(int[] nums, int target) {
        int[] out = new int[2];

        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++) {

                if(target - nums[i] == nums[j]) {
                    out[0] = j;
                    out[1] = i;
                    break;
                }
            }
        }
        return out;
    }

    /**
     * Single pass two sum, using HashMap
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(hashMap.containsKey(target-nums[i])) {
                return new int[]{hashMap.get(target-nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");
    }
}
