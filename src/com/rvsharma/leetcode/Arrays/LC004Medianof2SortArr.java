package com.rvsharma.leetcode.Arrays;

/**
 * solution taken from @see <a href="https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java"> Tushar Roy GitHub</a>
 * Video explanation link: https://www.youtube.com/watch?v=LPFhl65R7ww
 */
public class LC004Medianof2SortArr {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length) {
            // swap arrays so they are in sequence of smaller and larger
            return findMedianSortedArrays(nums2, nums1);
        }
        int nums1Len = nums1.length, nums2Len = nums2.length, n1Start = 0, n1End = nums1Len;

        while(n1Start <= n1End) {

            int n1Part = (n1Start + n1End) / 2;
            int n2Part = (nums1Len + nums2Len + 1) / 2 - n1Part;

            int maxLeftN1 = (n1Part == 0) ? Integer.MIN_VALUE : nums1[n1Part - 1];
            int maxLeftN2 = (n2Part == 0) ? Integer.MIN_VALUE : nums2[n2Part - 1];

            int minRightN1 = (n1Part == nums1Len) ? Integer.MAX_VALUE : nums1[n1Part];
            int minRightN2 = (n2Part == nums2Len) ? Integer.MAX_VALUE : nums2[n2Part];

            if (maxLeftN1 <= minRightN2 && maxLeftN2 <= minRightN1) {

                if (nums1Len + nums2Len % 2 == 0) {
                    return (Math.max(maxLeftN1, maxLeftN2) + Math.min(minRightN1, minRightN2)) / 2.0;
                } else {
                    return Math.max(maxLeftN1, maxLeftN2);
                }
            } else if (maxLeftN1 > minRightN2) {
                n1End = n1Part - 1;
            } else {
                n1Start = n1Part + 1;
            }
        }
        throw new IllegalArgumentException("No median found. The arrays must not be sorted.");
    }

    public static void main(String[] args) {
        int[] x = {1, 3, 8, 9, 15};
        int[] y = {7, 11, 19, 21, 18, 25};

        LC004Medianof2SortArr mm = new LC004Medianof2SortArr();
        double value = mm.findMedianSortedArrays(x, y);
        System.out.println("The median of two sorted array is: " + value);
    }
}
