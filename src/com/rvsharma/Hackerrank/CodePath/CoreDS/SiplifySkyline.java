package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SiplifySkyline {

    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>(), height = new ArrayList<>();
        for(int[] building : buildings){
            height.add(new int[]{building[0], -building[2]});
            height.add(new int[]{building[1], building[2]});
        }

        height.sort((a1, a2) -> a1[0] != a2[0] ? a1[0] - a2[0] : a1[1] - a2[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
        pq.offer(0);
        int prev = 0;
        for(int[] h : height){
            if(h[1] < 0)
                pq.offer(-h[1]);
            else
                pq.remove(h[1]);
            int curr = pq.peek();
            if(prev != curr){
                result.add(new int[]{h[0], curr});
                prev = curr;
            }
        }
        return result;
    }
}
