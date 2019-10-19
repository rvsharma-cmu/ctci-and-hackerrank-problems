package com.rvsharma.Hackerrank;

public class BoardPosition {
    public int num_rows ;
    public int num_columns ;
    public int row ;
    public int column ;

    BoardPosition(int rows, int columns) {
        num_rows = rows;
        num_columns = columns;
        row = column = 0;
    }

    BoardPosition(BoardPosition pos) {
        num_rows = pos.num_rows;
        num_columns = pos.num_columns;
        row = pos.row;
        column = pos.column;
    }

    void check_validity() {
        if (row < 0 || row >= num_rows
                || column < 0 || column >= num_columns) {
            row = column = -1;
        }
        return;
    }

    boolean valid() {
        return row != -1 && column != -1;
    }

    BoardPosition move(char how) {
        BoardPosition new_pos = new BoardPosition(this);
        if (how == 'U') {
            new_pos.row--;
        } else if (how == 'D') {
            new_pos.row++;
        } else if (how == 'L') {
            new_pos.column--;
        } else if (how == 'R') {
            new_pos.column++;
        } else { // how == '*'
            // position is unchanged
        }
        new_pos.check_validity();
        return new_pos.valid() ? new_pos : null;
    }
}
