package org.example.backedn;

import java.util.Random;

/**
 * klasa reprezentująca przeciwnika
 */
public class EnemyPlayer {
    /**
     * liczba pozycji statków rozpatrywana przez gracza
     */
    private static final int NUMBER_OF_SYMULATED_BOARD_POSITIONS = 10000;
    /**
     * tablica przechowująca informację czy dane pole zostało już kliknięte
     */
    private Boolean[][] clicked;
    /**
     * tablica przechowująca wartość przypisaną danemu polu (gracz klika w pole o największej wartości)
     */
    private int[][] value;

    /**
     * funkcja wykonująca ruch na podanej planszy
     * @param board plansza, na której gracz wykona ruch
     * @return  TRUE, jeżeli gracz trafił w statek FALSE, jeżeli spudłował
     */
    public Boolean make_move(Board board){
        //inicializacja tablic wewnętrznych obiektu enemyPlayer
        clicked = new Boolean[GameSettings.getBoardSize()][GameSettings.getBoardSize()];
        value = new int[GameSettings.getBoardSize()][GameSettings.getBoardSize()];
        for(int i=0;i<GameSettings.getBoardSize();i++) {
            for (int j = 0; j < GameSettings.getBoardSize(); j++) {
                value[i][j] = 0;
            }
        }
        for(int i=0;i<GameSettings.getBoardSize();i++){
            for(int j=0;j<GameSettings.getBoardSize();j++){
                if(board.getCell(i,j).clicked()){
                    //obiektów będących statkami nie traktujemy jako klikniętych
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
        //symulowanie różnych ustawień statków na planszy
        for (int k = 0; k< NUMBER_OF_SYMULATED_BOARD_POSITIONS; k++){
            for(int i=0;i< board.getShips().size();i++) {
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
                            value[x][y]++;
                        }
                    }
                    else{
                        for (int ii=0;ii<ship.getSize();ii++,x++){
                            value[x][y]++;
                        }
                    }
                }

            }
        }
        int rek=0;
        int besI=0,bestY=0;
        //wybieranie pola z największą szansą na bycie statkiem
        for(int i=0;i<GameSettings.getBoardSize();i++){
            for(int j=0;j<GameSettings.getBoardSize();j++){
                if(rek< value[i][j] && !board.getCell(i,j).isClicked){
                    rek= value[i][j];
                    besI = i;
                    bestY = j;
                }
            }
        }
        return board.Shoot(besI,bestY);
    }

    /**
     * metoda zwiększająca wartość pól sąsiadujących z segmentem niezniszczonego statku
     * @param x współrzędna x segmentu
     * @param y współrzędna y segmentu
     * @param board plansza, na której znajdują się pola
     */
    private void addValue(int x, int y,Board board) {
        if(checkPosition(x,y+1)){
            value[x][y+1]=10000;
        }
        if(checkPosition(x,y-1)){
            value[x][y-1]=10000;
        }
        if(checkPosition(x+1,y)){
            value[x+1][y]=10000;
        }
        if(checkPosition(x-1,y)){
            value[x-1][y]=10000;
        }
        if(checkPosition(x-1,y)&&checkPosition(x+1,y)) {
            if (board.getCell(x - 1, y).isClicked && board.getCell(x - 1, y).getState() == Cell.State.SHIP) {
                value[x + 1][y] = 20000;
            }
            if (board.getCell(x + 1, y).isClicked && board.getCell(x + 1, y).getState() == Cell.State.SHIP) {
                value[x - 1][y] = 20000;
            }
        }
        if(checkPosition(x,y-1)&&checkPosition(x,y+1)) {
            if (board.getCell(x , y - 1).isClicked && board.getCell(x , y -1).getState() == Cell.State.SHIP) {
                value[x][y+1] = 20000;
            }
            if (board.getCell(x , y + 1).isClicked && board.getCell(x , y + 1).getState() == Cell.State.SHIP) {
                value[x][y -1] = 20000;
            }
        }
    }

    /**
     * sprawdzanie czy statek nie koliduje z innymi statkami
     * @param ship statek który sprawdzamy
     * @param isVertical informacjia o tym czy statek ma xostać ułożony pionowo czy poziomo
     * @return  FALSE jeżeli statek koliduje TRUE jeżeli nie
     */
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

    /**
     * sprawdzenie czy pozycja jest legalna
     * @param x współrzędna x
     * @param y współrzędna y
     * @return  TRUE jeżeli wartość jest legalna FALSE jeżeli nie
     */
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

