package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class DetectCycle {
    public boolean detectCycle(SLNode head){
        if(head == null || head.next == null)
            return false;
        SLNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7, 4, 2, 4, 6, 7, 9};
        SLNode head = LLUtilities.createSL(arr) , cycStart;
        int x = 5;
        cycStart = head;
        while(x-- > 0){
            cycStart = cycStart.next;
        }
        SLNode tr = head;
        while (tr.next != null) {
            tr = tr.next;
        }
        tr.next = cycStart;
        System.out.println(new DetectCycle().detectCycle(head));

        SLNode head2 = LLUtilities.createSL(arr);
        System.out.println(new DetectCycle().detectCycle(head2));
    }
}
