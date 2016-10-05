/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.IntValue.IntValue;
import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class RefMatrixValue extends MatrixValue{
    
    private Matrix m;

    public RefMatrixValue(Matrix m, int line) {
        super(line);
        this.m = m;
    }

    @Override
    public Matrix Value() {
        
        Integer rows = m.rows();
        Integer cols = m.cols();
                
        Matrix aux = new Matrix(rows,cols);
        for(int i = 0; i < rows ; i++){
            for(int j = 0; j< cols; j++){
                aux.setValue(i, j, this.m.value(rows, cols));
            }
        }
        return aux;        
    }
    
    
    
}
