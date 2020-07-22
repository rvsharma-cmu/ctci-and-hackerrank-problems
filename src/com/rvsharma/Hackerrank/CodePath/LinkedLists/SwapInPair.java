package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class SwapInPair {

    public static SLNode swapInPair(SLNode head) {
        if(head == null || head.next == null) return head;
        SLNode dummy = new SLNode(-1);
        dummy.next = head;
        SLNode prev = dummy;
        while (head != null && head.next != null) {
            SLNode first = head;
            SLNode second = head.next;
            first.next = second.next;
            second.next = first;
            prev.next = second;
            prev = first;
            head = first.next;
        }
        return dummy.next;
    }
}
