package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CoinCollector {

    static int M_rows;
    static int N_cols;
    static int endRow;
    static int endCol;

    public static int[] collector(boolean[][] grid, int C) {

        int[] resultArr = new int[grid[0].length];
        if (grid[0].length == 0){
            return resultArr;
        }
        M_rows = grid.length;
        N_cols = grid[0].length;
        int[][] memo = new int[M_rows][N_cols];
        for(int[] arr : memo){
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
        for(int i = 0; i < N_cols; i++){
            endRow = M_rows-1;
            endCol = i;
            resultArr[i] = collectorHelper(grid, 0, 0, memo) + C;
        }
        return resultArr;
    }

    private static int collectorHelper(boolean[][] grid, int row, int col, int[][] memo) {

        if(!isValidCell(row, col) || (row == endRow && col == endCol)){
            if(row == endRow){
                if(grid[endRow][endCol]) {
                    return 1;
                }else {
                    return 0;
                }
            }
            return -1;
        }

        if(memo[row][col] != Integer.MIN_VALUE)
            return memo[row][col];

        memo[row][col] = (grid[row][col]) ? 1 : -1;

        int intermediateVal = Math.max(collectorHelper(grid, row, col - 1, memo),
                collectorHelper(grid, row + 1, col, memo));
        memo[row][col] += Math.max(collectorHelper(grid, row, col + 1, memo), intermediateVal);

        return memo[row][col];
    }

    static boolean isValidCell(int i, int j){

        return i >= 0 && i < M_rows && j >= 0 && j < N_cols;
    }

    // see http://www.jstatsoft.org/v08/i14/paper for definition of xorshift RNG
    static int seed = 123456;
    static int xorshift() {
        seed ^= (seed << 1);
        seed ^= (seed >> 3);
        seed ^= (seed << 10);
        return seed;
    }

    private static boolean[][] read_grid(int rows, int cols, BufferedReader in) throws IOException {
        boolean[][] grid = new boolean[rows][cols];
        if (cols < 100 && rows < 100) {
            for (int i=0; i < rows; i++) {
                String line = in.readLine().trim();
                for (int j=0; j < cols; j++) {
                    grid[i][j] = line.charAt(j) == '*';
                }
            }
        } else {
            seed = Integer.parseInt(in.readLine().trim());
            for (int i=0; i < rows; i++) {
                for (int j=0; j < rows; j++) {
                    grid[i][j] = ((xorshift()&0x100) != 0);
                }
            }
        }
        return grid;
    }

    private static void run(boolean[][] grid, int C) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        int[] res = null;
        PrintStream err = System.err;
        System.setErr(System.out);
        try {
            res = collector(grid,C);
        } catch (Exception e) {
            printException(err,e);
        }
        if (res == null) {
            bw.write("NULL\n");
        } else if (res.length > 1000) {
            bw.write(String.valueOf(res[0]));
            String plus = String.valueOf("+");
            String minus = String.valueOf("-");
            for (int i=1; i < res.length; i++) {
                if (res[i] == res[i-1]+1) bw.write(plus);
                else if (res[i] == res[i-1]-1) bw.write(minus);
                else {
                    bw.newLine();
                    bw.write(String.valueOf(res[i]));
                }
                if (i % 100 == 0) bw.newLine();
            }
            bw.newLine();
        } else {
            for(int i=0; i < res.length; i++) {
                bw.write(String.valueOf(res[i]));
                bw.newLine();
            }
        }
        bw.write("DONE");
        bw.newLine();
        bw.close();
        return;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(in.readLine().trim());
        int gridRows = Integer.parseInt(in.readLine().trim());
        int gridCols = Integer.parseInt(in.readLine().trim());
        run(read_grid(gridRows,gridCols,in),C) ;
    }

    private static void printException(PrintStream err, Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; ++i) {
            if (trace[i].getClassName().equals("Solution")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
