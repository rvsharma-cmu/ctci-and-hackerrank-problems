package com.rvsharma.Hackerrank.TwitterOA;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class EfficientJobProcessingOptimized {

    /*
     * Complete the 'maximumTotalWeight' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY weights
     *  2. INTEGER_ARRAY tasks
     *  3. INTEGER p
     */

    public static int maximumTotalWeight(List<Integer> weights, List<Integer> tasks, int p) {
        int n = tasks.size();
        int[] dp = new int[p+1];
        List<Integer> procTime = tasks.stream().mapToInt(x -> x).mapToObj(x -> 2 * x).collect(toList());
        for(int i = 0; i < n; i++) {
            for(int j = p; j >= procTime.get(i); j--) {
                dp[j] = Math.max(dp[j], weights.get(i) + dp[j - procTime.get(i)]);
            }
        }
        return dp[p];
    }

}

class JobProcessingOptimizedSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int weightsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> weights = IntStream.range(0, weightsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int tasksCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> tasks = IntStream.range(0, tasksCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int result = EfficientJobProcessingOptimized.maximumTotalWeight(weights, tasks, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

