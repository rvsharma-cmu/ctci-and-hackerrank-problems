package com.rvsharma.leetcode.Heap;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC0973KClosestToOrigin {

    public int[][] kClosest(int[][] points, int K) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(K, (b, a) -> (a[0]*a[0] + a[1]*a[1]) - (b[0]*b[0] + b[1]*b[1]));

        for(int[] x : points){
            pq.add(new int[]{x[0], x[1]});
            if(pq.size() > K) pq.poll();
        }

        int[][] res = new int[K][2];
        for(int i = 0; i < K && !pq.isEmpty(); i++){
            int[] p = pq.poll();
            res[i][0] = p[0];
            res[i][1] = p[1];
        }
        return res;
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }


    public int[][] quickSortkClosest(int[][] points, int K){
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);

    }

    public static void main(String[] args) {
        LC0973KClosestToOrigin sol = new LC0973KClosestToOrigin();
        int[][] input = new int[][]{{3,3},{5,-1},{-2,4}};

        int[][] output = sol.quickSortkClosest(input, 2);
        System.out.println(Arrays.deepToString(output));
    }
}
