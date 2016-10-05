/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.IntValue;

import Value.IntValue.IntValue;
import Value.MatrixValue.Matrix;
import Value.MatrixValue.MatrixValue;
import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public abstract class IntMatrixValue extends IntValue{
    
    protected Value<?> matrix;

    public IntMatrixValue(Value<?> matrix,int line) {
        super(line);
        this.matrix = matrix;        
    }
    
    public abstract Integer Value();
    
}
