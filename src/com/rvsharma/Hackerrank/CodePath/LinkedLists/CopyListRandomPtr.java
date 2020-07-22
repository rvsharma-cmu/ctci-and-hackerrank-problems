package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class CopyListRandomPtr {
    // Definition for a Node.
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head){
        if (head == null) {

            return null;
        }
        // eg. 1 -> 2 -> 3 -> 4


        // first copy all nodes and insert them right after their originals
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> 4 -> 4'
        Node iter = head, nextNode;
        while (iter != null) {
            nextNode = iter.next;
            Node copy = new Node(iter.val);
            iter.next = copy;
            copy.next = nextNode;
            iter = nextNode;
        }
        // after copying, initialize random ptr for these copies
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                Node copy = iter.next;
                copy.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // now segregate these two linked lists
        iter = head;
        Node dummyNode = new Node(0);
        Node copyIter = dummyNode;
        while (iter != null) {
            nextNode = iter.next.next;
            Node copy = iter.next;
            iter.next = nextNode;
            copyIter.next = copy;
            copyIter = copy;
            iter = nextNode;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        Node head = createEmptyRandom(new int[]{7, 13, 11, 10, 1});
        if (head != null) {
            head.next.random = head;
            head.next.next.random = head.next.next.next.next;
            head.next.next.next.random = head.next.next;
            head.next.next.next.next.random = head;
        }

        Node res = copyRandomList(head);
    }

    public static Node createEmptyRandom(int[] arr){
        if (arr.length == 0) return null;
        Node dummy = new Node(0), tr = dummy;
        for (int x : arr) {
            tr.next = new Node(x);
            tr = tr.next;
        }
        return dummy.next;
    }
}
