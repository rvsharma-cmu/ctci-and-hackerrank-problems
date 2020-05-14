package com.rvsharma.leetcode.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class LC1268SearchSuggestion {

    // sort then binary search
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        Arrays.sort(products);
        for (int i = 1; i <= searchWord.length(); ++i) {
            String cur = searchWord.substring(0, i);
            int k = Arrays.binarySearch(products, cur);
            while (k > 0 && cur.equals(products[k - 1])) // in case there are more than 1 cur in products.
                --k; // find the first one.
            if (k < 0) // no cur in products.
                k = ~k; // find the first one larger than cur.
            List<String> suggestion = new ArrayList<>();

            int j = k + 3;
            while (k < products.length && k < j) {
                if (products[k].startsWith(cur))
                    suggestion.add(products[k]);
                ++k;
            }
            ans.add(suggestion);
        }
        return ans;
    }



    public static void main(String[] args) {
        LC1268SearchSuggestion sol = new LC1268SearchSuggestion();

        String[] prods = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        List<List<String>> res = sol.suggestedProducts(prods, searchWord);
        System.out.println(res);
    }

}
