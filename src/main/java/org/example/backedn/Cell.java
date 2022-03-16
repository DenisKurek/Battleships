package org.example.backedn;

public class Cell {
    private double xPosition;
    private double yPosition;
    private double Size;
    private Ship shipReff = null;
    private Boolean isClicked = false;
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
