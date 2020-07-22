package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class DeleteNodeFromLL {
    public SLNode deleteFromLL(SLNode head, int key){
        SLNode dummy = new SLNode(-1);
        dummy.next = head;
        SLNode prev = dummy;
        SLNode traverse = head;
        while (traverse != null) {
            if (traverse.val == key) {
                prev.next = traverse.next;
                return dummy.next;
            }
            prev = traverse;
            traverse = traverse.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4};
        SLNode head = LLUtilities.createSL(arr);
        System.out.println("Before deleting");
        LLUtilities.printLinkedList(head);
        System.out.println("After deleting");
        LLUtilities.printLinkedList(new DeleteNodeFromLL().deleteFromLL(head, 2));
    }
}
