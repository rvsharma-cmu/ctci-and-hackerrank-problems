package com.rvsharma.Hackerrank;

import com.rvsharma.Ch02LinkedList.Introduction.LLNode;

import static com.rvsharma.Ch02LinkedList.Introduction.LLNode.printLinkedList;

public class LLDelGreaterThan {

    private static LLNode deleteGreaterThan(LLNode head, int x){

        LLNode temp = head;
        LLNode prev = null;

        // change the reference to head till you find a smaller element
        while(temp != null && temp.data > x) {
            head = temp.next;
            temp = head;
        }
        // loop till the end of the list
        while(temp != null){
            //loop till you find a larger element
            while(temp!=null && temp.data <= x){
                prev= temp;
                temp = temp.next;
            }
            // if you reach the end of the loop, return
            if (temp == null) return head;
            // change the next of prev to temp.next
            prev.next = temp.next;
            // change temp to prev.next
            temp = prev.next;
        }
        return head;
    }

    public static void main(String[] args){

        LLNode head = new LLNode(5);
        head.appendToTail(15);
        head.appendToTail(4);
        head.appendToTail(6);
        head.appendToTail(10);
        head.appendToTail(14);
        head.appendToTail(2);
        printLinkedList(head);
        // delete all values greater than 7
        LLNode result = deleteGreaterThan(head, 1);
        printLinkedList(result);
    }
}