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
public class TransposedMatrixValue extends MatrixValue{
    
    private Value<?> matrix;

    public TransposedMatrixValue(Value<?> matrix, int line) {
        super(line);
        this.matrix = matrix;
    }

    @Override
    public Matrix Value() {
        Value v1 = (this.matrix instanceof Variable ? ((Variable) this.matrix).Value() : this.matrix);
        
        if(!(v1 instanceof MatrixValue)){
            lp.SyntaticalAnalysis.showError(lp.TokenType.INVALID_OPERATION,line);
        }
        
        Matrix m = ((MatrixValue) v1).Value();
        
        return m.transposed();
        
    }
    
    
}
