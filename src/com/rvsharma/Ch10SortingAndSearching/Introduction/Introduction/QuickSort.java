package com.rvsharma.Ch10SortingAndSearching.Introduction.Introduction;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr, int left, int right){

        int index = partition(arr, left, right);
        if(left < index - 1){
            quickSort(arr, left, index - 1);
        }
        if(index < right){
            quickSort(arr, index, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {

        // select the pivot as median element of the left and right
        int pivot = arr[(left + right) / 2];

        while(left <= right){

            while (arr[left] < pivot) {
                left++;
            }
            while(arr[right] > pivot) {
                right--;
            }
            if(left <= right){
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    // in place swap left and right indexes in the arr
    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args){
        int[] arr = {1, 4, 9, 2, 8, 5};

        System.out.println("Input array is " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);

        // print the array
        System.out.print("Sorted array is " + Arrays.toString(arr));

    }
}
