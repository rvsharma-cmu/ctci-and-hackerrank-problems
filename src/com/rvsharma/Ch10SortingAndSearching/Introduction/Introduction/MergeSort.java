package com.rvsharma.Ch10SortingAndSearching.Introduction.Introduction;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] array){
        int[] helper = new int[array.length];
        mergeSortHelper(array, helper, 0, array.length - 1);
    }

    private static void mergeSortHelper(int[] array, int[] helper, int low, int high) {
        if(low < high) {

            int middle = (low + high) / 2;
            mergeSortHelper(array, helper, low, middle);
            mergeSortHelper(array, helper, middle+1, high);
            merge(array, helper, low, middle, high);
        }
    }

    private static void merge(int[] array, int[] helper, int low, int middle, int high) {

        // Copy the elements to a helper array
        for(int i = low; i <= high; i++){
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /*
            Iterate through helper array comparing the left and right half of the array and copying back the smaller
            element from the two halves into the original array.
         */
        while(helperLeft <= middle && helperRight <= high){
            if(helper[helperLeft] <= helper[helperRight]){
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        // copy the remaining of the left array to the result array
        int remaining = middle - helperLeft;
        for(int i = 0; i <= remaining; i++){
            array[current + i] = helper[helperLeft + i];
        }
    }

    public static void main(String[] args){
        int[] arr = {1, 4, 5, 2, 8, 9};

        System.out.println("Input array is " + Arrays.toString(arr));
        mergeSort(arr);

        // print the array
        System.out.print("Sorted array is " + Arrays.toString(arr));

    }
}
