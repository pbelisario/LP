/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacote.Command;

import Pacote.Command.Command;
import Pacote.*;
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
        if(value instanceof IntValue){
            IntValue iv = (IntValue) value;
            int n = iv.Value();
            System.out.println(n);
        } else if (value instanceof StringValue){
            StringValue sv = (StringValue) value;
            String s = sv.Value();
            System.out.println(s);
        } else if(value instanceof MatrixValue){
            MatrixValue mv = (MatrixValue) value;
            Matrix m = mv.Value();
            m.show();
        } else {
           //ERRO
        }
        
    }
    
}
