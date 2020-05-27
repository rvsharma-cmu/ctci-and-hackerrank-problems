package com.rvsharma.leetcode.Graphs;

import java.util.*;

public class LC1284MinFlipsToZeroMat {
    /*
        Use bit vector and BFS
    */
    private static final int[] d = {0, 0, 1, 0, -1, 0};
    public int minFlips(int[][] mat) {
        int start = 0, m = mat.length, n = mat[0].length;

        // For Input: mat = [[0,0],[0,1]], map it to 0b1000, that is, mapping mat[i][j] to the (i * n + j)th bit of an int
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                start |= mat[i][j] << (i * n + j); // convert the matrix to an int.
            }
        }

        Queue<Integer> q = new LinkedList<>(Arrays.asList(start));
        Set<Integer> seen = new HashSet<>(q);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                int cur = q.poll();
                if (cur == 0) // All 0s matrix found.
                    return level;
                for (int i = 0; i < m; ++i) { // traverse all m * n bits of cur.
                    for (int j = 0; j < n; ++j) {
                        int next = cur;
                        // next ^ 1 << k (where k = i * n + j) to flip kth bit of next, which is equal to flipping the corresponding cell (i, j) in the matrix
                        for (int k = 0; k < 5; ++k) { // flip the cell (i, j) and its neighbors.
                            int r = i + d[k], c = j + d[k + 1];
                            if (r >= 0 && r < m && c >= 0 && c < n)
                                next ^= 1 << (r * n + c);
                        }
                        if (seen.add(next)) // seen it before ?
                            q.offer(next); // no, put it into the Queue.
                    }
                }
                --size;
            }
            ++level;
        }
        return -1; // impossible to get all 0s matrix.
    }

    public static void main(String[] args) {
        LC1284MinFlipsToZeroMat sol = new LC1284MinFlipsToZeroMat();
        int[][] mat = new int[][]{{1,1,1}, {1,0,1}, {0,0,0}};
        System.out.println(sol.minFlips(mat));

    }
}
