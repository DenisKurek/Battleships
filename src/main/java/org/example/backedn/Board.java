package org.example.backedn;

public class Board {
    protected int BOARD_SIZE =10;
    private final int NUMBER_OF_SHIPS = 5;
    private final int MAX_SHIP_SIZE = 5;
    protected double CELL_SIZE = 40;
    private Cell[][] cells;
    private Ship[] ships;
    public Board(){
        initialize();
    }
    public Board(int boardSize , double cellSize){
        this.BOARD_SIZE = boardSize;
        this.CELL_SIZE = cellSize;
        initialize();
    }
    private void initialize(){
        initializeCells();
        initializeShips();
    }

    private void initializeShips() {
        this.ships = new Ship[NUMBER_OF_SHIPS];
        for(int i=0;i<NUMBER_OF_SHIPS;i++){
            this.ships[i] = new Ship(i,i,CELL_SIZE,CELL_SIZE*(Math.max(MAX_SHIP_SIZE-i,0)));
        }
    }

    private void initializeCells() {
        this.cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0;i <BOARD_SIZE;i++){
            for (int j = 0;j <BOARD_SIZE;j++){
                this.cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE,CELL_SIZE);
            }
        }
    }
}
