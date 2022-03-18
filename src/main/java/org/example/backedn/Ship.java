package org.example.backedn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    protected int x=0;
    protected int y=0;
    private  int health;
    protected int size;
    private Boolean vertical=false;
    protected boolean isDestroyed = false;

    public Ship(int x,int y,int size){
        this.x=x;
        this.y=y;
        this.size = size;
        this.health = size;
    }

    public void setX(int Position) {
        this.x = Position;
    }
    public void setY(int Position) {
        this.y = Position;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSize(){return size;}
    public Boolean isVertical() {return vertical;}
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setVertical(){
        vertical = true;
    }
    public void setHorizontal(){
        vertical = false;
    }

}
