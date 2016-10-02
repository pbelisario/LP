/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.StringValue;

import Value.StringValue.StringValue;
import Value.Value;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class StringConcat extends StringValue{
    
    private Value<?> left;
    private Value<?> right;
    
    
    public StringConcat(Value<?> left, Value<?> right,int line){
        super(line);
        this.left = left;
        this.right = right;
    }
    
    public String Value(){
        String v1 = ((StringValue) left).Value();
        String v2 = ((StringValue) right).Value();
        
        return (v1+v2);
    }
}
