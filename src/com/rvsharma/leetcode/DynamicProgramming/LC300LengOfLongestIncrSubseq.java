package com.rvsharma.leetcode.DynamicProgramming;

public class LC300LengOfLongestIncrSubseq {

    public static int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public static int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }

    public static void main(String[] args) {

        int[] nums = new int[]{0,3,1,6,2,2,7};
        System.out.println(lengthOfLIS(nums));

    }
}
