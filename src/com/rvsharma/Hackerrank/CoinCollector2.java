package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;

public class CoinCollector2 {

    public static int[] collector(boolean[][] grid, int C) {

        int M_rows;
        int N_cols;
        if(grid.length == 0)
            return new int[0];
        if (grid[0].length == 0){
            return new int[grid[0].length];
        }
        M_rows = grid.length;
        N_cols = grid[0].length;
        int[] result = new int[N_cols];
        int[][] dp = new int[M_rows][N_cols];
        dp[0][0] = (grid[0][0]?1:0);
        for(int i = 0; i < M_rows; i++){
            for(int j = 0; j<N_cols;j++){
                if(i==0 && j== 0)
                    continue;

                if(j == 0){
                    if(j+1 >= N_cols) {
                        dp[i][j] = dp[i-1][j] + (grid[i][j]?1:-1);
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j+1]) + (grid[i][j]?1:-1);
                } else if(j == N_cols-1){
                    if(i-1 < 0) {
                        dp[i][j] = dp[i][j-1] + (grid[i][j]?1:-1);
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + (grid[i][j]?1:-1);
                } else {
                    if(i-1 < 0) {
                        if(j+1 >= N_cols) {
                            dp[i][j] = dp[i][j-1] + (grid[i][j]?1:-1);
                            continue;
                        } else {
                            dp[i][j] = Math.max(dp[i][j-1], dp[i][j+1]) + (grid[i][j]?1:-1);
                            continue;
                        }
                    }
                    if(j+1 >= N_cols) {
                        dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])  + (grid[i][j]?1:-1);
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i][j+1])) + (grid[i][j]?1:-1);
                }
            }
        }

        System.out.println("DP matrix is");
        for(int[] a : dp){
            for(int i : a){
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        int[] gridLastRow = new int[N_cols];
        for(int i = 0; i < N_cols; i++) {
            gridLastRow[i] = (grid[M_rows-1][i] ? 1 : -1);
        }
        int maxIdx = 0;
        for(int i = 0; i < N_cols;i++){
            if(dp[M_rows-1][i] > dp[M_rows-1][maxIdx])
                maxIdx = i;
        }
        result[maxIdx] = dp[M_rows-1][maxIdx];
        gridLastRow[maxIdx] = 0;
        for(int i = maxIdx+1; i < N_cols; i++){
            result[i] = result[i-1] + gridLastRow[i];
        }
        for(int i = maxIdx-1; i >= 0; i--){
            result[i] = result[i+1] + gridLastRow[i];
        }
        for(int i = 0; i < N_cols; i++){
            result[i] += C;
        }

        return result;
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
            if (trace[i].getClassName().equals("CoinCollector2")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
