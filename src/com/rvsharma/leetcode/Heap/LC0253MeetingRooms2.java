package com.rvsharma.leetcode.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class LC0253MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0)
            return 0;
        // sort the intervals array by the meeting starting time
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        // initialize priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // add first meeting end time in the pq
        pq.add(intervals[0][1]);
        for(int i = 1; i < intervals.length; i++){
            // if current meeting start time is more than or equal to
            // the next meeting end time then remove next meeting
            // because we can accommodate the current meeting
            if(pq.peek() != null && intervals[i][0] >= pq.peek()){
                pq.poll();
            }
            // add current meeting's end time in min-heap
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }

    static class Solution {
        public String countAndSay(int n) {
            if(n==0)
                return null;
            if(n==1)
                return "1";
            else{
                String s=countAndSay(n-1);
                StringBuilder sb=new StringBuilder();
                char[] c=s.toCharArray();
                int count=1;
                for(int i=1; i < c.length;i++){
                    if(c[i]==c[i-1])
                        count++;
                    else{
                        sb.append(count).append(c[i-1]);
                        count=1;
                    }
                }
                sb.append(count).append(c[c.length-1]);
                return sb.toString();
            }
        }
    }


    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        LC0253MeetingRooms2 sol = new LC0253MeetingRooms2();
        int output = sol.minMeetingRooms(intervals);
        System.out.println("the minimum number of meeting rooms is " + output);
        Solution countSay = new Solution();
        String say = countSay.countAndSay(6);
        System.out.println(say);

    }
}
