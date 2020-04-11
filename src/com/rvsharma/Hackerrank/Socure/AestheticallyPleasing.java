package com.rvsharma.Hackerrank.Socure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AestheticallyPleasing {

    public int solution(int[] A) {
        // write your code in Java SE 8

        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
        boolean itPleasing = isItPleasing(list);
        if(itPleasing) return 0;
        int count = 0;
        for(int i = 0; i < list.size(); i++){
            List<Integer> copyList = new ArrayList<>(list);
            copyList.remove(i);
            if(isItPleasing(copyList))
                count++;
        }

        return count == 0 ? -1 : count;
    }

    private boolean isItPleasing(List<Integer> list){
        for(int i = 0; i < list.size() - 2; i++){
            if(list.get(i) < list.get(i+1) && list.get(i+1) > list.get(i+2)) continue;
            if(list.get(i) > list.get(i+1) && list.get(i+1) < list.get(i+2)) continue;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] input = new int[]{3, 4, 5, 3, 7};
        AestheticallyPleasing sol = new AestheticallyPleasing();

        int output = sol.solution(input);
        System.out.println(output);
    }
}
