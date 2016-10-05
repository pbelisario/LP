/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.IntValue;

import Value.StringValue.StringValue;
import Value.Value;
import Value.Variable;
import Value.MatrixValue.Matrix;
import Value.MatrixValue.MatrixValue;

import java.util.Scanner;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class InputIntValue extends IntValue{
    
    private Value<?> value;
    private static Scanner in;
    
    static {
    	in = new Scanner(System.in);
    }

    public InputIntValue(Value<?> value, int line) {
        super(line);

        this.value = value;
    }

    @Override
    public Integer Value() {
        Value<?> val = (this.value instanceof Variable ? ((Variable) this.value).Value() : this.value);
        
       if(val instanceof IntValue){
           IntValue iv = (IntValue) val;
           int n = iv.Value();
           System.out.print(n);
       } else if (val instanceof StringValue){
           StringValue sv = (StringValue) val;
           String s = sv.Value();
           System.out.print(s);
       } else if(val instanceof MatrixValue){
           MatrixValue mv = (MatrixValue) val;
           Matrix m = mv.Value();
           m.show();
       } else {
          lp.SyntaticalAnalysis.error(this.getLine(),"Valor Invalido[show]");
       }

       return in.nextInt();
    }
    
    
    
}
