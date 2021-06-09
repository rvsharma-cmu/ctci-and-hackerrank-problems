package com.rvsharma.leetcode.Graphs;


import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 *
 *
 *
 *
 */
public class LC0332ReconstructItinerary {

    private Map<String, PriorityQueue<String>> graph;
    private LinkedList<String> res = new LinkedList<>();
    private Map<String, Integer> outEdges = new HashMap<>();

    /*
        Solution taken from below links
        Euler path/circuit existance: https://youtu.be/xR4sGgwtR2I
        Euler path/circuit algorithm: https://youtu.be/8MpoO2zA2l4
        This problem can be reduced to finding a Eulerian Cycle in a graph. Above tutorials explain it
        completely.
     */
    public List<String> findItinerary(List<List<String>> tickets){
        graph = new HashMap<>();
        buildGraph(tickets);

        dfs("JFK");
        List<String> out = new ArrayList<>();
        while (!res.isEmpty()) {
            out.add(res.removeFirst());
        }
        return out;
    }

    private void dfs(String src) {
        while (outEdges.get(src) != 0){
            String dest = graph.get(src).poll();
            outEdges.put(src, outEdges.get(src) - 1);
            dfs(dest);
        }
        res.addFirst(src);
    }

    private void buildGraph(List<List<String>> tickets) {

        for (List<String> ticket : tickets) {
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            PriorityQueue<String> pq = graph.getOrDefault(origin, new PriorityQueue<>(Comparator.naturalOrder()));
            pq.offer(dest);
            graph.put(origin, pq);
            outEdges.put(origin, outEdges.getOrDefault(origin, 0) + 1);
            if (!outEdges.containsKey(dest)) outEdges.put(dest, 0);
            if (!graph.containsKey(dest)) graph.put(dest, new PriorityQueue<>(Comparator.naturalOrder()));
        }

    }

    public static void main(String[] args) {
        String[][] input = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> tickets = new ArrayList<>();
        for (String[] in : input) {
            List<String> temp = new ArrayList<>();
            temp.add(in[0]);
            temp.add(in[1]);
            tickets.add(temp);
        }
        LC0332ReconstructItinerary sol = new LC0332ReconstructItinerary();
        List<String> itinerary = sol.findItinerary(tickets);
        System.out.println(itinerary);
    }
}
