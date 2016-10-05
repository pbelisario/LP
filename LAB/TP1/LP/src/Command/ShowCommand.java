/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Value.MatrixValue.*;
import Value.IntValue.IntValue;
import Value.StringValue.StringValue;
import Command.Command;
import Value.*;
import lp.*;

/**
 *
 * @author root
 */
public class ShowCommand extends Command{
    
    private Value<?> value;
   
    public ShowCommand(Value<?> value, int line){
        super(line);
        this.value = value;
    }

    public void execute(){
        
         Value val = (this.value instanceof Variable ? ((Variable) this.value).Value() : this.value);
        
        if(val instanceof IntValue){
            IntValue iv = (IntValue) val;
            int n = iv.Value();
            System.out.println(n);
        } else if (val instanceof StringValue){
            StringValue sv = (StringValue) val;
            String s = sv.Value();
            System.out.println(s);
        } else if(val instanceof MatrixValue){
            MatrixValue mv = (MatrixValue) val;
            Matrix m = mv.Value();
            m.show();
        } else {
           lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido[show]");
        }
        
    }
    
}
