package com.rvsharma.Hackerrank;

import java.util.Scanner;
import java.util.Stack;

/**
 * You could use a simple method to follow the path to conversion as given in this article
 * {@url http://blog.moertel.com/posts/2013-05-11-recursive-to-iterative.html}
 *
 * Or just use a stack
 */
public class RecursionToIteration {

    private static int factRecursive(int n){

        if(n < 2)
            return 1;
        return n * factRecursive(n-1);
    }

    private static int factorialIterative(int n){
        return factHelper(n, 1);
    }

    private static int factHelper(int n, int acc){

        while(n > 1){
//            return factHelper(n -1 , acc*n );
            acc = acc * n;
            n = n - 1;
        }
        return acc;
    }

    public static int factIterativeStack(int n){
        Stack<Integer> st = new Stack<>();
        st.push(n);
        int result = 1;
        while(!st.isEmpty()){
            if(n < 2){
                break;
            }
            result = result * n;
            n -= 1;
            st.push(n-1);
        }
        return result;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Result = " + n +"! = " + factIterativeStack(n));
    }
}