package com.rvsharma.leetcode.Unclassified;

import java.util.*;

/**
 * Students are asked to stand in non-decreasing order of heights for an annual photo.
 *
 * Return the minimum number of students that must move in order for all students to be standing in non-decreasing order of height.
 *
 * Notice that when a group of students is selected they can reorder in any possible way between themselves and the non selected students remain on their seats.
 *
 *
 *
 * Example 1:
 *
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation:
 * Current array : [1,1,4,2,1,3]
 * Target array  : [1,1,1,2,3,4]
 * On index 2 (0-based) we have 4 vs 1 so we have to move this student.
 * On index 4 (0-based) we have 1 vs 3 so we have to move this student.
 * On index 5 (0-based) we have 3 vs 4 so we have to move this student.
 * Example 2:
 *
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 * Example 3:
 *
 * Input: heights = [1,2,3,4,5]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */
public class LCNonDecreasingHeighs {
    public class Student {
        int height;
        int origIdx;
        public Student(int height, int oIdx){
            origIdx = oIdx;
            this.height = height;
        }
    }

    public int heightChecker(int[] heights) {
        Set<Integer> set = new HashSet<>();
        List<Student> arr = new ArrayList<>();
        for(int i = 0; i < heights.length; i++){
            arr.add(new Student(heights[i], i));
        }

        int left = 0;
        while(left < arr.size() - 1){
            while(left < arr.size() - 1 && arr.get(left).height <= arr.get(left + 1).height) {
                left++;
            }
            if(left == arr.size()) break;
            int right = arr.size() - 1;
            while(right > left && arr.get(right).height >= arr.get(right - 1).height) {
                right--;
            }
            if(right == left) break;
            set.add(arr.get(left).origIdx);
            set.add(arr.get(right).origIdx);
            swap(arr, left, right);
            left++;
        }

        return set.size();
    }

    private void swap (List<Student> heights, int left, int right){
        Student temp = heights.get(left);
        heights.set(left, heights.get(right));
        heights.set(right, temp);
    }


    static int countPairs(int numCount, List<Integer> ratingValues, int target) {
        int ans = 0;
        Set<String> distinct = new HashSet<>();
        Set<Integer> complement = new HashSet<>();
        for (int value : ratingValues) {
            int comp = target - value;
            int[] pair = new int[]{comp, value};
            Arrays.sort(pair);
            String s = Arrays.toString(pair);
            if (complement.contains(comp) && !distinct.contains(s)) {
                ans++;
                distinct.add(s);
            } else {
                complement.add(value);
            }
        }
        return ans;
    }

    public ALNode deepCopy(ALNode head) {

        ALNode iter = head, next;

        while (iter != null) {
            next = iter.next;

            ALNode copy = new ALNode(iter.value);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        iter = head;
        while (iter != null) {
            if (iter.arbitrary != null) {
                iter.next.arbitrary = iter.arbitrary.next;
            }
            iter = iter.next.next;
        }

        iter = head;
        ALNode dummyNode = new ALNode(0);
        ALNode copyIter = dummyNode;
        while (iter != null) {
            next = iter.next.next;
            ALNode copy = iter.next;
            iter.next = next;
            copyIter.next = copy;
            copyIter = copy;
            iter = next;
        }
        return dummyNode.next;
    }

    class ALNode {
        int value;
        ALNode next;
        ALNode arbitrary;

        public ALNode(int val) {
            this.value = val;
            this.next = null;
            this.arbitrary = null;
        }
    }



    // test suite and driver method
    public static void main(String[] args) {

    }
}
