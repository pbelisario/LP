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
public class RandMatrixValue extends MatrixValue {

    private Value<?> r;
    private Value<?> c;
    private Matrix m;

    public RandMatrixValue(Value<?> r, Value<?> c, int line) {
        super(line);
        this.r = r;
        this.c = c;
        this.m = null;
    }

    @Override
    public Matrix Value() {

        if (m == null) {

            Value row = (r instanceof Variable ? ((Variable) r).Value() : r);
            Value col = (c instanceof Variable ? ((Variable) c).Value() : c);

            if (!(row instanceof IntValue && col instanceof IntValue)) {
                if (!(row instanceof IntValue)) {
                    lp.SyntaticalAnalysis.error(this.getLine(), "Valor Invalido : valor de linha invalido");
                } else {
                    lp.SyntaticalAnalysis.error(this.getLine(), "Valor Invalido : valor de coluna invalido");
                }
            }

            Integer rows = ((IntValue) row).Value();
            Integer cols = ((IntValue) col).Value();
            
            m = Matrix.rand(rows,cols);
        }
        return m;
    }

}
