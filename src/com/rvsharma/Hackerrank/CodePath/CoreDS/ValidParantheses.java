package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParantheses {
    public static boolean validateParentheses(String input){
        if(input == null) return false;
        if(input.length() == 0) return true;

        Deque<Character> stack = new LinkedList<>();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (c == '(') stack.push(')');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
            i++;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(validateParentheses("{{}"));
    }
}
