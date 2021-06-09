package com.rvsharma.leetcode.Arrays;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range {a,b} is smaller than range {c,d} if b-a < d-c or a < c if b-a == d-c.
 *
 *
 *
 * Example 1:
 *
 * Input: {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}}
 * Output: {20,24}
 * Explanation:
 * List 1: {4, 10, 15, 24,26}, 24 is in range {20,24}.
 * List 2: {0, 9, 12, 20}, 20 is in range {20,24}.
 * List 3: {5, 18, 22, 30}, 22 is in range {20,24}.
 *
 *
 * Note:
 *
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -105 <= value of elements <= 105.
 */
public class LC0632SmallestRangeCoveringKLists {

    /**
     * 1. Initialize `next` array(pointers) with all 0's.
     * 2. Find the indices of the lists containing the minimum (min_i) and the maximum (max_i) elements amongst the
     * elements pointed by the next array
     * 3. If the range formed by the maximum and minimum elements found above is smaller than the previous minimum
     * range, update the boundary values used for the minimum range.
     * 4. Increment the pointer nums{min_i}
     * 5. Repeat steps 2 to 4 till atleast one of the lists gets exhausted.
     *
     * @param nums List of Integer list
     * @return array of int having the minimum range that contains atleast one number from the list
     */
    public static int[] smallestRange (List<List<Integer>> nums) {
        int max = Integer.MAX_VALUE, min = 0;
        int lists = nums.size();
        int[] ptr = new int[lists];
        boolean exhausted = false;
        while (!exhausted) {
            int currMax = Integer.MIN_VALUE, currMin = Integer.MAX_VALUE, idx = -1;
            for(int i = 0; i < lists; i++){
                if (nums.get(i).get(ptr[i]) < currMin) {
                    currMin = nums.get(i).get(ptr[i]);
                    idx = i;
                }
                if (nums.get(i).get(ptr[i]) > currMax) currMax = nums.get(i).get(ptr[i]);

            }
            if (currMax - currMin < max - min) {
                max = currMax;
                min = currMin;
            }
            ptr[idx] += 1;
            if(ptr[idx] == nums.get(idx).size()){
                exhausted = true;
            }
        }
        return new int[]{min,max};
    }

    public static int[] smallestRange_LCApp3(List<List<Integer>> nums){
        int minx = 0, miny = Integer.MAX_VALUE;
        int[] next = new int[nums.size()];
        boolean flag = true;
        for (int i = 0; i < nums.size() && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                int min_i = 0, max_i = 0;
                for (int k = 0; k < nums.size(); k++) {
                    if (nums.get(min_i).get(next[min_i]) > nums.get(k).get(next[k]))
                        min_i = k;
                    if (nums.get(max_i).get(next[max_i]) > nums.get(k).get(next[k]))
                        max_i = k;
                }
                if (miny - minx > nums.get(max_i).get(next[max_i]) - nums.get(min_i).get(next[min_i])) {
                    miny = nums.get(max_i).get(next[max_i]);
                    minx = nums.get(min_i).get(next[min_i]);
                }
                next[min_i]++;
                if (next[min_i] == nums.get(min_i).size()) {
                    flag = false;
                }
            }
        }
        return new int[] {minx, miny};
    }

    public static int[] smallestRange_discuss(List<List<Integer>> nums){
        int[] res = new int[]{(int) -1e5, (int) 1e5};
        int right = (int) -1e5;
        Queue<int[]> pq = new PriorityQueue<>(nums.size(), (a, b) -> (a[0] - b[0]));
        for(int i = 0; i < nums.size(); i++) {
            pq.offer(new int[] {nums.get(i).get(0), i, 0});
            right = Math.max(right, nums.get(i).get(0));
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int left = curr[0];
            if(right - left < res[1] - res[0]){
                res[0] = left;
                res[1] = right;
            }

            if(curr[2] + 1 == nums.get(curr[1]).size())
                return res;
            right = Math.max(right, nums.get(curr[1]).get(curr[2]+1));
            pq.offer(new int[]{nums.get(curr[1]).get(curr[2]+1), curr[1], curr[2] + 1});
        }

        return new int[] {-1,-1};
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/raghavs/11601/ctci/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        List<List<Integer>> list = new ArrayList<>();
        while ((st = br.readLine()) != null){
            String[] arr = st.split("},");
            for (String str : arr){
                String[] split = str.split(",");
                List<Integer> in = new ArrayList<>();
                for (String sp : split) {
                    if (sp.startsWith("{"))
                        sp = sp.substring(1);
                    else if (sp.endsWith("}"))
                        sp = sp.substring(0, sp.length()-1);
                    in.add(Integer.parseInt(sp));
                }
                list.add(in);
            }
        }
        System.out.println(Arrays.toString(smallestRange_discuss(list)));
        int[][] arr = new int[][]{{4,10,15,24,26},{0,9,12,20},{5,18,22,30}};
        List<List<Integer>> lists = new ArrayList<>();
        for(int[] a : arr){
            lists.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
        }
        System.out.println(Arrays.toString(smallestRange_discuss(lists)));
    }
}
