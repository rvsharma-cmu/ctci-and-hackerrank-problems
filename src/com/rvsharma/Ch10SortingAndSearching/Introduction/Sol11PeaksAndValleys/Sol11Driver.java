package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol11PeaksAndValleys;

import com.rvsharma.Ch10SortingAndSearching.Introduction.AssortedMethods;

import java.util.Arrays;
import java.util.Timer;

public class Sol11Driver {

    public static void main(String[] args) {
        int[] array = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        System.out.println(AssortedMethods.arrayToString(array));
        Sol11PeaksAndValleysC.sortToPeaksandValleys(array);
        System.out.println(AssortedMethods.arrayToString(array));
    }
}
