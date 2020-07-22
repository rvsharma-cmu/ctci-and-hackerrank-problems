package com.rvsharma.Hackerrank.CodePath.CoreDS;
import sun.tools.tree.ArrayAccessExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPair {
    public static List<List<Integer>> kSmallestPair(int[] nums1, int[] nums2, int k) {
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));
        for(int num1 : nums1){
            for(int num2 : nums2){
                pq.offer(new int[]{num1, num2});
                if(pq.size() > k) {
                    pq.poll();
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(temp[0]);
            list.add(temp[1]);
            result.add(list);
        }
        return result;
    }

    /**
     * See <a href = "https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation"> LeetCode 373 Discuss</a>
     * Given two sorted arrays, finds the k smallest pair sums
     * @param nums1 First Array
     * @param nums2 Second Array
     * @param k smallest pair of numbers
     * @return List of pair of integers summing up to the smallest sum
     */
    public static List<List<Integer>> kSmallestPairII(int[] nums1, int[] nums2, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        // since arrays are sorted, the minimum sum will always be nums1[i] + nums2[0] or nums2[i] + nums1[0]
        // so to start with we add nums1[i] + nums2[0] till nums1.length and k whichever's smaller
        for(int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        List<List<Integer>> result = new ArrayList<>();
        while(k > 0 && !pq.isEmpty()) {
            int[] curr = pq.poll();
            result.add(new ArrayList<>(Arrays.asList(curr[0], curr[1])));
            // also check for other indexes in nums2
            if(curr[2] == nums2.length - 1) continue;
            pq.offer(new int[]{curr[0], nums2[curr[2] + 1], curr[2] + 1});
            k--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11, 16};
        int[] nums2 = {2, 9, 10, 15};
        System.out.println(kSmallestPairII(nums1, nums2, 4));
    }
}
