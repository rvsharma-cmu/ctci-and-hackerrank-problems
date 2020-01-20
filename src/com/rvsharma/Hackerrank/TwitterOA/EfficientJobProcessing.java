package com.rvsharma.Hackerrank.TwitterOA;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class EfficientJobProcessing {

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
        int i, j;
        int n = tasks.size();
        int[][] dp = new int[n+1][p+1];
        List<Integer> procTime = tasks.stream().mapToInt(x -> x).mapToObj(x -> 2 * x).collect(toList());
        for(i = 0; i <= n; i++) {
            for(j = 0; j <= p; j++) {

                if (i != 0 && j != 0) {
                    if (procTime.get(i - 1) > j) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = Math.max(weights.get(i-1) + dp[i-1][j - procTime.get(i-1)], dp[i-1][j]);
                    }
                } else {
                    dp[i][j] = 0;

                }
            }
        }
        return dp[n][p];
    }

}

class JobProcessingSolution {
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

        int result = EfficientJobProcessing.maximumTotalWeight(weights, tasks, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

