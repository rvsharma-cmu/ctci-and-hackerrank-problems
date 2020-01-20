package com.rvsharma.Hackerrank.TwitterOA;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class KDifference {

    /*
     * Complete the 'kDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER k
     */

    public static int kDifference(List<Integer> a, int k) {
        // Write your code here

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int output = 0;
        if( k < 0 ) {
            return output;
        }

        for(int num : a) {

            if(frequencyMap.containsKey(num)) {
                int freq = frequencyMap.get(num);
                if(k == 0 && freq == 1) {
                    output++;
                }
                frequencyMap.put(num, freq+1);
            } else {
                if(frequencyMap.containsKey(num + k)) {
                    output++;
                }
                if(frequencyMap.containsKey(num - k)) {
                    output++;
                }
                frequencyMap.put(num, 1);
            }
        }
        return output;
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = KDifference.kDifference(a, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

