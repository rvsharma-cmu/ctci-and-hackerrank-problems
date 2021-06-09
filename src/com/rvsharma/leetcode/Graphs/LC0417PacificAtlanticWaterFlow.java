package com.rvsharma.leetcode.Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note:
 *
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 *
 *
 * Example:
 *
 * Given the following 5x5 matrix:
 *
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Return:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class LC0417PacificAtlanticWaterFlow {

    // do BFS from the oceans
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        int rows = matrix.length, cols = matrix[0].length;
        Queue<int[]> pQ = new LinkedList<>();
        Queue<int[]> aQ = new LinkedList<>();
        boolean[][] pVisit = new boolean[rows][cols];
        boolean[][] aVisit = new boolean[rows][cols];

        for (int i = 0; i < cols; i++){
            pQ.offer(new int[]{0, i});
            aQ.offer(new int[]{rows - 1, i});
            pVisit[0][i] = true;
            aVisit[rows-1][i] = true;
        }

        for(int i = 0; i < rows; i++){
            pQ.offer(new int[]{i, 0});
            aQ.offer(new int[]{i, cols - 1});
            pVisit[i][0] = true;
            aVisit[i][cols-1] = true;
        }

        bfs(pQ, pVisit, matrix);
        bfs(aQ, aVisit, matrix);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pVisit[i][j] && aVisit[i][j]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    result.add(temp);
                }
            }
        }
        return result;
    }

    int[] dirs = {0, 1, 0, -1, 0};
    private void bfs(Queue<int[]> queue, boolean[][] visited, int[][] matrix) {

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] curr = queue.poll();
                for (int dir = 0; dir < 4; dir++){
                    int x = curr[0] + dirs[dir];
                    int y = curr[1] + dirs[dir + 1];
                    if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y] || matrix[x][y] < matrix[curr[0]][curr[1]])
                        continue;
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }

    public static void main(String[] args) {
        LC0417PacificAtlanticWaterFlow flow = new LC0417PacificAtlanticWaterFlow();

        int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(flow.pacificAtlantic(matrix));
    }

}
