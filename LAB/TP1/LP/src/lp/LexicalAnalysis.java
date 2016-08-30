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
 * @author pfbit
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
        while(estado != 8){
            c = input.read();// *****************
            switch(estado){
                case 1:
                    if( c == -1 ) 
                        return lex;
                    if (c == ' ' || c == '\t' || c == '\r' || c == '\n'){
                        lex.token += (char) c;
                        estado = 1;
                    } else if (c == '#'){
                        lex.token += (char) c;
                        estado = 2;
                    } else if(Character.isDigit(c)){
                        lex.token += (char) c;
                        estado = 3;
                    } else if(c == '!'){
                        lex.token += (char) c;
                        estado = 4;
                    } else if(c == '>' || c == '<' || c == '='){
                        lex.token += (char) c;
                        estado = 5;
                    } else if(Character.isLetter(c)){
                        lex.token += (char) c;
                        estado = 6;
                    } else if (c == '"'){
                        estado = 7;
                    } else if(c == ';' || c == '.' || c == '(' || c == ')' || c == '[' || c == ']' || c == '&' || c == '|' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%'){
                        lex.token += (char) c;
                        estado = 8;
                    } else {
                        lex.token += (char) c;
                        lex.type = TokenType.INVALID_TOKEN;
                        return lex;
                    }
                    break;
                case 2:
                    if( c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    if(c == '\n'){
                        lex.token += (char) c;
                        estado = 1;
                    }
                    else{
                        lex.token += (char) c;
                        estado = 2;
                    }
                case 3:
                    if( c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    if(Character.isDigit(c)){
                        lex.token += (char) c;
                        estado = 3;
                    } else if(c != -1){ 
                        input.unread(c);
                        lex.type = TokenType.NUMBER;
                        estado = 8;
                    }
                    break;
                case 4:
                    if( c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    if(c == '='){
                        lex.token += (char) c;
                        estado = 8;
                    } 
                    else {
                        lex.type = TokenType.INVALID_TOKEN;
                        return lex;
                    }
                    break;
                case 5:
                    if( c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    if(c == '='){
                        lex.token += (char) c;
                        estado = 8;                  
                    } else {
                        input.unread(c);
                        estado = 8;
                    }
                    break;
                case 6:
                    if( c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    if(Character.isLetter(c) || Character.isDigit(c)){
                        lex.token += (char) c;
                        estado = 6;
                    }
                    else{
                        input.unread(c);
                        estado = 8;
                    }
                    break;
                case 7:
                    if( c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    if(c != '"'){
                        lex.token += (char) c;
                        estado = 7;
                    }
                    else{
                        estado = 8;
                    }
                    lex.type = TokenType.STRING;
                    break;
            }
        }
        if(st.contains(lex.token)){
            lex.type = st.getSymbol(lex.token);
        } else {
            lex.type = TokenType.VAR;
        }
        return lex;
    }
}
