package com.rvsharma.leetcode.Graphs;

import java.util.*;

public class LC0126WordLadderII {

    // do BFS very standard BFS process
    // BFS of paths not words
    // the queue node becomes list of paths not words
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        // If there is no ending word, it will end directly, otherwise it will cause an endless loop
        if (!wordList.contains(endWord)) {
            return ans;
        }
        bfs(beginWord, endWord, wordList, ans);
        return ans;
    }

    public void bfs(String beginWord, String endWord, List<String> wordList, List<List<String>> ans) {
        // this queue is a queue of all the paths
        // so we will perform BFS on list of possible paths
        Queue<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        // add the path with the beginWord to the queue
        queue.offer(path);
        boolean isFound = false;
        Set<String> dict = new HashSet<>(wordList);
        /*
            visited records all the visited nodes on this level
            these words will never be visited again after this level
            and should be removed from wordList. This is guaranteed
            by the shortest path.
         */
        Set<String> visited = new HashSet<>();
        // add word to visited set so that we don't visit it again
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // sub visited for this level to add it in one shot
            Set<String> subVisited = new HashSet<>();
            for (int j = 0; j < size; j++) {
                List<String> p = queue.poll();
                // Get the last word of the current path
                String temp = p.get(p.size() - 1);
                // Get all the neighbor nodes of temp string which is the last added
                // string in the path
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                // backtrack for all the neighbor nodes if they have not been visited
                for (String neighbor : neighbors) {
                    // Only consider words that have not appeared before
                    if (!visited.contains(neighbor)) {
                        // reaching the end word
                        if (neighbor.equals(endWord)) {
                            // add to result and continue doing BFS
                            isFound = true;
                            p.add(neighbor);
                            ans.add(new ArrayList<>(p));
                            p.remove(p.size() - 1);
                        }
                        // Add the current word
                        p.add(neighbor);
                        queue.offer(new ArrayList<>(p));
                        p.remove(p.size() - 1);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
            if (isFound) {
                break;
            }
        }
    }

    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chs = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch)
                    continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        String beginWord = "hit",
                endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        LC0126WordLadderII sol = new LC0126WordLadderII();
        List<List<String>> result = sol.findLadders(beginWord, endWord, Arrays.asList(wordList));

        System.out.println(result);
    }

}
