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
        System.out.println(commands[0] + " - Create new session");
        System.out.println(commands[1] + " - Erase all information in database. Put"+
                "param 'AI', if need clear database AI");        
        System.out.println(commands[2] + " - Set password (ex. setPSWD Password");
        System.out.println(commands[3] + " - Continue last session");
        System.out.println(commands[4] + " - Create base table in bd");
        System.out.println(commands[5] + " - Get help");
        return null;
    }
}