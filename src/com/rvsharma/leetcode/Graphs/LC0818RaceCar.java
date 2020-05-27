package com.rvsharma.leetcode.Graphs;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class LC0818RaceCar {
    // BFS
    /*
    Well, the BFS solution is straightforward: we can keep track of all the possible positions of the racecar after
    n instructions (n = 0, 1, 2, 3, 4, ...) and return the smallest n such that the target position is reached.
    Naive BFS will run at O(2^n) since for each position we have two choices: either accelerate or reverse.
    Further observations reveal that there may be overlapping among intermediate states so we need to memorize
    visited states (each state is characterized by two integers: car position and car speed).
    However, the total number of unique states still blows up for large target positions
    (because the position and speed can grow unbounded), so we need further pruning of the search space
     */
    public int racecar(int target) {
        Deque<int[]> queue = new LinkedList<>();
        queue.offerLast(new int[] {0, 1}); // starts from position 0 with speed 1

        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + 1);

        /*
            Idea: We should allow the car to be able to pass the target and then reverse the direction to go to the
            target. However, there should be an upper bound because there's no point to keep accelerating after
            passing the target. To find the upper bound, lets' consider an extreme case where the car is always
            accelerating, the number of acceleration is m, the car with position (2^m) - 1 has not reached the
            target: (2^m)-1 < target. Since the target is an integer,
            we can rewrite it as: target = (2^m)+k, k >= 0... (1)

            In the above extreme case, the car's speed 2^m is the maximum possible speed at the position (2^m)-1.
            Note that we should allow the car to be able to pass the target, which leads to the
            maximum next position is (2^m)-1 + 2^m... (2)
            Combine (1) and (2) we get (2^m)-1+2^m = 2*(2^m)-1 < 2*(2^m)+2*k = 2*target, k >= 0.
            It says, the upper bound for any position with any possible speed is 2*target.

         */
        for (int level = 0; !queue.isEmpty(); level++) {
            for(int k = queue.size(); k > 0; k--) {
                int[] cur = queue.pollFirst();  // cur[0] is position; cur[1] is speed

                if (cur[0] == target) {
                    return level;
                }

                int[] nxt = new int[] {cur[0] + cur[1], cur[1] << 1};  // accelerate instruction
                String key = (nxt[0] + " " + nxt[1]);

                if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < (target << 1)) {
                    queue.offerLast(nxt);
                    visited.add(key);
                }

                nxt = new int[] {cur[0], cur[1] > 0 ? -1 : 1};  // reverse instruction
                key = (nxt[0] + " " + nxt[1]);

                if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < (target << 1)) {
                    queue.offerLast(nxt);
                    visited.add(key);
                }
            }
        }

        return -1;
    }

}
