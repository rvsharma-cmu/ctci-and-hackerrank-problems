package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.math.*;

public class ReverseBitMask {


    static int reverseBitField(int N, int l, int h) {
        /*
          165 = 0b10100101
          l = 1; h = 5;
          digit = 10010
          reverseDigit = 01001
          result = 0b10010011
         */
        System.out.println("N="+Integer.toBinaryString(N));
        int mask = (1<<(h-l+1))-1;
        System.out.println("mask="+Integer.toBinaryString(mask));
        int value = (N>>l) & (mask);
        System.out.println("value="+Integer.toBinaryString(value));
        int reversed = reverseBit(value, h-l);
        reversed &= mask;
        System.out.println("reversed="+ Integer.toBinaryString(reversed));
        int allMask = (reversed << l);
        int right = (~0<<h+1);
        int left = (1<<l)-1;
        int NClear = N & (right|left);
        System.out.println("return this value="+ Integer.toBinaryString(allMask));
        return NClear|(allMask);
    }

    static int reverseBit(int toReverse, int len){

        int result = 0;
        System.out.println("toReverse=" + Integer.toBinaryString(toReverse));
        for(int i = 0; i <= len; i++){
            result |= ((toReverse>>>(len-i))&0x1)<<i;
            System.out.println("current result=" + Integer.toBinaryString(result));
        }
        System.out.println("result=" + Integer.toBinaryString(result));
        return result;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int N = Integer.parseInt(in.nextLine().trim());
        int l = Integer.parseInt(in.nextLine().trim());
        int h = Integer.parseInt(in.nextLine().trim());

        PrintStream err = System.err;
        System.setErr(System.out);
        try {
            int res = reverseBitField(N, l, h);
            bw.write(String.valueOf(res));
            bw.newLine();
        } catch (Exception e) {
            printException(err,e);
        }
        bw.close();
    }

    private static void printException(PrintStream err, Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : trace) {
            if (stackTraceElement.getClassName().equals("Solution")) {
                err.println(e.getClass().getName() + " at line " + stackTraceElement.getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
