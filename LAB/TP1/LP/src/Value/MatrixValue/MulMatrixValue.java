/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.IntValue.IntValue;
import Value.Value;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class MulMatrixValue extends MatrixValue {
    

    private Value<?> matrix;
    private Value<?> value;

    public MulMatrixValue(Value<?> matrix, Value<?> value, int line) {
        super(line);
        this.matrix = matrix;
        this.value = value;
    }

    @Override
    public Matrix Value() {
        Matrix m = ((MatrixValue) matrix).Value();
            int val = ((IntValue) value).Value();
        
        return m.mul(val);
    }
    
    
    
    
}
