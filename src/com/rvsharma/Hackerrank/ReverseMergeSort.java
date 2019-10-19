package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ReverseMergeSort {

    /*
     * Complete the function below.
     */

    static int[] MergeSort(int[] values) {

        int[] traverseArray = new int[values.length];
        mergeSortHelper(values, traverseArray, 0, values.length - 1);
        System.out.println(Arrays.toString(values));
        reverseArray(values);
        return values;
    }

    private static void reverseArray(int[] values) {

        int last = values.length - 1;
        int first = 0;

        while (last > first) {
            int temp = values[first];
            values[first] = values[last];
            values[last] = temp;
            last--;
            first++;
        }
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

            if(traverseArray[left] <= traverseArray[right]){
                values[currentIndex] = traverseArray[left];
                left++;
            } else {
                values[currentIndex] = traverseArray[right];
                right++;
            }
            currentIndex++;
        }
        int remaining = middle - left;
        for(int i = 0; i <= remaining; i++){
            values[currentIndex+i] = traverseArray[left+i];
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        int[] res;

        int _values_size = Integer.parseInt(in.nextLine().trim());
        int[] _values = new int[_values_size];
        for(int _values_i = 0; _values_i < _values_size; _values_i++) {
            int _values_item = Integer.parseInt(in.nextLine().trim());
            _values[_values_i] = _values_item;
        }

        res = MergeSort(_values);
        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.println(res[res_i]);
        }
        System.out.println("DONE");
    }
}
