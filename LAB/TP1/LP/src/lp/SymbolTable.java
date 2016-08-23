/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alunoccc
 */
class  SymbolTable{
    
	private Map<String,TokenType> st = new HashMap<>();

	public TokenType getSymbol(String lex){
		if( st.containsKey(lex)) return st.get(lex);
		else return TokenType.INVALID_TOKEN;
	}

	public void addToken(String lex, TokenType s){
		st.put (lex,s);
	}

	public boolean haveLex(String lex){
		return st.containsKey(lex);
	}

	public SymbolTable(){
                //addToken("",Symbol.VAR);
		addToken("=",TokenType.ASSIGN);
		addToken("input",TokenType.INPUT);
		addToken("(",TokenType.PAR_OPEN);
		addToken(")",TokenType.PAR_CLOSE);
                //addToken("",Symbol.STRING);
                addToken(";",TokenType.POT_COMMA);
                addToken("[",TokenType.BRA_OPEN);
                addToken("]",TokenType.BRA_CLOSE);
                addToken(".",TokenType.DOT);
                addToken("rand",TokenType.RAND);
                addToken(",",TokenType.COMMA);
                addToken("show",TokenType.SHOW);
                //addToken("",Symbol.NUMBER);
                addToken("for",TokenType.FOR);
                addToken("seq",TokenType.SEQ);
                addToken("-",TokenType.MINUS);
                addToken("+",TokenType.PLUS);
                addToken("*",TokenType.TIMES);
                addToken("/",TokenType.DIV);
                addToken("%",TokenType.MOD);
                addToken("value",TokenType.VALUE);
                addToken("end",TokenType.END);
                addToken("iseq",TokenType.ISEQ);
                addToken("==",TokenType.EQUAL);
                addToken("!=",TokenType.DIFF);
                addToken("<",TokenType.LOWER);
                addToken("<=",TokenType.LOWER_EQUAL);
		addToken(">",TokenType.GREATER);
                addToken(">=",TokenType.GREATER_EQUAL);
                addToken("&",TokenType.AND);
                addToken("|",TokenType.OR);
                addToken("if",TokenType.IF);
                addToken("else",TokenType.ELSE);
                addToken("while",TokenType.WHILE);
		addToken("opposed",TokenType.OPPOSED);
		addToken("transposed",TokenType.TRANSPOSED);
		addToken("sum",TokenType.SUM);
		addToken("mul",TokenType.MUL);
		addToken("null",TokenType.NULL);
		addToken("fill",TokenType.FILL);
		addToken("id",TokenType.ID);
		addToken("size",TokenType.SIZE);
		addToken("rows",TokenType.ROWS);
		addToken("cols",TokenType.COLS);
	}
}