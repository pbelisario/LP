/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp;

import Pacote.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.parser.Token;

/**
 *
 * @author Aluno
 */
public class SyntaticalAnalysis {
    private LexicalAnalysis la;
    private Lexema current;
    private Map<String, Variable> variables = new HashMap<String, Variable>;
    
    public SyntaticalAnalysis(LexicalAnalysis la) throws IOException{
        this.la = la;
        this.current = la.nextToken();
    }

    SyntaticalAnalysis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void matchToken(TokenType type) throws IOException{
        if(type == current.type){
            current = la.nextToken();
        } else {
            if(current.type == TokenType.UNEXPECTED_EOF){
                unexpectedEofError();
            } else {
                unexpectedToken();
            }
        }
    }
    
    void start() throws IOException{
        procStatements();
        matchToken(TokenType.END_OF_FILE);
    }
    
    //<statements> ::= <statement> { <statement>}
    void procStatements() throws IOException{ 
        procStatement();
        while(current.type == TokenType.INPUT ||
        current.type == TokenType.SHOW ||
        current.type == TokenType.VAR||
        current.type == TokenType.IF ||
        current.type == TokenType.WHILE ||
        current.type == TokenType.FOR ){
            procStatement();
        }
    }
    
    //<statement> ::= <show> | <assign> | <if> | <while> | <for>
    void procStatement() throws IOException{ 
        if(current.type == TokenType.SHOW){
            procShow();
        } else if(current.type == TokenType.VAR){
            procAssign();
        } else if(current.type == TokenType.IF){
            procIf();
        } else if(current.type == TokenType.WHILE){
            procWhile();
        } else if(current.type == TokenType.FOR){
            procFor();
        } else{
            showError();
        }
    }
    
    //<input> ::= input '(' <text> ')' ';'
    void procInput() throws IOException{
        matchToken(TokenType.INPUT);
        matchToken(TokenType.PAR_OPEN);
        procText();
        matchToken(TokenType.PAR_CLOSE);
        matchToken(TokenType.DOT_COMMA);
    }
    
    //<show> ::= show '(' <text> ')' ';'
    ShowCommand procShow() throws IOException{
        matchToken(TokenType.SHOW);
        matchToken(TokenType.PAR_OPEN);
        Value<?> v = procText();
        matchToken(TokenType.PAR_CLOSE);
        matchToken(TokenType.DOT_COMMA);
        ShowCommand c = new ShowCommand(v);
        return c;
    }
    //<assign> ::= <var> '=' <value> ';'
    void procAssign() throws IOException{
        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value<?> val = procValue();
        matchToken(TokenType.DOT_COMMA);
        AssignedCommand c = new AssignedCommand(var,val,lex.getLine());
        return c;
    }
    
    //<if> ::= if <boolexpr> <statements> [else <statements>] end
    void procIf() throws IOException{
        matchToken(TokenType.IF);
        procBoolExpr();
        procStatement();
        if(current.type == TokenType.ELSE){
            matchToken(TokenType.ELSE);
            procStatement();
        }
        matchToken(TokenType.END);
    }
    
    //<while> ::= whlie <boolexpr> <statements> end
    void procWhile() throws IOException{
        matchToken(TokenType.PAR_OPEN);
        procBoolExpr();
        procStatements();
        matchToken(TokenType.END);
    }
    
    //<for> ::= for <var> '=' <value> <statements> end
    void procFor() throws IOException{
        matchToken(TokenType.FOR);
        procMatrixExpr();
        procStatements();
        matchToken(TokenType.END);
    }
    
    //<text> ::= { <string> | <value> }
    void procText() throws IOException{
      if(current.type == TokenType.STRING){
          procString();
      } else if(current.type == TokenType.VALUE){
          procValue();
      } else {
          showError();
      }
    }
    
    //<boolexpr> ::= <expr> <boolop> <expr> { ('&' | '|') <boolexpr> }
    void procBoolExpr() throws IOException{
        procExpr();
        procBoolOP();
        procExpr();
        while(current.type == TokenType.AND || current.type == TokenType.OR){
            if(current.type == TokenType.AND){
                matchToken(TokenType.AND);
                procBoolExpr();
            } else if(current.type == TokenType.OR){
                matchToken(TokenType.OR);
                procBoolExpr();
            } else {
                showError();
            }
        }
    }
    
    //<boolop> ::= '==' | '!=' | '<' | '>' | '<=' | '>='
    void procBoolOP() throws IOException{
        if(current.type == TokenType.EQUAL){
            matchToken(TokenType.EQUAL);
        } else if(current.type == TokenType.DIFF){
            matchToken(TokenType.DIFF);
        } else if(current.type == TokenType.LOWER){
            matchToken(TokenType.LOWER);
        } else if(current.type == TokenType.GREATER){
            matchToken(TokenType.GREATER);
        } else if(current.type == TokenType.LOWER_EQUAL){
            matchToken(TokenType.LOWER_EQUAL);
        } else if(current.type == TokenType.GREATER_EQUAL){
            matchToken(TokenType.GREATER_EQUAL);
        }
    }
   
    //<expr> ::= <term> [ ('+' | '-') <term> ]
    void procExpr() throws IOException {
        procTerm();
        if(current.type == TokenType.PLUS){
            matchToken(TokenType.PLUS);
            procTerm();
        } else if (current.type == TokenType.MINUS){
            matchToken(TokenType.MINUS);
            procTerm();
        } else {
            showError();
        }        
    }
    
    //<term> ::= <factor> [ ('*' | '/' | '%') <factor> ]
    void procTerm() throws IOException {
        procFactor();
        if(current.type == TokenType.TIMES){
            matchToken(TokenType.TIMES);
            procFactor();
        } else if (current.type == TokenType.DIV){
            matchToken(TokenType.DIV);
            procFactor();
        } else if(current.type == TokenType.MOD){
            matchToken(TokenType.MOD);
            procFactor();
        }
    }
    
    //<factor> ::= (['+' | '-'] <number>) | <value> | '(' <expr> ')'
    void procFactor() throws IOException {
         if(current.type == TokenType.PLUS){
            matchToken(TokenType.PLUS);
            procNumber();
        } else if (current.type == TokenType.MINUS){
            matchToken(TokenType.MINUS);
            procNumber();
        } else if(current.type == TokenType.VALUE){
            procValue();
        } else if(current.type == TokenType.PAR_OPEN){
            matchToken(TokenType.PAR_OPEN);
            procExpr();
            matchToken(TokenType.PAR_CLOSE);
        } else {
            showError(); 
        }
    }

//    <value> ::= (<var> | <gen>)
//        { '.' (<opposed> | <transposed> | <sum> | <mul>) }
//        [ '.' (<size> | <rows> | <cols> | <val>) ]
    void procValue() throws IOException {
        if(current.type == TokenType.VAR){
            procVar();
        } else if(current.type == TokenType.BRA_OPEN) {
            procGen();
        } else {
            showError();
        }
        while(current.type == TokenType.DOT){
            matchToken(TokenType.DOT);
            if(current.type == TokenType.OPPOSED){
                procOpposed();
            } else if(current.type == TokenType.TRANSPOSED){
                procTransposed();
            } else if(current.type == TokenType.SUM){
                procSum();
            } else if(current.type == TokenType.MUL){
                procMul();
            } else if(current.type == TokenType.SIZE){
                procSize();
                break;
            } else if(current.type == TokenType.ROWS){
                procRows();
                break;
            } else if(current.type == TokenType.COLS){
                procCols();
                break;
            } else if(current.type == TokenType.VALUE){
                procValue();
                break;
            }
        }
    }
    
    //<opposed> ::= opposed '(' ')' 
    void procOpposed() throws IOException {
        matchToken(TokenType.OPPOSED);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
    }
    
    //<transposed> ::= transposed '(' ')'
    void procTransposed() throws IOException {
        matchToken(TokenType.TRANSPOSED);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
    }

    //<sum> ::= sum '(' <value> ')'
    void procSum() throws IOException {
        matchToken(TokenType.SUM);
        matchToken(TokenType.PAR_OPEN);
        procValue();
        matchToken(TokenType.PAR_CLOSE);
    }

    //<mul> ::= mul '(' <value> ')'
    void procMul() throws IOException {
        matchToken(TokenType.MUL);
        matchToken(TokenType.PAR_OPEN);
        procValue();
        matchToken(TokenType.PAR_CLOSE);
    }
    
    void procSize() throws IOException{
        matchToken(TokenType.SIZE);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
    }
    
    void procRows() throws IOException{
        matchToken(TokenType.ROWS);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
    }
    
   //<cols> ::= cols '(' ')'
   void procCols() throws IOException{
        matchToken(TokenType.COLS);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
    }
    
   //<val> ::= value '(' <value> ',' <value> ')'
    void procVal() throws IOException{
        matchToken(TokenType.VALUE);
        matchToken(TokenType.PAR_OPEN);
        procValue();
        matchToken(TokenType.COMMA);
        procValue();
        matchToken(TokenType.PAR_CLOSE);
    }
    
    //<gen> ::= '[' ']' '.' (<null> | <fill> | <rand> | <id> | <seq> | <iseq>)
    private void procGen() throws IOException {
        matchToken(TokenType.BRA_OPEN);
        matchToken(TokenType.BRA_CLOSE);
        matchToken(TokenType.DOT);
        if(current.type == TokenType.NULL){
            procNull();
        } else if(current.type == TokenType.FILL){
            procFill();
        } else if(current.type == TokenType.RAND){
            procRand();
        } else if(current.type == TokenType.ID){
            procId();
        } else if(current.type == TokenType.SEQ){
            procSeq();
        } else if(current.type == TokenType.ISEQ){
            procIseq();
        } else {
            showError();
        }
    }
    
    //<null> ::= null '(' <expr> ',' <expr> ')'
    void procNull() throws IOException {
        matchToken(TokenType.NULL);
        matchToken(TokenType.PAR_OPEN);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.PAR_CLOSE);
    }

    //<fill> ::= fill '(' <expr> ',' <expr> ',' <expr> ')'
    void procFill() throws IOException {
        matchToken(TokenType.FILL);
        matchToken(TokenType.PAR_OPEN);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.PAR_CLOSE);
    }

    //<rand> ::= rand '(' <expr> ',' <expr> ')'
    void procRand() throws IOException {
        matchToken(TokenType.RAND);
        matchToken(TokenType.PAR_OPEN);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.PAR_CLOSE);
    }

    //<id> ::= id '(' <expr> ',' <expr> ')'
    void procId() throws IOException {
        matchToken(TokenType.ID);
        matchToken(TokenType.PAR_OPEN);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.PAR_CLOSE);
    }

    //<seq> ::= seq '(' <expr> ',' <expr> ')'
    void procSeq() throws IOException {
        matchToken(TokenType.SEQ);
        matchToken(TokenType.PAR_OPEN);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.PAR_CLOSE);
    }

    //<iseq> ::= iseq '(' <expr> ',' <expr> ')'
    void procIseq() throws IOException {
        matchToken(TokenType.ISEQ);
        matchToken(TokenType.PAR_OPEN);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.PAR_CLOSE);
    }
   
    
    Variable procVar() throws IOException{
        String varName = current.token;
        matchToken(TokenType.VAR);
        Variable v = variables.get(varName);
        if(v == null){
            v = new Variable(varName);
            variables.put(varName, v);
        }
        return v;
    }
    
    ConstInt procNumber() throws IOException{
        String tmp = current.token;
        matchToken(TokenType.NUMBER);
        int n = Integer.parseInt(tmp);
        ConstInt v = new ConstInt(n);
        return v;
    }
    
    ConstString procString() throws IOException{
        String tmp = current.token;
        matchToken(TokenType.String);
        String s = tmp;
        ConstStringv = new ConstString(s);
        return s;
    }
    

   
     // <matrixexpr> ::= ( <var>|<gen> ) {'.' ( <opposed>| <transposed> | <sum> | <mul> )}
    void procMatrixExpr() throws IOException{
        if(current.type == TokenType.VAR) procVar();
        else if(current.type == TokenType.BRA_OPEN) procGen();
        else {
            showError();
        } while(current.type == TokenType.DOT){
            matchToken(TokenType.DOT);
            if(current.type == TokenType.TRANSPOSED){
                procTransposed();
            } else if(current.type == TokenType.OPPOSED){
                procOpposed();  
            } else if(current.type == TokenType.SUM){
                procSum();
            } else if(current.type == TokenType.MUL){
                procMul();
            } else {
                showError();
            }
            
        }
        
    }

    private void unexpectedEofError() {
        System.out.println("Error, Fim de arquivo inexperado");
    }

    private void unexpectedToken() {
        System.out.println("ERROR, Token inesperado : "+ '"'+current.type+'"');
        System.exit(1);
              
    }

    private void showError() {
        if(current.type == TokenType.UNEXPECTED_EOF){
            unexpectedEofError();
        } else if(current.type == TokenType.INVALID_TOKEN){
            unexpectedToken();
        }
    }

}
