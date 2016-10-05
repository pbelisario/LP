/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value;

import Value.Value;

/**
 *
 * @author root
 */
public class Variable extends Value<Value<?>> {
    
    private String name;
    private Value<?> value;
    
    public Variable(String name,Value<?> value,int line){
        super(line);
        this.name = name;
        this.value = value;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setValue(Value<?> value){
        this.value = value;
    }
    
    public Value<?> Value(){
        return this.value;
    }
}
