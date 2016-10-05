/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.IntValue;

import Value.MatrixValue.Matrix;
import Value.MatrixValue.MatrixValue;
import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class RowsIntMatrixValue extends IntMatrixValue{

    public RowsIntMatrixValue(Value<?> matrix, int line) {
        super(matrix, line);
    }

    public Integer Value() {
       Value v1 = (this.matrix instanceof Variable ? ((Variable) this.matrix).Value() : this.matrix);
        
        if(!(v1 instanceof MatrixValue)){
           lp.SyntaticalAnalysis.error(this.getLine()," Matriz invalida");
        }
        
        Matrix m = ((MatrixValue) v1).Value();
        return m.rows();   
    }
    
    
}
