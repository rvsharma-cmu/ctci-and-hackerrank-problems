package com.rvsharma.leetcode.BreadthFirstSearch;

import java.util.*;

/**
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * Examples:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * Note:
 *
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 */
public class LC0773SlidingPuzzle {

    public static int slidingPuzzle(int[][] board) {
        String dest = "123450";
        StringBuilder sb = new StringBuilder();
        String src = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                sb.append(board[i][j]);
            }
        }
        src = sb.toString();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(src);
        visited.add(src);
        int level = 0;
        int[][] dirs = {{1,3},{0,2,4},{1, 5},{0,4},{1,3,5},{2,4}};
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size ; i++){
                String curr = q.poll();
                assert curr != null;
                if(curr.equals(dest)) return level;
                int zeroPos = curr.indexOf('0');
                for(int n : dirs[zeroPos]){
                    String next = swap(curr, zeroPos, n);
                    if(visited.contains(next)) continue;
                    visited.add(next);
                    q.offer(next);
                }
            }
            level++;
        }
        return -1;
    }

    public static List<String> slidingPuzzle_Moves(int[][] board) {
        String dest = "123450";
        StringBuilder sb = new StringBuilder();
        String src = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                sb.append(board[i][j]);
            }
        }
        src = sb.toString();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(src);
        visited.add(src);
        int level = 0;
        List<String> list = new ArrayList<>();
        int[][] dirs = {{1,3},{0,2,4},{1, 5},{0,4},{1,3,5},{2,4}};
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size ; i++){
                String curr = q.poll();
                list.add(curr);
                if(curr.equals(dest)) return list;
                int zeroPos = curr.indexOf('0');
                for(int n : dirs[zeroPos]){
                    String next = swap(curr, zeroPos, n);
                    if(visited.contains(next)) continue;
                    visited.add(next);
                    q.offer(next);
                }
            }
            level++;
        }
        return new ArrayList<>();
    }

    private static String swap(String str, int x, int y){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(x, str.charAt(y));
        sb.setCharAt(y, str.charAt(x));
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{1, 3, 5}, {4, 0, 2}};
        int moves = slidingPuzzle(input);
        List<String> moves_detail = slidingPuzzle_Moves(input);
        System.out.println("Total number of moves required = " + moves);
        for (int i = 0; i < moves_detail.size(); i++) {
            String s = moves_detail.get(i);
            System.out.print(s);
            if(i != moves_detail.size() - 1) System.out.print(" -> ");
        }
    }
}
