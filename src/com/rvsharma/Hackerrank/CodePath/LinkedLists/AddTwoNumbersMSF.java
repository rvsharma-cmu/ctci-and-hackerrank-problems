package com.rvsharma.Hackerrank.CodePath.LinkedLists;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class AddTwoNumbersMSF {
    public static SLNode addTwoNumbers(SLNode l1, SLNode l2){
        int len1 = 0, len2 = 0, carry = 0;
        SLNode curr1 = l1, curr2 = l2, res = null;
        while (curr1 != null) {
            curr1 = curr1.next;
            len1++;
        }
        while (curr2 != null) {
            curr2 = curr2.next;
            len2++;
        }
        curr1 = l1;
        curr2 = l2;
        while (len1 > 0 && len2 > 0) {
            int sum = 0;
            if(len1 >= len2){
                sum += curr1.val;
                curr1 = curr1.next;
                len1--;
            }
            if(len2 > len1){
                sum += curr2.val;
                curr2 = curr2.next;
                len2--;
            }
            res = addToFront(sum, res);
        }
        curr1 = res;
        res = null;
        while (curr1 != null) {
            curr1.val += carry;
            carry = curr1.val/10;
            res = addToFront(curr1.val % 10, res);
            curr1 = curr1.next;
        }
        if (carry > 0) {
            addToFront(1, res);
        }
        return res;
    }

    public static SLNode addToFront(int sum, SLNode head){
        SLNode temp = new SLNode(sum);
        temp.next = head;
        return temp;
    }

    public static SLNode addTwoNumbersStack(SLNode l1, SLNode l2){
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        while (l1 != null) {
            s1.addLast(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.addLast(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        SLNode list = new SLNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                sum += s1.removeLast();
            }
            if (!s2.isEmpty()) {
                sum += s2.removeLast();
            }
            list.val = sum % 10;
            SLNode head = new SLNode(sum / 10);
            head.next = list;
            list = head;
            sum = sum / 10;
        }
        // if no carry (sum == 0) return the list.next : otherwise return list
        return sum == 0 ? list.next : list;
    }

    public static void main(String[] args) {
        SLNode l1 = LLUtilities.createSL(new int[]{7,2,4,3});
        SLNode l2 = LLUtilities.createSL(new int[]{5,6,4});
        LLUtilities.printLinkedList(addTwoNumbersStack(l1, l2));
    }
}
