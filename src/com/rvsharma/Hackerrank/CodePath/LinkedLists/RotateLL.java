package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class RotateLL {

    public static SLNode rotateRight(SLNode head, int k) {
        // base cases
        if(head == null || head.next == null){
            return head;
        }

        // close the linked list into the ring
        SLNode oldTail = head;
        int len;
        len = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            len++;
        }
        oldTail.next = head;
        k = k % len;
        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        SLNode newTail = head;

        while (len - k - 1 > 0) {
            newTail = newTail.next;
            k++;
        }
        SLNode newHead = newTail.next;

        // break the ring
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        SLNode head = LLUtilities.createSL(new int[]{1,2,3,4,5});
        int k = 2;
        LLUtilities.printLinkedList(rotateRight(head, k));
    }

}
