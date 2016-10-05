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
public class SeqMatrixValue extends MatrixValue{
    
    private Value<?> from;
    private Value<?> to;
    private boolean inverted;

    public SeqMatrixValue(Value<?> from, Value<?> to, boolean inverted, int line) {
        super(line);
        this.from = from;
        this.to = to;
        this.inverted = inverted;
    }

    @Override
    public Matrix Value() {
        
        Value f = (from instanceof Variable ? ((Variable) from).Value() : from);
        Value t = (to instanceof Variable ? ((Variable) to ).Value(): to);

        if (!(f instanceof IntValue && t instanceof IntValue)) {
            lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido : valor de linha invalido");
        }
        
        Integer from = ((IntValue) f).Value();
        Integer to = ((IntValue) t).Value();
        
        
        if (inverted) {
            return Matrix.iseq(from, to);
        } else {
            return Matrix.seq(from, to);
        }
    }
    
    
}
