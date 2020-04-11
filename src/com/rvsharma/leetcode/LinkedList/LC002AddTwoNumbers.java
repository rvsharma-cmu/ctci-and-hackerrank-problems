package com.rvsharma.leetcode.LinkedList;

import com.rvsharma.leetcode.LinkedList.Utilities.ListNode;

/**
 * Given two linkedlist with reverse order numbering, return a resultant linked-list
 * that gives you the output of the sum of these two linked lists.
 */
public class LC002AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode t = result, p = l1, q = l2;

        boolean carry = false;
        while(p != null || q != null) {
            int x = 0, y = 0;
            if(p != null) x = p.val;
            if(q != null) y = q.val;
            int sum = x + y + (carry ? 1 : 0);
            carry = false;
            if(sum / 10 >= 1) {
                sum = sum % 10;
                carry = true;
            }
            ListNode temp = new ListNode(sum);
            t.next = temp;
            t = temp;
            if(p != null) p = p.next;
            if(q != null) q = q.next;
        }
        if(carry){
            t.next = new ListNode(1);
        }
        return result.next;
    }

}
