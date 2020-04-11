package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class SlidingPuzzle {

    /*
     * Complete the 'movesToSolve' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY puzzle as parameter.
     */
    static int[] dir = {0, 1, 0, -1, 0};
    public static int movesToSolve(List<List<Integer>> puzzle) {
        // Write your code here
        /*
         * find the number of rows and columns
         */
        int row = puzzle.size();
        int col = puzzle.get(0).size();

        /*
         * build the reference output
         * the reference output would be all integers
         * from 0 to m*n-1
         */
        StringBuilder builder = new StringBuilder(), builder1 = new StringBuilder();
        String output = "";
        for(int i = 0; i < row*col; i++){
            builder.append(i);
        }
        output = builder.toString();
        /*
         * convert the input to a string
         */
        String input = "";
        for(List<Integer> eRow : puzzle) {
            for(int eachNum : eRow){
                builder1.append(eachNum);
            }
        }

        input = builder1.toString();

        /*
         * run a Breadth-First search by first adding the input list
         * as the first node in the queue.
         */
        Queue<String> queue = new LinkedList<>(Collections.singletonList(input));

        /*
            visited HashSet to verify if you have already seen this string
            combination
         */
        Set<String> visited = new HashSet<>(queue);

        int moves = 0;
        while (!queue.isEmpty()) {
            // loop used to control search breadth.
            for (int sz = queue.size(); sz > 0; --sz) {
                String tempString = queue.poll();
                if(tempString == null){
                    continue;
                }
                if (tempString.equals(output)) {
                    return moves;
                }
                int i = tempString.indexOf("0"), x = i / col, y = i % col;
                for (int k = 0; k < 4; ++k) {
                    int r = x + dir[k], c = y + dir[k + 1];
                    if (r >= 0 && r < row && c >= 0 && c < col) {
                        char[] ch = tempString.toCharArray();
                        ch[i] = ch[r * col + c];
                        ch[r * col + c] = '0';
                        input = String.valueOf(ch);
                        if (!visited.contains(input)) {
                            visited.add(input);
                            queue.offer(input);
                        }
                    }
                }
            }
            ++moves;
        }
        return -1;
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int puzzleRows = Integer.parseInt(bufferedReader.readLine().trim());
        int puzzleColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> puzzle = new ArrayList<>();

        IntStream.range(0, puzzleRows).forEach(i -> {
            try {
                puzzle.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = SlidingPuzzle.movesToSolve(puzzle);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
