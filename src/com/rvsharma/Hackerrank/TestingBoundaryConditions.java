package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;

public class TestingBoundaryConditions {

    static float runningAverage(int[] values) {
        if (values == null || values.length <= 0)
            return -1.0f;
        float total = 0;
        float count = 0;
        for (int i = 0; i < values.length ; i++) {
            if (values[i] >= 0) {
                total += (float) values[i];
                count++;
            }
        }
        if(count == 0) return 0.0f;
        else return total / count;
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);

        int _values_size = Integer.parseInt(in.nextLine().trim());
        int[] _values = new int[_values_size];
        for(int _values_i = 0; _values_i < _values_size; _values_i++) {
            int _values_item = Integer.parseInt(in.nextLine().trim());
            _values[_values_i] = _values_item;
        }
        float res = runningAverage(_values);
        System.out.println("Result = " + res);
        System.err.close();
    }
}