package com.rvsharma.leetcode.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1192CriticalConnections {

    /*
        Tarjan's Algorithm: https://www.youtube.com/watch?v=aZXi1unBdJA

     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        int[] disc = new int[n];
        int[] low = new int[n];
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        Arrays.fill(disc, -1);  // use disc to track if visited (disc[i] == -1)
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : connections) {
            int from = edge.get(0);
            int to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for(int i = 0; i < n; i++){

            dfs(i, disc, low, graph, result, i);
        }

        return result;
    }

    int time = 0;   // time when discover each vertex
    private void dfs(int from, int[] disc, int[] low, List<List<Integer>> graph, List<List<Integer>> result, int parent){
        // discover u
        disc[from] = ++time;
        low[from] = disc[from];

        for(int j = 0; j < graph.get(from).size(); j++) {
            int to = graph.get(from).get(j);
            if(to == parent)    // if parent vertex, ignore
                continue;
            if(disc[to] == -1) {    // if not discovered
                dfs(to, disc, low, graph, result, from);
                low[from] = Math.min(low[from], low[to]);
                if(disc[from] < low[to]) {
                    // from - to is critical, there is no path for v to reach back to u or previous vertices of u
                    result.add(Arrays.asList(from, to));
                }
            } else {
                // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                low[from] = Math.min(low[from], disc[to]);
            }
        }
    }
}
