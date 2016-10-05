/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.BoolValue;

import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class DualBoolExpr extends BoolValue {
    
    private BoolOp op;
    private BoolValue left;
    private BoolValue right;

    public DualBoolExpr(BoolOp op, BoolValue left, BoolValue right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }
    
    public Boolean Value() {
        //Value v1 = (left instanceof Variable ? ((Variable) left).Value() : left);
        Value v1 = left;
        Value v2 = right;
        
        if(!(v1 instanceof BoolValue && v2 instanceof BoolValue)){
            if(!(v1 instanceof BoolValue)){
                lp.SyntaticalAnalysis.error(this.getLine(),"Primeira expressao invalida");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine(),"Segunda expressao invalida");
            }
        }
        
        boolean l = ((BoolValue) v1).Value();
        boolean r = ((BoolValue) v2).Value();
        
        if(op == BoolOp.And){
            return (l && r);
        } else if(op == BoolOp.Or){
            return (l || r);
        }
        return null;
        
    }
    
    
    
}
