/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class OpposedMatrixValue extends MatrixValue{
    
    private Value<?> matrix;

    public OpposedMatrixValue(Value<?> matrix, int line) {
        super(line);
        this.matrix = matrix;
    }

    @Override
    public Matrix Value() {
        Value v1 = (this.matrix instanceof Variable ? ((Variable) this.matrix).Value() : this.matrix);
        
        if(!(matrix instanceof MatrixValue)){
            lp.SyntaticalAnalysis.error(this.getLine(),"Matriz invalida");
        }
        
        Matrix m = ((MatrixValue) matrix).Value();

        return m.opposed();
    }
    
    
}
