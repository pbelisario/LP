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
        Value v1 = (this.matrix instanceof Variable ? ((Variable) this.matrix).Value() : this.matrix);
        
        if(v1 instanceof MatrixValue){
           //Excecao
        }
        
        Matrix m = ((MatrixValue) v1).Value();
        int rows = ((IntValue) v1).Value();
        int cols = ((IntValue) v2).Value();
        
        return m.value(rows,cols);
    }
}
