package com.rvsharma.Hackerrank.CodePath.LinkedLists;

public class DetectCycleII {

    public static SLNode getIntersection(SLNode head){
        SLNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }

    public static SLNode detectCycleII(SLNode head){
        if(head == null) return null;

        SLNode intersect = getIntersection(head);
        if (intersect == null) {
            return null;
        }

        SLNode start = head;
        while (start != intersect) {
            start = start.next;
            intersect = intersect.next;
        }
        return intersect;
    }

    public static void main(String[] args) {
        SLNode head = LLUtilities.createSL(new int[]{3, 2, 0, -4});
        head.next.next.next.next = head.next;
        SLNode intersect = detectCycleII(head);
        SLNode t = intersect;
        System.out.print(t.val + " -> ");
        t = intersect.next;
        while (t != intersect) {
            System.out.print(t.val);
            if(t.next != intersect) System.out.print(" -> ");
            t = t.next;
        }
    }
}
