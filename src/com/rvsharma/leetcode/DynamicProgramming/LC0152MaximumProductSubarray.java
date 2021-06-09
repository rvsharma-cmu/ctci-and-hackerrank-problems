package com.rvsharma.leetcode.DynamicProgramming;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class LC0152MaximumProductSubarray {

    /*
        Brute force
     */
    public int maxProduct(int[] nums) {

        int maxProd = 0;
        for(int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++) {
                int accumulate = 1;
                for (int k = i; k < j; k++) {
                    accumulate *= nums[k];
                }
                maxProd = Math.max(maxProd, accumulate);
            }
        }
        return maxProd;
    }

    /*
        Better brute force O(n^2)
     */
    public int maxProduct_betterBruteForce(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int maxProd = 0;
        for (int i = 0; i < nums.length; i++){
            int accumulate = 1;
            for (int j = i; j < nums.length; j++){
                accumulate *= nums[i];
                maxProd = Math.max(maxProd, accumulate);
            }
        }
        return maxProd;
    }

    /*
        Dynamic Programming Algorithm
     */
    public int maxProduct_dp(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        // store the result that is the max we have found so far
        int maxProd = nums[0];

        // maximum / minimum stores the max/min product of
        // subarray that ends with the current number A[i]
        int maximum = maxProd;
        int minimum = maximum;
        int n = nums.length;
        for (int i = 0; i < n; i++){
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (nums[i] < 0){
                int temp = maximum;
                maximum = minimum;
                minimum = temp;
            }

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            maximum = Math.max(nums[i], maximum * nums[i]);
            minimum = Math.min(nums[i], minimum * nums[i]);

            // the newly computed max value is a candidate for our global result
            maxProd = Math.max(maxProd, maximum);
        }

        return maximum;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        LC0152MaximumProductSubarray sol = new LC0152MaximumProductSubarray();
        System.out.println(sol.maxProduct(nums));
    }
}
