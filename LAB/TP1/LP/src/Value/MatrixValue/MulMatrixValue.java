/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.IntValue.IntValue;
import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class MulMatrixValue extends MatrixValue {
    

    private Value<?> matrix;
    private Value<?> value;

    public MulMatrixValue(Value<?> matrix, Value<?> value, int line) {
        super(line);
        this.matrix = matrix;
        this.value = value;
    }

    @Override
    public Matrix Value() {
        
        Value v = (value instanceof Variable ? ((Variable) value).Value() : value);
        Value vM = (matrix instanceof Variable ? ((Variable) matrix).Value() : matrix);
        
        if(!(v instanceof IntValue && vM instanceof MatrixValue)){
            if(!(v instanceof IntValue)){
                lp.SyntaticalAnalysis.error(this.getLine(),"Erro na multiplicacao. Inteiro errado");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine(),"Erro na multiplicacao. Matriz invalida");
            }
        }
        
        
        Matrix m = ((MatrixValue) vM).Value();
        int val = ((IntValue) v).Value();
        
        return m.mul(val);
    }
    
    
    
    
}
