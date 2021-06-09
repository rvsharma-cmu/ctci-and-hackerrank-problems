package com.rvsharma.Hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryAutcom {

    class TrieNode {
        Map<Character, TrieNode> children;
        int prevIndex;

        TrieNode() {
            children = new HashMap<>();
            prevIndex = 0;
        }
    }

    int previousIndex(TrieNode root, String word){
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            TrieNode next = curr.children.get(c);
            if(next == null) break;
            curr = next;
        }
        return curr.prevIndex;
    }

    void insert(TrieNode root, int index, String word){
        root.prevIndex = index;
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children.get(c) == null){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.prevIndex = index;
        }
    }

    TrieNode root = new TrieNode();

    public List<Integer> commonPrefix(String[] words){
        int x = 0;
        root.prevIndex = 0;
        List<Integer> list = new ArrayList<>();
        for(String word: words){
            list.add(previousIndex(root, word));
            insert(root,x + 1, word);
        }
        return list;
    }

    public static void main(String[] args) {
        BinaryAutcom sol = new BinaryAutcom();
        String[] in = new String[]{"000", "1110", "01", "001", "110", "11"};
        System.out.println(sol.commonPrefix(in));
    }
}
