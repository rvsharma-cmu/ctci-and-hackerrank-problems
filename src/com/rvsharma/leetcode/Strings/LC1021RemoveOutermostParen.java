package com.rvsharma.leetcode.Strings;

public class LC1021RemoveOutermostParen {
    public String removeOuterParentheses(String S) {

        if(S.length() == 0) return S;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < S.length(); i++){
            if(isPrimitive(S.substring(0,i))){
                String s = S.substring(0,i);
                StringBuilder sNew = new StringBuilder(s);
                sNew.deleteCharAt(s.length() - 1);
                sNew.deleteCharAt(0);
                sb.append(sNew);
            }
        }
        return sb.toString();
    }

    private boolean isPrimitive(String s){
        if (s.length() == 0) return false;

        for(int i = 1; i < s.length(); i++){
            boolean left = validParenString(s.substring(0,i));
            boolean right = validParenString(s.substring(i + 1));
            if (left && right) return false;
        }
        return true;
    }

    private boolean validParenString(String s){
        int stack = 0;
        for(int i =0 ; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                stack++;
            } else {
                stack--;
                if(stack < 0) return false;
            }
        }
        return stack == 0;
    }

    public static void main(String[] args) {
        LC1021RemoveOutermostParen sol = new LC1021RemoveOutermostParen();
        System.out.println(sol.removeOuterParentheses("(()())(())"));
    }
}
