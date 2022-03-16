package org.example.fxImplementation;

import org.example.backedn.Board;
import org.example.backedn.Cell;

public class FxBoard extends Board{
    private FxCell[][] board;
    public FxBoard(){
        initialize();
    }
    public FxBoard(int boardSize, double cellSize) {
        super(boardSize, cellSize);
        initialize();
    }
    public FxCell getCell(int i,int j) {
        return board[i][j];
    }

    private void initialize(){
        this.board = new FxCell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0;i <BOARD_SIZE;i++){
            for (int j = 0;j <BOARD_SIZE;j++){
                this.board[i][j] = new FxCell(i*CELL_SIZE, j*CELL_SIZE,CELL_SIZE);
            }
        }
    }
}
