package com.rvsharma.Ch01ArraysAndStrings.sol01;

/* assuming the string consists of only character alphabets
 * the program can be optimized and the space requirements reduced
 * 8 times. Following is how that can be achieved
 */
public class Sol01A {

    public static boolean isUniqueChars(String str) {

        int checker = 0;
        for(int i=0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';

            if((checker & (1 << val)) > 0) return false;
            checker |= (1<<val);
        }
        return true;
    }
}
