package com.rvsharma.leetcode.Graphs;

import java.util.*;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers.
 * If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations,
 * vector<double>& values,
 * vector<pair<string, string>> queries ,
 * where equations.size() == values.size(), and the values are positive.
 * This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class LC0399EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++){
            res[i] = evaluate(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<String>());
        }
        return res;
    }

    private double evaluate(String start, String end, Map<String, Map<String, Double>> graph, HashSet<String> visited) {
        if(!graph.containsKey(start)) return -1.0;
        if (graph.get(start).containsKey(end)) return graph.get(start).get(end);

        visited.add(start);
        for (Map.Entry<String, Double> neighbors : graph.get(start).entrySet()){
            if (!visited.contains(neighbors.getKey())){
                double res = evaluate(neighbors.getKey(), end, graph, visited);
                if (res != -1.0)
                    return res * neighbors.getValue();
            }
        }
        return -1.0;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        int i = 0;
        for (List<String> eq : equations){
            Map<String, Double> map = graph.getOrDefault(eq.get(0), new HashMap<>());
            map.put(eq.get(1), values[i]);
            graph.put(eq.get(0), map);
            map = graph.getOrDefault(eq.get(1), new HashMap<>());
            map.put(eq.get(0), 1/values[i]);
            graph.put(eq.get(1), map);
            i++;
        }
        return graph;
    }

    public static void main(String[] args) {
        String[][] eqs = {{"a","b"},{"b","c"}};
        String[][] que = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};
        double[] values = {2.0,3.0};
        List<List<String>> equations = new ArrayList<>();
        List<List<String>> queries = new ArrayList<>();
        for (String[] e : eqs){
            List<String> temp = new ArrayList<>( );
            temp.add(e[0]);
            temp.add(e[1]);
            equations.add(temp);
        }
        for (String[] e : que){
            List<String> temp = new ArrayList<>( );
            temp.add(e[0]);
            temp.add(e[1]);
            queries.add(temp);
        }
        LC0399EvaluateDivision eval = new LC0399EvaluateDivision();
        System.out.println(Arrays.toString(eval.calcEquation(equations, values, queries)));
    }

}
