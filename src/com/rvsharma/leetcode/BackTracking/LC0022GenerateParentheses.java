package com.rvsharma.leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:

    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]

 */
public class LC0022GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helper(result, "", 0, 0, n);
        return result;
    }

    private static void helper(List<String> result, String current, int open, int close, int max){
        if(current.length() == 2 * max){
            result.add(current);
            return;
        }
        if(open < max){
            helper(result, current + "(", open + 1, close, max);
        }
        if(close < open){
            helper(result, current + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
