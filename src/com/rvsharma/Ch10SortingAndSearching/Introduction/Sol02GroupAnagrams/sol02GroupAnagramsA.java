package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol02GroupAnagrams;

import java.util.Arrays;
import java.util.Comparator;

public class sol02GroupAnagramsA {

    static class AnagramComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return sortChars(s1).compareTo(sortChars(s2));
        }

        private String sortChars(String s) {
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            return Arrays.toString(charArr);
        }
    }

    static void groupAnagrams(String[] arr){

        Arrays.sort(arr, new AnagramComparator());
    }
}
