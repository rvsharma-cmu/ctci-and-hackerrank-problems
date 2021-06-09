package com.rvsharma.leetcode.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given k sorted arrays of possibly different sizes, merge them and print the sorted output.
 *
 * Examples:
 *
 * Input: k = 3
 *       arr[][] = { {1, 3},
 *                   {2, 4, 6},
 *                   {0, 9, 10, 11}} ;
 * Output: 0 1 2 3 4 6 9 10 11
 *
 * Input: k = 2
 *       arr[][] = { {1, 3, 20},
 *                   {2, 4, 6}} ;
 * Output: 1 2 3 4 6 20
 *
 */
public class MergeKSortedArrays {
    /*
        Solution involves use of a priority Queue similar to the one used when merging k sorted lists
        1. Create an output array.
        2. Create a min heap of size k and insert 1st element in all the arrays into the heap
        3. Repeat following steps while priority queue is not empty.
            …..a) Remove minimum element from heap (minimum is always at root) and store it in output array.
            …..b) Insert next element from the array from which the element is extracted.
                    If the array doesn’t have any more elements, then do nothing.
     */
    public static List<Integer> mergeKArrays(List<int[]> input) {
        int k = input.size();
        List<Integer> output = new ArrayList<>();
        // each PQ element = [value, row, idx]
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        // insert 1st element in all the arrays into the heap
        for(int i = 0; i < k; i++) {
            if (input.get(i) != null && input.get(i).length != 0)
                pq.offer(new int[]{input.get(i)[0], i, 0});
        }
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            output.add(curr[0]);
            if (curr[2] != input.get(curr[1]).length - 1)
                pq.offer(new int[]{input.get(curr[1])[curr[2]+1], curr[1], curr[2] + 1});
        }
        return output;
    }

    public static void main(String[] args) {
        List<int[]> input = new ArrayList<>();
        input.add(new int[]{2, 6, 12});
        input.add(new int[]{1, 9});
        input.add(new int[]{23, 34, 90, 2000});
        System.out.println(mergeKArrays(input));
    }
}
