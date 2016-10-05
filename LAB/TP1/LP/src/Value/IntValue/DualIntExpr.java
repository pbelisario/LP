/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.IntValue;

import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class DualIntExpr extends IntValue{
    
    private IntOp op;
    private Value<?> left;
    private Value<?> right;

    public DualIntExpr(IntOp op, Value<?> left, Value<?> right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Integer Value() {
        Value<?> v1 = (left instanceof Variable ? ((Variable) left).Value() : left);
        Value<?> v2 = (right instanceof Variable ? ((Variable) right).Value() : right);
        
        if(!(v1 instanceof IntValue && v2 instanceof IntValue)){
            if(!(v1 instanceof IntValue)){
                lp.SyntaticalAnalysis.error(this.getLine()," Primeiro inteiro invalido");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine()," Segundo inteiro invalido");
            }
        }
        
        Integer l = ((IntValue) v1).Value();
        Integer r = ((IntValue) v2).Value();
        
        if(op == IntOp.Add){
            return l+r;
        } else if(op == IntOp.Sub){
            return l-r;
        } else if(op == IntOp.Mul){
            return l*r;
        } else if(op == IntOp.Div){
            return l/r;
        } else if(op == IntOp.Mod){
            return l%r;
        }
        return 0;
    }
    
    
}
