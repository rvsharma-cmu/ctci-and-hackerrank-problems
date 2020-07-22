package com.rvsharma.Hackerrank.CodePath.LinkedLists;

import java.util.PriorityQueue;

public class MergeKSortedLL {
    public static SLNode mergeKSorted (SLNode[] lists){
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<SLNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for(SLNode list : lists){
            if(list != null) {
                pq.add(list);
            }
        }
        SLNode dummy = new SLNode(-1), traverse = dummy;
        while(!pq.isEmpty()){
            SLNode temp = pq.poll();
            traverse.next = temp;
            traverse = temp;
            if(temp.next != null){
                pq.add(temp.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        SLNode[] slNodes = new SLNode[3];
        slNodes[0] = LLUtilities.createSL(new int[]{1, 4, 5});
        slNodes[1] = LLUtilities.createSL(new int[]{1, 3, 4});
        slNodes[2] = LLUtilities.createSL(new int[]{2, 6});
        LLUtilities.printLinkedList(mergeKSorted(slNodes));
    }
}
