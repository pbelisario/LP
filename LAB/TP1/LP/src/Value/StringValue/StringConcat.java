/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.StringValue;

import Value.IntValue.IntValue;
import Value.MatrixValue.MatrixValue;
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
        
        String s1 = "";
        if (val1 instanceof IntValue)s1 = Integer.toString(((IntValue) val1).Value());
        else if (val1 instanceof StringValue)s1 = ((StringValue) val1).Value();
        else if (val1 instanceof MatrixValue) s1 = ((MatrixValue) val1).Value().toString();

        
        String s2 = "";
        if (val2 instanceof IntValue) s2 = Integer.toString(((IntValue) val2).Value());
        else if (val2 instanceof StringValue) s2 = ((StringValue) val2).Value();
        else if (val2 instanceof MatrixValue) s2 = ((MatrixValue) val2).Value().toString();
        
        return (s1+s2);
    }
}
