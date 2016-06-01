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
    
    private final String type;
    private int hungry;
    private int older;
    private final int x;
    private final int y;
    private final int n;

    public Cell(String type, int hungry, int older, int x, int y, int n)
    {
        this.type = type;
        this.hungry = hungry;
        this.older = older;
        this.x = x;
        this.y = y;
        this.n = n;
    }
    
    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public void setOlder(int older) {
        this.older = older;
    }
 
    public String getType() {
        return type;
    }

    public int getHungry() {
        return hungry;
    }

    public int getOlder() {
        return older;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getN() {
        return n;
    }    
}
