package org.example.backedn;

public class Cell {
    protected double xPosition;
    protected double yPosition;
    protected double Size;
    protected Ship shipReff = null;
    protected Boolean isClicked = false;
    private int nearShipsCount=0;
    public int getNearShipsCount(){
        return nearShipsCount;
    }
    public enum State {SHIP , NEAR_SHIP, SEA}
    State state = State.SEA;

    public Cell(double x, double y, double size){
        this.xPosition = x;
        this.yPosition = y;
        this.Size = size;
    }

    public Ship get_ship(){
        return shipReff;
    }
    public void setShipReff(Ship ship){
        this.shipReff = ship;
    }
    public void click(){
        isClicked = true;
    }
    public boolean ifClicked(){
        return isClicked;
    }
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
    public State  getState(){
        return state;
    }

    public double getX() {
        return xPosition;
    }

    public double getY() {
        return yPosition;
    }

    public double getCellSize() {
        return Size;
    }

}
