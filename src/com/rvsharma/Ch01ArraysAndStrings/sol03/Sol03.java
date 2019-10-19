package com.rvsharma.Ch01ArraysAndStrings.sol03;

public class Sol03 {

    public static void URLify(char[] str, int trueLength){

        int spaceCount = 0, last;
        for(int i = 0; i< trueLength; i++){ // only needed to run till the truelength for finding white spaces

            if(str[i] == ' ') spaceCount++;
        }
        last = trueLength+2*spaceCount; // not multiplied by 3 since space character can for eg. be replaced with %
        if(trueLength < str.length) str[trueLength] = '\0'; //add null only if the last position does not have null character
        for(int i = trueLength-1; i >= 0; i--){
            if(str[i] == ' '){
                str[last-1] = '0';
                str[last-2] = '2';
                str[last-3] = '%';
                last -= 3;
            } else {
                str[last-1] = str[i];
                last--;
            }
        }
    }

}
