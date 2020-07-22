package com.rvsharma.Hackerrank.CodePath.LinkedLists;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {
    public static void reorderList(SLNode head) {
        if (head == null) return;

        // find the middle of the linkedlist
        SLNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the linkedlist
        SLNode head2 = reverseLinkedList(slow.next);
        slow.next = null;

        // weave the two linkedlist
        while (head != null && head2 != null) {
            SLNode temp = head.next;
            SLNode temp2 = head2.next;
            head.next = head2.next;
            head2.next = head.next;
            head = temp;
            head2 = temp2;
        }
    }

    private static SLNode reverseLinkedList(SLNode start) {
        SLNode newHead = null;
        while (start != null) {
            SLNode nextNode = start.next;
            start.next = newHead;
            newHead = start;
            start = nextNode;
        }
        return newHead;
    }

    public static void main(String[] args) {
        SLNode head = LLUtilities.createSL(new int[]{1, 2, 3, 4, 5});
        reorderList(head);
        LLUtilities.printLinkedList(head);
    }
}
