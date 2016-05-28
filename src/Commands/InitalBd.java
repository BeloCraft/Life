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
public class InitalBd extends Command {

    @Override
    public Object DoAction(Object[] params) throws ClassNotFoundException, SQLException {
        Database database = new Database();
        
        try
        {
            database.sendQuerry("DROP TABLE Cells");     
            System.out.println("Table Cells removed");
        }
        catch(Exception ex)
        {}
        
        try
        {            
            database.sendQuerry("DROP TABLE History");            
            System.out.println("Table History removed");
        }
        catch(Exception ex)
        {}
        
        try
        {         
            database.sendQuerry("DROP TABLE AI");
            System.out.println("Table AI removed");
        }
        catch(Exception ex)
        {}
        
        try
        {         
            database.sendQuerry("DROP TABLE Pas");
            System.out.println("Table Pas removed");
        }
        catch(Exception ex)
        {}
        
        database.sendQuerry("CREATE TABLE Cells"
                + "("
                + "type varchar(1),"
                + "hungry int,"
                + "older int,"
                + "x int8,"
                + "y int8,"
                + "n int8"
                + ");");
        System.out.println("Table Cells created");
        database.sendQuerry("CREATE TABLE History"
                + "("
                + "type varchar(1),"
                + "x int8,"
                + "y int8,"
                + "n int8"
                + ");");
        System.out.println("Table History created");
        database.sendQuerry("CREATE TABLE AI"
                + "("
                + "type varchar(1),"
                + "hungry int,"
                + "older int,"
                + "action smallint,"
                + "map varchar(100)"
                + ");");        
        System.out.println("Table AI created");
        database.sendQuerry("CREATE TABLE Pas"
                + "("                
                + "password varchar(100)"
                + ");");      
        System.out.println("Table pas created");
        System.out.println("All tables created");          
        database.sendInsertQuerry("INSERT INTO Pas VALUES ('nopassword')");
        return null;
    }
}
