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
    private final String setPassword = "setPSWD";
    private final String continueTeach = "continue";
    private final String help = "?";
    private final String initalBd = "initBd";
    private final String screen = "screen";
    
    public Command getCommand(String command)
    {
        switch(command)
        {
            case startNew:
                return new StartNew();
            case erase:
                return new Erase();            
            case setPassword:
                return new SetPassword();
            case continueTeach:
                return new ContinueTeach();
            case help:
                String[] commands = {startNew,erase,setPassword,
                continueTeach,initalBd,help,screen};
                return new Help(commands);
            case initalBd:
                return new InitalBd();
            case screen:
                return new Screen();
        }        
        return null;
    }
}
