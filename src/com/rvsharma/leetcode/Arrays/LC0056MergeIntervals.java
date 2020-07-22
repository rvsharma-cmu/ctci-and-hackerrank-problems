package com.rvsharma.leetcode.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0056MergeIntervals {

    public static int[][] merge(int[][] intervals) {

        if(intervals.length <= 1)
            return intervals;

        // first sort the intervals array by their start times
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        List<int[]> result = new ArrayList<>();
        int[] curr = intervals[0];
        result.add(curr);
        for(int[] interval : intervals){
            if(curr[1] >= interval[0]){
                curr[1] = Math.max(interval[1], curr[1]);
            } else {
                curr = interval;
                result.add(curr);
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(merge(intervals));
    }
}
