package com.rvsharma.leetcode.Stacks;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC0224BasicCalculator {

    /**
     * Subtraction is neither associative nor commutative.
     *
     * This problem could be solved very easily by reversing the string and then using basic drill using a stack.
     * Reversing a string helps since we now put the elements of the expression into the stack from right to left
     * and evaluation for the expression is done correctly from left to right.
     * @param s input string / expression
     * @return evaluation of the expression
     */
    public static int calculate(String s){
        int operand = 0;
        Deque<String> st = new ArrayDeque<>();
        int pow = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                operand += Math.pow(10, pow) * (c - '0');
                pow++;
            } else if(c != ' '){
                if(pow != 0){
                    st.addLast(Integer.toString(operand));
                    pow = 0;
                    operand = 0;
                }
                if(c == '('){
                    int x = 0;
                    if(!st.isEmpty()){
                        x = Integer.parseInt(st.removeLast());
                    }
                    while(!st.isEmpty() && !st.peekLast().equals(")")){
                        x = st.removeLast().equals("+") ? x + Integer.parseInt(st.removeLast()) : x - Integer.parseInt(st.removeLast());
                    }
                    if(!st.isEmpty()) st.removeLast();
                    st.addLast(Integer.toString(x));
                } else {
                    st.addLast(c + "");
                }
            }
        }

        if(pow != 0)
            st.addLast(Integer.toString(operand));

        int res = 0;
        if(!st.isEmpty()){
            res = Integer.parseInt(st.removeLast());
        }
        while(!st.isEmpty() && !st.peekLast().equals("(")){
            res = st.removeLast().equals("+") ? res + Integer.parseInt(st.removeLast()) : res - Integer.parseInt(st.removeLast());
        }

        return res;
    }

    public static void main(String[] args) {
        String exp = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(exp));
    }
}
