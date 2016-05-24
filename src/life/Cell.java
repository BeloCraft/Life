/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

/**
 *
 * @author Eugene
 */
public class Cell {
    
    private char type;
    private int hungry;
    private int older;
    private long x;
    private long y;
    
    public Cell(char type, int hungry, int older, int x, int y)
    {
        this.type = type;
        this.hungry = hungry;
        this.older = older;
        this.x = x;
        this.y = y;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int getOlder() {
        return older;
    }

    public void setOlder(int older) {
        this.older = older;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }     
}
