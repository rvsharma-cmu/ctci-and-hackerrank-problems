package com.rvsharma.leetcode.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LC1509MinDiffBwLgstSmlst3Moves {

	public int minDifference(int[] nums) {

		PriorityQueue<Integer> max = new PriorityQueue<>();
		PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder());

		for(int i : nums){
			if(max.size() < 4){
				max.add(i);
				min.add(i);
			}else {
				max.add(Math.max(max.poll(), i));
				min.add(Math.min(min.poll(), i));
			}
		}

		List<Integer> al = new ArrayList<>();
		while(!min.isEmpty()){
			al.add(min.poll());
		}
		int minValue = Integer.MAX_VALUE;
		for(int i = al.size() - 1; i >=0 ; i--){
			minValue = Math.min(minValue, max.poll() - al.get(i));
		}
		return minValue;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{6,6,0,1,1,4,6};
		LC1509MinDiffBwLgstSmlst3Moves sol = new LC1509MinDiffBwLgstSmlst3Moves();
		System.out.println(sol.minDifference(arr));
	}
}
