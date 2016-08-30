/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp;

import java.io.IOException;

/**
 *
 * @author Aluno
 */
public class SyntaticalAnalysis {
    private LexicalAnalysis la;
    private Lexema current;
    
    public SyntaticalAnalysis(LexicalAnalysis la) throws IOException{
        this.la = la;
        this.current = la.nextToken();
    }
    
    public void matchToken(TokenType type) throws IOException{
        if(type == current.type){
            current = la.nextToken();
        } else {
            System.exit(1);
        }
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
        matchToken(TokenType.END_OF_FILE);
    }
    
    //<statement> ::= <input> | <show> | <assign> | <if> | <while> | <for>
    void procStatement() throws IOException{ 
        if(current.type == TokenType.INPUT){
            procInput();
        } else if(current.type == TokenType.SHOW){
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
            // Erro : ABORTAR
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
    
    //<show> ::= show '(' (<text> | <matrix> ) ')' ';'
    void procShow() throws IOException{
        matchToken(TokenType.SHOW);
        matchToken(TokenType.PAR_OPEN);
        procText();
    }
    //<assign> ::= <var> '=' <value> ';'
    void procAssign() throws IOException{
        matchToken(TokenType.VAR);
        matchToken(TokenType.ASSIGN);
        procValue();
        matchToken(TokenType.DOT_COMMA);
    }
    
    //<if> ::= if <boolexpr> <commands> [else <commands>] end
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
          //ERRO : ABORT
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
                //ERROR : ABORT
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
            //ERRO : ABORT
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
            //ERRO : ABORT. 
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
            //ERRO : ABORT
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
            //ERROR :ABORT
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
   
    void procVar() throws IOException{
        matchToken(TokenType.VAR);
    }
    
    void procNumber() throws IOException{
        matchToken(TokenType.NUMBER);
    }
    
    void procString() throws IOException{
        matchToken(TokenType.STRING);
    }
    

   
     // <matrixexpr> ::= ( <var>|<gen> ) {'.' ( <opposed>| <transposed> | <sum> | <mul> )}
    void procMatrixExpr() throws IOException{
        if(current.type == TokenType.VAR) procVar();
        else if(current.type == TokenType.BRA_OPEN) procGen();
        else {
            //ERRO : lexema invalido
            //ABORTA
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
                //ERRO : ABORTAR
            }
            
        }
        
    }

}
