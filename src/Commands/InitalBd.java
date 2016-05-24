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
                + "x0y0 varchar(1),"
                + "x1y0 varchar(1),"
                + "x2y0 varchar(1),"
                + "x3y0 varchar(1),"
                + "x4y0 varchar(1),"
                + "x5y0 varchar(1),"
                + "x6y0 varchar(1),"
                + "x7y0 varchar(1),"
                + "x8y0 varchar(1),"
                + "x9y0 varchar(1),"
                + "x0y1 varchar(1),"
                + "x1y1 varchar(1),"
                + "x2y1 varchar(1),"
                + "x3y1 varchar(1),"
                + "x4y1 varchar(1),"
                + "x5y1 varchar(1),"
                + "x6y1 varchar(1),"
                + "x7y1 varchar(1),"
                + "x8y1 varchar(1),"
                + "x9y1 varchar(1),"
                + "x0y2 varchar(1),"
                + "x1y2 varchar(1),"
                + "x2y2 varchar(1),"
                + "x3y2 varchar(1),"
                + "x4y2 varchar(1),"
                + "x5y2 varchar(1),"
                + "x6y2 varchar(1),"
                + "x7y2 varchar(1),"
                + "x8y2 varchar(1),"
                + "x9y2 varchar(1),"
                + "x0y3 varchar(1),"
                + "x1y3 varchar(1),"
                + "x2y3 varchar(1),"
                + "x3y3 varchar(1),"
                + "x4y3 varchar(1),"
                + "x5y3 varchar(1),"
                + "x6y3 varchar(1),"
                + "x7y3 varchar(1),"
                + "x8y3 varchar(1),"
                + "x9y3 varchar(1),"
                + "x0y4 varchar(1),"
                + "x1y4 varchar(1),"
                + "x2y4 varchar(1),"
                + "x3y4 varchar(1),"
                + "x4y4 varchar(1),"
                + "x5y4 varchar(1),"
                + "x6y4 varchar(1),"
                + "x7y4 varchar(1),"
                + "x8y4 varchar(1),"
                + "x9y4 varchar(1),"
                + "x0y5 varchar(1),"
                + "x1y5 varchar(1),"
                + "x2y5 varchar(1),"
                + "x3y5 varchar(1),"
                + "x4y5 varchar(1),"
                + "x5y5 varchar(1),"
                + "x6y5 varchar(1),"
                + "x7y5 varchar(1),"
                + "x8y5 varchar(1),"
                + "x9y5 varchar(1),"
                + "x0y6 varchar(1),"
                + "x1y6 varchar(1),"
                + "x2y6 varchar(1),"
                + "x3y6 varchar(1),"
                + "x4y6 varchar(1),"
                + "x5y6 varchar(1),"
                + "x6y6 varchar(1),"
                + "x7y6 varchar(1),"
                + "x8y6 varchar(1),"
                + "x9y6 varchar(1),"
                + "x0y7 varchar(1),"
                + "x1y7 varchar(1),"
                + "x2y7 varchar(1),"
                + "x3y7 varchar(1),"
                + "x4y7 varchar(1),"
                + "x5y7 varchar(1),"
                + "x6y7 varchar(1),"
                + "x7y7 varchar(1),"
                + "x8y7 varchar(1),"
                + "x9y7 varchar(1),"
                + "x0y8 varchar(1),"
                + "x1y8 varchar(1),"
                + "x2y8 varchar(1),"
                + "x3y8 varchar(1),"
                + "x4y8 varchar(1),"
                + "x5y8 varchar(1),"
                + "x6y8 varchar(1),"
                + "x7y8 varchar(1),"
                + "x8y8 varchar(1),"
                + "x9y8 varchar(1),"
                + "x0y9 varchar(1),"
                + "x1y9 varchar(1),"
                + "x2y9 varchar(1),"
                + "x3y9 varchar(1),"
                + "x4y9 varchar(1),"
                + "x5y9 varchar(1),"
                + "x6y9 varchar(1),"
                + "x7y9 varchar(1),"
                + "x8y9 varchar(1),"
                + "x9y9 varchar(1)"
                + ");");
        System.out.println("Table AI created");
        System.out.println("All tables created");
        return null;
    }
}
