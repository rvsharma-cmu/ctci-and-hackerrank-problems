package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.math.*;

public class DynamicRobber {


    static int maximumLoot(short[] loot) {

        if(loot.length == 0) return 0;
        int[] DP = new int[loot.length + 2];
        DP[0] = 0;
        DP[1] = loot[0];
        DP[2] = loot[1];
        for(int i = 2; i < loot.length;i++){
            DP[i+1] = Math.max(DP[i], DP[i-2] + (int) loot[i]);
        }
        return DP[loot.length];
    }

    // see http://www.jstatsoft.org/v08/i14/paper for definition of xorshift RNG
    static int seed = 123456;
    static int xorshift() {
        seed ^= (seed << 1);
        seed ^= (seed >> 3);
        seed ^= (seed << 10);
        return seed & 0x7FFFFFFF;
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int _loot_size = Integer.parseInt(in.nextLine().trim());
        short[] _loot = new short[_loot_size];
        if (_loot_size <= 100) {
            for(int _loot_i = 0; _loot_i < _loot_size; _loot_i++) {
                _loot[_loot_i] = (short)Integer.parseInt(in.nextLine().trim());
            }
        } else {
            seed = Integer.parseInt(in.nextLine().trim());
            for(int _loot_i = 0; _loot_i < _loot_size; _loot_i++) {
                _loot[_loot_i] = (short)((xorshift() % 100) + 1) ;
            }
        }

        PrintStream err = System.err ;
        System.setErr(System.out);
        try {
            int res = maximumLoot(_loot);
            bw.write(String.valueOf(res));
            bw.newLine();
        } catch (Exception e) {
            printException(err,e);
        }
        bw.close();
    }

    private static void printException(PrintStream err, Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; ++i) {
            if (trace[i].getClassName().equals("DynamicRobber")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
