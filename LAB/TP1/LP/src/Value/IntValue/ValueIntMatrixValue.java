/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.IntValue;

import Value.MatrixValue.Matrix;
import Value.MatrixValue.MatrixValue;
import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class ValueIntMatrixValue extends IntMatrixValue {
    
    private Value<?> v1;
    private Value<?> v2;

    public ValueIntMatrixValue(Value<?> v1, Value<?> v2, Value<?> matrix, int line) {
        super(matrix, line);
        this.v1 = v1;
        this.v2 = v2;
    }
 
    public Integer Value(){
        Value valM = (this.matrix instanceof Variable ? ((Variable) this.matrix).Value() : this.matrix);
        Value val1 = (this.v1 instanceof Variable ? ((Variable) this.v1).Value() : this.v1);
        Value val2 = (this.v2 instanceof Variable ? ((Variable) this.v2).Value() : this.v2);
        
        if(!(valM instanceof MatrixValue && val1 instanceof IntValue && val2 instanceof IntValue)){
           lp.SyntaticalAnalysis.error(this.getLine()," Matriz invalida");
        }
        
        Matrix m = ((MatrixValue) valM).Value();
        int rows = ((IntValue) val1).Value();
        int cols = ((IntValue) val2).Value();
        
        return m.value(rows,cols);
    }
}
