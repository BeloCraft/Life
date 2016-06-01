/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;
import static org.postgresql.hostchooser.HostRequirement.any;

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

    public Rules(int offset) {
        this.OFFSET = offset;
    }

    public ArrayList<Cell> generateMap(ArrayList<Cell> newGenCells, Cell cell, Cell[][] map) {
        if (map[cell.getX() + 1][cell.getY()] == null) {
            if (cell.getX() - OFFSET < MAX_X) {
                createCell(newGenCells, cell, cell.getX() + 1, cell.getY());
                map[cell.getX() + 1][cell.getY()] = newGenCells.get(newGenCells.size() - 1);
            }
        }

        if (map[cell.getX() - 1][cell.getY()] == null) {
            if (cell.getX() - OFFSET > -MAX_X) {
                createCell(newGenCells, cell, cell.getX() - 1, cell.getY());
                map[cell.getX() - 1][cell.getY()] = newGenCells.get(newGenCells.size() - 1);
            }
        }

        if (map[cell.getX()][cell.getY() - 1] == null) {
            if (cell.getY() - OFFSET > -MAX_Y) {
                createCell(newGenCells, cell, cell.getX(), cell.getY() - 1);
                map[cell.getX()][cell.getY() - 1] = newGenCells.get(newGenCells.size() - 1);
            }
        }

        if (map[cell.getX()][cell.getY() + 1] == null) {
            if (cell.getY() - OFFSET < MAX_Y) {
                createCell(newGenCells, cell, cell.getX(), cell.getY() + 1);
                map[cell.getX()][cell.getY() + 1] = newGenCells.get(newGenCells.size() - 1);
            }
        }

        return newGenCells;
    }

    private ArrayList<Cell> createCell(ArrayList<Cell> newGen, Cell cell, int x, int y) {
        int startFood = 0;
        switch (cell.getType()) {
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
        newGen.add(new Cell(getNextCell(cell.getType(), cell.getHungry(), cell.getOlder(), cell.getX(), cell.getY(), cell.getN()),
                startFood, 0, x, y, cell.getN() + 1));
        return newGen;
    }

    private String getNextCell(String type, int hungry, int older, int x, int y, int n) {
        Random rnd = new Random();
        int r = rnd.nextInt(1000);

        int r2 = rnd.nextInt(1000);

        switch (type) {
            case "X":
                if (r2 < 100) {
                    return "X";
                }
                break;
            case "R":
                if (r2 < 500) {
                    return "R";
                }
                break;
            case "S":
                if (r2 < 300) {
                    return "S";
                }
                break;
            case "H":
                if (r2 < 700) {
                    return "H";
                }
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

    public ArrayList<Cell> lifeCell(Database database, ArrayList<Cell> newGen, Cell cell, Cell[][] map) throws SQLException {
        if ("X".equals(cell.getType()) || "S".equals(cell.getType())) {
            teachCell(database, newGen, cell, map);
        } else {
            newGen.add(new Cell(cell.getType(), cell.getHungry(), cell.getOlder(),
                    cell.getX(), cell.getY(), cell.getN() + 1));
            map[cell.getX()][cell.getY()] = newGen.get(newGen.size()-1);
        }
        return newGen;
    }

    private ArrayList<Cell> teachCell(Database database, ArrayList<Cell> newGen, Cell cell, Cell[][] map) throws SQLException {
        String patternNow = "";
        for (int j = -5; j <= 5; j++) {
            for (int i = -5; i <= 5; i++) {
                if (map[cell.getX() + i][cell.getY() + j] != null) {
                    patternNow += map[cell.getX() + i][cell.getY() + j].getType();
                }
            }
        }

        ResultSet findedPatterns
                = database.sendQuerryWithResult(
                        "SELECT type, hungry, older, action, map, rang FROM ai WHERE map='" + patternNow
                        + "' AND type='" + cell.getType() + "' ORDER BY rang");
        ArrayList<Pair<Integer, Integer>> patternsRang = new ArrayList<>();
        while (findedPatterns.next()) {
            patternsRang.add(
                    new Pair<>(findedPatterns.getInt("action"), findedPatterns.getInt("rang")));
        }

        for (int i = 0; i < 4; i++) {
            patternsRang.add(new Pair<>(i, 10));
        }

        String selected = "";

        for (int j = 0; j < patternsRang.size(); j++) {
            for (int i = 0; i < patternsRang.get(j).getValue(); i++) {
                selected += j;
            }
        }

        Random rnd = new Random();
        int selectedAction = rnd.nextInt(selected.length());

        int x = cell.getX();
        int y = cell.getY();       
        switch (Integer.valueOf(selected.substring(selectedAction, selectedAction + 1))) {
            case 0:
                if (x-OFFSET < MAX_X) {
                    x++;
                }
                break;
            case 1:
                if (x-OFFSET > -MAX_X) {
                    x--;
                }
                break;
            case 2:
                if (y-OFFSET < MAX_Y) {
                    y++;
                }
                break;
            case 3:
                if (y-OFFSET > MAX_Y) {
                    y--;
                }
                break;
        }
        
        Pair<Integer, Boolean> result = physics(newGen,cell,map,x,y);

        if (result.getValue())
        {
            newGen.add(new Cell(cell.getType(), cell.getHungry(), cell.getOlder(),
                    x, y, cell.getN() + 1));
            map[x][y] = newGen.get(newGen.size()-1);
        }  
        else
        {
           newGen.add(new Cell(cell.getType(), cell.getHungry(), cell.getOlder(),
                    cell.getX(), cell.getY(), cell.getN() + 1));
            map[cell.getX()][cell.getY()] = newGen.get(newGen.size()-1); 
        }      

        database.sendQuerry("INSERT INTO ai VALUES ('" + cell.getType() + "'," + cell.getHungry()+
                "," + cell.getOlder() + "," + Integer.valueOf(selected.substring(selectedAction, selectedAction + 1))
        + ",'" + patternNow + "'," + result.getKey() + ")");
        
        return newGen;
    }
    
    private Pair<Integer,Boolean> physics(ArrayList<Cell> newGen,Cell cell, Cell[][] map, int nx, int ny)
    {
        int Rang = 0;
        int hungry = cell.getHungry();
        Boolean canGo = false;
        if ("S".equals(cell.getType()))
        {
            if (map[nx][ny] != null)
            {
                if ("H".equals(map[nx][ny].getType()))
                {
                    cell.setHungry(cell.getHungry()+map[nx][ny].getHungry());
                    newGen.remove(map[nx][ny]);
                    newGen.add(new Cell(" ",0,0,cell.getX(),cell.getY(),cell.getN()+1));
                    map[cell.getX()][cell.getY()] = newGen.get(newGen.size()-1);    
                    canGo = true;
                }
            }
        }
        
        Rang += (cell.getHungry() - cell.getHungry()) - cell.getOlder();
        
        return new Pair<>(Rang,canGo);
    }
}
