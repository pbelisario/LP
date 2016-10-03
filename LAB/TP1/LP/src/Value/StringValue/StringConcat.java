/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.StringValue;

import Value.StringValue.StringValue;
import Value.Value;
import Value.Variable;

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
        Value val1 = (left instanceof Variable ? ((Variable) left).Value() : left);
        Value val2 = (right instanceof Variable ? ((Variable) right).Value() : right);
        
        if(!(val1 instanceof StringValue && val2 instanceof StringValue)){
            if(!(val1 instanceof StringValue)){
                lp.SyntaticalAnalysis.error(this.getLine()," primeira String invalida");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine()," primeira String invalida");
            }
        }
        
        String v1 = ((StringValue) val1).Value();
        String v2 = ((StringValue) val2).Value();
        
        return (v1+v2);
    }
}
