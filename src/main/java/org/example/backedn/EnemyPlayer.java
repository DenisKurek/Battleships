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
                        for(int ii=-1;ii<2;ii++){
                            if(checkPosition(ii+i,j)){
                                count[i+ii][j]=10000;
                            }
                        }
                        for(int jj=-1;jj<2;jj++){
                            if(checkPosition(i,j+jj)){
                                count[i][jj+j]=10000;
                            }
                        }
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
