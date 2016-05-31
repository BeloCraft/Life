/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author Eugene
 */
public class Rules {
    
    public final int MAX_X = 40;
    public final int MAX_Y = 40;
    public final int START_FOOD_WOLF = 10;
    public final int START_FOOD_SVEN = 10;
    public final int START_FOOD_GRASS = 10;
     
    public void generateMap(Database db, String type, int hungry, int older,
            int x, int y, int n) throws SQLException, ClassNotFoundException
    {
        ResultSet rCell = db.sendQuerryWithResult("SELECT type,hungry,older,x,y,n FROM cells WHERE x="+(x+1) + 
                "AND y="+ y);
        
        
        if (!rCell.next())
        {
            if (x < MAX_X)
            {
                createCell(db,type,hungry,older,x+1,y,n);
            }
        }
               
        ResultSet lCell = db.sendQuerryWithResult("SELECT type,hungry,older,x,y,n FROM cells WHERE x="+(x-1) +
                "AND y=" + y);
        
        if (!lCell.next())
        {
            if (x > -MAX_X)
            {
                createCell(db,type,hungry,older,x-1,y,n);
            }
        }
        
        ResultSet dCell = db.sendQuerryWithResult("SELECT type,hungry,older,x,y,n FROM cells WHERE y="+(y-1)+
                "AND x=" + x);
        
        if (!dCell.next())
        {
            if (y > -MAX_Y)
            {
                createCell(db,type,hungry,older,x,y-1,n);
            }
        }
        
        ResultSet uCell = db.sendQuerryWithResult("SELECT type,hungry,older,x,y,n FROM cells WHERE y="+(y+1) +
                "AND x=" + x);
        
        if (!uCell.next())
        {
            if (y < MAX_Y)
            {
                createCell(db,type,hungry,older,x,y+1,n);
            }
        }
                       
    }
    
    private void createCell(Database db, String type,int hungry, int older, int x, int y, int n) throws SQLException
    {
        int startFood = 0;        
        switch(type)
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
        db.sendQuerry("INSERT INTO cells VALUES ('"+getNextCell(type,hungry,older,x,y,n)+
            "',"+startFood+",0,"+x+","+y+","+(n+1)+")");
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
    
    public void lifeCell(Database db, String type, int hungry, int older,
            int x, int y, int n) throws SQLException
    {
        db.sendQuerry("INSERT INTO cells VALUES ('"+type+"',"+hungry+","+older+","+x+","+y+","+(n+1)+")");
    }
}
