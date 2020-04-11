package com.rvsharma.leetcode.Arrays;

import java.util.Stack;

public class LC042TrappingRainWater {

    public int rainWaterBrute(int[] heights) {
        int answer = 0;

        int size = heights.length;

        for(int i = 0; i < size; i++){
            int maxLeft = 0, maxRight = 0;
            for(int j = i; j >= 0; j--){
                maxLeft = Math.max(maxLeft, heights[j]);
            }
            for(int j = i; j < size; j++){
                maxRight = Math.max(maxRight, heights[j]);
            }
            answer += Math.min(maxLeft, maxRight) - heights[i];
        }
        return answer;
    }

    /*
    Stack implementation of
     */
    public int rainWaterStack(int[] height){

        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        for(int i = 0; i < n; i++){

            while(!stack.isEmpty() && height[i] > height[stack.peek()]) {

                int top = stack.pop();
                if(stack.isEmpty()) break;

                int distance = i - stack.peek() - 1;
                int bounds = Math.min(height[i], height[stack.peek()]) - height[top];
                answer += bounds * distance;
            }

            stack.push(i);
        }

        return answer;
    }

    /*
        Two pointers - One from the left end and one from the right end.
        We decide which pointer to move on basis of the square that is larger than
        the other.
     */
    public int rainWaterTwoPointer(int[] height){

        int maxLeft = 0, maxRight = 0;
        int ans = 0;
        int left = 0, right = height.length - 1;
        while(left < right) {

            if(maxLeft < height[left]) maxLeft = height[left];
            if(maxRight < height[right]) maxRight = height[right];

            if (maxLeft < maxRight) {
                ans += Math.max(0, maxLeft - height[left]);
                left++;
            } else {
                ans += Math.max(0, maxRight - height[right]);
                right--;
            }
        }
        return ans;
    }
}
