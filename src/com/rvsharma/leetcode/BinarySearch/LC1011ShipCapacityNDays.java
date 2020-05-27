package com.rvsharma.leetcode.BinarySearch;

public class LC1011ShipCapacityNDays {

    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            if (canShipAll(weights, mid, D)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean canShipAll(int[] weights, int mid, int D) {
        int need = 1, cur = 0;
        for (int w: weights) {
            if (cur + w > mid) {
                need += 1;
                cur = 0;
            }
            cur += w;
        }
        return need <= D;
    }

    public static void main(String[] args) {
        LC1011ShipCapacityNDays sol = new LC1011ShipCapacityNDays();
        int capacity = sol.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3);
        System.out.println("Capacity:"+capacity);
    }
}
