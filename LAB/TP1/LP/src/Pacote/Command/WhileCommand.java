/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacote.Command;

import Pacote.*;

/**
 *
 * @author decom
 */
public class WhileCommand {
    
    private BoolValue expr;
    private Command cmd;
    
    public WhileCommand(BoolValue expr, Command cmd){
        this.expr = expr;
        this.cmd = cmd;
    }
    
    public void execute(){
        
    }
}
