package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol01SortedMerge;

import com.rvsharma.Ch10SortingAndSearching.Introduction.AssortedMethods;

public class Sol05SortedMerge {

    private static void twoArraysMerge(int[] a, int[] b, int countA, int countB, int[] result) {

        int indexA = 0, indexB = 0, indexC = 0;

        while(indexA < countA && indexB < countB){

            if(a[indexA] < b[indexB]) {
                result[indexC] = a[indexA];
                indexA++;
            } else {
                result[indexC] = b[indexB];
                indexB++;
            }
            indexC++;
        }

        while(indexA < countA){
            result[indexC] = a[indexA];
            indexA++;
            indexC++;
        }
        while (indexB < countB) {
            result[indexC] = b[indexB];
            indexB++;
            indexC++;
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 5, 6, 8, 10, 100};
        int[] b = {1, 4, 7, 6, 7, 7};
        int[] c = new int[a.length + b.length];
        twoArraysMerge(a, b, a.length, b.length, c);
        System.out.println(AssortedMethods.arrayToString(c));
    }
}
