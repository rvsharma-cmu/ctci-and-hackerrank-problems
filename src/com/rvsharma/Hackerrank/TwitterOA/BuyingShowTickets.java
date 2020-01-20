package com.rvsharma.Hackerrank.TwitterOA;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class BuyingShowTickets {

    /*
     * Complete the 'waitingTime' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY tickets
     *  2. INTEGER p
     */

    public static long waitingTime(List<Integer> tickets, int p) {
        // Write your code here

        long waitingTimeP = 0;
        int ticketsToBuy = tickets.get(p);
        int i = 0;
        while (i < tickets.size()) {

            if (i > p) waitingTimeP += Math.min(tickets.get(i), ticketsToBuy - 1);
            else waitingTimeP += Math.min(tickets.get(i), ticketsToBuy);

            i++;
        }
        return waitingTimeP;
    }

}

class ShowTicketsMainDriver {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int ticketsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> tickets = IntStream.range(0, ticketsCount).mapToObj(i -> {
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

        long result = BuyingShowTickets.waitingTime(tickets, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
