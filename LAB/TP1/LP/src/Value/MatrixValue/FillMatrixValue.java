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
public class FillMatrixValue extends MatrixValue{
    
    private Value<?> r;
    private Value<?> c;
    private Value<?> v;

    public FillMatrixValue(Value<?> r, Value<?> c, Value<?> v, int line) {
        super(line);
        this.r = r;
        this.c = c;
        this.v = v;
    }

    @Override
    public Matrix Value() {
        Value row = (r instanceof Variable ? ((Variable) r).Value() : r);
        Value col = (c instanceof Variable ? ((Variable) c).Value() : c);
        Value val = (v instanceof Variable ? ((Variable) v).Value() : v);
        
        if(!(row instanceof IntValue && col instanceof IntValue && val instanceof IntValue)){
            if(!(row instanceof IntValue)){
                lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido : valor de linha invalido");
            } else if(col instanceof IntValue){
                lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido : valor de coluna invalido");
            } else {
                lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido : valor invalido");
            }
        }
        
        int rows = ((IntValue) r).Value();
        int cols = ((IntValue) c).Value();
        int value = ((IntValue) v).Value();
        
        
        
        return Matrix.fill(rows, cols, value);
        
    }
    
    
}
