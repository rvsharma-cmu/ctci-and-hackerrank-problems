package com.rvsharma.leetcode.Graphs;

public class UnionFind {
    int[] parent;
    int rows, cols;
    int disjointCount = 0;
    public UnionFind(char[][] grid){
        rows = grid.length;
        cols = grid[0].length;
        parent = new int[rows * cols];
        for(int i =0 ; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int id = i * cols + j;
                if(grid[i][j] == '1') {
                    disjointCount++;
                    parent[id] = id;
                }
            }
        }
    }

    int find(int node) {

        if(parent[node] == node) {
            return node;
        }
        parent[node] = find(parent[node]);
        return parent[node];
    }

    void union(int node1, int node2){
        int find1 = find(node1);
        int find2 = find(node2);
        if (find1 != find2) {
            parent[find1] = find2;
            disjointCount--;
        }
    }
}
