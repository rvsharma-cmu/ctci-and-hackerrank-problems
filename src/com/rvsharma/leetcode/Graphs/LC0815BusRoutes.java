package com.rvsharma.leetcode.Graphs;

import java.util.*;

public class LC0815BusRoutes {
    /**
     * For each of the bus stop, we maintain all the buses (bus routes) that go through it.
     * To do that, we use a HashMap, where bus stop number is the key and all the buses (bus routes) that go through
     * it are added to an ArrayList.
     *
     * We use BFS, where we process elements in a level-wise manner. We add the Start bus stop in the queue.
     * Next, when we enter the while loop, we add all the bus stops that are reachable by all the bus routes that
     * go via the Start. Thus, if we have the input as [[1, 2, 7], [3, 6, 7]] and Start as 6, then upon processing
     * bus stop 6, we would add bus stops 3 and 7.
     * With this approach, all the bus stops at a given level, are "equal distance" from the start node, in terms
     * of number of buses that need to be changed.
     *
     * To avoid loops, we also maintain a HashSet that stores the buses that we have already visited.
     *
     * Note that while in this approach, we use each stop for doing BFS, one could also consider each
     * bus (route) also for BFS.
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int level = 0;

        if (S==T) return 0;

        for(int i = 0; i < routes.length; i++){
            for(int j = 0; j < routes[i].length; j++){
                ArrayList<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                map.put(routes[i][j], buses);
            }
        }

        q.offer(S);
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                ArrayList<Integer> buses = map.get(cur);
                for (int bus: buses) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == T) return level;
                        q.offer(routes[bus][j]);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,2,7}, {3,6,7}};
        System.out.println(new LC0815BusRoutes().numBusesToDestination(mat, 1, 6));
    }
}
