package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol11PeaksAndValleys;

public class Sol11PeaksAndValleysB {

    public static void sortToPeaksAndValleys(int[] arr){

        for(int i = 1; i < arr.length; i += 2){

            int index = maxIndex(arr, i - 1, i, i + 1);
            if(i != index){
                Sol11PeaksAndValleysA.swap(arr, i, index);
            }
        }
    }

    private static int maxIndex(int[] arr, int a, int b, int c) {
        int arrayLength = arr.length;

        int prevIdx = a >=0 && a < arrayLength ? arr[a] : Integer.MIN_VALUE;
        int currIdx = b >=0 && b < arrayLength ? arr[b] : Integer.MIN_VALUE;
        int nextIdx = c >=0 && c < arrayLength ? arr[c] : Integer.MIN_VALUE;

        int maxIndex = Math.max(prevIdx, Math.max(currIdx, nextIdx));

        if(maxIndex == prevIdx) return a;
        else if(maxIndex == currIdx) return b;
        else return c;
    }
}
