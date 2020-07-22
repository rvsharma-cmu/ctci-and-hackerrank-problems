package com.rvsharma.Hackerrank.CodePath.LinkedLists;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromLast {

    public static SLNode removeNthFromLast(SLNode head, int n) {
        SLNode dummy = new SLNode();
        SLNode prev = dummy, end = dummy;
        dummy.next = head;
        while (n >= 0) {
            end = end.next;
            n--;
        }
        while (end != null) {
            end = end.next;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 3;
        SLNode head = LLUtilities.createSL(arr);
        SLNode res = removeNthFromLast(head, k);
        LLUtilities.printLinkedList(res);
    }
}
