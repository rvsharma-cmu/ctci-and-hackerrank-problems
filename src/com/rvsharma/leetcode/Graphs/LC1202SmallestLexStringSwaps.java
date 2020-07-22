package com.rvsharma.leetcode.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC1202SmallestLexStringSwaps {
    public String smallestStringWithSwaps(String s, int[][] pairs) {
        List<Integer>[] graph=new ArrayList[s.length()];
        for(int i=0;i<s.length();i++) graph[i]=new ArrayList<>();
        for(int i=0;i<pairs.length;i++) {
            graph[pairs[i][0]].add(pairs[i][1]);
            graph[pairs[i][1]].add(pairs[i][0]);
        }
        boolean[] visited=new boolean[s.length()];
        char[] res=new char[s.length()];
        for(int i=0;i<s.length();i++) {
            if(visited[i]) continue;
            visited[i]=true;
            List<Integer> pos=new ArrayList<>();
            StringBuilder sb=new StringBuilder();
            DFS(graph, visited, i, s, pos, sb);
            char[] cur=sb.toString().toCharArray();
            Arrays.sort(cur);
            Collections.sort(pos);
            for(int j=0;j<pos.size();j++) res[pos.get(j)]=cur[j];
        }
        return new String(res);
    }

    void DFS(List<Integer>[] graph, boolean[] visited, int root, String s, List<Integer> pos, StringBuilder sb) {
        sb.append(s.charAt(root));
        pos.add(root);
        for(int i=0;i<graph[root].size();i++) {
            if(visited[graph[root].get(i)]) continue;
            visited[graph[root].get(i)]=true;
            DFS(graph, visited, graph[root].get(i), s, pos, sb);
        }
    }

    public static void main(String[] args) {
        String s = "dcab";
        int[][] pairs = {{0, 3}, {1, 2}, {0, 2}};
        LC1202SmallestLexStringSwaps sol = new LC1202SmallestLexStringSwaps();
        System.out.println(sol.smallestStringWithSwaps(s, pairs));
    }

}
