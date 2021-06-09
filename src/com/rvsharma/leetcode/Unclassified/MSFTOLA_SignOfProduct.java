package com.rvsharma.leetcode.Unclassified;

public class MSFTOLA_SignOfProduct {

    /**
     * Check if the sign of the product of the array is positive or negative.
     * We traverse through the array counting the negative signs, and if the
     * count of the signs are positive, return 1 else 0.
     * @param arr The input array to check.
     * @return return 1, if sign is positive, return 0 if sign is negative. If any number is 0, return 'positive'
     * immediately
     */
    private static int signOfProduct(int[] arr){
        // traverse through the array checking sign
        int count = 0; // increment the count if negative num encountered
        for(int x : arr) {
            if (x == 0) return 1;  // product is 0, return positive
            if (x < 0){
                count++;
            }
        }
        // if count is even, product of arr is positive, else negative
        return count % 2 == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1,-4,-3,4,5};

        int x = signOfProduct(arr);
        String s = x == 1 ? "Positive" : "Negative";
        System.out.println(s);
    }
}
