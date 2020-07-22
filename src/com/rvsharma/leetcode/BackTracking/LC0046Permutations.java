package com.rvsharma.leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class LC0046Permutations {


    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        helper(res, nums, new ArrayList<>());
        return res;
    }

    private static void helper(List<List<Integer>> res, int[] nums, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        // backtrack by adding the not present number in the list one by one
        for(int i = 0; i < nums.length; i++){
            if(tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            helper(res, nums, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }


}
