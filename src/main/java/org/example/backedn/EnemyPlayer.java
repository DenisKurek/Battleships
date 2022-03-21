package org.example.backedn;

import org.example.exceptions.InvalidPositionException;

import java.util.ArrayList;
import java.util.Random;

public class EnemyPlayer {
    Boolean[][] clicked;
    int[][] count;
    public Boolean make_move(Board board){
        clicked = new Boolean[GameSettings.getBoardSize()][GameSettings.getBoardSize()];
        count = new int[GameSettings.getBoardSize()][GameSettings.getBoardSize()];

        for(int i=0;i<GameSettings.getBoardSize();i++) {
            for (int j = 0; j < GameSettings.getBoardSize(); j++) {
                count[i][j] = 0;
            }
        }
        for(int i=0;i<GameSettings.getBoardSize();i++){
            for(int j=0;j<GameSettings.getBoardSize();j++){
                if(board.getCell(i,j).ifClicked()){
                    if(board.getCell(i,j).getState()== Cell.State.SHIP){
                        clicked[i][j] = false;
                        addValue(i,j,board);
                    }
                    else{
                        clicked[i][j]=true;
                    }
                }
                else{
                    clicked[i][j]=false;
                }
            }
        }
        Random random=new Random();

        for (int k=0;k<10000;k++){
            for(int i=0;i< board.getNumberOfShips();i++) {
                Ship ship = new Ship(random.nextInt(GameSettings.getBoardSize()),
                        random.nextInt(GameSettings.getBoardSize()),
                        board.getShips().get(i).size);
                if (random.nextBoolean()) {
                    ship.setVertical();
                }
                else {
                    ship.setHorizontal();
                }
                if(checkIfShipPositionValid(ship,ship.isVertical())){
                    int x=ship.getX();
                    int y=ship.getY();
                    if(ship.isVertical()){
                        for (int ii=0;ii<ship.getSize();ii++,y++){
                            count[x][y]++;
                        }
                    }
                    else{
                        for (int ii=0;ii<ship.getSize();ii++,x++){
                            count[x][y]++;
                        }
                    }
                }

            }
        }
        int rek=0;
        int besI=0,bestJ=0;
        for(int i=0;i<GameSettings.getBoardSize();i++){
            for(int j=0;j<GameSettings.getBoardSize();j++){
                if(rek<count[i][j] && !board.getCell(i,j).isClicked){
                    rek=count[i][j];
                    besI = i;
                    bestJ = j;
                }
            }
        }

        return board.Shoot(besI,bestJ);
    }

    private void addValue(int x, int y,Board board) {
        if(checkPosition(x,y+1)){
            count[x][y+1]=10000;
        }
        if(checkPosition(x,y-1)){
            count[x][y-1]=10000;
        }
        if(checkPosition(x+1,y)){
            count[x+1][y]=10000;
        }
        if(checkPosition(x-1,y)){
            count[x-1][y]=10000;
        }
        if(checkPosition(x-1,y)&&checkPosition(x+1,y)) {
            if (board.getCell(x - 1, y).isClicked && board.getCell(x - 1, y).getState() == Cell.State.SHIP) {
                count[x + 1][y] = 20000;
            }
            if (board.getCell(x + 1, y).isClicked && board.getCell(x + 1, y).getState() == Cell.State.SHIP) {
                count[x - 1][y] = 20000;
            }
        }
        if(checkPosition(x,y-1)&&checkPosition(x,y+1)) {
            if (board.getCell(x , y - 1).isClicked && board.getCell(x , y -1).getState() == Cell.State.SHIP) {
                count[x][y+1] = 20000;
            }
            if (board.getCell(x , y + 1).isClicked && board.getCell(x , y + 1).getState() == Cell.State.SHIP) {
                count[x][y -1] = 20000;
            }
        }
    }

    private boolean checkIfShipPositionValid(Ship ship,boolean isVertical) {
        int x=ship.getX();
        int y=ship.getY();
        if(isVertical){
            for (int i=0;i<ship.getSize();i++,y++){
                if(!checkPosition(x,y) || clicked[x][y]){
                    return false;
                }
            }
        }
        else{
            for (int i=0;i<ship.getSize();i++,x++){
                if(!checkPosition(x,y) || clicked[x][y]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkPosition(int x, int y) {
        if(x<0 || x>= GameSettings.getBoardSize()){
            return false;
        }
        if(y<0 || y>= GameSettings.getBoardSize()){
            return false;
        }
        return true;
    }
}
