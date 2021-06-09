package com.rvsharma.Hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryAutocomplete {

    static String commonPrefix(String[] arr, int n) {
        String longestPrefix = "";
        for (int i = 0; i < n; i++) {
            int commonLen = Math.min(arr[i].length(), arr[n].length());
            int len = 0;
            while (len < commonLen) {
                if (arr[i].charAt(len) != arr[n].charAt(len)) {
                    break;
                }
                len++;
            }
            String currentPrefix = arr[i].substring(0, len);
            if (currentPrefix.length() > longestPrefix.length()) {
                longestPrefix = currentPrefix;
            }
        }
        return longestPrefix;
    }


    static int search(String[] arr, String pref, int n) {
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i].startsWith(pref)) {
                return i + 1;
            }
        }
        return 0;
    }

//    public static List<Integer> autocomplete(List<String> command){
//        int[] output = new int[command.size()];
//        String[] commands = command.toArray(new String[0]);
//        for (int i = 0; i < command.size(); i++) {
//            String pref = commonPrefix(commands, i);
//            output[i] = search(commands, pref, i);
//        }
//        return Arrays.stream(output).boxed().collect(Collectors.toList());
//    }

    public static void main(String[] args) {
        String[] words = { "000", "1110", "01", "001", "110", "11" };
        List<String> command = Arrays.stream(words).collect(Collectors.toList());
        List<Integer> autocomplete = autocomplete(command);
        System.out.println(autocomplete);

    }

    private static List<Integer> autocomplete(List<String> words) {
        int[] result = new int[words.size()];
        final int firstWordLength = words.size() > 0 ? words.get(0).length() : 8;
        // prefix -> [indexes of prefix occurrence]
        HashMap<String, TreeSet<Integer>> prefixes = new HashMap<>(words.size() * (firstWordLength + 1) * 2);
        int wordLength = 1;
        boolean isUpdatedResult;
        do { // O(k)
            isUpdatedResult = false;
            for (int wordIdx = 0; wordIdx < words.size(); wordIdx++) { // O(n)
                if (words.get(wordIdx).length() < wordLength) {
                    continue;
                }
                final String currentPrefix = words.get(wordIdx).substring(0, wordLength); // Java >= 7 update 6 ? O(k) : O(1)
                final TreeSet<Integer> prefixIndexes = prefixes.get(currentPrefix); // O(1)
                if (prefixIndexes != null) {
                    // floor instead of lower, because we have put `wordIdx + 1` inside
                    final Integer closestPrefixIndex = prefixIndexes.floor(wordIdx); // O(log n)
                    if (closestPrefixIndex != null) {
                        result[wordIdx] = closestPrefixIndex;
                        isUpdatedResult = true;
                    }
                }
                // take the previous index for the result if no match
                if (result[wordIdx] == 0) {
                    result[wordIdx] = wordIdx;
                }
                final TreeSet<Integer> newPrefixIndexes = prefixes.computeIfAbsent(currentPrefix, p -> new TreeSet<>()); // O(1)
                // the result index must be + 1
                newPrefixIndexes.add(wordIdx + 1); // O(log n)
            }
            wordLength++;
        } while (isUpdatedResult);
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }


}
