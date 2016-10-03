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
public class NullMatrixValue extends MatrixValue{
    
    private Value<?> r;
    private Value<?> c;

    public NullMatrixValue(Value<?> r, Value<?> c, int line) {
        super(line);
        this.r = r;
        this.c = c;
    }

    @Override
    public Matrix Value() {
        Value row = (r instanceof Variable ? ((Variable) r).Value() : r);
        Value col = (c instanceof Variable ? ((Variable) c).Value() : c);
        
        if(!(row instanceof IntValue && col instanceof IntValue)){
            if(!(row instanceof IntValue)){
                lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido : valor de linha invalido");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido : valor de coluna invalido");
            }
        }
        
        int rows = ((IntValue) row).Value();
        int cols = ((IntValue) col).Value();
        
        return Matrix.Null(rows, cols);
    }
    
    
}
