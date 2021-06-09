package com.rvsharma.leetcode.Heap;

import java.util.*;

public class LC0295MedianFromDataStream {
    public static List<List<Integer>> getMedian(int[] nums){
        int sum = 0;
        for (int x : nums){
//            balance(x);
            sum += x;
        }

        List<List<Integer>> res = new ArrayList<>();
        int weight = sum / 3;
        Map<Integer, Integer> listSum = new HashMap<>();
        for (int i = 0; i< 3; i++){
            listSum.put(i, 0);
        }

        for (int i = 0; i < nums.length; i++){
            
        }

        for (int i = 0 ; i < 3; i++){
            List<Integer> temp = new ArrayList<>();
            int acc = 0;
            while (!low.isEmpty() && !high.isEmpty()){
                if (low.peek() + acc > weight) break;
                int l = low.poll();
                temp.add(l);
                acc += l;
                if(high.peek() + acc > weight) break;
                int h = high.poll();
                acc += h;
                temp.add(h);
            }
            if(low.isEmpty()){
                while(!high.isEmpty()){
                    temp.add(high.poll());
                }
            }
            if(high.isEmpty()){
                while (!low.isEmpty()){
                    temp.add(low.poll());
                }
            }
            res.add(temp);
        }

        return res;
    }

    static PriorityQueue<Integer> low = new PriorityQueue<>(1, (a,b) -> Integer.compare(a,b));
    static PriorityQueue<Integer> high = new PriorityQueue<>(1, (a,b) -> b - a);

    public static void balance(int x){
        low.offer(x);

        high.offer(low.remove());
        if(high.size() > low.size()){
            low.offer(high.remove());
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{100, 2, 4, 5, 12, 20};
//        System.out.println(getMedian(nums));
        MedianFinder sol = new MedianFinder();
        sol.addNum(100);
        sol.addNum(2);
        sol.addNum(4);
        sol.addNum(5);
        sol.addNum(12);
        sol.addNum(20);
        sol.addNum(3);
        sol.addNum(8);
        System.out.println(sol.findMedian());
    }
}

/**
 * This finds the median in a number stream. It keeps the list sorted during the insertion of the number
 * and provides retrieval of the median in O(1) time. When adding a number to the stream, it will find the
 * index of the insertion using Binary Search (O(log N)), and insert it into the list (Worst case O(N)).
 */
class MedianFinder {

    List<Integer> list;
    /** initialize your data structure here. */
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int indexToInsert;
        if(list.size() == 0)
            list.add(num);
        else {
            indexToInsert = binarySearch(list, 0, list.size() - 1, num);
            list.add(indexToInsert, num);
        }
    }

    public int binarySearch(List<Integer> list, int start, int end, int value){
        int mid = (start+end)/2;
        if(list.get(mid) == value){
            return mid;
        }
        if(start > end){
            return start;
        }
        if(list.get(mid) > value){
            return binarySearch(list, start, mid-1, value);
        } else {
            return binarySearch(list, mid+1, end, value);
        }
    }

    public double findMedian() {
        double median = 0.0;
        int size = list.size();
        if(size % 2 != 0) { // odd size
            median = list.get(size / 2);
        } else {
            median = (list.get(size/2) + list.get(size/2 - 1)) / 2.0;
        }
        return median;
    }
}

