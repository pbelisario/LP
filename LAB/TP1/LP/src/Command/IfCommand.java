/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Value.BoolValue.BoolValue;
import Value.*;
import Command.Command;

/**
 *
 * @author decom
 */
public class IfCommand extends Command{
    
    private BoolValue expr;
    private Command then;
    private Command elsee;
    
    public IfCommand(BoolValue expr, Command then, int line){
        super(line);
        this.then = then;
        this.expr = expr;
    }
    
    public IfCommand(BoolValue expr, Command then, int line, Command elsee){
        super(line);
        this.then = then;
        this.expr = expr;
        this.elsee = elsee;
    }
    
    public void execute(){
        
       if(expr.Value()){
           then.execute();
       } else {
           if(elsee != null){
               elsee.execute();
           }
       }
       
    }
    
}
