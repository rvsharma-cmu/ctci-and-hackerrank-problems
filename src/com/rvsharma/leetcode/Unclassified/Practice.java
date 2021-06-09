package com.rvsharma.leetcode.Unclassified;

import java.util.Arrays;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * Example 4:
 *
 * Input: s = "  Bob    Loves  Alice   "
 * Output: "Alice Loves Bob"
 * Example 5:
 *
 * Input: s = "Alice does not even like bob"
 * Output: "bob like even not does Alice"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 *
 * Follow up: Could you solve it in-place with O(1) extra space?
 */
public class Practice {
	public static String reverseWords(String s) {
//		System.out.println(s);
		s = s.trim();
		// System.out.println(s);
		String[] splits = s.split("\\s+");
//		System.out.println(Arrays.toString(splits));

		for(int i = 0; i < splits.length / 2; i++){
			String temp = splits[i];
			splits[i] = splits[splits.length - i - 1];
			splits[splits.length - i - 1] = temp;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < splits.length; i++){
			sb.append(splits[i]);
			if(i != splits.length - 1)
				sb.append(" ");
		}
		return sb.toString();
	}

	public static String reverseWords_inplace(String s) {

		int n = s.length();
		char[] chars = s.toCharArray();
		// 1. reverse the entire String
		reverse(chars, 0, n - 1);
		reverseWords(chars, n);
		return "";
	}

	private static void reverseWords(char[] ch, int n){
		int i = 0, j = 0;

		while (i < n) {
			while (i < j || i < n && ch[i] == ' ') i++; // skip spaces
			while (j < i || j < n && ch[j] != ' ') j++; // skip non spaces
			reverse(ch, i, j - 1);                   // reverse the word
		}
	}

	/**
	 * Reverse a character array split from a string
	 * @param s Charater array representation of a string
	 * @param i starting index from which to reverse the string
	 * @param j ending index to which reverse the string
	 * @return return the reversed string
	 */
	private static void reverse(char[] s, int i, int j) {
		while (i < j){
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}

	public static void main(String[] args) {
		String input = "a good   example";
		System.out.println("\"" + reverseWords_inplace(input) + "\"");
	}
}
