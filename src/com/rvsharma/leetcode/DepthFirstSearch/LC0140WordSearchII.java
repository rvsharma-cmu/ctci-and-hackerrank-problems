package com.rvsharma.leetcode.DepthFirstSearch;

import java.util.*;

public class LC0140WordSearchII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, new HashSet<>(wordDict)
//                , new HashMap<>()
        );
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict
//            , HashMap<String, LinkedList<String>> map
    ) {
//        if (map.containsKey(s))
//            return map.get(s);

        LinkedList<String>res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict
//                        , map
                );
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
//        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        LC0140WordSearchII sol = new LC0140WordSearchII();
        long startTime = System.currentTimeMillis();
        List<String> result = sol.wordBreak(s, dict);
        long endTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("Ran in " + (endTime - startTime) + " ms.");
    }

}
