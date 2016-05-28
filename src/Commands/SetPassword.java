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
public class SetPassword extends Command{
    
    @Override
    public Object DoAction(Object[] params) throws ClassNotFoundException, SQLException
    {
        Database database = new Database();
        database.sendQuerry("DELETE FROM Pas WHERE 1=1");
        database.sendQuerry("INSERT INTO Pas VALUES ('"+params[0].toString()+"')");
        return true;
    }
}
