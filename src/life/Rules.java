/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Eugene
 */
public class Rules {
    
    public final int MAX_X = 20;
    public final int MAX_Y = 20;
    public final int START_FOOD_WOLF = 10;
    public final int START_FOOD_SVEN = 10;
    public final int START_FOOD_GRASS = 10;
    public final int OFFSET;
    
    public Rules(int offset)
    {
        this.OFFSET = offset;
    }
     
    public ArrayList<Cell> generateMap(ArrayList<Cell> newGenCells, Cell cell, Cell[][] map)
    {         
        if (map[cell.getX()+1][cell.getY()] == null)
        {
            if (cell.getX()-OFFSET < MAX_X)
            {                
                createCell(newGenCells,cell,cell.getX()+1,cell.getY());
                map[cell.getX()+1][cell.getY()] = newGenCells.get(newGenCells.size()-1);
            }
        }
                       
        if (map[cell.getX()-1][cell.getY()] == null)
        {
            if (cell.getX()-OFFSET > -MAX_X)
            {                
                createCell(newGenCells,cell,cell.getX()-1,cell.getY());
                map[cell.getX()-1][cell.getY()] = newGenCells.get(newGenCells.size()-1);
            }
        }                
        
        if (map[cell.getX()][cell.getY()-1] == null)
        {
            if (cell.getY()-OFFSET > -MAX_Y)
            {                
                createCell(newGenCells,cell,cell.getX(),cell.getY()-1);
                map[cell.getX()][cell.getY()-1] = newGenCells.get(newGenCells.size()-1);
            }
        }                
        
        if (map[cell.getX()][cell.getY()+1] == null)
        {
            if (cell.getY()-OFFSET < MAX_Y)
            {                
                createCell(newGenCells,cell,cell.getX(),cell.getY()+1);
                map[cell.getX()][cell.getY()+1] = newGenCells.get(newGenCells.size()-1);
            }
        }
         
        return newGenCells;
    }
    
    private ArrayList<Cell> createCell(ArrayList<Cell> newGen, Cell cell, int x, int y)
    {
        int startFood = 0;        
        switch(cell.getType())
        {
            case "X":
                startFood = START_FOOD_WOLF;
                break;
            case "S":
                startFood = START_FOOD_SVEN;
                break;
            case "H":
                startFood = START_FOOD_GRASS;
                break;
        }        
        newGen.add(new Cell(getNextCell(cell.getType(),cell.getHungry(),cell.getOlder(),cell.getX(),cell.getY(),cell.getN()),
                startFood,0,x,y,cell.getN()+1));
        return newGen;
    }
    
    private String getNextCell(String type, int hungry, int older, int x, int y, int n) {
        Random rnd = new Random();
        int r = rnd.nextInt(1000);

        int r2 = rnd.nextInt(1000);
        
        switch(type)
        {
            case "X":
                if (r2 < 100)
                    return "X";
                break;
            case "R":
                if (r2 < 500)
                    return "R";
                break;
            case "S":
                if (r2 < 300)
                    return "S";
                break;
            case "H":
                if (r2 < 700)
                    return "H";
                break;
        }
        
        if (r == 0) {
            return "X";
        }
        if (r >= 1 && r <= 5) {
            return "R";
        }
        if (r > 5 && r < 10) {
            return "S";
        }
        if (r >= 10 && r < 150) {
            return "H";
        }
        
        return " ";
    }
    
    public ArrayList<Cell> lifeCell(ArrayList<Cell> newGen, Cell cell, Cell[][] map)
    {        
        newGen.add(new Cell(cell.getType(),cell.getHungry(),cell.getOlder(),
                cell.getX(), cell.getY(),cell.getN()+1));
        return newGen;
    }
        
}
