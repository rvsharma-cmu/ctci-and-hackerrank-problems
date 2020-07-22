package com.rvsharma.Hackerrank.CodePath.LinkedLists;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class LRUCaches {



    public static class Node {
        int key, val;
        Node next, prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }

        public Node(){
            this(0,0);
        }
    }

    static class LRUCache {

        private Node head, tail;
        private int count, capacity;
        private Map<Integer, Node> cache = new HashMap<>();
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.count = 0;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if(node == null){
                return -1;
            }
            update(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if(node == null){
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                add(newNode);
                count++;
            } else{
                node.val = value;
                update(node);
            }
            if (count > capacity) {
                Node toRemove = tail.prev;
                remove(toRemove);
                cache.remove(toRemove.key);
                count--;
            }
        }

        public void update(Node node){
            remove(node);
            add(node);
        }

        public void remove(Node n){
            Node headNext = head.next;
            head.next = n;
            n.prev = head;
            n.next = headNext;
            headNext.prev = n;
        }

        public void add(Node n){
            Node before = n.prev, after = n.next;
            before.next = after;
            after.prev = before;
        }
    }

    static class Operation {
        public Operation(String op, int key, int value) {
            this.op = op;
            this.key = key;
            this.value = value;
        }
        public String op;
        public int key;
        public int value;
    }
    static String getOutput(int capacity, Operation[] sequence) {
        LRUCache cache = new LRUCache(capacity);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i].op.equals("PUT")) {
                cache.put(sequence[i].key, sequence[i].value);
            } else {
                sb.append(cache.get(sequence[i].key) + "\n");
            }
        }
        return sb.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int capacity = Integer.parseInt(scanner.nextLine());
        int numOps = Integer.parseInt(scanner.nextLine());
        Operation[] operations = new Operation[numOps];
        for (int i = 0; i < numOps; i++) {
            String[] line = scanner.nextLine().split(" ");
            if (line[0].equals("PUT"))
                operations[i] = new Operation(line[0],
                        Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            else
                operations[i] = new Operation(line[0],
                        Integer.parseInt(line[1]), -1);
        }

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        String output = getOutput(capacity, operations);

        bufferedWriter.write(output);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

