package com.rvsharma.leetcode.DynamicProgramming;

import java.util.Arrays;

public class LC0221MaximumSquare {
    public int maximalSquare_bruteForce(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int maxsqlen = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int sqlen = getMaxSquare(matrix, i, j);
                    if (maxsqlen < sqlen) {
                        maxsqlen = sqlen;
                    }
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    private int getMaxSquare(char[][] matrix, int i, int j) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int sqlen = 1;
        boolean flag = true;
        while (sqlen + i < rows && sqlen + j < cols && flag) {
            for (int k = j; k <= sqlen + j; k++) {
                if (matrix[i + sqlen][k] == '0') {
                    flag = false;
                    break;
                }
            }
            for (int k = i; k <= sqlen + i; k++) {
                if (matrix[k][j + sqlen] == '0') {
                    flag = false;
                    break;
                }
            }
            if (flag)
                sqlen++;
        }
        return sqlen;
    }

    public int maximalSquare(char[][] mat) {
        if (mat == null || mat.length == 0) return 0;
        int rows = mat.length, cols = mat[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxLen = 0;
        for(int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if(mat[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]), dp[i-1][j]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }

    public static void main(String[] args) {

        char[][] mat = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}};
        LC0221MaximumSquare sol = new LC0221MaximumSquare();
        int square = sol.maximalSquare(mat);
        System.out.println("maximalSquare " + square);
    }
}
