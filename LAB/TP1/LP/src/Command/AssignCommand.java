/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Value.IntValue.ConstIntValue;
import Value.IntValue.IntValue;
import Value.MatrixValue.Matrix;
import Value.MatrixValue.MatrixValue;
import Value.MatrixValue.RefMatrixValue;
import Value.StringValue.StringValue;
import Value.Value;
import Value.Variable;

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

        Value variable = (value instanceof Variable ? ((Variable) value).Value() : value);
        
        if (variable instanceof IntValue) {
            IntValue a = (IntValue) variable;
            Integer b = a.Value();
            ConstIntValue c = new ConstIntValue(b, variable.getLine());
            var.setValue(c);
        } else if (variable instanceof MatrixValue) {
            var.setValue(variable);
        } else {
            lp.SyntaticalAnalysis.error(this.getLine(),"Erro Assign AQUI");
        }
    
    }
    
}
