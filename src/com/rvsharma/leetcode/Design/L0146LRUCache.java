package com.rvsharma.leetcode.Design;

import java.util.HashMap;
import java.util.Map;

public class L0146LRUCache {

    class Node{
        int k, v;
        Node prev;
        Node next;
        Node (int k, int v){
            this.k = k;
            this.v = v;
        }
        Node() {
            this(0,0);
        }
    }

    Map<Integer, Node> cache = new HashMap<>();
    int capacity, count;
    Node tail, head;

    public L0146LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        tail = new Node();
        head = new Node();
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        Node curr = cache.get(key);
        if (curr == null){
            return -1;
        }

        update(curr);
        return curr.v;
    }

    public void put(int key, int value) {
        Node curr = cache.get(value);
        if(curr == null){
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            add(newNode);
            count++;
        } else {
            curr.v = value;
            update(curr);
        }
        if(count > this.capacity){
            Node toRemove = tail.prev;
            remove(toRemove);
            cache.remove(toRemove.k);
            count--;
        }
    }

    private void update(Node curr){
        remove(curr);
        add(curr);
    }

    private void remove(Node toRemove){
        Node prev = toRemove.prev;
        Node next = toRemove.next;
        prev.next = next;
        next.prev = prev;
        toRemove.next = null;
        toRemove.prev = null;
    }

    private void add(Node toAdd){
        Node oldFirst = head.next;
        head.next = toAdd;
        toAdd.next = oldFirst;
        toAdd.prev = head;
        oldFirst.prev = toAdd;
    }

    public static void main(String[] args) {
        int cacheCap = 1;
        L0146LRUCache cache = new L0146LRUCache(cacheCap);
        cache.put(2,1);
        System.out.println(cache.get(2));
        cache.put(3,2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
