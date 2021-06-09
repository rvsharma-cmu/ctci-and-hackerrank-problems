package com.rvsharma.leetcode.Graphs;


/**
 * Union Find by path compression and rank
 * Source: https://leetcode.com/problems/friend-circles/discuss/101336/Java-solution-Union-Find/266043
 *
 */
public class UnionFind {
    private final int[] parent;
    private final int[] rank;
    int count;
    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        count = n;
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // with path-compression
    public int find(int p) {
        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // with union by rank
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if(rank[rootP] > rank[rootQ]){
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        } else {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
        count--;
    }
}
