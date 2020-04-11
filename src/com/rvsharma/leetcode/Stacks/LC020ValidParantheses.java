package com.rvsharma.leetcode.Stacks;

import java.util.Stack;

/**
 * If closed parantheses found; pop from stack and verify if the values are matching
 * the closed parantheses, if not push to stack; at the end if stack is empty then we have
 * a valid expression; otherwise not
 */
public class LC020ValidParantheses {

    /**
     * method that simply pushes brackets into the stack if it finds and opening
     * bracket; else if the stack is not empty and it encountered a closing bracket;
     * pops the stack and checks the validity of that expression by comparing if it found
     * a matching opening pair or not; and returns or continues on basis of that
     * @param s string to evaluate
     * @return true if the expression is valid; false otherwise
     */
    public boolean ValidExpression(String s) {

        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){

            if(stack.isEmpty() && (c == ']' || c == ')' || c == '}')) {
                Character popChar = stack.pop();
                switch (c){
                    case ']':
                        if(popChar != '['){
                            return false;
                        }
                        break;
                    case '}':
                        if(popChar != '{'){
                            return false;
                        }
                        break;
                    case ')':
                        if(popChar != '(') {
                            return false;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    /**
     * this method is a smart implementation of the algorithm,
     * in which if a opening bracket is found, it will push its closing pair;
     * if a closing bracket is found, it will pop from stack if its not empty and match it with
     * the closing bracket; if they are not equal it will return false, else it will return true.
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(Character c : s.toCharArray()) {
            if(c == '['){
                st.push(']');
            } else if (c == '{') {
                st.push('}');
            } else if (c == '(') {
                st.push(')');
            } else if(st.isEmpty() || st.pop() != c){
                    return false;
            }
        }
        return st.isEmpty();
    }
}
