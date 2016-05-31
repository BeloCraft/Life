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
public class ContinueTeach extends Command{
    
    @Override
    public Object DoAction(Object[] params) throws InterruptedException, ClassNotFoundException, SQLException, SocketException, IOException
    {
        GameLoop gameLoop = new GameLoop(new Database());                
        gameLoop.run(Integer.valueOf(params[0].toString()));
        return true;
    }
}
