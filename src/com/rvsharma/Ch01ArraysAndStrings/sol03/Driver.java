package com.rvsharma.Ch01ArraysAndStrings.sol03;

public class Driver {
    private static int findLastCharacter(char[] str) {
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != ' ') {
                return i;
            }
        }
        return -1;
    }

    private static String charArrToString(char[] charArr){
        StringBuffer stringBuffer = new StringBuffer();

        for (char c : charArr) {
            stringBuffer.append(c);
        }
        return new String(stringBuffer);
    }

    public static void main(String[] args) {
        String str = "Mr John Smith    ";
        char[] arr = str.toCharArray();
        int trueLength = findLastCharacter(arr) + 1;
        Sol03.URLify(arr, trueLength);
        System.out.println("\"" + charArrToString(arr) + "\"");
    }
}
