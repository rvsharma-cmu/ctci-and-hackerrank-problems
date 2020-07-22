package com.rvsharma.leetcode.DynamicProgramming;

import java.util.Arrays;

public class LC0085MaximalRectangle {

    /*
        This method is similar to LC0084 max area of rectangle in a histogram
     */
    static int maxAreaHistogram(int[] heights){
        if(heights == null || heights.length == 0) return 0;
        int n = heights.length;
        int[] lessFromLeft = new int[n],
                lessFromRight = new int[n];
        lessFromLeft[0] = -1;
        lessFromRight[n-1] = n;
        for (int i = 1; i < n; i++){
            int prev = i - 1;
            while (prev >= 0 && heights[prev] >= heights[i])
                prev = lessFromLeft[prev];
            lessFromLeft[i] = prev;
        }
        for(int i = n - 2; i >= 0; i--){
            int prev = i + 1;
            while(prev < n && heights[prev] >= heights[i])
                prev = lessFromRight[prev];
            lessFromRight[i] = prev;
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int currArea = heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1);
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    /*
        Max Area is found by going across each row and storing the height and updating the maxArea
        of the rectangle after visiting each row
     */
    static int maxAreaRectangle(int[][] grid) {
        if(grid.length == 0) return 0;
        int maxArea = 0;
        int[] dp = new int[grid[0].length];
        int n = grid.length, m = grid[0].length;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                dp[j] = grid[i][j] == 1 ? dp[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, maxAreaHistogram(dp));
        }
        return maxArea;
    }

    public static int maximalRectangle(int[][] grid){

        if(grid.length == 0) return 0;
        int numRows = grid.length, numCols = grid[0].length;
        int maxArea = 0;
        int[] heights = new int[numCols];
        int[] leftBound = new int[numCols];
        int[] rightBound = new int[numCols];
        Arrays.fill(rightBound, numCols);
        for (int i = 0; i < numRows; i++) {
            int currLeft = 0, currRight = numCols;
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == 1)
                    heights[j]++;
                else
                    heights[j] = 0;
            }
            for(int j = 0; j < numCols; j++){
                if(grid[i][j] == 1){
                    leftBound[j] = Math.max(currLeft, leftBound[j]);
                } else {
                    leftBound[j] = 0;
                    currLeft = j+1;
                }
            }
            for(int j = numCols - 1; j >= 0; j--){
                if(grid[i][j] == 1){
                    rightBound[j] = Math.min(currRight, rightBound[j]);
                } else {
                    rightBound[j] = numCols;
                    currRight = j;
                }
            }

            for(int j = 0; j < numCols; j++){
                maxArea = Math.max(maxArea, heights[j] * (rightBound[j] - leftBound[j]));
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{{1, 0, 1, 0, 0}, {1,0,1,1,1}, {1,0,1,1,1},{1,0,0,1,0}};
        System.out.print("Maximum area that can be formed is: ");
        System.out.println(maximalRectangle(mat));
    }
}
