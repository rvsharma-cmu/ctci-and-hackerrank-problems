package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.math.*;

public class Convertible {

    static class Tuple{
        int a;
        int b;
        Tuple(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    static String convertible(int a, int b, int c, int d) {

        if(a == c || b == d)
            return "Yes";

        Tuple start = new Tuple(a, b);
        Queue<Tuple> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()){
            Tuple current = q.poll();
            Tuple left = new Tuple(current.a+current.b, current.b);
            Tuple right = new Tuple(current.a, current.a+current.b);
            if(current.a+current.b == c && current.b == d || current.a == c && current.a+current.b == d){
                return "Yes";
            } else {
                if(current.a+current.b <= c && current.b <= d){
                    q.add(left);
                }
                if(current.a <= c && current.a+current.b <= d){
                    q.add(right);
                }
            }
        }
        return "No";

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

        int a = Integer.parseInt(in.nextLine().trim());
        int b = Integer.parseInt(in.nextLine().trim());
        int c = Integer.parseInt(in.nextLine().trim());
        int d = Integer.parseInt(in.nextLine().trim());

        String res = convertible(a, b, c, d);
        bw.write(res);
        bw.newLine();
        bw.close();
    }
}
