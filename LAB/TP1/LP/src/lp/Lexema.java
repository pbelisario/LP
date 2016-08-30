/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp;

/**
 *
 * @author alunoccc
 */
class Lexema{
    public String token;
    public TokenType type;
        
    public Lexema(String s, TokenType tt){
        token = s;
        type = tt;          
    }
}