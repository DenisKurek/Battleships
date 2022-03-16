package org.example.backedn;

public class Board {
    private final int BOARD_SIZE =10;
    private final double CELL_SIZE = 40;
    private Cell[][] board;
    public Board(){
        this.board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0;i <BOARD_SIZE;i++){
            for (int j = 0;j <BOARD_SIZE;j++){
                this.board[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE,CELL_SIZE);
            }gi
        }
    }
}
