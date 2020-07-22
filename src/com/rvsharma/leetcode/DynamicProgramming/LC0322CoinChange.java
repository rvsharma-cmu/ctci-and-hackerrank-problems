package com.rvsharma.leetcode.DynamicProgramming;

public class LC0322CoinChange {
    public static int minCoinChange(int[] coins, int amount){
        return helper(0, coins, amount);
    }

    private static int helper(int idx, int[] coins, int amount){
        if(amount == 0) return 0;
        if(idx >= coins.length || amount < 0) return -1;
        int maxAmount = amount / coins[idx];
        int minCoins = Integer.MAX_VALUE;
        for(int x = 0; x <= maxAmount; x++){
            if(x * coins[idx] <= amount){
                int res = helper(idx+1, coins, amount - x * coins[idx]);
                if(res != -1){
                    minCoins = Math.min(minCoins, x + res);
                }
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    public static void main(String[] args) {
        System.out.println(minCoinChange(new int[]{1,2,5}, 11));
    }
}
