package com.rvsharma.leetcode.Graphs;

import java.util.*;

public class DetectCycleGraph {
    static int nodes = 0;
    public static boolean detectCycle(int[][] grid){
        Map<Integer, List<Integer>> graph = buildGraph(grid);
        int[] visited = new int[nodes];
        Arrays.fill(visited, -1);
        for (int u : graph.keySet()) {
            if (dfs(graph, visited, u)){
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(Map<Integer, List<Integer>> graph, int[] visited, int u) {

        if (visited[u] == 2) return false;
        visited[u] = 1; // visiting
        for (int v : graph.get(u)) {
            if (graph.containsKey(v)){
                if (visited[v] == 1) return true;
            }
            else if (dfs(graph, visited, v))
                return true;
        }
        visited[u] = 2;
        return false;
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] mat){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int[] ints : mat) {
            List<Integer> neighbors = graph.getOrDefault(ints[0], new ArrayList<>());
            neighbors.add(ints[1]);
            graph.put(ints[0], neighbors);
            set.add(ints[0]);
            set.add(ints[1]);
        }
        nodes = set.size();
        return graph;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1},{0,2},{2,1},{3,1},{4,1}};
        System.out.println(detectCycle(grid));
    }
}
