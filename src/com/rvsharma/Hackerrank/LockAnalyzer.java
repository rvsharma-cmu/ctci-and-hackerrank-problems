package com.rvsharma.Hackerrank;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LockAnalyzer {

    Set<Integer> usedLocks = new HashSet<>();
    Stack<Integer> currentLocks = new Stack<>();

    static int lockAnalyzer(String[] locks){

        if(locks.length == 0) return 0;
        Set<Integer> usedLocks = new HashSet<>();
        Stack<Integer> currentLocks = new Stack<>();
        int currentVal = 1;
        for(String str : locks){
            String[] strs = str.split("\\s+");
            int lockNum = Integer.parseInt(strs[1]);
            if(strs[0].equalsIgnoreCase("acquire")){
                // acquire an already held lock
                if(usedLocks.contains(lockNum)) return currentVal;
                usedLocks.add(lockNum);
                currentLocks.push(lockNum);
            } else if(strs[0].equalsIgnoreCase("release")){
                // release a never acquired lock
                if(!usedLocks.contains(lockNum)) return currentVal;
                int currentLock = currentLocks.pop();
                usedLocks.remove(currentLock);
                if(currentLock != lockNum){
                    return currentVal;
                }
                else if(usedLocks.contains(currentLock)){
                    return currentVal;
                }
//                currentLocks.pop();

            }
            currentVal++;
        }
        if(!currentLocks.isEmpty()) return locks.length+1;
        else return 0;
    }

    public static void main(String[] args){

        String[] values = {"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364", "ACQUIRE 789", "RELEASE 456", "RELEASE 123"};
        int ret = lockAnalyzer(values);
        System.out.println(ret);

    }

}
