package com.rvsharma.leetcode.Graphs;

import java.util.HashSet;
import java.util.Set;

public class NumberOfColors {


    public static void main(String[] args) {

        String[] words = {"aaaba", "ababa", "aaaca"};
        char[][] grid = new char[words.length][words[0].length()];
        int i = 0;
        for(String s : words){
            char[] chars = s.toCharArray();
            for(int j = 0; j < words[0].length(); j++){
                grid[i][j] = chars[j];
            }
            i++;
        }
        int res = countColors(grid);
        System.out.println(res);
    }

    private static int countColors(char[][] grid) {
        int count = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] != '#'){
                    dfs(grid, grid[i][j], i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(char[][] grid, char c, int i, int j) {

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != c || grid[i][j] == '#'){
            return;
        }

        grid[i][j] = '#';
        dfs(grid, c, i, j+1);
        dfs(grid, c, i+1, j);
        dfs(grid, c, i, j-1);
        dfs(grid, c, i-1, j);
    }


}
