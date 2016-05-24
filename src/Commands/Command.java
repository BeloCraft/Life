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
public abstract class Command {
    
    public Object DoAction(Object[] params) throws Exception
    {
        throw new Exception("Not Implemented Yet");
    }
}
