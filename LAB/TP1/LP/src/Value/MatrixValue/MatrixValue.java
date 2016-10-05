/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.*;
import Value.Value;

/**
 *
 * @author decom
 */
public abstract class MatrixValue extends Value<Matrix>{
    
    public MatrixValue(int line) {
        super(line);
    }
    
    @Override
    public abstract Matrix Value();
    
}
