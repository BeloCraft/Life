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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugene
 */
public class GameLoop {
    
    private final Database database;
    private Boolean stop;           
    private final Database dbhistory;
    
    public GameLoop (Database database) throws SocketException, ClassNotFoundException, SQLException
    {
        this.stop = false;
        this.database = database;  
        dbhistory = new Database();
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
                
        Rules rules = new Rules();                
        
        while(!stop)
        {
            int nMax = -1;            
            ResultSet sendQuerryWithResult = database.sendQuerryWithResult("SELECT * FROM cells");            
            while(sendQuerryWithResult.next())
            {
                String type = sendQuerryWithResult.getString("type");
                int hungry = sendQuerryWithResult.getInt("hungry");
                int older = sendQuerryWithResult.getInt("older");
                int x = sendQuerryWithResult.getInt("x");
                int y = sendQuerryWithResult.getInt("y");
                int n = sendQuerryWithResult.getInt("n");
                
                if (nMax < n) nMax = n;
                
                rules.generateMap(dbhistory, type, hungry, older, x, y, n);
                rules.lifeCell(dbhistory, type, hungry, older, x, y, n);                                       
                
                dbhistory.sendQuerry("INSERT INTO history VALUES ('"+type+"',"+x+","
                        +y+ ","+n+")");                                
            }                          
            database.sendQuerry("DELETE FROM cells WHERE n<>"+(nMax+1));
            dbhistory.sendQuerry("DELETE FROM history WHERE n<((SELECT MAX(n) FROM history)-"+maxgen+")");
        }
    }
}
