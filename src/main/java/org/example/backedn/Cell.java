package org.example.backedn;

import java.lang.invoke.SwitchPoint;

/**
 * klasa reprezenyująca komórkę na planszy
 */
public class Cell {
    /**
     * współrzędna x komórki
     */
    protected double xPosition;
    /**
     * współrzędna y komórki
     */
    protected double yPosition;
    /**
     * rozmiar komórki
     */
    protected double size;
    /**
     *  referencja do statku leżącego na danej komórce
     */
    protected Ship shipReff = null;
    /**
     * informacja czy komórka jest kliknięta
     */
    protected Boolean isClicked = false;
    /**
     *  liczba statków sąsiadujących z komórką
     */
    private int nearShipsCount=0;
    /**
     * funkcja zwracająca liczbę statków sąsiadujących z komórką
     * @return liczba sąsiadujących statków
     */
    public int getNearShipsCount(){
        return nearShipsCount;
    }

    /**
     * klasa reprezentująca stany komórki
     */
    public enum State {
        /**
         * na komórce znajduje się statek
         */
        SHIP ,
        /**
         * komórka znajduje się przy statku
         */
        NEAR_SHIP,
        /**
         * komórka nie znajduje się przy żadnym statku
         */
        SEA
    }

    /**
     * egzemplarz klasy enum state
     */
    State state = State.SEA;
    /**
     * konstruktor tworzący obiekt klasy Cell
     * @param y współrzędna x
     * @param x współrzędna y
     * @param size  rozmiar komórki
     */
    public Cell(double x, double y, double size){
        this.xPosition = x;
        this.yPosition = y;
        this.size = size;
    }

    /**
     * funkcja zwracająca referencję do statku leżącego na danym polu
     * @return obiekt klasy Ship
     */
    public Ship get_ship(){
        return shipReff;
    }
    /**
     *  ustawienie referencji do statku
     */
    public void setShipReff(Ship ship){
        this.shipReff = ship;
    }
    /**
     *  kliknięcie pola
     */
    public void click(){
        isClicked = true;
    }
    /**
     *  sprawdzanie zcy pole zostało kliknięte
     */
    public boolean clicked(){
        return isClicked;
    }
    /**
     * ustawianie stanu pola
     */
    public void setState(State state){
        if(this.state == State.NEAR_SHIP){
            switch(state){
                case NEAR_SHIP:
                    nearShipsCount++;
                    break;
                case SEA:
                    //w przypadku stanu NEAR_SHIP zmienia się on, dopiero gdy nie sąsiaduje już z żadnym statkiem
                    nearShipsCount--;
                    if(nearShipsCount<0){
                        nearShipsCount=0;
                    }
                    if(nearShipsCount<=0){
                        this.state = state;
                    }
                    break;
                case SHIP:
                    nearShipsCount=0;
                    this.state=state;
                    break;
            }

        }
        else{
            if(state==State.NEAR_SHIP){
                nearShipsCount++;
            }
            this.state = state;
        }
    }

    /**
     * funkcja zwracająca stan pola
     * @return stan pola
     */
    public State  getState(){
        return state;
    }

    /**
     * funkcja zwracająca współrzędną x pola
     * @return współrzędna x pola
     */
    public double getX() {
        return xPosition;
    }
    /**
     * funkcja zwracająca współrzędną y pola
     * @return współrzędna y pola
     */
    public double getY() {
        return yPosition;
    }
    /**
     * funkcja zwracająca rozmiar pola
     * @return rozmiar pola
     */
    public double getCellSize() {
        return size;
    }

}
