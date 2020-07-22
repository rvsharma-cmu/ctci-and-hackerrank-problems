package com.rvsharma.leetcode.Graphs;

import java.util.*;

public class KahnsAlgorithm {


    public List<Character> buildExecution(char[][] grid) {


    /*
        A <- [B]
        B <- [C]
        [[A, B]
         [B, C]
        ]
        N
        E, V
    */

        if(grid == null || grid.length == 0) return new ArrayList<>();
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // initialization
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++) {
                indegree.put(grid[i][j], 0);
            }
        }
        // construct indegree number
        for(int i = 0; i < grid.length; i++){
            Set<Character> set = graph.getOrDefault(grid[i][0], new HashSet<>());
            set.add(grid[i][1]);
            graph.put(grid[i][0], set);
            indegree.put(grid[i][1], indegree.get(grid[i][1]) + 1);
        }
        // queue for BFS O(E)
        Queue<Character> q = new LinkedList<>();
        // add all the nodes which have no parent nodes / or no dependencies on it
        for(char c : indegree.keySet()) {
            if(indegree.get(c) == 0) {
                q.offer(c);
            }
        }

        List<Character> res = new ArrayList<>();
        // BFS O(V+E)
        while(!q.isEmpty()) {
            // poll each non-dependents which are already in the queue
            char c = q.poll();
            res.add(c);
            // santiy check if it exist in the graph or not
            if(graph.containsKey(c)){
                // for each of its neighbors
                Set<Character> neigh = graph.get(c);
                for(char n : neigh) {
                    // decrementing the indgree for neigh

                    indegree.put(n, indegree.get(n) - 1);
                    // if indegree becomes 0 add to the queue
                    if(indegree.get(n) == 0) q.offer(n);

                /*
                    A->B->C
                    A-[0]
                    B-[0]
                    C-[0]
                    A - [B]
                    B - [C]
                    q - []
                    Set - [C]
                    res = A, B, C
                */
                }
            }
        }
        // O(V + E)

        if(res.size() != indegree.size()) return new ArrayList<>();

        return res;

    }

    public static void main(String[] args) {
        KahnsAlgorithm sol = new KahnsAlgorithm();
        char[][] grid = new char[][]{{'A', 'B'}, {'B', 'C'}};
        List<Character> characters = sol.buildExecution(grid);
        System.out.println(characters);
    }


}
