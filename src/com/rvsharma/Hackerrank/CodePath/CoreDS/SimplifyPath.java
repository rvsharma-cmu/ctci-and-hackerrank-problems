package com.rvsharma.Hackerrank.CodePath.CoreDS;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

    public static String simplifyPath(String path) {
        if(path == null) return "/";

        String[] dirs = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for(String dir : dirs){
            if(dir.startsWith("/")){
                int idx = 0;
                while(idx < dir.length() && dir.charAt(idx) != '/'){
                    idx++;
                }
                dir = dir.substring(idx);
            }
            if (dir.equals(".")) continue;
            else if (dir.equals("..")){
                if(!stack.isEmpty()) stack.removeFirst();
            } else if (!dir.equals("")){
                stack.addFirst(dir);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while(!stack.isEmpty()){
            sb.append(stack.removeFirst());
            if(!stack.isEmpty()) sb.append("/");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/home//foo"));
    }
}
