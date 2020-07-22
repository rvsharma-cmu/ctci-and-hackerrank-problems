package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is "happy".
 *
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle
 * which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Return True if n is a happy number, and False if not.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {
    public static boolean isHappy(int n){
        int x = n, sum = 0;
        Set<Integer> set = new HashSet<>();
        while(x > 0) {
            sum += Math.pow(x%10, 2.0);
            x = x/10;
            if(x == 0){
                if(!set.add(sum)){
                    return false;
                }
                if(sum == 1)
                    return true;
                x = sum;
                sum = 0;
            }
        }
        return false;
    }
}
