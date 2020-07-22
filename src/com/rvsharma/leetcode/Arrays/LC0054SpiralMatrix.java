package com.rvsharma.leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

public class LC0054SpiralMatrix {
    public static List<Integer> spiralMatrix(int[][] matrix){
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        List<Integer> res = new ArrayList<>();
        int n = matrix.length, m = matrix[0].length;
        while(res.size() < n * m){
            for(int i = left; i <= right && res.size() < n * m; i++){
                res.add(matrix[top][i]);
            }
            for(int i = top+1; i <= bottom - 1 && res.size() < n * m; i++){
                res.add(matrix[right][i]);
            }
            for(int i = right; i >= left && res.size() < n * m; i--){
                res.add(matrix[bottom][i]);
            }
            for(int i = bottom - 1; i >= top + 1 && res.size() < n * m; i--){
                res.add(matrix[left][i]);
            }
            top++; right--; bottom--; left++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(spiralMatrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
