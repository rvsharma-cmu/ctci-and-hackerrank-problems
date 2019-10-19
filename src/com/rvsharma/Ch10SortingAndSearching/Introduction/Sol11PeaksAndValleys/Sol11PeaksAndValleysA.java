package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol11PeaksAndValleys;

import java.util.Arrays;

public class Sol11PeaksAndValleysA {

    static void sortToPeaksAndValleys(int[] array){

        Arrays.sort(array);

        for(int i = 1; i < array.length; i += 2){
            swap(array, i - 1, i);
        }
    }

    static void swap(int[] array, int prev, int curr) {

        int temp = array[curr];
        array[curr] = array[prev];
        array[prev] = temp;
    }
}
