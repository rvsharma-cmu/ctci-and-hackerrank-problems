package com.rvsharma.leetcode.DepthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0679_24Game {

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int i : nums) {
            list.add((double) i);
        }
        return dfs(list);
    }

    // Each time dfs chooses two cards
    private boolean dfs(List<Double> list) {
        if (list.size() == 1) {
            // If there is only one card left in the list at this time
            if (Math.abs(list.get(0)- 24.0) < 0.001) {
                return true;
            }
            return false;
        }

        // Pick two cards
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                // For every next possible combination
                for (double c : compute(list.get(i), list.get(j))) {
                    List<Double> nextRound = new ArrayList<>();
                    // Add them to the next list loop
                    nextRound.add(c);
                    for(int k = 0; k < list.size(); k++) {
                        if(k == j || k == i) continue;
                        nextRound.add(list.get(k));
                    }
                    if(dfs(nextRound)) return true;
                }
            }
        }
        return false;

    }
    // Calculate the next possible combination
    private List<Double> compute(double a, double b) {
        List<Double> res = Arrays.asList(a + b,a-b,b-a,a*b,a/b,b/a);
        return res;
    }

    public static void main(String[] args) {
        int[] input = {4,1,8,7};
        System.out.println(new LC0679_24Game().judgePoint24(input));
    }

}
