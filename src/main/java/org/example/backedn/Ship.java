package org.example.backedn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    protected double xPosition=0;
    protected double yPosition=0;
    protected double length;
    protected double width;
    protected boolean isDestroyed = false;

    public Ship(double x,double y,double width, double length ){
        this.xPosition=x;
        this.yPosition=y;
        this.length = length;
        this.width = width;
    }

    public void setX(double Position) {
        this.xPosition = Position;
    }
    public void setY(double Position) {
        this.yPosition = Position;
    }
    public double getX() {
        return xPosition;
    }
    public double getY() {
        return yPosition;
    }
    public void setPosition(double x, double y) {
        this.xPosition = x;
        this.yPosition = y;
    }

}
