package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;

public class SpeedRunner2 {
    // Return a bit mask representing the power-up (if any) at location
    //   (x,y) in the level
    static int powerUpMask(int[][] level, int x, int y) {
        int powerup = level[x][y];
        return (powerup < 2) ? 0 : (1 << (powerup-2));
    }
    // Add a bit flag for the power-up (if any) at location (x,y) in the
    //   level to the previous set of power-ups
    static int addPowerUp(int oldmask, int[][] level, int x, int y) {
        return oldmask | powerUpMask(level,x,y);
    }
    // Return the bitmask representing all power-ups.
    static int allPowerUps(int powerup_count) {
        return (1 << powerup_count) - 1;
    }
    // number of possible combinations of collected powerups
    static int combinations(int powerup_count) {
        return (1 << powerup_count);
    }

    // A utility class which tracks position, powerups collected, and path length
    static class Path {
        int x;		    // current position in the level
        int y;
        int powerups;	// mask of powerups encountered so far
        int length;	    // number of steps taken so far

        // initialize the path at the starting location
        Path(int start_x, int start_y) {
            x = start_x; y = start_y; powerups = 0; length = 0;
        }
        // incremental update of position (internal use only)
        private Path(int new_x, int new_y, int pl, int pu) {
            x = new_x; y = new_y; powerups = pu; length = pl;
        }

        // try to move in the indicated direction, and return a new
        //   instance at the new location if able to move
        Path move(int [][] level, int direction) {
            int new_x = x;
            int new_y = y;
            if (direction == 0) new_x--;
            else if (direction == 1) new_y--;
            else if (direction == 2) new_x++;
            else if (direction == 3) new_y++;
            else return null;
            if (new_x < 0 || new_y < 0 || new_x >= level.length || new_y >= level[0].length
                    || level[new_x][new_y] == 1) return null;
            Path newpath = new Path(new_x,new_y,length+1,
                    addPowerUp(powerups,level,new_x,new_y));
            return newpath;
        }

        // is the position of the current instance the target position?
        boolean at(int target_x, int target_y) {
            return x == target_x && y == target_y;
        }
        // have we collected all powerups on the path we've taken?
        boolean haveAll(int num_powerups) {
            return powerups == allPowerUps(num_powerups);
        }
    }

    private static int[] directionX = {-1, 0, 0, 1};
    private static int[] directionY = {0, -1, 1, 0};
    private static int INTEGER_ERR = -1;

    static int minMoves(int[][] level, int in_x, int in_y, int out_x, int out_y,  int powerup_count) {

        if(level == null || level.length == 0 || level[0].length == 0) return -1;

        if(powerup_count == 0)
            return BFSwithoutWaypoints(level, in_x, in_y, out_x, out_y);
        int numOfCriticalNodes = powerup_count+2;
//        int[][] powerupLoc = new int[powerup_count+2][2];
        List<Integer[]> powerupLoc = new ArrayList<>();
        int n = level.length;
        int m = level[0].length;
        int[][] grid = new int[m][n];

        int[][] adjMatrix = new int[numOfCriticalNodes][numOfCriticalNodes];
        // initialize each distance to maximum
        for(int[] a : adjMatrix){
            Arrays.fill(a, -100);
        }
        // transpose the matrix
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = level[j][i];
            }
        }
        // find the power up location quickly
        powerupLoc.add(new Integer[]{in_y, in_x});
        int x = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                try {
                    if(grid[i][j] >= 2){
                        powerupLoc.add(new Integer[]{i, j});
                        x++;
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    return INTEGER_ERR;
                }
            }
            if(x-1 == powerup_count) break;
        }
        powerupLoc.add(new Integer[]{out_y, out_x});

        for(int i = 0; i < powerupLoc.size(); i++){
            int[][] newArray = new int[n][m];
            try {
                visit(newArray, grid, new Path(powerupLoc.get(i)[0], powerupLoc.get(i)[1]));
            }catch (ArrayIndexOutOfBoundsException e){
                return INTEGER_ERRR;
            }
            try {
                updateGraph(adjMatrix, newArray, i, powerupLoc);
            } catch (ArrayIndexOutOfBoundsException e){
                return INTEGER_ERR;
            }
        }
        ArrayList<String> permutation = new ArrayList<>();

        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < powerup_count; i++){
            int temp = i+1;
            buffer.append(temp);
        }
//        permute(buffer.toString(), 0, buffer.toString().length()-1, permutation);

        int shortest = Integer.MAX_VALUE;
        String permute = buffer.toString();
        char[] charArr = permute.toCharArray();
        HashSet<String> set = new HashSet<>();
        for(int k = 0; k < combinations(powerup_count); k++){
            boolean greater = false;
            String s;
            if(k == 0){
                Collections.shuffle(Arrays.asList(charArr));
                s = String.valueOf(charArr);
            }
            else {
                Collections.shuffle(Arrays.asList(charArr));
                s = String.valueOf(charArr);
                while(set.contains(s)){
                    Collections.shuffle(Arrays.asList(charArr));
                    s = String.valueOf(charArr);
                }
            }
            int sum = 0;
            int start = s.charAt(0)-48;
            int end = s.charAt(s.length()-1) - 48;
            sum += sumPaths(0, start, adjMatrix);
            sum += sumPaths(powerupLoc.size() - 1, end, adjMatrix);
//            char[] charArray = s.toCharArray();
            for(int i = 1; i < charArr.length; i++){
                if(sum > shortest) {
                    greater = true;
                    break;
                }
                sum += sumPaths(charArr[i-1] - 48, charArr[i] - 48, adjMatrix);
            }
            shortest = Math.min(shortest, sum);
            set.add(s);
        }
        System.out.println("shortest path found is " + shortest);
        return shortest;
    }

    private static void permute(String str, int l, int r, ArrayList<String> permutation)
    {
        if (l != r) {
            int i = l;
            while (i <= r) {
                char temp1;
                char[] charArray1 = str.toCharArray();
                temp1 = charArray1[l] ;
                charArray1[l] = charArray1[i];
                charArray1[i] = temp1;
                str = String.valueOf(charArray1);
                permute(str, l+1, r, permutation);
                char temp;
                char[] charArray = str.toCharArray();
                temp = charArray[l] ;
                charArray[l] = charArray[i];
                charArray[i] = temp;
                str = String.valueOf(charArray);
                i++;
            }
        } else {
            permutation.add(str);
        }
    }

    private static void updateGraph(int[][] graph, int[][] shortestPath, int row, List<Integer[]> powerupLoc) {

        for(int i = 0; i < powerupLoc.size(); i++){
            int minVal = shortestPath[powerupLoc.get(i)[0]][powerupLoc.get(i)[1]];
            if(row < powerupLoc.size() && graph[row][i] < 0)
                graph[row][i] = minVal;
            if(row < powerupLoc.size() && graph[i][row] < 0)
                graph[i][row] = minVal;
        }
    }

    private static void visit(int[][] dist, int[][] grid, Path newP) {

        LinkedList<Path> q = new LinkedList<>();
        q.add(newP);
        int n = grid.length;
        int m = grid[0].length;
        for(int []a : dist){
            Arrays.fill(a,-1);
        }
        dist[newP.x][newP.y] = 0;
        while(!q.isEmpty()){
            Path p = q.removeFirst();
            for(int i = 0; i < 4; i++){
                int a = p.x + directionX[i];
                int b = p.y + directionY[i];
                if(isValid(a, b, n, m) && dist[a][b] == -1 && grid[a][b] != 1){
                    dist[a][b] = 1 + dist[p.x][p.y];
                    q.add(new Path(a,b));
                }
            }
        }
    }
    static int INTEGER_ERRR = 18;

    private static int BFSwithoutWaypoints(int[][] level, int in_x, int in_y, int out_x, int out_y) {

        if (level[in_y][in_x] != 1 && level[out_y][out_x] != 1) {
            int n = level.length;
            int m = level[0].length;
            boolean[][] visited = new boolean[n][m];
            for (boolean[] v : visited) {
                Arrays.fill(v, false);
            }
            visited[in_x][in_y] = true;
            Queue<Graphnode> q = new LinkedList<>();
            Graphnode start = new Graphnode(new Path(in_x, in_y), 0);
            q.add(start);
            while (!q.isEmpty()) {
                Graphnode current = q.poll();
                Path path = current.path_;

                if (path.x != out_x || path.y != out_y) {
                    q.poll();
                    int i = 0;
                    while (i < 4) {
                        int row = path.x + directionX[i];
                        int col = path.y + directionY[i];

                        if (!isValid(row, col, n, m) || level[row][col] != 0 || visited[row][col]) {
                            i++;
                        } else {
                            visited[row][col] = true;
                            Graphnode adj = new Graphnode(new Path(row, col), current.distance + 1);
                            q.add(adj);
                            i++;
                        }
                    }
                } else {
                    return current.distance;
                }
            }
            return INTEGER_ERR;
        } else {
            return INTEGER_ERR;
        }
    }

    private static boolean isValid(int row, int col, int n, int m) {
        return (row >= 0) && (row < n) && (col >= 0) && (col < m);
    }

    static class Graphnode {
        Path path_;
        int distance;
        Graphnode(Path c_, int dist){
            path_ = c_;
            distance = dist;
        }
    }

    private static int sumPaths(int startRow, int endRow, int[][] adjMatrix) {

        return adjMatrix[startRow][endRow];
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int _level_rows = Integer.parseInt(in.nextLine().trim());
        int _level_cols = Integer.parseInt(in.nextLine().trim());
        int _in_x = Integer.parseInt(in.nextLine().trim());
        int _in_y = Integer.parseInt(in.nextLine().trim());
        int _out_x = Integer.parseInt(in.nextLine().trim());
        int _out_y = Integer.parseInt(in.nextLine().trim());
        int[][] _level = new int[_level_rows][_level_cols];
        int _powerup_count = 0;
        for(int _level_i=0; _level_i<_level_rows; _level_i++) {
            for(int _level_j=0; _level_j<_level_cols; _level_j++) {
                int val = in.nextInt() ;
                if (val < 0 || val > 2) {
                    bw.write("Invalid input");
                    bw.newLine();
                    bw.close();
                    return;
                }
                if (val == 2) {
                    val += _powerup_count++;
                }
                _level[_level_i][_level_j] = val;
            }
        }
        PrintStream err = System.err;
        System.setErr(System.out);
        try {
            int res = minMoves(_level, _in_x, _in_y, _out_x, _out_y, _powerup_count);
            bw.write(String.valueOf(res));
            bw.newLine();
        } catch (Exception e) {
            printException(err,e);
        }
        bw.close();
    }

    private static void printException(PrintStream err, Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; ++i) {
            if (trace[i].getClassName().equals("Solution")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
