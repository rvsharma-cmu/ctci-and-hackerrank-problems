package com.rvsharma.Ch10SortingAndSearching.Introduction.Sol02GroupAnagrams;

public class sol02Driver {

    public static void main(String[] args){

        String[] strs = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};

        sol02GroupAnagramsB.groupAnagrams(strs);

        for(String str: strs){

            System.out.println(str);
        }
    }
}
