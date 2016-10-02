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
         if(value instanceof IntValue){
            IntValue iv = (IntValue) value;
            int n = iv.Value();
            
            ConstIntValue cnsI = new ConstIntValue(n,-1);
            var.setValue(cnsI);
                    
            
        } else if (value instanceof StringValue){
            StringValue sv = (StringValue) value;
            String s = sv.Value();
            
            
        } else if(value instanceof MatrixValue){
            MatrixValue mv = (MatrixValue) value;
            Matrix m = mv.Value();
            
            RefMatrixValue rvM = new RefMatrixValue(m,-1);
            var.setValue(rvM);
        } else {
           //ERRO
        }
    
    }
    
}
