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
public class CommandsConsole {
 
    private final String startNew = "new";
    private final String erase = "clear";
    private final String changePassword = "changePSWD";
    private final String setPassword = "setPSWD";
    private final String continueTeach = "continue";
    private final String help = "?";
    
    public Command getCommand(String command)
    {
        switch(command)
        {
            case startNew:
                return new StartNew();
            case erase:
                return new Erase();
            case changePassword:
                return new ChangePassword();
            case setPassword:
                return new SetPassword();
            case continueTeach:
                return new ContinueTeach();
            case help:
                String[] commands = {startNew,erase,changePassword,setPassword,
                continueTeach,help};
                return new Help(commands);
        }        
        return null;
    }
}
