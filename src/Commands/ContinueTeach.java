/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Database;
import java.sql.SQLException;
import life.GameLoop;

/**
 *
 * @author Eugene
 */
public class ContinueTeach extends Command{
    
    @Override
    public Object DoAction(Object[] params) throws InterruptedException, ClassNotFoundException, SQLException
    {
        GameLoop gameLoop = new GameLoop(new Database());
        gameLoop.run();
        return true;
    }
}
