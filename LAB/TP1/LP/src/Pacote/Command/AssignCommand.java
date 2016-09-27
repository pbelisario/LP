/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacote.Command;

import Pacote.Value;
import Pacote.Variable;

/**
 *
 * @author decom
 */
public class AssignCommand extends Command{

    public Variable var;
    public Value<?> value;
    
    
    public AssignCommand(Variable var, Value<?> value, int line){
        super(line);
        this.var = var;
        this.value = value;
    }
    
    public void execute(){
    
    
    }
    
}
