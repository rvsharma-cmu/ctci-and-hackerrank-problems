package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * You are given an array which stores an implicit complete binary tree. Write a function
 * to traverse the implicit (sub) tree rooted at the given array index and print out its
 * in either pre-order(node before its children) or post-order(node after its children).
 * Print invalid if the given array index is out of range. In the implicit tree, the
 * children of array[k] are array[2k] and array[2k+1]. The root node is array[1];
 * array[0] is not used.
 */
public class ArrayToTreeTraversal {
    /*
     * Complete the function below.
     */

    static void traverse(String order, int rootIndex, int[] treeArr) {

        if(rootIndex != 1 || (!order.equals("post") && !order.equals("pre"))){
            System.out.println("Invalid");
            return;
        }
        if(order.equals("pre")) {
            traversePreOrder(rootIndex, treeArr);
        } else {
            traversePostOrder(rootIndex, treeArr);
        }
    }

    private static void traversePostOrder(int index, int[] treeArr) {

        if(index >= treeArr.length)
            return;

        traversePostOrder(2*index, treeArr);
        traversePostOrder(2*index+1, treeArr);
        System.out.println(treeArr[index]);
    }

    private static void traversePreOrder(int index, int[] treeArr) {

        if(index >= treeArr.length)
            return;

        System.out.println(treeArr[index]);
        traversePreOrder(2*index, treeArr);
        traversePreOrder(2*index+1, treeArr);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String _order;
        try {
            _order = in.nextLine();
        } catch (Exception e) {
            _order = null;
        }

        int _rootIndex = Integer.parseInt(in.nextLine().trim());
        int _treeArr_size = Integer.parseInt(in.nextLine().trim());
        int[] _treeArr = new int[_treeArr_size];
        for(int _treeArr_i = 0; _treeArr_i < _treeArr_size; _treeArr_i++) {
            _treeArr[_treeArr_i] = Integer.parseInt(in.nextLine().trim());
        }

        traverse(_order, _rootIndex, _treeArr);
    }
}
