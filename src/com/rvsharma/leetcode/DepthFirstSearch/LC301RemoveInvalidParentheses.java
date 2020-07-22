package com.rvsharma.leetcode.DepthFirstSearch;

import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */
public class LC301RemoveInvalidParentheses {

    /*
        Naive BFS solution
     */
    public static List<String> removeInvalidParentheses_BFS(String input){
        List<String> res = new ArrayList<>();
        if(input == null || input.length() == 0) return res;
        Deque<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.addLast(input);
        visited.add(input);
        boolean found = false;
        while (!q.isEmpty()){
            String curr = q.removeFirst();
            if(valid(curr)){
                res.add(curr);
                found = true;
            }
            if (found) continue;
            for (int i = 0; i < curr.length(); i++) {
                if(curr.charAt(i) != '(' && curr.charAt(i) != ')') continue;
                String t = curr.substring(0, i) + curr.substring(i+1);
                if(!visited.contains(t)) {
                    q.offer(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }

    private static boolean valid(String str){
        if(str == null) return false;
        if (str.length() == 0) return true;
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                count++;
            } else if (str.charAt(i) == ')'){
                if(count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }

    /*
        DFS Solution
     */

    /**
     * Explanation:
     * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
     * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
     *
     * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
     *
     * After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
     * For this, we keep tracking the last removal position and only remove ‘)’ after that.
     *
     * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
     * The answer is: do the same from right to left.
     * However a cleverer idea is: reverse the string and reuse the code!
     * @param input String input
     */
    public static List<String> removeInvalidParentheses(String input) {
        List<String> result = new ArrayList<>();
        dfsRemove(input, result, 0,0,'(',')');
        return result;
    }

    private static void dfsRemove(String inputStr, List<String> resList, int lastI, int lastJ, char openParen, char closedParen){
        int open = 0, close = 0;
        for(int i = lastI; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) == openParen) open++;
            if (inputStr.charAt(i) == closedParen) close++;
            if (close > open) {
                for (int j = lastJ; j <= i; j++) {
                    if(inputStr.charAt(j) == closedParen && (j == lastJ || inputStr.charAt(j-1) != closedParen)){
                        dfsRemove(inputStr.substring(0,j)+inputStr.substring(j+1),resList,i,j,openParen,closedParen);
                    }
                }
                return;
            }

        }
        String reversed = new StringBuilder(inputStr).reverse().toString();
        if (openParen == '(') {
            dfsRemove(reversed, resList, 0,0, closedParen, openParen);
        } else {
            resList.add(reversed);
        }
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
    }

}
