/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Value.*;
import Value.IntValue.ConstIntValue;
import Value.MatrixValue.Matrix;
import Value.MatrixValue.MatrixValue;

/**
 *
 * @author decom
 */
public class ForCommand extends Command {
    
    private Variable var;
    private Value<?> value;
    private Command cmd;

    public ForCommand(Variable var, Value<?> value, Command cmd, int line) {
        super(line);
        this.var = var;
        this.value = value;
        this.cmd = cmd;
    }

    public void execute(){
        Value valor = (value instanceof Variable ? ((Variable) value).Value() : value);
        if (!(var instanceof Variable && valor instanceof MatrixValue)) {
            lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido[for]");
        }
        
        Matrix mat = ((MatrixValue) value).Value();
        
        int rows = mat.rows();
        int cols = mat.cols();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ConstIntValue value1 = new ConstIntValue(mat.value(i, j), this.getLine());
                var.setValue(value1);
                cmd.execute();
                
            }
        }
    }
    
}
