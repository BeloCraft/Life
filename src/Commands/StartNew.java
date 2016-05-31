/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Database;
import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import life.GameLoop;

/**
 *
 * @author Eugene
 */
public class StartNew extends Command{
    
    @Override
    public Object DoAction(Object[] params) throws ClassNotFoundException, SQLException, InterruptedException, SocketException, IOException
    {
        Database db = new Database();
        db.sendQuerry("DELETE FROM cells WHERE 1=1");
        db.sendQuerry("INSERT INTO cells VALUES (' ',0,0,0,0,0)");
        db.sendQuerry("INSERT INTO cells VALUES ('X',0,0,1,1,0)");
        GameLoop gameLoop = new GameLoop(new Database());
        gameLoop.run(Integer.valueOf(params[0].toString()));
        return true;
    }
}