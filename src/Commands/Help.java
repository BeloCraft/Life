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
     private final String startNew = "new";
    private final String erase = "clear";
    private final String changePassword = "changePSWD";
    private final String setPassword = "setPSWD";
    private final String continueTeach = "continue";
    private final String help = "?";
    
    @Override
    public Object DoAction(Object[] params)
    {
        System.out.println(commands[0] + " - Create new session");
        System.out.println(commands[1] + " - Erase all information in database. Put"+
                "param 'AI', if need clear database AI");
        System.out.println(commands[2] + " - Change password (ex. changePSWD lastPSWD newPSWD)");
        System.out.println(commands[3] + " - Set password (ex. setPSWD Password");
        System.out.println(commands[4] + " - Continue last session");
        System.out.println(commands[5] + " - Create base table in bd");
        System.out.println(commands[6] + " - Get help");
        return null;
    }
}