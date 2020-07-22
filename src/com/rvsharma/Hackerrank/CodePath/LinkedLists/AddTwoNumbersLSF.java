package com.rvsharma.Hackerrank.CodePath.LinkedLists;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbersLSF {
    public static SLNode addTwoNumbers(SLNode l1, SLNode l2){
        SLNode result = new SLNode();
        SLNode t1 = l1, t2 = l2, t = result;
        int sum = 0;
        while (t1 != null || t2 != null) {
            if(t1 != null) {
                sum += t1.val;
                t1 = t1.next;
            }
            if (t2 != null) {
                sum += t2.val;
                t2 = t2.next;
            }
            t.next = new SLNode(sum % 10);
            sum = sum / 10;
            t = t.next;
        }
        if(sum != 0){
            t.next = new SLNode( 1);
        }
        return result.next;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,4,3};
        int[] arr2 = new int[]{5,6,4};
        SLNode result = addTwoNumbers(LLUtilities.createSL(arr1), LLUtilities.createSL(arr2));
        LLUtilities.printLinkedList(result);
    }
}
