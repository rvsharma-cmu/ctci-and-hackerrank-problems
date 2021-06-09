package com.rvsharma.leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class LC0078Subsets {

    public void backtrack(List<List<Integer>> output, int first, List<Integer> temp, int[] nums, int candidates) {
        // if the combination is done
        if (temp.size() == candidates){
            output.add(new ArrayList<>(temp));
            return;
        }

        for (int i = first; i < nums.length; ++i) {
            // add i into the current combination
            temp.add(nums[i]);
            // use next integers to complete the combination
            backtrack(output,i + 1, temp, nums, candidates);
            // backtrack
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        for (int candidates = 0; candidates < nums.length + 1; ++candidates) {
            backtrack(output, 0, new ArrayList<>(), nums, candidates);
        }
        return output;
    }

    public static void main(String[] args) {
        int[] nms = {1,2,3};
        LC0078Subsets sol = new LC0078Subsets();
        System.out.println(sol.subsets(nms));
    }
}
