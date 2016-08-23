/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.PushbackReader;

/**
 *
 * @author alunoccc
 */
public class LexicalAnalysis {
    //PUSH BACK READER input
    public PushbackInputStream input;
    public SymbolTable st;
    
    public LexicalAnalysis(String file) throws FileNotFoundException{
        input = new PushbackInputStream( new FileInputStream(file) );
        st = new SymbolTable();
    }
    
    public Lexema nextToken() throws IOException{
        Lexema lex = new Lexema("", TokenType.END_OF_FILE);
        //PushbackReader input = new PushbackReader();
        int estado = 1;
        int c;
        while(estado != 7){
            c = input.read();// *****************
            switch(estado){
                case 1:
                    if( c == -1 ) return lex;
                    if(Character.isDigit(c)){
                        lex.token += (char) c;
                        estado = 6;
                    } else if( c == '>'){
                        lex.token += (char) c;
                        estado = 5;
                    } else if(Character.isLetter(c)){
                        lex.token += (char) c;
                        estado = 6;
                    } else {
                        lex.token += (char) c;
                        lex.type = TokenType.INVALID_TOKEN;
                        return lex;
                    }
                    break;
                case 3:
                    if(Character.isDigit(c)){
                        lex.token += (char) c;
                        estado = 3;
                    } else {
                        if(c != -1) input.unread(c);
                        lex.type = TokenType.NUMBER; // TokenType = Symbol.
                    }
                    break;
                case 4:
                    if(c == '='){
                        lex.token += (char) c;
                        estado = 7;
                    } else {
                        if( c == -1){
                            lex.type = TokenType.UNEXPECTED_EOF;
                            return lex;
                        } else {
                            lex.type = TokenType.INVALID_TOKEN;
                            return lex;
                        }
                    }
                    break;
                case 5:
                    if(c == '='){
                        lex.token += (char) c;
                        estado = 7;                  
                    } else {
                        input.unread(c);
                        estado = 7;
                    }
                    break;
            }
        }
        if(st.haveLex(lex.token)){
            lex.type = st.getSymbol(lex.token);
        } else {
            lex.type = TokenType.VAR;
        }
        return lex;
    }
}
