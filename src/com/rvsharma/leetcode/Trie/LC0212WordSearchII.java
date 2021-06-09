package com.rvsharma.leetcode.Trie;

import com.rvsharma.leetcode.BackTracking.LC0079WordSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class LC0212WordSearchII {

    /**
     * Naive idea is to go through each word in the set and try to find it.
     * We can reuse the {@link com.rvsharma.leetcode.BackTracking.LC0079WordSearch} class here to determine if
     * the word exists in the grid or not
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for(String w : words) {
            if(LC0079WordSearch.exist(board, w)){
                result.add(w);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
    }

}
