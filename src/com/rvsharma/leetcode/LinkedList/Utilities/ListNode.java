package com.rvsharma.leetcode.LinkedList.Utilities;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode reverseLinkedList(ListNode head){
        ListNode newHead = null;
        while(head != null){
            ListNode nextNode = head.next;
            head.next = newHead;
            newHead = head;
            head = nextNode;
        }
        return newHead;
    }
}
