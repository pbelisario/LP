////UNEXPECTED_EOF = -2,
////INVALID_TOKEN = -1,
////END_OF_FILE = 0,
////VAR = 1, ----------------- (none)
////ASSIGN = 2, -------------- =
////INPUT = 3,  -------------- input
////PAR_OPEN= 4,-------------- (
////PAR_CLOSE = 5, ----------- )
////STRING = 6(Entre aspas),-- (none)
////POT_COMMA = 7, ----------- ;
////BRA_OPEN = 8, ------------ [
////BRA_CLOSE = 9, ----------- ]
////DOT = 10, ---------------- .
////RAND = 11, --------------- rand
////COMMA = 12, -------------- ,
////SHOW = 13, --------------- show
////NUMBER = 14, ------------- (none)
////FOR = 15, ---------------- for
////SEQ = 16, ---------------- seq
////MINUS = 17, -------------- -
////PLUS = 18, --------------- +
////TIMES = 19, -------------- *
////DIV = 20, ---------------- /
////MOD = 21, ---------------- %
////VALUE = 22, -------------- value
////END = 23, ---------------- end
////ISEQ = 24, --------------- iseq
////EQUAL = 25, -------------- ==
////DIFF = 26, --------------- !=
////LOWER = 27, -------------- <
////LOWER-EQUAL = 28, -------- <=
////GREATER = 29, ------------ >
////GREATER-EQUAL = 30, ------ >=
////AND = 31, ---------------- &
////OR = 32, ----------------- |
////IF = 33, ----------------- if
////ELSE = 34, --------------- else
////WHILE = 35, -------------- while
////OPPOSED = 36, ------------ opposed
////TRANSPOSED = 37, --------- transposed
////SUM = 38, ---------------- sum
////MUL = 39, ---------------- mul
////NULL = 40, --------------- null
////FILL = 41, --------------- fill
////ID = 42, ----------------- id
////SIZE = 43,---------------- size
////ROWS 44, ----------------- rows
////COLS = 45. --------------- cols
//
//
//class  SymbolTable{
//	private Map<String,Symbol> st = new HashMap<>();
//
//	public Symbol getSymbol(String lex){
//		if( st.contain(lex)) return st.get(lex);
//		else return Symbols.INVALID-LEX;
//	}
//
//	public void addSymbol(String lex, Symbol s){
//		st.put (lex,s);
//	}
//
//	public bool haveLex(String lex){
//		if(st.contain(lex)) return true
//		return false
//	}
//
//	public SymbolTable(){
//                //addSymbol("",Symbol.VAR);
//		addSymbol("=",Symbol.ASSIGN);
//		addSymbol("input",Symbol.INPUT);
//		addSymbol("(",Symbol.PAR_OPEN);
//		addSymbol(")",Symbol.PAR_CLOSE);
//                //addSymbol("",Symbol.STRING);
//                addSymbol(";",Symbol.POT_COMMA);
//                addSymbol("[",Symbol.BRA_OPEN);
//                addSymbol("]",Symbol.BRA_CLOSE);
//                addSymbol(".",Symbol.DOT);
//                addSymbol("rand",Symbol.RAND);
//                addSymbol(",",Symbol.COMMA);
//                addSymbol("show",Symbol.SHOW);
//                //addSymbol("",Symbol.NUMBER);
//                addSymbol("for",Symbol.FOR);
//                addSymbol("seq",Symbol.SEQ);
//                addSymbol("-",Symbol.MINUS);
//                addSymbol("+",Symbol.PLUS);
//                addSymbol("*",Symbol.TIMES);
//                addSymbol("/",Symbol.DIV);
//                addSymbol("%",Symbol.MOD);
//                addSymbol("value",Symbol.VALUE);
//                addSymbol("end",Symbol.END);
//                addSymbol("iseq",Symbol.ISEQ);
//                addSymbol("==",Symbol.EQUAL);
//                addSymbol("!=",Symbol.DIFF);
//                addSymbol("<",Symbol.LOWER);
//                addSymbol("<=",Symbol.LOWER-EQUAL);
//		addSymbol(">",Symbol.GREATER);
//                addSymbol(">=",Symbol.GREATER-EQUAL);
//                addSymbol("&",Symbol.AND);
//                addSymbol("|",Symbol.OR);
//                addSymbol("if",Symbol.IF);
//                addSymbol("else",Symbol.ELSE);
//                addSymbol("while",Symbol.WHILE);
//		addSymbol("opposed",Symbol.OPPOSED);
//		addSymbol("transposed",Symbol.TRANSPOSED);
//		addSymbol("sum",Symbol.SUM);
//		addSymbol("mul",Symbol.MUL);
//		addSymbol("null",Symbol.NULL);
//		addSymbol("fill",Symbol.FILL);
//		addSymbol("id",Symbol.ID);
//		addSymbol("size",Symbol.SIZE);
//		addSymbol("rows",Symbol.ROWS);
//		addSymbol("cols",Symbol.COLS);
//
//
//	}
//}
//
//Lexima = (Token, Symbol);
//("dim", Symbol.VAR)
//("=", Symbol.ASSIGN)
//("input", Symbol.INPUT)
//("(", Symbol.PAR_OPEN)
//("Entre com ... ", Symbol.STRING)
//( ")", Symbol.PAR_CLOSE)
//(";", Symbol.DOT_COMMA)
//("matriz", Symbol.VAR)
//("=", Symbol.ASSIGN)
//("[", Symbol.BRA_OPEN)
//("]", Symbol.BRA_CLOSE)
//(".", Symbol.DOT)
//
//
//
//
//
//class LexicalAnalysis{
//
//	private InputStream input;
//	private SymbolTable st;
//
//	public LexicalAnalysis(String fileName){
//		input = ...(fileName);
//		st = new SymbolTable();
//	}
//
//	public Lexema nextToken(){
//
//	}
//}
//
