/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.BoolValue;

/**
 *
 * @author higorfischerdepaulalopes
 */
public enum RelOp {
    Equal,
    NotEqual,
    LowerThan,
    LowerEqual,
    GreaterThan,
    GreaterEqual;
    
    private int aux;
    
    public int getValue(){
        return aux;
    }
}
