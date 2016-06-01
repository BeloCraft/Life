/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import Database.Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugene
 */
public class GameLoop {
    
    private final Database database;
    private final int OFFSET;
    private final int SIZE_ARRAY;
    private Boolean stop;          
    
    public GameLoop (Database database) throws ClassNotFoundException, SQLException
    {
        this.stop = false;
        this.database = database; 
        SIZE_ARRAY = 100;
        OFFSET = SIZE_ARRAY/2;
    }
    
    public void run(int maxgen) throws InterruptedException, ClassNotFoundException, SQLException, IOException
    {               
        Thread inputs = new Thread(() -> {
                while(!stop)
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        if ("stop".equals(in.readLine()))
                        {
                            stop = true;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        inputs.start();
                
        Rules rules = new Rules(OFFSET);                
        ArrayList<Cell> cells = new ArrayList<>();
        Cell[][] map;
        ArrayList<Cell> newGenCells;
        
        while(!stop)
        {      
            LocalTime starth = LocalTime.now();
            int nMax = -1;            
            ResultSet sendQuerryWithResult = database.sendQuerryWithResult("SELECT * FROM cells");                            
            map = new Cell[SIZE_ARRAY][SIZE_ARRAY];     
            
            while(sendQuerryWithResult.next())
            {
                String type = sendQuerryWithResult.getString("type");
                int hungry = sendQuerryWithResult.getInt("hungry");
                int older = sendQuerryWithResult.getInt("older");
                int x = sendQuerryWithResult.getInt("x");
                int y = sendQuerryWithResult.getInt("y");
                int n = sendQuerryWithResult.getInt("n");
                
                if (nMax < n) nMax = n;
                
                Cell newCell = new Cell(type,hungry,older,x+OFFSET,y+OFFSET,n);
                cells.add(newCell);  
                map[x+OFFSET][y+OFFSET] = newCell;
            }              
                                
            newGenCells = applyRules(cells,rules,map);                
            saveHistoryGen(database,cells);                                       
            
            String querry = "INSERT INTO cells VALUES ";            
            for (Cell cell : newGenCells)
            {
                querry += "('" + cell.getType() + "'," + cell.getHungry() +
                    "," + cell.getOlder() + "," + (cell.getX()-OFFSET) + ","
                    + (cell.getY()-OFFSET) + "," + cell.getN() + "),";
            }                        
            
            if (cells.size() > 0) {
                database.sendQuerry(querry.substring(0, querry.length() - 1) + ";");
            }                        
            
            database.sendQuerry("DELETE FROM cells WHERE n<"+(nMax+1));
            if (maxgen > 0)
            {
                database.sendQuerry("DELETE FROM history WHERE n<((SELECT MAX(n) FROM history)-"+maxgen+")");
            }            
            long sizeCells = newGenCells.size();
            
            newGenCells.clear();
            cells.clear();
            
            LocalTime endh = LocalTime.now();           
            /*ResultSet dbSize = database.sendQuerryWithResult("SELECT pg_database_size('life');");
            long size = 0;
            while(dbSize.next())
            {
                size = dbSize.getLong("pg_database_size");
            }
            System.out.println("Gen: " + (nMax+1) + " Time: " + (endh.getSecond() - starth.getSecond())+
                    " Cells:" + sizeCells + " Size: " + (size/1000000) + "mb");            */
            System.out.println("Gen: " + (nMax+1) + " Time: " + (endh.getSecond() - starth.getSecond())+
                    " Cells:" + sizeCells);            
        }
    }
   
    private void saveHistoryGen(Database dbhistory, ArrayList<Cell> cells) throws SQLException {
        if (cells.size() > 0) {
            String querry = "INSERT INTO history VALUES ";
            for (Cell cell : cells) {
                querry += "('" + cell.getType() + "',"
                        + (cell.getX() - OFFSET) + "," + (cell.getY() - OFFSET)
                        + "," + cell.getN() + "),";
            }
            dbhistory.sendQuerry(querry.substring(0, querry.length() - 1) + ";");
        }
    }

    private ArrayList<Cell> applyRules(ArrayList<Cell> cells, Rules rules, Cell[][] map) throws SQLException {

        ArrayList<Cell> newGen = new ArrayList<>();
        for (Cell cell : cells)
        {
            rules.generateMap(newGen,cell,map);
            rules.lifeCell(database, newGen,cell,map);  
        }       
        
        return newGen;
    }
}
