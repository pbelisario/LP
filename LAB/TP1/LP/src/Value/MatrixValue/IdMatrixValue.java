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
public class IdMatrixValue extends MatrixValue{
    private Value<?> r;
    private Value<?> c;

    public IdMatrixValue(Value<?> r, Value<?> c, int line) {
        super(line);
        this.r = r;
        this.c = c;
    }

    @Override
    public Matrix Value() {
        int rows = ((IntValue) r).Value();
        int cols = ((IntValue) c).Value();
        
        return Matrix.id(rows, cols);
    }
    
    
}
