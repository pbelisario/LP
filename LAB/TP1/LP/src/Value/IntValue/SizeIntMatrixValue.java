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
public class SizeIntMatrixValue extends IntMatrixValue{

    public SizeIntMatrixValue(Value<?> matrix, int line) {
        super(matrix, line);
    }

    public Integer Value() {
         Value m1 = (matrix instanceof Variable ? ((Variable) matrix).Value() : matrix);
        
        if (!(m1 instanceof MatrixValue)) {
            lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido");
        }
        
        Matrix m = ((MatrixValue) m1).Value();

        return m.size();
    }
    
    
}
