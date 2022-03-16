package org.example.backedn;

public class Board {
    protected int BOARD_SIZE =10;
    protected double CELL_SIZE = 40;
    private Cell[][] board;
    public Board(){
        initialize();
    }
    public Board(int boardSize , double cellSize){
        this.BOARD_SIZE = boardSize;
        this.CELL_SIZE = cellSize;
        initialize();
    }
    private void initialize(){
        this.board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0;i <BOARD_SIZE;i++){
            for (int j = 0;j <BOARD_SIZE;j++){
                this.board[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE,CELL_SIZE);
            }
        }
    }
}
