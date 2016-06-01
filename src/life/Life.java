/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import Commands.CommandsConsole;
import Database.Database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eugene
 */
public class Life {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
                       
        System.out.println("Welcome in the LIFE!");  
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        File fileCfg = new File("bd.cfg");        
        if (!fileCfg.exists())
        {
            System.out.println("bd.cfg can't find");
            System.out.println("For connect to postgreSQL need create it file now");
            System.out.println("Input adress (ex. localhost)");
            System.out.print("->");
            String adress = in.readLine();
            System.out.println("Input port");
            System.out.print("->");
            String port = in.readLine();
            System.out.println("Input name database");
            System.out.print("->");
            String database = in.readLine();
            System.out.println("Input login");
            System.out.print("->");
            String login = in.readLine();
            System.out.println("Input password");
            System.out.print("->");
            String password = in.readLine();       
            
            try (FileWriter writer = new FileWriter("bd.cfg", false)) {                                
                writer.write(adress);
                writer.append(':');
                writer.write(port);
                writer.append(':');
                writer.write(database);
                writer.append(':');
                writer.write(login);
                writer.append(':');
                writer.write(password);
                writer.append(':');
                writer.flush();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
        
        System.out.println("What do you whis?");
        
        CommandsConsole commandsConsole = new CommandsConsole();
        
        if (args.length != 0)
        {
            for (String command : args)
            {                                                      
                ArrayList<Object> arg = new ArrayList<>();
                for (int i = 1; i < command.split(" ").length; i++)
                {
                    arg.add(command.split(" ")[i]);
                }
                commandsConsole.getCommand(command.split(" ")[0]).DoAction(arg.toArray());
            }
        }
        
        Database db = new Database();
        ResultSet querryResult;
        try {
            db.sendQuerryWithResult("SELECT password FROM pas");
        } catch (SQLException ex) {
            System.out.println("Don't get password from bd, erase all bd and create again? Y/n");
            System.out.print("-> ");
            if ("Y".equals(in.readLine())) {
                commandsConsole.getCommand("initBd".split(" ")[0]).DoAction(null);
            } else {
                return;
            }
        }
        querryResult = db.sendQuerryWithResult("SELECT password FROM pas");
        String password = "";
        while (querryResult.next()) 
        {
            password = querryResult.getString("password");
        }

        if (!password.equals("nopassword") && !password.equals("")) 
        {
            System.out.println("Input password");
            System.out.print("-> ");
            String inputPassword = in.readLine();
            if (!inputPassword.equals(password))
            {
                System.out.println("Incorect password");
                in.readLine();
                return;
            }
        }
        
        System.out.println("Type ? for get help");
                
        System.out.print("-> ");
        String inputCommand = in.readLine();
        
        while(!"exit".equals(inputCommand))
        {
            try
            {
            ArrayList<Object> arg = new ArrayList<>();
                for (int i = 1; i < inputCommand.split(" ").length; i++)
                {
                    arg.add(inputCommand.split(" ")[i]);
                }
                commandsConsole.getCommand(inputCommand.split(" ")[0]).DoAction(arg.toArray());            
            }catch(Exception ex)
            {
                System.out.println("Error runtime! \n" + ex.getMessage());
            }
            System.out.print("-> ");
            inputCommand = in.readLine();
        }
    }    
}