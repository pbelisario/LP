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
public class RefMatrixValue extends MatrixValue{
    
    private Value<?> r;
    private Value<?> c;
    private Matrix m;

    public RefMatrixValue(Value<?> r, Value<?> c, Matrix m, int line) {
        super(line);
        this.r = r;
        this.c = c;
        this.m = m;
    }

    @Override
    public Matrix Value() {
        return null;
    }
    
    
    
}
