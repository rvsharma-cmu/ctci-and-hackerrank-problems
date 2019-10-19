package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;

public class PlasticConservationMSTPrim {

    static class Position {
        int x, y;
        Position(int x_pos, int y_pos) { x = x_pos; y = y_pos ; }
        Position(Position pos) { x = pos.x ; y = pos.y ; }
    }

    static class Edge {
        Position p1, p2;
        int index1, index2; // indices into original Position[] array
        double length;
        Edge(Position pos1, int idx1, Position pos2, int idx2) {
            p1 = pos1;
            p2 = pos2;
            index1 = idx1;
            index2 = idx2;
            double xsquared = (p1.x - p2.x) * (p1.x - p2.x) ;
            double ysquared = (p1.y - p2.y) * (p1.y - p2.y) ;
            length = Math.sqrt(xsquared + ysquared) ;
        }
    }

    static class Vertex {
        int v;         // vertex number: its index in the original Position[] array
        Position pos;  // the position of this vertex
        Edge[] edges;  // the edges leaving this vertex
        Edge min_edge; // available for use by MST algorithm
        double min_length;
        int data;      // available for use by MST algorithm

        Vertex(Position[] positions, int index) {
            v = index;
            pos = positions[index];
            edges = new Edge[positions.length];
            min_edge = null;
            min_length = Double.POSITIVE_INFINITY;
        }

        // initialize edges from each vertex to all other vertices
        // you can ignore this function if you use make_graph() to convert
        // the array given to min_cost() into a graph
        static void set_edges(Vertex[] vertices) {
            if (vertices.length < 1) return;
            for (int i = 0; i < vertices.length; i++) {
                for (int j = 0; j < vertices.length; j++) {
                    Edge e ;
                    if (vertices[j].edges != null && vertices[j].edges[i] != null)
                        e = vertices[j].edges[i];
                    else if (j != i)
                        e = new Edge(vertices[i].pos,i,vertices[j].pos,j);
                    else
                        e = null;
                    vertices[i].edges[j] = e;
                }
            }
        }
    }

    // initialize edge graph, which is the complete graph between all positions
    static Vertex[] make_graph(Position[] positions) {
        if (positions == null) return new Vertex[0];
        Vertex[] vertices = new Vertex[positions.length];
        for (int i = 0 ; i < positions.length; i++) {
            vertices[i] = new Vertex(positions,i) ;
        }
        Vertex.set_edges(vertices);
        return vertices;
    }

    static double min_cost(Position[] positions) {

        int N = positions.length;
        if(N==0||N==1) return 0.0;
        Vertex[] vertices = make_graph(positions);
        int[] parent = new int[N];
        double[] keyvalue = new double[N];
        Boolean[] visited = new Boolean[N];
        Arrays.fill(visited, false);
        for(int i = 0; i < N; i++){
            keyvalue[i] = vertices[i].min_length;
        }
        parent[vertices[0].v] = -1;
        keyvalue[vertices[0].v] = 0;

        for(int i = 0; i < N; i++){

            int u = prim(N, keyvalue, visited);
            visited[u] = true;
            for(Edge edge : vertices[u].edges){
                if(edge==null) {
                    continue;
                }
                if(vertices[edge.index1] != vertices[u] && !visited[edge.index1] && vertices[edge.index2].min_length < keyvalue[edge.index1]){
                    keyvalue[edge.index1] = vertices[edge.index2].min_length;
                    parent[edge.index1] = vertices[u].v;
                }
            }

        }
        double score = 0.0;

        for(int i = 1; i < N; i++){
            score += vertices[parent[i]].min_length;
        }
        return score;
    }

    private static int prim(int n, double[] keyvalue, Boolean[] visited) {
        double minimum = Double.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i] && keyvalue[i] < minimum){
                minimum = keyvalue[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


    private static double prim_mst(Position[] positions) {
        Vertex[] vertices = make_graph(positions);
        int N = positions.length;
        if(N ==0 || N == 1){
            return 0.0;
        }
        double total = 0.0;
        Boolean[] visit = new Boolean[N];
        Arrays.fill(visit, false);

        PriorityQueue<Edge> allEdges = new PriorityQueue<>((N * (N - 1) / 2), new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.length, o2.length);
            }
        });

        for(Vertex eachV : vertices){
            for(Edge e : eachV.edges){
                if(e != null && !allEdges.contains(e)){
                    allEdges.add(e);
                }
            }
        }
        visit[allEdges.peek().index1] = true;
        while(!allEdges.isEmpty()){
            Edge currEdge = allEdges.poll();
            int nextIdx = visit[currEdge.index1] ? currEdge.index2 : currEdge.index1;
            double length = currEdge.length;
            if(!visit[nextIdx]){
                total += length;
                visit[nextIdx] = true;
                for(Edge neighborEdges : vertices[nextIdx].edges){
                    if(neighborEdges!=null && !allEdges.contains(neighborEdges)) allEdges.add(neighborEdges);
                }
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int N = Integer.parseInt(in.nextLine().trim());
        Position[] positions = new Position[N];
        for (int i = 0; i < N; i++) {
            String[] coords = in.nextLine().trim().split(new String(" "));
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            positions[i] = new Position(x,y);
        }
        PrintStream err = System.err;
        System.setErr(System.out);
        try {
            // round result to three places to avoid cumulative rounding errors causing 'wrong result'
            long res = (long)(1000.0*min_cost(positions)+0.5);
            bw.write(String.valueOf(res/1000.0));
            bw.newLine();
        } catch (Exception e) {
            printException(err,e);
        }
        bw.close();
    }

    private static void printException(PrintStream err, Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; ++i) {
            if (trace[i].getClassName().equals("PlasticConservationMSTPrim")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
