package com.rvsharma.leetcode.Graphs;

public class LC200NumberOfIsland {

    /**
     * DFS algorithm for finding the number of island. Basically this reduces to
     * finding the number of disconnected components in graph
     * @param grid
     * @return
     */
    public int numIslandsDFS(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int rows = grid.length, cols = grid[0].length;
        int count = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(grid[i][j] == '1'){
                    dfsHelper(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void dfsHelper(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >=grid.length || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        dfsHelper(grid, i-1, j);
        dfsHelper(grid, i, j-1);
        dfsHelper(grid, i+1, j);
        dfsHelper(grid, i, j+1);
    }

    int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    /**
     * Union Find; navigate the nodes on the grid, while checking their neighbors
     * whether they are land or water; if they are land, union them
     */
    public int numIslandUF(char[][] grid) {

        UnionFind uf = new UnionFind(grid);
        int rows = grid.length, cols = grid[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '1') {
                    for(int[] dir : directions) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if(x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == '1'){
                            int node1 = i * cols + j;
                            int node2 = x * cols + y;
                            uf.union(node1, node2);
                        }
                    }
                }
            }
        }
        return uf.disjointCount;
    }
}
