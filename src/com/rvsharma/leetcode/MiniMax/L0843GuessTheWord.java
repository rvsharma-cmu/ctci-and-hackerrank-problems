package com.rvsharma.leetcode.MiniMax;

import java.util.*;

public class L0843GuessTheWord {

    static class Master{
        private Set<String> set;
        private String secretKey;
        private int count;

        public Master(String secret, List<String> wordList){
            set = new HashSet<>(wordList);
            secretKey = secret;
            count = 0;
        }
        public int guess(String word) throws Exception {
            if(!set.contains(word)) return -1;
            if(word.equals(secretKey) && count < 10) throw new Exception("You guessed the word correctly within 10 tries!");
            if(count >= 10) throw new IllegalArgumentException("You failed to guess the word in 10 tries");
            int match = 0;
            for(int i = 0; i < word.length(); i++){
                if(secretKey.charAt(i) == word.charAt(i)) match++;
            }
            count++;
            return match;
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {
        int charsMatched = 0;
        for (int randomGuess = 0; randomGuess < 10 && charsMatched < 6; ++randomGuess) {
            HashMap<String, Integer> countMap = new HashMap<>();
            for(int prevWord = 0; prevWord < wordlist.length-1; prevWord++){
                for (int currWord = prevWord + 1; currWord < wordlist.length; currWord++){
                    if(match(wordlist[prevWord], wordlist[currWord]) == 0){
                        countMap.put(wordlist[prevWord], countMap.getOrDefault(wordlist[prevWord], 0)+1);
                        countMap.put(wordlist[currWord], countMap.getOrDefault(wordlist[currWord], 0)+1);
                    }
                }
            }
            String guess = "";
            int min0 = 100;
            for (String w : wordlist)
                if (countMap.getOrDefault(w, 0) < min0) {
                    guess = w;
                    min0 = countMap.getOrDefault(w, 0);
                }
            try {
                charsMatched = master.guess(guess);
            } catch (Exception e) {
                if(e instanceof IllegalArgumentException) {
                    System.out.println("You failed to guess the word in 10 tries");
                }else{
                    System.out.println("You guessed the word correctly!");
                }
            }
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist)
                if (match(guess, w) == charsMatched)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }
    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) == b.charAt(i))
                matches ++;
        return matches;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"acckzz", "ccbazz", "eiowzz", "abcczz"};
        String secret = "acckzz";
        Master master = new Master(secret, Arrays.asList(words));
        L0843GuessTheWord guessTheWord = new L0843GuessTheWord();
        guessTheWord.findSecretWord(words, master);
    }
}
