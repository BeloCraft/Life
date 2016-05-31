/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 *
 * @author Eugene
 */
public class Help extends Command
{   
    
    private final String[] commands;
    
    public Help(String[] commands)
    {
        this.commands = commands;
    }
    
    @Override
    public Object DoAction(Object[] params)
    {
        System.out.println(commands[0] + " - Create new session, put number in param, it number is max gen in history, put -1 for save all (ex. new 10)");
        System.out.println(commands[1] + " - Erase all information in database. Put"+
                "param 'AI', if need clear database AI");        
        System.out.println(commands[2] + " - Set password (ex. setPSWD Password");
        System.out.println(commands[3] + " - Continue last session, put number in param, it number is max gen in history, put -1 for save all (ex. continue 10)");
        System.out.println(commands[4] + " - Create base table in bd");
        System.out.println(commands[5] + " - Connect to server screen (ex. screen 192.168.1.2:2148)");
        System.out.println(commands[6] + " - Get help");        
        return true;
    }
}