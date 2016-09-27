/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacote;

import Pacote.Value;

/**
 *
 * @author decom
 */
public abstract class IntValue extends Value<Integer> {
    
    public IntValue(int line){
        super(line);
    }
    
    @Override
    public abstract Integer Value();
}
