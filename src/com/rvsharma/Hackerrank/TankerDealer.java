package com.rvsharma.Hackerrank;

import java.util.Arrays;

public class TankerDealer {

    private static int minTankers(int[] water, int[] capacity) {
        Arrays.sort(capacity);
        reverse(capacity);
        int totalWater = 0;
        for (int value : water) {
            totalWater += value;
        }
        System.out.println(totalWater);
        int res = 0;
        for (int value : capacity) {
            totalWater = totalWater - value;
            if (totalWater > 0) res++;
            else break;
        }
        return res + 1;
    }

    private static void reverse(int[] capacity) {
        int i = 0, j = capacity.length - 1;
        while(i < j) {
            int temp = capacity[i];
            capacity[i] = capacity[j];
            capacity[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] capacity =    {5, 4, 2, 7, 3, 6, 7, 3, 2, 8, 3, 10, 5, 2, 6, 7, 2, 5, 34, 13};
        int[] water =       {2, 3, 2, 5, 2, 5, 3, 1, 1, 6, 1, 6,  5, 1, 6, 7, 1, 6, 20, 9};
        int out_ = minTankers(water, capacity);
        System.out.println(out_);
    }
}
