/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author Eugene
 */
public class Database {

    private final String url;
    private final String name;
    private final String password;
    private final Connection connection;
    private Statement statement;

    public Database() throws ClassNotFoundException, SQLException {

        String cfg = "";
        File f = new File("bd.cfg");
        try (FileReader reader = new FileReader(f)) {
            char[] buffer = new char[(int) f.length()];
            // считаем файл полностью
            reader.read(buffer);
            cfg = new String(buffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String adress = cfg.split(":")[0];
        String port = cfg.split(":")[1];
        String database = cfg.split(":")[2];
        String login = cfg.split(":")[3];
        String _password = cfg.split(":")[4];

        this.name = login;
        this.password = _password;
        url = "jdbc:postgresql://" + adress + ":" + port + "/" + database;

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver bd connected");        
        connection = DriverManager.getConnection(url, name, password);
        System.out.println("Connected database");
        
        this.statement = connection.createStatement();
    }

    public ResultSet sendQuerryWithResult(String querry) throws SQLException
    {
        ResultSet rs = statement.executeQuery(querry);
        return rs;
    }
    
    public void sendInsertQuerry(String querry) throws SQLException
    {
        statement.executeUpdate(querry);
    }
    
    public void sendQuerry(String querry) throws SQLException
    {
        statement.execute(querry);
    }
    
    public void closeConnection() throws SQLException{
        statement.close();
        connection.close();
    }          
}
