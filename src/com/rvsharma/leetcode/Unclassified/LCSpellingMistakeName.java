package com.rvsharma.leetcode.Unclassified;

/**
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 * Example 3:
 *
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 * Example 4:
 *
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 *
 *
 * Constraints:
 *
 * 1 <= name.length <= 1000
 * 1 <= typed.length <= 1000
 * The characters of name and typed are lowercase letters
 */
public class LCSpellingMistakeName {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j= 0;
        while(i < typed.length() && j < name.length()){
            if(typed.charAt(i) == name.charAt(j)){
                int len1 = getRepeatedLen(typed, i);
                int len2 = getRepeatedLen(name, j);

                if(len1 < 2 && len1 != len2 || len1 >= 2 && len1 < len2){
                    return false;
                }
                i += len1;
                j += len2;
            }
            else {
                return false;
            }
        }

        return i == typed.length() && j == name.length();
    }
    private int getRepeatedLen(String s, int i){
        int j = i + 1, count = 1;
        while(j < s.length() && s.charAt(j) == s.charAt(i)){
            count++;
            j++;
        }

        return count;
    }
}
