package org.example.backedn;

public class Cell {
    /**
     * pozycja osi x
     */
    protected double xPosition;
    /**
     * pozycja osi y
     */
    protected double yPosition;
    /**
     * rozmiar
     */
    protected double Size;
    /**
     *
     */
    protected Ship shipReff = null;
    /**
     * czy jest w formie ruchu
     */
    protected Boolean isClicked = false;
    /**
     *liczba statków przy danej komórce
     */
    private int nearShipsCount=0;
    /**
     *pobranie liczby statków przy danej komórce
     * @return liczba statków przy danej komórce
     */
    public int getNearShipsCount(){
        return nearShipsCount;
    }
    /**
     *
     */
    public enum State {SHIP , NEAR_SHIP, SEA}
    State state = State.SEA;
    /**
     *informacje o komórkach
     * @param y oś y
     * @param x oś x
     * @param size  rozmiar
     */
    public Cell(double x, double y, double size){
        this.xPosition = x;
        this.yPosition = y;
        this.Size = size;
    }
    /**
     *
     */
    public Ship get_ship(){
        return shipReff;
    }
    /**
     *
     */
    public void setShipReff(Ship ship){
        this.shipReff = ship;
    }
    /**
     *
     */
    public void click(){
        isClicked = true;
    }
    /**
     *
     */
    public boolean ifClicked(){
        return isClicked;
    }
    /**
     *
     */
    public void setState(State state){
        if(state!=State.NEAR_SHIP){
            nearShipsCount--;
            if(nearShipsCount<0){
                nearShipsCount=0;
            }
            if(nearShipsCount<=0){
                this.state = state;
            }
        }
        else{
            if(state == State.NEAR_SHIP) {
                nearShipsCount++;
            }
            this.state = state;
        }
    }
    /**
     * pobranie
     */
    public State  getState(){
        return state;
    }
    /**
     * pobranie osi x
     */
    public double getX() {
        return xPosition;
    }
    /**
     * pobranie osi y
     */
    public double getY() {
        return yPosition;
    }
    /**
     * pobranie rozmiaru komurki
     */
    public double getCellSize() {
        return Size;
    }

}
