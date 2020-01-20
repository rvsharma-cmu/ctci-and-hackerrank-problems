package com.rvsharma.leetcode.Graphs;

import java.util.HashSet;
import java.util.Set;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1;
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        if(!wordList.contains(endWord)) return 0;
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
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
        WordLadder wordLadder = new WordLadder();
        int result = wordLadder.ladderLength(beginWord, endWord, set);
        System.out.println("Length of the transformation is : " + result);
    }
}


