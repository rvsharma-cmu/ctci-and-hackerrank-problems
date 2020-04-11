package com.rvsharma.leetcode.Arrays;

public class LC053MaxSubArray {

    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {

        if (left == right) {
            return nums[left];
        }

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = cross(nums, left, p, right);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private int cross(int[] nums, int left, int p, int right) {
        if(left == right) return nums[left];

        int leftSubSum = Integer.MIN_VALUE;
        int subSum = 0;
        for(int i = p; i > left - 1; i--){

            subSum += nums[i];
            leftSubSum = Math.max(leftSubSum, subSum);
        }

        subSum = 0;
        int rightSubSum = Integer.MIN_VALUE;
        for(int i = p + 1; i < right + 1; i++){
            subSum += nums[i];
            rightSubSum = Math.max(rightSubSum, subSum);
        }
        return leftSubSum + rightSubSum;
    }
}
