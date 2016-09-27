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
public class ForCommand {
    
    private Variable var;
    private Value<?> value;
    private Command cmd;

    public ForCommand(Variable var, Value<?> value, Command cmd) {
        this.var = var;
        this.value = value;
        this.cmd = cmd;
    }

    public void execute(){
        
    }
    
}
