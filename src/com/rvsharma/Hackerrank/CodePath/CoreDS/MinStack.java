package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack(){
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x){
        stack.addLast(x);
        if(!minStack.isEmpty()){
            if (x <= minStack.getLast()) {
                minStack.addLast(x);
            }
        } else {
            minStack.addLast(x);
        }
    }

    public int pop() {
        int x = stack.removeLast();
        if (!minStack.isEmpty() && x == minStack.getLast()){
            minStack.removeLast();
        }
        return x;
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.getLast();
    }

    public int getMin() {
        return minStack.isEmpty() ? -1 : minStack.getLast();
    }

    public static void main(String[] args) {
        MinStack st = new MinStack();
        st.push(-2);
        st.push(0);
        st.push(-3);
        System.out.println(st.getMin());
        st.pop();
        System.out.println(st.top());
        System.out.println(st.getMin());
    }
}
