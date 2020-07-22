package com.rvsharma.leetcode.DepthFirstSearch;


import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class LC0140WordBreakII {
    private static Map<String, List<String>> map = new HashMap<>();
    public static List<String> wordBreak(String s, List<String> wordDict){
        return dfs(s, new HashSet<String>(wordDict));
    }

    private static List<String> dfs(String str, Set<String> dict){
        List<String> res = new ArrayList<>();
        if(str == null || str.length() == 0) return res;
        if(dict.contains(str)) res.add(str);
        if(map.containsKey(str)){
            return map.get(str);
        }
        for(int i = 1; i < str.length(); i++){
            String suffix = str.substring(i);
            if(dict.contains(suffix)){
                List<String> temp = dfs(str.substring(0, i), dict);
                for(int j = 0; j < temp.size(); j++){
                    res.add(temp.get(j) + " " + suffix);
                }
            }
        }
        map.put(str, res);
        return res;
    }

    public static void main(String[] args) {
        String[] wordDict = {"apple", "pen", "applepen", "pine", "pineapple"};
        System.out.println(wordBreak("pineapplepenapple", Arrays.asList(wordDict)));
    }
}
