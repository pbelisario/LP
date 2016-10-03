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
public class SeqMatrixValue extends MatrixValue{
    
    private Value<?> from;
    private Value<?> to;
    private boolean inverted;

    public SeqMatrixValue(Value<?> from, Value<?> to, boolean inverted, int line) {
        super(line);
        this.from = from;
        this.to = to;
        this.inverted = inverted;
    }

    @Override
    public Matrix Value() {
        return null;
    }
    
    
}
