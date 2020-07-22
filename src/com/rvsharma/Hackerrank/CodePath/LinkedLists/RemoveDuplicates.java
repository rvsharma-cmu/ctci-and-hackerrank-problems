package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class RemoveDuplicates {

    public static SLNode removeDup(SLNode head) {
        if (head == null || head.next == null) return head;
        SLNode traverse = head;
        while (traverse.next != null) {
            if (traverse.val == traverse.next.val) {
                traverse.next = traverse.next.next;
            } else {
                traverse = traverse.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        SLNode head = LLUtilities.createSL(new int[]{1,1,2});
        SLNode result = removeDup(head);
        LLUtilities.printLinkedList(result);
    }
}
