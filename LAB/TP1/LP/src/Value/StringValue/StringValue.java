/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.StringValue;

import Value.Value;
import Value.Value;

/**
 *
 * @author decom
 */
public abstract class StringValue extends Value<String>{
    
    public StringValue(int line) {
        super(line);
    }
    
    public abstract String Value();
    
    
}
