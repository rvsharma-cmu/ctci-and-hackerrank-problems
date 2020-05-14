package com.rvsharma.leetcode.HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0957PrisonAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N > 0) {
            /*
                Put the string representation of today's day into
                map
             */
            String key = Arrays.toString(cells);
            seen.put(key, N);
            N--;
            // calculate next day
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            // update the cell state
            cells = cells2;
            /*
            You store the state in the map the first time you see a new state.
            Then when you see the same state again, you know that you have passed (lastSeen - currVal)
            state in between. So now you know your states repeat every (lastSeen - currVal) times.
            So finally you can mod the current N with that value to not repeat the same steps
             */
            key = Arrays.toString(cells);
            if (seen.containsKey(key)) {
                N %= (seen.get(key) - N);
            }
        }
        return cells;
    }
}
