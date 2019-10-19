package com.rvsharma.Hackerrank;

import java.util.Arrays;

/**
 * Merge sort tried in reverse order
 * TODO: fix the bug, currently fails on this input array = [1, 3, 2, 7]
 * TODO: Output in this case is [7, 7, 7, 7]
 */
public class ReverseMergeSortB {

    static int[] MergeSort(int[] values) {

        int[] traverseArray = new int[values.length];
        mergeSortHelper(values, traverseArray, 0, values.length - 1);
        System.out.println(Arrays.toString(values));
        return values;
    }

    static void mergeSortHelper(int[] values, int[] traverseArray, int first, int last){
        if(first < last){
            int middle = (first + last) / 2;
            mergeSortHelper(values, traverseArray, first, middle);
            mergeSortHelper(values, traverseArray, middle + 1, last);
            mergeSortHelper(values, traverseArray, first, middle, last);
        }
    }

    static void mergeSortHelper(int[] values, int[] traverseArray, int first, int middle, int last){

        for(int i = first; i <= last; i++){
            traverseArray[i] = values[i];
        }

        int left = first;
        int right = middle + 1;
        int currentIndex = first;

        while(left <= middle && right <= last){

            if(traverseArray[right] >= traverseArray[left]){
                values[currentIndex] = traverseArray[right];
                right++;
            } else {
                values[currentIndex] = traverseArray[left];
                left++;
            }
            currentIndex++;
        }

        int remaining = last - right;
        for(int i = 0; i <= remaining; i++){
            values[currentIndex+i] = traverseArray[right+i];
        }
    }
}
