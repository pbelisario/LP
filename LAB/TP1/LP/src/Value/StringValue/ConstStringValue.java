/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.StringValue;

import Value.StringValue.StringValue;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class ConstStringValue extends StringValue{
    
    private String value;
    
    public ConstStringValue(String value,int line){
        super(line);
        this.value = value;
    }
 
    public String Value(){
        return this.value;
    }
    
    
}
