package com.rvsharma.leetcode.BackTracking;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 *
 * Constraints:
 *
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class LC0079WordSearch {

    public static boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || word == null) return false;
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                if(backtrack(board, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;

    }
    private static boolean backtrack(char[][] board, int i, int j, String word, int idx){
        if(idx == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(idx))
            return false;
        board[i][j] = '*';
        boolean found = backtrack(board, i+1, j, word, idx+1) ||
                backtrack(board, i, j+1, word, idx+1) ||
                backtrack(board, i-1,j, word, idx+1) ||
                backtrack(board, i, j-1, word, idx+1);
        board[i][j] = word.charAt(idx);
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ADEE"));
        System.out.println(exist(board, "ABCB"));

    }
}
