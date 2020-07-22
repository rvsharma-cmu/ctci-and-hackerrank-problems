package com.rvsharma.Hackerrank.CodePath.CoreDS;

import sun.java2d.pipe.SpanClipRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeap {
    private List<Integer> heapList;
    public MinHeap(){
        heapList = new ArrayList<>();
    }
    public void insert(int val){
        heapList.add(val);
        bubbleUp(heapList.size() - 1);
    }

    public void bubbleUp(int currIdx) {
        int parentIdx = (currIdx - 1) / 2;
        while (parentIdx >= 0) {
            int curr = heapList.get(currIdx);
            int parent = heapList.get(parentIdx);
            if (curr < parent) {
                heapList.set(parentIdx, curr);
                heapList.set(currIdx, parent);
                currIdx = parentIdx;
                parentIdx = (parentIdx - 1) / 2;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(4);
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(5);
        System.out.println(minHeap.heapList);
    }
}
