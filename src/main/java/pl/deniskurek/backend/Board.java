package pl.deniskurek.backend;
import pl.deniskurek.exceptions.InvalidPositionException;

import java.util.ArrayList;
import java.util.List;
/**
 * klasa prezentująca Planszę do gry
 */
public class Board {
    /**
     * dwuwymiarowa tablica pól
     */
    private Cell[][] cells;
    /**
     * lista statków
     */
    private List<Ship> ships;
    /**
     * konstruktor domyślny klasy Board
     */
    public Board(){
        initialize();
    }

    /**
     * geter zwracający dane polne na planszy
     * @param cellX współrzędna x pola
     * @param cellY współrzędna y pola
     * @return  obiekt klasy cell reprezentujący pole na planszy
     */
    public Cell getCell(int cellX,int cellY){
        return cells[cellX][cellY];
    }

    /**
     * metoda zwracająca statki umieszcone na planszy
     * @return lista powstałych statków
     */
    public List<Ship> getShips(){return ships;}

    /**
     * metoda dodająca statek do planszy
     * @param ship obiekt typu Ship do dodania
     */
    public void addShip(Ship ship) {
        //dodanie statku do listy
        ships.add(ship);
        //próba położenia statku na planszy
        if(checkIfShipPositionValid(ship,ship.isVertical())){
            int x = ship.getX();
            int y = ship.getY();
            if(ship.isVertical()){
                for (int i=0;i<ship.getSize();i++,y++){
                    cells[x][y].setShipReff(ship);
                    cells[x][y].setState(Cell.State.SHIP);
                    changeNeighbourState( x,y, Cell.State.NEAR_SHIP);
                }
            }
            else{
                for (int i=0;i<ship.getSize();i++,x++){
                    cells[x][y].setShipReff(ship);
                    cells[x][y].setState(Cell.State.SHIP);
                    changeNeighbourState(x,y, Cell.State.NEAR_SHIP);
                }
            }
        }
        else{
            // w przypadku gdy statku nie udało się umieścić
            ships.remove(ship);
            throw new InvalidPositionException();
        }
    }

    /**
     * metoda przemieszczająca statek na planszy
     * @param newX  nowa współrzędna x
     * @param newY  nowa współrzędna y
     * @param ship  obiekt typu Ship do dodania
     * @param changePosition informacja czy obrócić statek przy przemieszczaniu
     * @return TRUE, jeżeli udało się przemieścić statek FALSE, jeżeli nie
     */
    public boolean moveShip(int newX,int newY,Ship ship,boolean changePosition){
        // usunięcie statku z planszy
        removeShip(ship);
        ships.remove(ship);
        //zapamiętanie jego starych współrzędnych
        int oldX = ship.getX();
        int oldY = ship.getY();
        //zmiana pozyscji  i obrót statku
        ship.setPosition(newX,newY);
        if(changePosition){
            if(ship.isVertical()){
                ship.setHorizontal();
            }
            else{
                ship.setVertical();
            }
        }
        //próba dodania statku na nowej pozycji
        try{
            addShip(ship);
        }
        catch (InvalidPositionException ignore){
            //ustawienie statku spowrotem na pozycji początkowej
            ship.setPosition(oldX,oldY);
            if(changePosition){
                if(ship.isVertical()){
                    ship.setHorizontal();
                }
                else{
                    ship.setVertical();
                }
            }
            addShip(ship);
            return false;
        }
        return true;
    }

    /**
     * strzelenie w daną pozycję na planszy
     * @param x współrzędna x
     * @param y współrzędna y
     * @return TRUE, jeżeli trafiono w statek FALSE, jeżeli nie trafiono
     */
    public Boolean shoot(int x, int y) {
        Cell cell = getCell(x, y);
        Ship ship = cell.get_ship();
        cell.click();
        if (ship != null) {
            //sprawdzenie czy statek został zatopiony
            if (ship.shoot()) {
                int shipX = ship.getX();
                int shipY = ship.getY();
                //zaznaczenie pól dookoła zatopionego statku
                if (ship.isVertical()) {
                    for (int i = 0; i < ship.getSize(); i++, shipY++) {
                        clickNeighbour(shipX, shipY);
                    }
                }
                else {
                    for (int i = 0; i < ship.getSize(); i++, shipX++) {
                        clickNeighbour(shipX, shipY);
                    }
                }
                ships.remove(ship);
            }
            return true;

        }
        return false;
    }

    /**
     * metoda inicializująca planszę
     */
    private void initialize(){
        ships = new ArrayList<Ship>();
        this.cells = new Cell[GameSettings.boardSize][GameSettings.boardSize];
        for (int i = 0; i < GameSettings.boardSize; i++){
            for (int j = 0; j < GameSettings.boardSize; j++){
                this.cells[i][j] = new Cell(i* GameSettings.cellSize, j* GameSettings.cellSize, GameSettings.cellSize);
                cells[i][j].setState(Cell.State.SEA);
            }
        }
    }

    /**
     * metoda usuwająca statek z planszy
     * @param ship statek do usunięcia
     */
    private void removeShip(Ship ship) {
        int x = ship.getX();
        int y = ship.getY();
        if(ship.isVertical()){
            for (int i=0;i<ship.getSize();i++,y++){
                cells[x][y].setShipReff(null);
                cells[x][y].setState(Cell.State.SEA);
                changeNeighbourState( x,y, Cell.State.SEA);
            }
        }
        else{
            for (int i=0;i<ship.getSize();i++,x++){
                cells[x][y].setShipReff(ship);
                cells[x][y].setState(Cell.State.SEA);
                changeNeighbourState(x,y, Cell.State.SEA);
            }
        }

    }

    /**
     * metoda zmieniająca status sąsiadów pola
     * @param cellX współrzędna x pola
     * @param cellY współrzędna y pola
     * @param state stan do ustawienia
     */
    private void changeNeighbourState(int cellX , int cellY, Cell.State state) {
        for(int x=-1;x<2;x++){
            for(int y=-1;y<2;y++){
                //sprawdzanie czy pozycja istnieje
                if(checkPosition(cellX+x,cellY+y)){
                    //status NEAR_SHIP nie powinien nadpisywać statusu SHIP
                    if(cells[cellX + x][cellY + y].getState() != Cell.State.SHIP &&
                            state == Cell.State.NEAR_SHIP ) {
                        cells[cellX + x][cellY + y].setState(state);
                    }
                    //status SEA nadpisuje każdy inny status
                    else if (state == Cell.State.SEA){
                        cells[cellX + x][cellY + y].setState(state);
                    }
                }
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
                if(!checkPosition(x,y) || cells[x][y].getState()!= Cell.State.SEA){
                    return false;
                }
            }
        }
        else{
            for (int i=0;i<ship.getSize();i++,x++){
                if(!checkPosition(x,y) || cells[x][y].getState()!= Cell.State.SEA){
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
        if(x<0 || x>= GameSettings.boardSize){
            return false;
        }
        return y >= 0 && y < GameSettings.boardSize;
    }

    /**
     * kliknięcie sąsiadów danej pola
     * @param cellX współrzędna x pola
     * @param cellY współrzędna y pola
     */
    private void clickNeighbour(int cellX ,int cellY){
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if(checkPosition(cellX+i,cellY+j)){
                        cells[cellX + i][cellY + j].click();
                }
            }
        }
    }

}

