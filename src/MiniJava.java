/* Generated By:JavaCC: Do not edit this line. MiniJava.java */
import exceptions.ConstantModificationException;
import exceptions.SymbolNotDeclaredException;
import exceptions.SymbolDoesNotExist;
import exceptions.SymbolAlreadyDefinedException;

public class MiniJava implements MiniJavaConstants {
    static SymbolTable table = new SymbolTable();
    static CodeErzeugung codeErzeugung = new  CodeErzeugung();

  static final public void Empty() throws ParseException {

  }

  static final public void programm() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    constDecl();
    varDecl();
    statement();
                                      codeErzeugung.add("return");
    jj_consume_token(0);
                                                                            codeErzeugung.print(); codeErzeugung.translateToOpcodes(); codeErzeugung.printByteCode(); System.out.println(codeErzeugung.getByteCount());
  }

  static final public void constDecl() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FINAL:
      jj_consume_token(FINAL);
      jj_consume_token(INT);
      constZuw();
      constList();
      jj_consume_token(21);
      break;
    default:
      jj_la1[0] = jj_gen;
      Empty();
    }
  }

  static final public void constZuw() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    jj_consume_token(IDENT);
           String ide = token.image;
    jj_consume_token(22);
    jj_consume_token(NUMBER);
            table.addConst(ide, Integer.parseInt( token.image));
  }

  static final public void constList() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 23:
      jj_consume_token(23);
      constZuw();
      constList();
      break;
    default:
      jj_la1[1] = jj_gen;
      Empty();
    }
  }

  static final public void varDecl() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
      jj_consume_token(IDENT);
                  String ide = token.image;
      varZuw(ide);
      varList();
      jj_consume_token(21);
      break;
    default:
      jj_la1[2] = jj_gen;
      Empty();
    }
  }

  static final public void varZuw(String ide) throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 22:
      jj_consume_token(22);
      jj_consume_token(NUMBER);
                 table.addVar(ide, Integer.parseInt(token.image));  codeErzeugung.add("bipush "+table.getValue(ide)); codeErzeugung.add("istore "+table.getAddress(ide));
      break;
    default:
      jj_la1[3] = jj_gen;
      Empty();
    }
  }

  static final public void varList() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 23:
      jj_consume_token(23);
      jj_consume_token(IDENT);
                String ide = token.image;
      varZuw(ide);
      varList();
      break;
    default:
      jj_la1[4] = jj_gen;
      Empty();
    }
  }

  static final public void expression() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    term();
    summe();
  }

  static final public void term() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    faktor();
    produkt();
  }

  static final public void summe() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
      term();
                codeErzeugung.add("iadd");
      summe();
      break;
    case MINUS:
      jj_consume_token(MINUS);
      term();
                  codeErzeugung.add("isub");
      summe();
      break;
    default:
      jj_la1[5] = jj_gen;
      Empty();
    }
  }

  static final public void produkt() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MAL:
      jj_consume_token(MAL);
      faktor();
                  codeErzeugung.add("imul");
      produkt();
      break;
    case DIV:
      jj_consume_token(DIV);
      faktor();
                  codeErzeugung.add("idiv");
      produkt();
      break;
    default:
      jj_la1[6] = jj_gen;
      Empty();
    }
  }

  static final public void faktor() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      jj_consume_token(NUMBER);
               codeErzeugung.add("bipush " + token.image);
      break;
    case IDENT:
      jj_consume_token(IDENT);
      if (!table.contains(token.image)) {
         {if (true) throw new SymbolNotDeclaredException();}
     }
     if (table.getTyp(token.image).equals("var")) {
         codeErzeugung.add("iload " + table.getAddress(token.image));
     } else {
         codeErzeugung.add("bipush " + table.getValue(token.image));
     }
      break;
    case KLAMMERAUF:
      jj_consume_token(KLAMMERAUF);
      expression();
      jj_consume_token(KLAMMERZU);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void statement() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENT:
      jj_consume_token(IDENT);
        String ide = token.image;
        if (table.getTyp(ide).equals("const")) {
            {if (true) throw new ConstantModificationException();}
        }
      jj_consume_token(22);
      expression();
      jj_consume_token(21);
        codeErzeugung.add("istore " + table.getAddress(ide));
      break;
    case PRINT:
      jj_consume_token(PRINT);
      jj_consume_token(KLAMMERAUF);
      expression();
      jj_consume_token(KLAMMERZU);
      jj_consume_token(21);
      break;
    case 24:
      jj_consume_token(24);
      stmtLIST();
      jj_consume_token(25);
      break;
    case IF:
      jj_consume_token(IF);
      condition();
      statement();
      optElse();
      break;
    case WHILE:
      jj_consume_token(WHILE);
              int rücksprung_if = codeErzeugung.getByteCount(); System.out.println("Vor while" +rücksprung_if);
      condition();
      int bevor_Statement =codeErzeugung.getByteCount();
      System.out.println( "Bevor Statement:" + bevor_Statement);
      statement();
       int nach_Statement = codeErzeugung.getByteCount(); // Adresse nach Statement
          int statementByteCode = nach_Statement - bevor_Statement; // Statement-Länge berechnen
          System.out.println("Das Statement ist " + statementByteCode + " Byte lang.");

          // Zieladresse für Bedingung setzen (if-Anweisung)
          codeErzeugung.insertAddress(Integer.toString(statementByteCode + 4));

          // Berechnung für goto-Sprung
          int afterStatement = codeErzeugung.getByteCount(); // Adresse nach Statement
          int gotoStrung = rücksprung_if - afterStatement - 3; // Rücksprung berechnen (-3 für Befehlslänge)
          System.out.println("Goto-Sprung: " + gotoStrung);

          // Generiere den Rücksprung
          codeErzeugung.add("goto " + gotoStrung);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void stmtLIST() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHILE:
    case IF:
    case PRINT:
    case IDENT:
    case 24:
      statement();
      stmtLIST();
      break;
    default:
      jj_la1[9] = jj_gen;
      Empty();
    }
  }

  static final public void optElse() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      statement();
      break;
    default:
      jj_la1[10] = jj_gen;
      Empty();
    }
  }

  static final public void condition() throws ParseException, ConstantModificationException, SymbolAlreadyDefinedException, SymbolNotDeclaredException, SymbolDoesNotExist {
    expression();
    jj_consume_token(ComOp);
                      String compOp =token.image;
    expression();
 switch (compOp) {
         case "<":
             codeErzeugung.add("if_icmpge -1"); // Umgekehrter Vergleich für "<"
             break;
         case "<=":
             codeErzeugung.add("if_icmpgt -1"); // Umgekehrter Vergleich für "<="
             break;
         case ">":
             codeErzeugung.add("if_icmple -1"); // Umgekehrter Vergleich für ">"
             break;
         case ">=":
             codeErzeugung.add("if_icmplt -1 "); // Umgekehrter Vergleich für ">="
             break;
         case "==":
             codeErzeugung.add("if_icmpne -1" ); // Umgekehrter Vergleich für "=="
             break;
         case "!=":
             codeErzeugung.add("if_icmpeq -1"); // Umgekehrter Vergleich für "!="
             break;
         default:
     }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MiniJavaTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20,0x800000,0x40,0x400000,0x800000,0x18000,0x60000,0x102800,0x1100580,0x1100580,0x200,};
   }

  /** Constructor with InputStream. */
  public MiniJava(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MiniJava(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MiniJavaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MiniJava(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MiniJavaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MiniJava(MiniJavaTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MiniJavaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[26];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 11; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 26; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
