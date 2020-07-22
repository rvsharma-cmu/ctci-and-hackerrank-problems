package com.rvsharma.leetcode.Graphs;

import java.util.*;

public class LC0127WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList), visited = new HashSet<>();
        if(!dictionary.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++){
                String curr = q.poll();
                if(curr.equals(endWord)) return level;
                for(int j = 0; j < curr.length(); j++) {
                    StringBuilder sb = new StringBuilder(curr);
                    for(char k = 'a'; k <= 'z'; k++){
                        if (k == sb.charAt(j)) continue;
                        sb.setCharAt(j, k);
                        String next = sb.toString();
                        if(dictionary.contains(next) && !visited.contains(next)){
                            visited.add(next);
                            q.offer(next);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    /**
     * Bidirectional BFS
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength_bidi(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1;
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        if(!wordList.contains(endWord)) return 0;
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // swap beginSet and endSet if beginSet is smaller
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }

    public static void main(String[] args){

        String beginWord = "hit";
        String endWord = "cog";
        Set<String> set = new HashSet<>();
        set.add("hot");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
        set.add("cog");
        LC0127WordLadder wordLadder = new LC0127WordLadder();
//        int result = wordLadder.ladderLength(beginWord, endWord, new ArrayList<>(set));
        int result = wordLadder.ladderLength_bidi(beginWord, endWord, set);
        System.out.println("Length of the transformation is : " + result);
    }
}


