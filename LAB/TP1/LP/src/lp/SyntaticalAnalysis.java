/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp;

import Command.*;
import Value.*;
import Value.BoolValue.*;
import Value.IntValue.*;
import Value.MatrixValue.*;
import Value.StringValue.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lp.*;

/**
 *
 * @author Aluno
 */
public class SyntaticalAnalysis {

    private LexicalAnalysis la;
    private Lexema current;
    private Map<String, Variable> variables = new HashMap<String, Variable>();

    public SyntaticalAnalysis(LexicalAnalysis la) throws IOException {
        this.la = la;
        this.current = la.nextToken();
    }

    public void matchToken(TokenType type) throws IOException {
        System.out.printf("%02d. (\"%s\", %s) = %s\n", la.getLine(), current.token, current.type, type);
        if (type == current.type) {
            current = la.nextToken();
        } else {
            showError(current, la.getLine());
        }
    }

    void start() throws IOException {
        CommandBlock commands = procStatements();
        matchToken(TokenType.END_OF_FILE);
        commands.execute();
    }

    //<statements> ::= <statement> { <statement>}
    CommandBlock procStatements() throws IOException {

        CommandBlock block = new CommandBlock(la.getLine());
        Command commands = procStatement();
        block.addCommand(commands);

        while (current.type == TokenType.SHOW
                || current.type == TokenType.VAR
                || current.type == TokenType.IF
                || current.type == TokenType.WHILE
                || current.type == TokenType.FOR) {
            Command command = procStatement();
            block.addCommand(command);
        }
        return block;
    }

    //<statement> ::= <show> | <assign> | <if> | <while> | <for>
    Command procStatement() throws IOException {
        if (current.type == TokenType.SHOW) {
            return procShow();
        } else if (current.type == TokenType.VAR) {
            return procAssign();
        } else if (current.type == TokenType.IF) {
            return procIf();
        } else if (current.type == TokenType.WHILE) {
            return procWhile();
        } else if (current.type == TokenType.FOR) {
            return procFor();
        }
        return null;
    }

    //<show> ::= show '(' <text> ')' ';'
    ShowCommand procShow() throws IOException {
        matchToken(TokenType.SHOW);
        matchToken(TokenType.PAR_OPEN);
        Value val = procText();
        matchToken(TokenType.PAR_CLOSE);
        matchToken(TokenType.DOT_COMMA);

        ShowCommand com = new ShowCommand(val, la.getLine());
        return com;
    }

    //<assign> ::= <var> '=' <expr> ';'
    AssignCommand procAssign() throws IOException {

        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value<?> val = procExpr();
        matchToken(TokenType.DOT_COMMA);

        AssignCommand com = new AssignCommand(var, val, la.getLine());
        return com;
    }

    //<if> ::= if <boolexpr> <statements> [else <statements>] end
    IfCommand procIf() throws IOException {
        matchToken(TokenType.IF);
        BoolValue bool = procBoolExpr();
        Command then = procStatements();
        Command elsee = null;
        if (current.type == TokenType.ELSE) {
            matchToken(TokenType.ELSE);
            elsee = procStatements();
        }
        matchToken(TokenType.END);
        if (elsee == null) {
            return new IfCommand(bool, then, la.getLine());
        } else {
            return new IfCommand(bool, then, la.getLine(), elsee);
        }
    }

    //<while> ::= whlie <boolexpr> <statements> end
    WhileCommand procWhile() throws IOException {
        matchToken(TokenType.PAR_OPEN);
        BoolValue bool = procBoolExpr();
        Command command = procStatements();
        matchToken(TokenType.END);
        WhileCommand commands = new WhileCommand(bool, command, la.getLine());
        return commands;
    }

    //<for> ::= for <var> '=' <value> <statements> end
    ForCommand procFor() throws IOException {
        matchToken(TokenType.FOR);
        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value val = procValue();
        Command command = procStatements();
        matchToken(TokenType.END);
        ForCommand commands = new ForCommand(var, val, command, la.getLine());
        return commands;
    }

    //<text> ::= { <string> | <expr> }
    Value<?> procText() throws IOException {
        Value<?> val1, val = null;
        while (current.type == TokenType.STRING
                || current.type == TokenType.NUMBER
                || current.type == TokenType.INPUT
                || current.type == TokenType.VAR
                || current.type == TokenType.BRA_OPEN
                || current.type == TokenType.PAR_OPEN) {
            val1 = (current.type == TokenType.STRING ? procString() : procExpr());
            if (val != null) {
                val = new StringConcat(val, val1, la.getLine());
            } else {
                val = val1;
            }
        }
        return val;
    }

    //<boolexpr> ::= <expr> <boolop> <expr> { ('&' | '|') <boolexpr> }
    BoolValue procBoolExpr() throws IOException {
        Value left = procExpr();
        RelOp op = procBoolOp(); // <, >, ==, ...
        Value right = procExpr();
        BoolOp bop = null; // and, or

        DualBoolExpr c;// a & b, a | b
        BoolValue a; // left < right, > , ==  ...
        a = new CompareBoolValue(op, left, right, la.getLine());
        while (current.type == TokenType.AND || current.type == TokenType.OR) {
            if (current.type == TokenType.AND) {
                matchToken(TokenType.AND);
                bop = BoolOp.And;
            } else if (current.type == TokenType.OR) {
                matchToken(TokenType.OR);
                bop = BoolOp.Or;
            } else {
                showError(current, la.getLine());
            }
            BoolValue bool = procBoolExpr();
            a = new DualBoolExpr(bop, a, bool, la.getLine());
        }
        return a;
    }

    //<boolop> ::= '==' | '!=' | '<' | '>' | '<=' | '>='
    RelOp procBoolOp() throws IOException {
        if (current.type == TokenType.EQUAL) {
            matchToken(TokenType.EQUAL);
            return RelOp.Equal;
        } else if (current.type == TokenType.DIFF) {
            matchToken(TokenType.DIFF);
            return RelOp.NotEqual;
        } else if (current.type == TokenType.LOWER) {
            matchToken(TokenType.LOWER);
            return RelOp.LowerThan;
        } else if (current.type == TokenType.GREATER) {
            matchToken(TokenType.GREATER);
            return RelOp.GreaterThan;
        } else if (current.type == TokenType.LOWER_EQUAL) {
            matchToken(TokenType.LOWER_EQUAL);
            return RelOp.LowerEqual;
        } else if (current.type == TokenType.GREATER_EQUAL) {
            matchToken(TokenType.GREATER_EQUAL);
            return RelOp.GreaterEqual;
        } else {
           showError(current, la.getLine());
        }
        return null;
    }

    //<expr> ::= <term> [ ('+' | '-') <term> ]
    Value<?> procExpr() throws IOException {
        Value val = procTerm();
        IntOp op = null;

        if (current.type == TokenType.PLUS || current.type == TokenType.MINUS) {
            switch (current.type) {
                case PLUS:
                    matchToken(TokenType.PLUS);
                    op = IntOp.Add;
                    break;
                case MINUS:
                    matchToken(TokenType.MINUS);
                    op = IntOp.Sub;
                    break;
                default:
                    showError(current, la.getLine());
                    break;
            }
            Value val1 = procTerm();
            return new DualIntExpr(op, val, val1, la.getLine());
        }
        return val;

    }

    //<term> ::= <factor> [ ('*' | '/' | '%') <factor> ]
    Value<?> procTerm() throws IOException {
        Value v1 = procFactor();
        IntOp op = null;
        if (current.type == TokenType.TIMES || current.type == TokenType.DIV || current.type == TokenType.MOD) {
            if (current.type == TokenType.TIMES) {
                matchToken(TokenType.TIMES);
                op = IntOp.Mul;
            } else if (current.type == TokenType.DIV) {
                matchToken(TokenType.DIV);
                op = IntOp.Div;
            } else if (current.type == TokenType.MOD) {
                matchToken(TokenType.MOD);
                op = IntOp.Mod;
            } else {
                showError(current, la.getLine());

            }
            Value v2 = procFactor();

            return new DualIntExpr(op, v1, v2, la.getLine());
        }
        return v1;
    }

    //<factor> ::= <number> |<input> | <value> | '(' <expr> ')'
    Value<?> procFactor() throws IOException {
        if (current.type == TokenType.NUMBER) {
            return procNumber();
        } else if (current.type == TokenType.INPUT) {
            return procInput();
        } else if (current.type == TokenType.VAR || current.type == TokenType.BRA_OPEN) {
            return procValue();
        } else if (current.type == TokenType.PAR_OPEN) {
            matchToken(TokenType.PAR_OPEN);
            Value val = procExpr();
            matchToken(TokenType.PAR_CLOSE);
            return val;
        } else {
            showError(current, la.getLine());
            return null;
        }
    }

    //<input> ::= input '(' <text> ')'
    Value<?> procInput() throws IOException {
        matchToken(TokenType.INPUT);
        matchToken(TokenType.PAR_OPEN);
        Value val = procText();
        matchToken(TokenType.PAR_CLOSE);

        InputIntValue input = new InputIntValue(val, la.getLine());
        return input;
    }

//    <value> ::= (<var> | <gen>)
//        { '.' (<opposed> | <transposed> | <sum> | <mul>) }
//        [ '.' (<size> | <rows> | <cols> | <val>) ]
    Value<?> procValue() throws IOException {
        Value<?> val = null;
        if (current.type == TokenType.VAR) {
            Variable var = procVar();
            if (var.Value() == null) {
                val = var;
            } else {
                val = var.Value();
            }
        } else if (current.type == TokenType.BRA_OPEN) {
            val = procGen();
        } else {
            showError(current, la.getLine());
        }

        while (current.type == TokenType.DOT) {
            matchToken(TokenType.DOT);
            if (current.type == TokenType.OPPOSED) {
                val = procOpposed(val);
            } else if (current.type == TokenType.TRANSPOSED) {
                val = procTransposed(val);
            } else if (current.type == TokenType.SUM) {
                val = procSum(val);
            } else if (current.type == TokenType.MUL) {
                val = procMul(val);
            } else if (current.type == TokenType.SIZE) {
                return procSize(val);
            } else if (current.type == TokenType.ROWS) {
                return procRows(val);
            } else if (current.type == TokenType.COLS) {
                return procCols(val);
            } else if (current.type == TokenType.VALUE) {
                return procVal(val);
            } else {
                showError(current, la.getLine());
            }
        }
        return val;
    }

    //<opposed> ::= opposed '(' ')' 
    Value<?> procOpposed(Value<?> val) throws IOException {
        matchToken(TokenType.OPPOSED);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        OpposedMatrixValue op = new OpposedMatrixValue(val, la.getLine());
        return op;
    }

    //<transposed> ::= transposed '(' ')'
    Value<?> procTransposed(Value<?> val) throws IOException {
        matchToken(TokenType.TRANSPOSED);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        TransposedMatrixValue mat = new TransposedMatrixValue(val, la.getLine());
        return mat;
    }

    //<sum> ::= sum '(' <expr> ')'
    Value<?> procSum(Value<?> value) throws IOException {
        matchToken(TokenType.SUM);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);
        return new SumMatrixValue(val, val1, la.getLine());
    }

    //<mul> ::= mul '(' <value> ')'
    Value<?> procMul(Value<?> val) throws IOException {
        matchToken(TokenType.MUL);
        matchToken(TokenType.PAR_OPEN);
        Value v1 = procExpr();
        matchToken(TokenType.COMMA);
        Value v2 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        MulMatrixValue mat = new MulMatrixValue(v1, v2, la.getLine());
        return mat;
    }

    Value<?> procSize(Value<?> val) throws IOException {
        matchToken(TokenType.SIZE);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        SizeIntMatrixValue mat = new SizeIntMatrixValue(val, la.getLine());
        return mat;
    }

    Value<?> procRows(Value<?> val) throws IOException {
        matchToken(TokenType.ROWS);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        RowsIntMatrixValue mat = new RowsIntMatrixValue(val, la.getLine());
        return mat;
    }

    //<cols> ::= cols '(' ')'
    Value<?> procCols(Value<?> val) throws IOException {
        matchToken(TokenType.COLS);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);

        ColsIntMatrixValue mat = new ColsIntMatrixValue(val, la.getLine());
        return mat;
    }

    //<val> ::= value '(' <expr> ',' <expr> ')'
    Value<?> procVal(Value<?> value) throws IOException {
        matchToken(TokenType.VALUE);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);
        ValueIntMatrixValue mat = new ValueIntMatrixValue(val, val1, value, la.getLine());
        return mat;
    }

    //<gen> ::= '[' ']' '.' (<null> | <fill> | <rand> | <id> | <seq> | <iseq>)
    Value<?> procGen() throws IOException {
        matchToken(TokenType.BRA_OPEN);
        matchToken(TokenType.BRA_CLOSE);
        matchToken(TokenType.DOT);
        if (current.type == TokenType.NULL) {
            return procNull();
        } else if (current.type == TokenType.FILL) {
            return procFill();
        } else if (current.type == TokenType.RAND) {
            return procRand();
        } else if (current.type == TokenType.ID) {
            return procId();
        } else if (current.type == TokenType.SEQ) {
            return procSeq();
        } else if (current.type == TokenType.ISEQ) {
            return procIseq();
        } else {
            showError(current, la.getLine());
            return null;
        }
    }

    //<null> ::= null '(' <expr> ',' <expr> ')'
    Value<?> procNull() throws IOException {
        matchToken(TokenType.NULL);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        NullMatrixValue mat = new NullMatrixValue(val, val1, la.getLine());
        return mat;
    }

    //<fill> ::= fill '(' <expr> ',' <expr> ',' <expr> ')'
    Value<?> procFill() throws IOException {
        matchToken(TokenType.FILL);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.COMMA);
        Value val2 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        FillMatrixValue mat = new FillMatrixValue(val, val1, val2, la.getLine());
        return mat;
    }

    //<rand> ::= rand '(' <expr> ',' <expr> ')'
    Value<?> procRand() throws IOException {
        matchToken(TokenType.RAND);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        RandMatrixValue mat = new RandMatrixValue(val, val1, la.getLine());
        return mat;

    }

    //<id> ::= id '(' <expr> ',' <expr> ')'
    Value<?> procId() throws IOException {
        matchToken(TokenType.ID);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        IdMatrixValue mat = new IdMatrixValue(val, val1, la.getLine());
        return mat;
    }

    //<seq> ::= seq '(' <expr> ',' <expr> ')'
    Value<?> procSeq() throws IOException {
        matchToken(TokenType.SEQ);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        SeqMatrixValue mat = new SeqMatrixValue(val, val1, false, la.getLine());
        return mat;

    }

    //<iseq> ::= iseq '(' <expr> ',' <expr> ')'
    Value<?> procIseq() throws IOException {
        matchToken(TokenType.ISEQ);
        matchToken(TokenType.PAR_OPEN);
        Value val = procExpr();
        matchToken(TokenType.COMMA);
        Value val1 = procExpr();
        matchToken(TokenType.PAR_CLOSE);

        SeqMatrixValue mat = new SeqMatrixValue(val, val1, true, la.getLine());
        return mat;
    }

    Variable procVar() throws IOException {

        String varName = current.token;
        matchToken(TokenType.VAR);

        Variable v = variables.get(varName);

        if (v == null) {
            Variable var = new Variable(varName, null, la.getLine());
            variables.put(varName, var);
            return var;
        }
        return v;
    }

    ConstIntValue procNumber() throws IOException {

        String tmp = current.token;
        matchToken(TokenType.NUMBER);

        int n = Integer.parseInt(tmp);
        ConstIntValue v = new ConstIntValue(n, la.getLine());
        return v;
    }

    ConstStringValue procString() throws IOException {

        String tmp = current.token;
        matchToken(TokenType.STRING);
        String s = tmp;

        ConstStringValue v = new ConstStringValue(s, la.getLine());
        return v;
    }

    public static void showError(Lexema current, int line) {
        if (current.type == TokenType.UNEXPECTED_EOF) {
            System.out.println("["+ line +"] :" + "Fim de arquivo inexperado");
            System.exit(1);
        } else if (current.type == TokenType.INVALID_TOKEN) {
            System.out.println("["+ line +"] :" + "Token invalido " + "["+ current.token + "]");
            System.exit(1);
        } else if (current.type == TokenType.INVALID_OPERATION) {
            System.out.println("["+ line +"] :" + "Operando invalido " + "[" + current.token + "]");
            System.exit(1);
        } else if (current.type == TokenType.INVALID_TYPE) {
            System.out.println("["+ line +"] :" + "Tipo invalido " + "["+ current.token + "]");
            System.exit(1);
        } else if (current.type == TokenType.UNEXPECTED_TOKEN) {
            System.out.println("["+ line +"] :" + "Token inesperado : " + '"' + current.type + '"');
            System.exit(1);
        } else {
            System.out.println("Erro nao consta!");
            System.exit(1);
        }
    }

//    public static void error(int line, String erro) {
//        System.out.println("[" + line + "] Erro \n ->" + erro);
//        System.exit(1);
//    }

}
