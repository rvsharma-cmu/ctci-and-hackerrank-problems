package com.rvsharma.leetcode;


import java.util.*;

public class Solution {

    static class TreeNodeP {

        long val;
        TreeNodeP left, right;
        TreeNodeP(long val){

            this.val =val;
        }
    }
    public static long solution(long[] tree) {
        // Type your solution here
        Deque<TreeNodeP> q = new ArrayDeque<>();
        TreeNodeP root = new TreeNodeP(tree[0]);
        q.addLast(root);
        for(int i = 1; i < tree.length; i++) {
            TreeNodeP parent = q.removeFirst();
            if(tree[i] != -1){
                TreeNodeP left = new TreeNodeP(tree[i]);
                parent.left = left;
                q.addLast(left);
            }
            if((i+1) < tree.length && tree[++i] != -1){
                TreeNodeP right = new TreeNodeP(tree[i]);
                parent.right = right;
                q.addLast(right);
            }
        }

        long res = calc_height(root);
        return res;
    }

    static long calc_height(TreeNodeP root){
        if(root == null) return 0;
        return 1 + Math.max(calc_height(root.left), calc_height(root.right));
    }


    public static boolean maze(long[][] maze, long n) {
        // Type your solution here
        int cols = maze[0].length;
        long[] dp = new long[cols];
        dp[0] = 1;
        for(long[] row : maze) {
            for(int j = 0; j < cols; j++){

                if(row[j] == 1) {
                    dp[j] = 0;
                } else if (j > 0){
                    dp[j] += dp[j-1];
                }
            }
        }
        return dp[cols - 1] != 0;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new long[]{1,2,3,4,-1,-1,-1}));
//        System.out.println(maze(new long[][]{{0,0,1},{1,0,0},{1,1,0}}, 12));
        Integer[] arr = {0,0,3,2,4,1};
        System.out.println(marioJump(Arrays.asList(arr)));
    }



    public static int marioJump(List<Integer> mission) {
        int end = mission.size() - 1;
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < mission.size() - 1; i++) {
            for (int j = i + 1; j < mission.size(); j++) {
                if (mission.get(i) != 0 && mission.get(j) != 0) {
                    list.add(new int[]{(j - i) * (j - i) * mission.get(j) * mission.get(i), i, j});
                }
            }
        }
        list.sort((a, b) -> b[0] - a[0]);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int[] curr = list.get(i);
            int loop = curr[0];
            int stop = curr[2];
            for (int j = i + 1; j < list.size(); j++) {
                int nextStart = list.get(j)[1];
                int nextStop = list.get(j)[2];
                if (stop == nextStart) {
                    loop += list.get(j)[0];
                    stop = nextStop;
                    if (nextStop == end) break;
                }
            }
            max = Math.max(max, loop);
        }

        return max;
    }


}


