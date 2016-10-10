/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.BoolValue;

import Value.IntValue.ConstIntValue;
import Value.IntValue.IntValue;
import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class CompareBoolValue extends BoolValue{

    private RelOp op;
    private Value<?> left;
    private Value<?> right;
    
    public CompareBoolValue(RelOp op,Value<?> left, Value<?> right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean Value() {
        
        Value v1 = (left instanceof Variable ? ((Variable) left).Value() : left);
        Value v2 = (right instanceof Variable ? ((Variable) right).Value() : right);
        
        if(!(v1 instanceof IntValue && v2 instanceof IntValue)){
            if(!(v1 instanceof IntValue)){
                lp.SyntaticalAnalysis.error(this.getLine()," Primeiro valor invalido");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine()," Segundo valor invalido");
            }
        }
        
        int l = ((IntValue) v1).Value();
        int r = ((IntValue) v2).Value();
       
        
        if(op == RelOp.Equal){
            return (l == r);
        } else if(op == RelOp.GreaterEqual){
            return (l >= r);
        } else if(op == RelOp.GreaterThan){
            return (l > r);
        } else if(op == RelOp.LowerEqual){
            return (l <= r);
        } else if(op == RelOp.LowerThan){
            return (l < r);
        } else if(op == RelOp.NotEqual){
            return (l != r);
        }
        return null;
    }
    
}
