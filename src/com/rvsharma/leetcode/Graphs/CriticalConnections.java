package com.rvsharma.leetcode.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnections {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        int[] ids = new int[n];
        int[] low = new int[n];
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        Arrays.fill(ids, -1);
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (List<Integer> edge : connections) {
            int from = edge.get(0);
            int to = edge.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for(int i = 0; i < n; i++){

            dfs(i, ids, low, graph, result, i);
        }

        return result;
    }

    int time = 0;
    private void dfs(int from, int[] ids, int[] low, List<List<Integer>> graph, List<List<Integer>> result, int parent){

        ids[from] = ++time;
        low[from] = ids[from];

        for(int j = 0; j < graph.get(from).size(); j++) {
            int to = graph.get(from).get(j);
            if(to == parent)
                continue;
            if(ids[to] == -1) {
                dfs(to, ids, low, graph, result, from);
                low[from] = Math.min(low[from], low[to]);
                if(low[to] > ids[from]) {
                    result.add(Arrays.asList(from, to));
                }
            } else {
                low[from] = Math.min(low[from], ids[to]);
            }
        }
    }
}
