/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value;

/**
 *
 * @author decom
 */
public abstract class Value<T> {
    
    private int line;
    
    public Value(int line){
        this.line = line;
    }
    
    public int getLine(){
        return this.line;
    }
    
    public abstract T Value();
}
