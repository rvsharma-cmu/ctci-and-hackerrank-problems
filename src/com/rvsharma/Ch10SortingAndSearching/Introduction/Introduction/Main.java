package com.rvsharma.Ch10SortingAndSearching.Introduction.Introduction;

import com.rvsharma.Ch10SortingAndSearching.Introduction.AssortedMethods;

public class Main {

    public static void main(String[] args) {
        int size = 20;
        int[] array = AssortedMethods.randomArray(size, 0, size - 1);
        int[] validate = new int[size];
        AssortedMethods.printIntArray(array);
        for (int i = 0; i < size; i++) {
            validate[array[i]]++;
        }
        MergeSort.mergeSort(array);
        for (int i = 0; i < size; i++) {
            validate[array[i]]--;
        }
        AssortedMethods.printIntArray(array);
        for (int i = 0; i < size; i++) {
            if (validate[i] != 0 || (i < (size-1) && array[i] > array[i+1])) {
                System.out.println("ERROR");
            }
        }
    }
}
