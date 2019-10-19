package com.rvsharma.Ch01ArraysAndStrings.Sol05;

public class sol05A {

    static boolean oneEditAway(String a, String b){

        if(a.length() == b.length()){
            return oneReplaceAway(a, b);
        } else if(a.length()-1 == b.length()){
            return oneInsertAway(b, a);
        } else if(a.length()+1 == b.length()){
            return oneInsertAway(a, b);
        }
        return false;
    }

    private static boolean oneInsertAway(String shorter, String longer) {
        int index1 = 0;
        int index2 = 0;
        while((index2 < longer.length()) && (index1 < shorter.length())){
            if(shorter.charAt(index1) != longer.charAt(index2)){
                if(index1!= index2)
                    return false;
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    private static boolean oneReplaceAway(String a, String b) {
        boolean foundDifference = false;
        for(int i=0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                if(foundDifference) return false;
                foundDifference = true;
            }
        }
        return true;
    }
}
