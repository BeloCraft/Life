/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import life.ScreenVisual;


/**
 *
 * @author Eugene
 */
public class Screen extends Command {
      
    @Override
    public Object DoAction(Object[] params) throws Exception
    {
        ScreenVisual screen = new ScreenVisual(params[0].toString(),
                Integer.valueOf(params[1].toString()));
        screen.run();
        return true;
    }
}
