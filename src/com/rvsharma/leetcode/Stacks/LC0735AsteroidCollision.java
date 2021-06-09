package com.rvsharma.leetcode.Stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LC0735AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids){
        if(asteroids == null || asteroids.length <= 1)
            return asteroids;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < asteroids.length; i++){
            if(asteroids[i] > 0)
                stack.addLast(asteroids[i]);
            else {
                while (!stack.isEmpty() && stack.peekLast() > 0 && Math.abs(asteroids[i]) > stack.peekLast()){
                    stack.removeLast();
                }
                if(stack.isEmpty() || stack.peekLast() < 0)
                    stack.addLast(asteroids[i]);
                else if (stack.peekLast() + asteroids[i] == 0)
                    stack.removeLast();
            }
        }
        int[] res = new int[stack.size()];
        for(int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.removeLast();
        }
        return res;
    }

    public static void main(String[] args) {
        LC0735AsteroidCollision ac = new LC0735AsteroidCollision();
        int[] asteroids = new int[]{10, 2, -5};
        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
    }
}
