package com.rvsharma.Hackerrank.TwitterOA;


import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


public class FountainsNeeded {

    /*
     * Complete the 'fountainActivation' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int fountainActivation(List<Integer> a) {
        // Write your code here
        int size = a.size();
        int[] fountainReach = new int[size];
        for(int i = 0; i < size; i++) {
            int left = Math.max(i+1-a.get(i), 1);
            int right = Math.min(i+1+a.get(i), size);
            fountainReach[left - 1] = Math.max(fountainReach[left - 1], right);
        }

        int nextRight = fountainReach[0];
        int right = nextRight;
        int output = 1;

        for(int i = 1; i < size; i++) {

            nextRight = Math.max(nextRight, fountainReach[i]);
            if(i == right) {
                output++;
                right = nextRight;
            }
        }
        return output;
    }

}
class FountainsNeededMain {
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

        int result = FountainsNeeded.fountainActivation(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
