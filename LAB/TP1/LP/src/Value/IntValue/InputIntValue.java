/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value.IntValue;

import Value.Value;
import java.util.Scanner;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class InputIntValue extends IntValue{
    
    private Value<?> string;

    public InputIntValue(Value<?> string, int line) {
        super(line);
        this.string = string;
    }

    @Override
    public Integer Value() {
        Scanner in = new Scanner(System.in);
        try{
            return Integer.parseInt(in.next());
        } catch(NumberFormatException e) {
            System.out.println("Tipo errado");
        }
        return null;
    }
    
    
    
}
