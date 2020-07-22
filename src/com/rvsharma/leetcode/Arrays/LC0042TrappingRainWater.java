package com.rvsharma.leetcode.Arrays;

import java.util.Stack;

public class LC0042TrappingRainWater {

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

            if (height[left] < height[right]) {
                ans += Math.max(0, maxLeft - height[left]);
                left++;
            } else {
                ans += Math.max(0, maxRight - height[right]);
                right--;
            }
        }
        return ans;
    }

    public int rainWaterDP(int[] height) {
        int n = height.length, ans = 0;
        int[] leftMax = new int[n], rightMax = new int[n];

        leftMax[0] = height[0];
        for(int i = 1; i < n; i++){
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }

        rightMax[n-1] = height[n-1];
        for(int i = n-2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        for(int i = 0; i < n; i++){
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        LC0042TrappingRainWater trappingRainWater = new LC0042TrappingRainWater();
        System.out.println(trappingRainWater.rainWaterTwoPointer(heights));
    }
}
