package com.rvsharma.leetcode.LinkedList;

import com.rvsharma.Ch02LinkedList.Introduction.LLNode;
import com.rvsharma.leetcode.LinkedList.Utilities.ListNode;

public class LC021MergeTwoSortedLL {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2){

        ListNode node = new ListNode(0), temp = node, t1 = l1, t2 = l2;

        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                temp.next = new ListNode(t1.val);
                t1 = t1.next;
            } else {
                temp.next = new ListNode(t2.val);
                t2 = t2.next;
            }
            temp = temp.next;
        }

        if (t1 == null) {
            while (t2 != null) {
                temp.next = new ListNode(t2.val);
                t2 = t2.next;
                temp = temp.next;
            }
        } else {
            while (t1 != null) {
                temp.next = new ListNode(t1.val);
                t1 = t1.next;
                temp = temp.next;
            }
        }
        return node.next;
    }
}
