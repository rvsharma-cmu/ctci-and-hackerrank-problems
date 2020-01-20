package com.rvsharma.leetcode.Graphs;

import java.util.Stack;

public class BracketBalance {

    public boolean Solution(String s) {

        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()) {

            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (c == '}') {
                if (bracketBalanced(stack, '{')) return false;
                stack.pop();
            } else if (c == ']') {
                if (bracketBalanced(stack, '[')) return false;
                stack.pop();
            } else if (c == ')') {
                if (bracketBalanced(stack, '(')) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private boolean bracketBalanced(Stack<Character> stack, char c2) {
        return stack.isEmpty() || stack.peek() != c2;
    }
}
