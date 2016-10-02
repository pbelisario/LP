/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.Value;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class SumMatrixValue extends MatrixValue{
    
    private Value<?> matrix1;
    private Value<?> matrix2;

    public SumMatrixValue(Value<?> matrix1, int line) {
        super(line);
        this.matrix1 = matrix1;
    }

    @Override
    public Matrix Value() {
        Matrix m1 = ((MatrixValue) matrix1 ).Value();
        Matrix m2 = ((MatrixValue) matrix2 ).Value();
        
        return m1.sum(m2);
    }
    
    
    
}
