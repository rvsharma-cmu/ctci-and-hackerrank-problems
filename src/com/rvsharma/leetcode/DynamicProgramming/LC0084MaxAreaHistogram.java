package com.rvsharma.leetcode.DynamicProgramming;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LC0084MaxAreaHistogram {

    public static int largestRectangleArea_stack(int[] heights){
        Deque<Integer> st = new ArrayDeque<>();
        int n = heights.length;
        int maxArea = 0;
        int i = 0;
        for (; i < n; i++){
            if(st.isEmpty() || heights[i] >= heights[st.peekLast()]){
                st.addLast(i);
            } else {
                int top = st.removeLast();
                maxArea = Math.max(maxArea, heights[top] * (st.isEmpty() ? i : i - st.peekLast() - 1));
                i--;
            }
        }
        while (!st.isEmpty()) {
            int top = st.removeLast();
            maxArea = Math.max(maxArea, heights[top] * (st.isEmpty() ? i : i - st.peekLast() - 1));
        }

        return maxArea;
    }

    public static int largestRectangleArea(int[] heights){
        int len = heights.length;
        int[] lessLeft = new int[len], lessRight = new int[len];
        int maxArea = 0;
        lessLeft[0] = -1;
        for (int i = 1; i < len; i++) {
            int prev = i - 1;
            while(prev >= 0 && heights[prev] >= heights[i]){
                prev = lessLeft[prev];
            }
            lessLeft[i] = prev;
        }
        lessRight[len - 1] = len;
        for(int i = len - 2; i >= 0; i--){
            int next = i + 1;
            while (next < len && heights[next] >= heights[i]){
                next = lessRight[next];
            }
            lessRight[i] = next;
        }
        for(int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessRight[i] - lessLeft[i] - 1));
        }
        return maxArea;
    }

    private static int[][] dir = new int[][]{{0,1}, {0,-1},{1,0},{-1,0}};

    /**
     * Calculates the shortest distance from the start 'O' to end point 'T' using
     * the breadth first search.
     * @param cityMap Semi-colon separated string input. start 'O'; end point 'T'; obstacle 'X'
     * @return shortest distance to the target from the officer location
     */
    public static int calculateDistance(String cityMap){
        // split among each row
        String[] maps = cityMap.split(";");

        int rows = maps.length;
        // for uneven grid sizes; find out the max col size and
        // append 'X' for rest of shorter cols
        // OX_;_X_T;___
        int cols = Integer.MIN_VALUE;
        for(String str : maps){
            cols = Math.max(cols,str.length());
        }

        // build the grid; append 'X' for rest of shorter cols
        char[][] grid = new char[rows][cols];
        for(int i = 0; i < rows; i++){
            int j;
            for (j = 0; j < maps[i].length(); j++){
                grid[i][j] = maps[i].charAt(j);
            }
            while( j < cols) {
                grid[i][j] = 'X';
                j++;
            }
        }
        // find out the start and end points in the grid;
        int[] start = new int[2];
        int[] end = new int[2];
        int startEndFound = 0;
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(grid[i][j] == 'O'){
                    start[0] = i;
                    start[1] = j;
                    startEndFound++;
                } else if (grid[i][j] == 'T'){
                    end[0] = i;
                    end[1] = j;
                    startEndFound++;
                }
            }
        }
        // if start or End point not found return -2 as indicative of an error condition
        if(startEndFound != 2){return -2;}

        // keep visited coordinate record
        boolean[][] visited = new boolean[rows][cols];

        // queue for BFS
        Queue<int[]> q = new LinkedList<>();
        // add starting point and mark it as visited.
        q.add(start);
        visited[start[0]][start[1]] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            // check for all directions
            int size = q.size();
            for (int i = 0 ; i < size; i++){
                int[] curr = q.poll();
                if (curr[0] == end[0] && curr[1] == end[1]){
                    return steps;
                }
                for(int j = 0; j < 4; j++){
                    int x = dir[j][0] + curr[0];
                    int y = dir[j][1] + curr[1];
                    // if the coordinate is within the grid bounds and not visited and is not an obstacle;
                    // visit the coordinate and add to the queue for next level BFS
                    if(x >= 0 && y >= 0 && x < rows && y < cols && !visited[x][y] && grid[x][y] != 'X'){
                        q.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            steps++;
        }

        // return -1 if no route is found
        return -1;
    }

    public static void main(String[] args) {
//        int[] heights = {2,1,5,6,2,3};
//        System.out.println(largestRectangleArea(heights));
        System.out.println(calculateDistance("___"));
    }
}
