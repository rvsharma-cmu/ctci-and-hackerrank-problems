package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol11PeaksAndValleys;

public class Sol11PeaksAndValleysC {

    public static void sortToPeaksandValleys(int[] arrays){

        for(int i = 1; i < arrays.length; i += 2){

            if(arrays[i-1] < arrays[i]) {
                Sol11PeaksAndValleysA.swap(arrays, i-1, i);
            }
            if(i + 1 < arrays.length && arrays[i+1] < arrays[i]){
                Sol11PeaksAndValleysA.swap(arrays, i+1, i);
            }
        }
    }
}
