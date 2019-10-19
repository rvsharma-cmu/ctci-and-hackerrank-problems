package com.rvsharma.Hackerrank;

import javafx.geometry.Pos;

import java.io.*;
import java.util.*;
import java.math.*;

class PlasticConservationMSTKruskalUF {

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

    static Vertex[] parent;
    static double min_cost(Position[] positions){
        int N = positions.length;
        if(N == 0 || N == 1) return 0.0;
        Vertex[] vertices = make_graph(positions);
        List<Edge> allEdges = new ArrayList<>();
        parent = new Vertex[N];
        double cost = 0.0;
        for(Vertex eachV : vertices){
            for(Edge e : eachV.edges){
                if(e != null && !allEdges.contains(e)){
                    allEdges.add(e);
                }
            }
            parent[eachV.v] = eachV;
        }
        Collections.sort(allEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.length, o2.length);
            }
        });
        for(Edge e : allEdges){
            Vertex v1 = vertices[e.index1];
            Vertex v2 = vertices[e.index2];
            if(find(v1) != find(v2)){
                cost = cost + e.length;
                union(v1, v2);
            }
        }
        return cost;
    }

    private static void union(Vertex v1, Vertex v2) {
        Vertex px = find(v1);
        Vertex py = find(v2);
        if(px != py){
            parent[px.v] = py;
        }
    }

    private static Vertex find(Vertex x){
        if(parent[x.v] == x){
            return parent[x.v];
        }
        return find(parent[x.v]);
    }

    private static double prim_mst(Position[] positions) {
        Vertex[] vertices = make_graph(positions);
        Boolean[] visited = new Boolean[vertices.length];
        Vertex initVertex = vertices[0];
        visited[0] = true;
        int totalLength = 0;
        while(initVertex != null){

            for(Edge everyEdge : initVertex.edges){

                int index = everyEdge.p1 == initVertex.pos ? everyEdge.index2 : everyEdge.index1;

                if(!visited[index] && everyEdge.length < vertices[index].min_length){
                    vertices[index].min_length = everyEdge.length;
                    vertices[index].min_edge = everyEdge;
                    totalLength += everyEdge.length;
                }
            }
            Double minLength = Double.MAX_VALUE;
            Edge minEdge = null;
            for(Edge neighborEdge : initVertex.edges){
                int index = neighborEdge.p1 == initVertex.pos ? neighborEdge.index2 : neighborEdge.index1;
                if (!visited[index] && neighborEdge.length < minLength) {
                    minLength = neighborEdge.length;
                    minEdge = neighborEdge;
                }
            }
            initVertex = minEdge.p1 == initVertex.pos ? vertices[minEdge.index2] : vertices[minEdge.index1];
        }
        return totalLength;
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
            if (trace[i].getClassName().equals("PlasticConservationMSTKruskalUF")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}

