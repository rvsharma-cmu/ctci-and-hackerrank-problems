package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.math.*;

public class XORSquared {


    static int xorxor(int[] a) {

        int length = a.length;
        if(length == 0) return 0;
        int result = 0;
        if(length%2 == 0){
            for(int i = 1; i < length; i += 2){
                result ^= a[i];
            }
        } else {
            for(int i = 0; i < length; i += 2){
                result ^= a[i];
            }
        }
        return result;
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

        int _a_size = Integer.parseInt(in.nextLine().trim());
        int[] _a = new int[_a_size];
        if (_a_size <= 100) {
            for(int _i = 0; _i < _a_size; _i++) {
                _a[_i] = Integer.parseInt(in.nextLine().trim());
            }
        } else {
            seed = Integer.parseInt(in.nextLine().trim());
            for(int _i = 0; _i < _a_size; _i++) {
                _a[_i] = xorshift() ;
            }
        }

        PrintStream err = System.err ;
        System.setErr(System.out);
        try {
            int res = xorxor(_a);
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
            if (trace[i].getClassName().equals("Solution")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
