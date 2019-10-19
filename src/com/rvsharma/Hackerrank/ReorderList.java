package com.rvsharma.Hackerrank;

import java.util.LinkedList;
import java.util.Scanner;

public class ReorderList {
    public static class LinkedListNode{
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    }

    static LinkedListNode reorderList(LinkedListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return head;
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        LinkedListNode prev = null;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        LinkedListNode lastNode = null;
        if(fast == null){
            lastNode = prev.next;
            prev.next = reverseLinkedList(slow);
        } else {
            prev = prev.next;
            prev.next = reverseLinkedList(slow.next);
        }
        fast = head;
        System.out.println("Linkedlist after reversing");
        printLinkedList(fast);
        slow = prev.next;
        prev.next = null;
        LinkedListNode temp1;
        LinkedListNode temp2;
        while(slow != null && fast != prev){
            temp1 = fast.next;
            temp2 = slow.next;
            slow.next = fast.next;
            fast.next = slow;
            fast = temp1;
            slow = temp2;
        }
        if(fast == prev){
            fast.next = lastNode;
        }
        return head;
    }

    /*
     * returns the head of the reversed LinkedList
     */
    private static LinkedListNode reverseLinkedList(LinkedListNode node) {
        LinkedListNode prev = null;
        LinkedListNode temp;
        LinkedListNode current = node;
        while(current != null){
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    /*
     * For debugging
     */
    static void printLinkedList(LinkedListNode head){

        if(head == null) return;
        LinkedListNode temp = head;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int _head_size = _head_size = Integer.parseInt(in.nextLine());
        LinkedListNode _head = null;
        LinkedListNode _tail = null;
        for (int _head_i = 0; _head_i < _head_size; _head_i++) {
            int _item;
            try {
                _item = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                _item = -1 ;
            }
            if (_tail != null) {
                _tail.next = new LinkedListNode(_item);
                _tail = _tail.next;
            } else
                _tail = _head = new LinkedListNode(_item);
        }

        LinkedListNode newHead = reorderList(_head);
        while(newHead != null){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
