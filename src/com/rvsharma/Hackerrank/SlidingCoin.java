package com.rvsharma.Hackerrank;

import java.io.*;
import java.util.*;

public class SlidingCoin {

    private static char[] directions = {'L', 'R', 'U', 'D'};
    static class Cell extends BoardPosition{
        char direction;
        int ops;
        int time;

        Cell(BoardPosition position, char direction){
            super(position);
            this.ops = Integer.MAX_VALUE;
            this.time = 0;
            this.direction = direction;
        }
    }

    static int minimum_ops(char[][] board, int time_K, int row, int column){
        int N = board.length;
        if(N==0) return -1;
        int M = board[0].length;
        Cell[][] completeBoard = new Cell[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                char direction = board[i][j];
                BoardPosition boardPosition = new BoardPosition(N, M);
                boardPosition.row = i;
                boardPosition.column = j;
                completeBoard[i][j] = new Cell(boardPosition, direction);
            }
        }
        int min = bfs(board, time_K, row, column, completeBoard);

        if (min != Integer.MAX_VALUE) {
            return min;
        } else {
            return -1;
        }
    }

    private static int bfs(char[][] board, int time_K, int row, int column, Cell[][] completeBoard) {
        Cell cell0 = completeBoard[0][0];

        int min = Integer.MAX_VALUE;
        Queue<Cell> cellQueue = new LinkedList<>();
        cellQueue.add(cell0);

        cell0.ops = 0;
        while (!cellQueue.isEmpty()) {
            Cell currCell = cellQueue.poll();
            if (currCell.time <= time_K) {

                if (currCell.direction != board[row][column] || currCell.ops >= min) {
                    int dir_i = 0;
                    while (dir_i <= 3) {
                        int r = dir_i < 2 ? currCell.row : (dir_i%2==0 ? currCell.row-1: currCell.row+1);
                        int c = dir_i < 2 ? (dir_i%2==0 ? currCell.column -1 : currCell.column + 1) : currCell.column;
                        if(invalidPosition(r, c, completeBoard)) {
                            dir_i++;
                            continue;
                        }
                        Cell neighborCell = completeBoard[r][c];
                        int newOpsCount = currCell.direction != directions[dir_i] ? currCell.ops + 1 : currCell.ops;
                        if (neighborCell.ops >= newOpsCount) {
                            neighborCell.ops = newOpsCount;
                            neighborCell.time = currCell.time + 1;
                            if (cellQueue.contains(neighborCell)) {
                                dir_i++;
                                continue;
                            }
                            cellQueue.add(neighborCell);
                        }
                        dir_i++;
                    }
                } else {
                    min = currCell.ops;
                }
            } else break;
        }
        return min;
    }

    private static boolean invalidPosition(int row, int col, Cell[][] cell) {
        return row < 0 || row >= cell.length || col < 0 || col >= cell[0].length;
    }

    private static void addToQueue(Queue<Cell> queue, int row, int col, Cell[][] completeBoard, Cell currentCell, char expectedDirection) {

        if (invalidPosition(row, col, completeBoard)) return;

        Cell pos = completeBoard[row][col];
        int newChangeCount;

        if (currentCell.direction == expectedDirection) {
            newChangeCount = currentCell.ops;
        }else{
            newChangeCount = currentCell.ops + 1;
        }
        if (pos.ops < newChangeCount) {
            return;
        }

        pos.ops = newChangeCount;
        pos.time = currentCell.time + 1;

        if(!queue.contains(pos)){
            queue.add(pos);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int res;
        String[] params = in.nextLine().split(" ") ;
        int _board_rows = Integer.parseInt(params[0]);
        int _board_cols = Integer.parseInt(params[1]);
        int _time_K = Integer.parseInt(params[2]);

        int _row = 0;
        int _column = 0;

        char[][] _board = new char[_board_rows][_board_cols];
        try {
            for(int _board_i=0; _board_i<_board_rows; _board_i++) {
                String row = in.nextLine();
                for(int _board_j=0; _board_j<_board_cols; _board_j++) {
                    char c = row.charAt(_board_j);
                    _board[_board_i][_board_j] = c;
                    if (c == '*') {
                        _row = _board_i;
                        _column = _board_j;
                    }
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input - line too short");
            return;
        }
        PrintStream err = System.err;
        System.setErr(System.out);
        try {
            res = minimum_ops(_board, _time_K, _row, _column);
            System.out.println(res);
        } catch (Exception e) {
            printException(err,e);
        }
    }

    private static void printException(PrintStream err, Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        System.out.println(Arrays.toString(trace));
        for (int i = 0; i < trace.length; ++i) {
            if (trace[i].getClassName().equals("SlidingCoin")) {
                err.println(e.getClass().getName()+" at line "+trace[i].getLineNumber());
                System.exit(1);
            }
        }
        err.println(e.getClass().getName());
        System.exit(1);
    }
}
