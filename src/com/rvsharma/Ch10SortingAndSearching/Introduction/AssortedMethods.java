package com.rvsharma.Ch10SortingAndSearching.Introduction;

import java.util.Arrays;
import java.util.Random;

public class AssortedMethods {
    private static Random random = new Random();

    public static int[] randomArray(int size, int low, int high) {

        int[] result = new int[size];
        if(size == 0)
            return result;
        for(int i = low; i < high; i++){
            result[i] = random.nextInt(size);
        }
        return result;
    }

    public static void printIntArray(int[] array) {
        for(int i : array){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static String arrayToString(int[] a) {
        return Arrays.toString(a);
    }
}
