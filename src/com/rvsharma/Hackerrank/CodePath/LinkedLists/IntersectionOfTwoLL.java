package com.rvsharma.Hackerrank.CodePath.LinkedLists;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 */
public class IntersectionOfTwoLL {

    public static SLNode intersectTwoLL (SLNode headA, SLNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        SLNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        SLNode commonPart = LLUtilities.createSL(new int[]{});
        SLNode headA = LLUtilities.createSL(new int[]{2, 6, 4});
        SLNode headB = LLUtilities.createSL(new int[]{1, 5});
        SLNode t = headA;
        while (t.next != null) {
            t = t.next;
        }
        t.next = commonPart;
        t = headB;
        while (t.next != null){
            t = t.next;
        }
        t.next = commonPart;
        SLNode res = intersectTwoLL(headA, headB);
        System.out.println("Common part");
        LLUtilities.printLinkedList(res);
    }
}
