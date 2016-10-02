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
public class FillMatrixValue extends MatrixValue{
    
    private Value<?> r;
    private Value<?> c;
    private Value<?> v;

    public FillMatrixValue(Value<?> r, Value<?> c, Value<?> v, int line) {
        super(line);
        this.r = r;
        this.c = c;
        this.v = v;
    }

    @Override
    public Matrix Value() {
        
        int rows = ((IntValue) r).Value();
        int cols = ((IntValue) c).Value();
        int value = ((IntValue) v).Value();
        
        return Matrix.fill(rows, cols, value);
        
    }
    
    
}
