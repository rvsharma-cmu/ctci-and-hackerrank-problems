package com.rvsharma.Ch02LinkedList.sol01;

import com.rvsharma.Ch02LinkedList.Introduction.LLNode;

import java.util.HashSet;
import java.util.Set;

public class Sol01A {

    public void deleteDuplicate(LLNode head){
        if(head == null) return;
        Set<Integer> set = new HashSet<>();
        LLNode curr = head;
        LLNode prev = null;
        while(curr != null) {
            if (set.contains(curr.data)) {
                prev.next = curr.next;
            } else {
                set.add(curr.data);
                prev=curr;
            }
            curr = curr.next;
        }
    }
}
