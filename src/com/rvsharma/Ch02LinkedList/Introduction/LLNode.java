package com.rvsharma.Ch02LinkedList.Introduction;

public class LLNode {
    public LLNode next = null;
    public int data;

    public LLNode(int data){
        this.data = data;
    }

    static public void printLinkedList(LLNode head){

        LLNode current = head;
        while(current != null){
            System.out.print(current.data);
            if(current.next != null) System.out.print("->");
            current = current.next;
        }
        System.out.println();
    }

    public void appendToTail(int d){
        LLNode node = new LLNode(d);
        LLNode temp = this;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public LLNode deleteNode(LLNode head, int data){
        LLNode t = head;

        if (t.data == data) {
            return head.next;
        }

        while (t.next != null) {
            if (t.next.data == data) {
                t.next = t.next.next;
                return head;
            }
            t=t.next;
        }
        return head;
    }
}
