/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.MatrixValue;

import Value.Value;
import Value.Variable;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class SumMatrixValue extends MatrixValue{
    
    private Value<?> matrix1;
    private Value<?> matrix2;

    public SumMatrixValue(Value<?> matrix1,Value<?> matrix2, int line) {
        super(line);
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    @Override
    public Matrix Value() {
        Value v1 = (this.matrix1 instanceof Variable ? ((Variable) this.matrix1).Value() : this.matrix1);
        Value v2 = (this.matrix2 instanceof Variable ? ((Variable) this.matrix2).Value() : this.matrix2);
        
        if(!(v1 instanceof MatrixValue && v2 instanceof MatrixValue)){
            if(!(v1 instanceof MatrixValue)){
                lp.SyntaticalAnalysis.error(this.getLine(),"Matriz 1 invalida");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine(),"Matriz 2 invalida");
            }
        }
        
        Matrix m1 = ((MatrixValue) v1 ).Value();
        Matrix m2 = ((MatrixValue) v2 ).Value();
        
        return m1.sum(m2);
    }
    
    
    
}
