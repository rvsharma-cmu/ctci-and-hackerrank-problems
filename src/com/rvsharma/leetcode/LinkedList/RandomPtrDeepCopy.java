package com.rvsharma.leetcode.LinkedList;

class RandomListNode {
    int label, index;
    RandomListNode next;
    RandomListNode random;

    public RandomListNode(int label, int val) {
        this.label = label;
        this.next = null;
        this.random = null;
        this.index = val;
    }
}

public class RandomPtrDeepCopy {

    public RandomListNode copyRandomList(RandomListNode head) {

        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label, -1);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        printRandomList(head);

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0, -100);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }
        return pseudoHead.next;
    }

    void printRandomList(RandomListNode head){

        RandomListNode iter = head;
        while(iter != null){
            int val = -1;
            if (iter.random != null) val = iter.random.index;
            System.out.print("[" + iter.label + ", " + val + "]");
            if (iter.next != null) System.out.print(", ");
            iter = iter.next;
        }
        System.out.println();
    }

    public static void main(String[] args){

        RandomPtrDeepCopy deepCopy = new RandomPtrDeepCopy();
        RandomListNode head = new RandomListNode(7, 0);
        RandomListNode l2 = new RandomListNode(13, 1);
        RandomListNode l3 = new RandomListNode(11, 2);
        RandomListNode l4 = new RandomListNode(10, 3);
        RandomListNode l5 = new RandomListNode(1, 4);
        head.next = l2; l2.next = l3; l3.next = l4; l4.next = l5;
        head.random = null;
        l2.random = head;
        l3.random = l5;
        l4.random = l3;
        l5.random = head;
        RandomListNode copiedList = deepCopy.copyRandomList(head);
    }
}
