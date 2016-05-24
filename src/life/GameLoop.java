/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import Database.Database;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugene
 */
public class GameLoop {
    
    private final Database database;
    private Boolean stop;    
    
    public GameLoop (Database database)
    {
        this.stop = false;
        this.database = database;
    }
    
    public void run() throws InterruptedException
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
        
        while(!stop)
        {
            
        }
    }
}
