package com.rvsharma.Ch02LinkedList.sol01;

import com.rvsharma.Ch02LinkedList.Introduction.LLNode;

public class Sol01B {

    public void deleteDup(LLNode head){
        LLNode curr = head;
        while (curr != null) {
            LLNode runner = curr;
            while(runner.next != null){
                if (runner.next.data == curr.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            curr = curr.next;
        }
    }
}
