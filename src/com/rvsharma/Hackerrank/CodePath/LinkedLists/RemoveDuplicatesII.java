package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class RemoveDuplicatesII {

    public static SLNode removeDuplicatesII(SLNode head) {
        if(head == null) return null;
        SLNode dummyNode = new SLNode(-1);
        dummyNode.next = head;
        SLNode pre = dummyNode;
        SLNode curr = head;
        while (curr != null) {
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }
            if(pre.next == curr) {
                pre = pre.next;
            } else {
                pre.next = curr.next;
            }
            curr = curr.next;
        }
        return dummyNode.next;
    }


    public static void main(String[] args) {
        SLNode head = LLUtilities.createSL(new int[] {1,2,3,3,4,4,5});
        LLUtilities.printLinkedList(removeDuplicatesII(head));
    }
}
