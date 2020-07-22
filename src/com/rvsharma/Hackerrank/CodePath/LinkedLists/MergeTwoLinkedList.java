package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class MergeTwoLinkedList {
    public static SLNode mergeTwoLists(SLNode l1, SLNode l2) {
        SLNode n1 = l1, n2 = l2;
        SLNode dummy = new SLNode(-1);
        SLNode traverse = dummy;
        while(n1 != null && n2 != null){
            if(n1.val < n2.val){
                traverse.next = new SLNode(n1.val);
                n1 = n1.next;
            } else {
                traverse.next = new SLNode(n2.val);
                n2 = n2.next;
            }
            traverse = traverse.next;
        }
        if (n1 != null){
            while (n1 != null){
                traverse.next = new SLNode(n1.val);
                n1 = n1.next;
                traverse = traverse.next;
            }
        } else {
            while (n2 != null){
                traverse.next = new SLNode(n2.val);
                n2 = n2.next;
                traverse = traverse.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] l1Arr = {1, 2, 4};
        int[] l2Arr = {1, 3, 4};
        SLNode l1 = LLUtilities.createSL(l1Arr);
        SLNode l2 = LLUtilities.createSL(l2Arr);
        SLNode result = mergeTwoLists(l1, l2);
        LLUtilities.printLinkedList(result);
    }
}
