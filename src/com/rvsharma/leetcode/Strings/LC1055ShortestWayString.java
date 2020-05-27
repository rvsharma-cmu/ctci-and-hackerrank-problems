package com.rvsharma.leetcode.Strings;

public class LC1055ShortestWayString {

    /*
        TC: O(M.N)
        SC: O(M)
        where M = length of source and N = length of Target
     */
    public int shortestWay_sol1(String source, String target) {
        char[] cs = source.toCharArray(), ts = target.toCharArray();
        boolean[] map = new boolean[26];
        for (int i = 0; i < cs.length; i++)
            map[cs[i] - 'a'] = true;
        int i = 0, j = 0, res = 1;
        while (i < ts.length) {
            if (!map[ts[i] - 'a'])
                return -1;
            while (j < cs.length && cs[j] != ts[i]) {
                j++;
            }
            if (j == cs.length) {
                j = -1;
                res++;
                i--;
            }
            i++;
            j++;
        }
        return res;
    }

    /*
        Constant O(1) Space
     */
    public int shortestWay_sol2(String source, String target) {
        char[] cs = source.toCharArray(), ts = target.toCharArray();
        int res = 0;
        int i = 0;
        while (i < ts.length) {
            int oriI = i;
            int j = 0;
            while (j < cs.length) {
                if (i < ts.length && cs[j] == ts[i])
                    i++;
                j++;
            }
            if (i == oriI) return -1;
            res++;
        }
        return res;
    }


    public static void main(String[] args) {
        LC1055ShortestWayString sol = new LC1055ShortestWayString();
        String source = "abc", target = "abcbc";
        int ways = sol.shortestWay_sol2(source, target);
        System.out.println(source + "-->" + target + " = " + ways);
    }
}
