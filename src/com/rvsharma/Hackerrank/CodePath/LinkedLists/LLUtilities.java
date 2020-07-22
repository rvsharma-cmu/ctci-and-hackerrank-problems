package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class LLUtilities {

    public LLUtilities(){

    }

    /**
     * Print single LinkedList
     * @param head The start of the linked-list to be printed.
     */
    public static void printLinkedList(SLNode head){
        SLNode tr = head;
        while (tr != null) {
            System.out.print(tr.val);
            if(tr.next != null)
                System.out.print(" -> ");
            tr = tr.next;
        }
        System.out.println();
    }

    /**
     * Print Double LinkedList
     * @param head The start of the linked-list to be printed.
     */
    public static void printLinkedList(DLNode head){
        DLNode tr = head;
        while (tr != null) {
            System.out.print(tr.val);
            if(tr.next != null)
                System.out.print(" --> ");
            tr = tr.next;
        }
        System.out.println();
    }

    /**
     * Creates a Singly linked-list from the given input
     * @param arr Input array to convert to linked list. Numbers must be ordered naturally as desired in the
     *            output linked list.
     * @return returns the linkedlist created from the input array
     */
    public static SLNode createSL(int[] arr){
        SLNode dummy = new SLNode(-1), temp;
        temp = dummy;
        for(int x : arr){
            SLNode newNode = new SLNode(x);
            temp.next = newNode;
            temp = newNode;
        }
        return dummy.next;
    }

    /**
     * Creates a doubly linked list from the given input
     * @param arr Input array to convert to linked list. Numbers must be ordered naturally as desired in the
     *            output linked list.
     * @return Returns the doubly linked list according to the input given.
     */
    public static DLNode createDL(int[] arr){
        DLNode dummy = new DLNode(-1), temp;
        temp = dummy;
        for(int x : arr){
            DLNode newNode = new DLNode(x);
            temp.next = newNode;
            newNode.prev = temp;
            temp = newNode;
        }
        return dummy.next;
    }

    /**
     * Reverses a single linkedlist in place
     * @param head The start of the linkedlist to reverse
     * @return The head of the resultant linkedlist
     */
    public static SLNode reverseSLL(SLNode head){
        if(head == null) return null;
        SLNode newHead = null, curr = head;
        while(curr != null) {
            SLNode next = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = next;
        }
        return newHead;
    }
}
