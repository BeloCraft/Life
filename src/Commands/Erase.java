/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.Database;
import java.sql.SQLException;

/**
 *
 * @author Eugene
 */
public class Erase extends Command{
    //Erase all information in database. Put param 'AI', if need clear database AI
    @Override
    public Object DoAction(Object[] params) throws ClassNotFoundException, SQLException
    {
        Database database = new Database();
        if (params.length > 0)
        {
            if ("AI".equals(params[0].toString()))
            {
                database.sendQuerry("DELETE FROM AI WHERE 1=1");
            }
        }
        database.sendQuerry("DELETE FROM history WHERE 1=1");
        database.sendQuerry("DELETE FROM cells WHERE 1=1");
        return true;
    }
}