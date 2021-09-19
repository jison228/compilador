// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: src/main/jflex/lexer.jflex

package Analizadores;
import java_cup.runtime.*;

/* Directivas */

// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class AnalizadorLexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\36\u0200\1\u0300\267\u0200\10\u0400\u1020\u0200";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\4\1\5\22\0\1\1"+
    "\1\6\1\7\3\0\1\10\1\0\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\12\21\1\22\1\23"+
    "\1\24\1\25\1\26\2\0\1\27\2\30\1\31\1\32"+
    "\1\33\1\34\1\35\1\36\2\30\1\37\1\40\1\41"+
    "\1\42\1\43\1\30\1\44\1\45\1\46\2\30\1\47"+
    "\1\50\1\51\1\30\1\52\1\0\1\53\3\0\1\27"+
    "\2\30\1\31\1\32\1\33\1\34\1\35\1\36\2\30"+
    "\1\37\1\40\1\41\1\42\1\43\1\30\1\44\1\45"+
    "\1\46\2\30\1\47\1\50\1\51\1\30\1\0\1\54"+
    "\10\0\1\3\252\0\2\55\115\0\1\56\u01a8\0\2\3"+
    "\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1280];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\3\2\1\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\1\1\12\1\13\1\1\1\14"+
    "\1\15\1\1\1\16\15\17\1\20\1\21\3\1\1\22"+
    "\1\0\1\23\1\24\1\0\1\25\1\26\1\27\1\30"+
    "\1\31\2\32\1\17\1\0\4\17\1\33\5\17\1\34"+
    "\1\17\1\35\1\33\3\0\1\36\1\17\1\0\1\36"+
    "\1\17\1\0\1\17\1\37\1\40\6\17\3\0\1\17"+
    "\1\0\2\41\2\17\1\0\1\17\1\42\1\43\1\44"+
    "\1\17\1\0\1\17\2\0\1\17\1\0\1\45\1\17"+
    "\1\45\2\17\1\0\2\46\1\0\1\17\1\0\1\17"+
    "\1\0\1\17\2\47\1\0\2\50\1\17\1\0\2\51"+
    "\2\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[131];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\57\0\136\0\215\0\274\0\353\0\57"+
    "\0\57\0\u011a\0\57\0\57\0\57\0\u0149\0\57\0\u0178"+
    "\0\u01a7\0\57\0\u01d6\0\u0205\0\u0234\0\u0263\0\u0292\0\u02c1"+
    "\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac\0\u03db\0\u040a\0\u0439"+
    "\0\u0468\0\u0497\0\57\0\57\0\u04c6\0\u04f5\0\u0524\0\57"+
    "\0\274\0\274\0\57\0\u0553\0\u0149\0\57\0\57\0\57"+
    "\0\57\0\u0292\0\57\0\u0582\0\u05b1\0\u05e0\0\u060f\0\u063e"+
    "\0\u066d\0\u0292\0\u069c\0\u06cb\0\u06fa\0\u0729\0\u0758\0\u0292"+
    "\0\u0787\0\57\0\57\0\u07b6\0\u07e5\0\u0814\0\u0292\0\u0843"+
    "\0\u0872\0\57\0\u08a1\0\u08d0\0\u08ff\0\u0292\0\u0292\0\u092e"+
    "\0\u095d\0\u098c\0\u09bb\0\u09ea\0\u0a19\0\u0a48\0\u0a77\0\u0aa6"+
    "\0\u0ad5\0\u0b04\0\u0292\0\57\0\u0b33\0\u0b62\0\u0b91\0\u0bc0"+
    "\0\u0292\0\u0292\0\u0292\0\u0bef\0\u0c1e\0\u0c4d\0\u0c7c\0\u0cab"+
    "\0\u0cda\0\u0d09\0\u0292\0\u0d38\0\57\0\u0d67\0\u0d96\0\u0dc5"+
    "\0\u0292\0\57\0\u0df4\0\u0e23\0\u0e52\0\u0e81\0\u0eb0\0\u0edf"+
    "\0\u0292\0\57\0\u0f0e\0\u0292\0\57\0\u0f3d\0\u0f6c\0\u0292"+
    "\0\57\0\u0292\0\57";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[131];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\27\1\34\1\35\1\27"+
    "\1\36\2\27\1\37\1\40\1\41\1\42\2\27\1\43"+
    "\1\44\1\45\1\46\1\47\61\0\1\3\101\0\1\50"+
    "\31\0\2\51\4\0\1\51\1\52\47\51\10\0\1\53"+
    "\66\0\1\54\57\0\1\55\54\0\1\55\1\0\1\20"+
    "\62\0\1\56\56\0\1\57\56\0\1\60\56\0\1\61"+
    "\52\0\1\27\5\0\16\27\1\62\4\27\4\0\1\63"+
    "\21\0\1\27\5\0\23\27\26\0\1\27\5\0\7\27"+
    "\1\64\13\27\3\0\1\65\22\0\1\27\5\0\10\27"+
    "\1\66\1\27\1\67\10\27\26\0\1\27\5\0\13\27"+
    "\1\70\7\27\26\0\1\27\5\0\3\27\1\71\17\27"+
    "\26\0\1\27\5\0\4\27\1\72\5\27\1\73\10\27"+
    "\26\0\1\27\5\0\13\27\1\74\7\27\26\0\1\27"+
    "\5\0\3\27\1\75\17\27\26\0\1\27\5\0\3\27"+
    "\1\76\17\27\26\0\1\27\5\0\17\27\1\77\3\27"+
    "\26\0\1\27\5\0\13\27\1\100\7\27\26\0\1\27"+
    "\5\0\6\27\1\101\14\27\61\0\1\102\35\0\1\103"+
    "\5\0\1\104\63\0\1\105\11\0\2\54\1\0\2\54"+
    "\12\0\1\106\1\54\5\0\23\54\26\0\1\27\5\0"+
    "\11\27\1\107\4\27\1\110\4\27\4\0\1\111\40\0"+
    "\1\112\4\0\1\111\10\0\1\111\21\0\1\27\5\0"+
    "\16\27\1\113\4\27\4\0\1\114\21\0\1\27\5\0"+
    "\2\27\1\115\20\27\26\0\1\27\5\0\15\27\1\116"+
    "\5\27\26\0\1\27\5\0\17\27\1\117\3\27\26\0"+
    "\1\27\5\0\17\27\1\120\3\27\26\0\1\27\5\0"+
    "\12\27\1\121\10\27\26\0\1\27\5\0\21\27\1\122"+
    "\1\27\26\0\1\27\5\0\1\123\22\27\26\0\1\27"+
    "\5\0\15\27\1\124\5\27\26\0\1\27\5\0\7\27"+
    "\1\125\13\27\3\0\1\126\47\0\1\127\54\0\1\130"+
    "\25\0\1\3\64\0\1\27\5\0\14\27\1\131\6\27"+
    "\50\0\1\132\34\0\1\27\5\0\3\27\1\133\17\27"+
    "\37\0\1\134\45\0\1\27\5\0\7\27\1\135\10\27"+
    "\1\136\2\27\3\0\1\137\22\0\1\27\5\0\3\27"+
    "\1\140\17\27\26\0\1\27\5\0\5\27\1\141\15\27"+
    "\26\0\1\27\5\0\17\27\1\142\3\27\26\0\1\27"+
    "\5\0\10\27\1\143\12\27\26\0\1\27\5\0\7\27"+
    "\1\144\13\27\3\0\1\145\22\0\1\27\5\0\10\27"+
    "\1\146\12\27\44\0\1\147\51\0\1\150\62\0\1\145"+
    "\16\0\1\145\22\0\1\27\5\0\10\27\1\151\12\27"+
    "\44\0\1\152\40\0\1\27\5\0\4\27\1\153\16\27"+
    "\26\0\1\27\5\0\6\27\1\154\14\27\40\0\1\155"+
    "\44\0\1\27\5\0\5\27\1\156\15\27\26\0\1\27"+
    "\5\0\12\27\1\157\10\27\46\0\1\160\36\0\1\27"+
    "\5\0\3\27\1\161\17\27\37\0\1\162\60\0\1\163"+
    "\43\0\1\27\5\0\1\164\22\27\34\0\1\165\50\0"+
    "\1\27\5\0\7\27\1\166\13\27\3\0\1\167\22\0"+
    "\1\27\5\0\3\27\1\170\17\27\26\0\1\27\5\0"+
    "\5\27\1\171\15\27\41\0\1\172\54\0\1\173\45\0"+
    "\1\27\5\0\22\27\1\174\56\0\1\175\26\0\1\27"+
    "\5\0\10\27\1\176\12\27\44\0\1\177\40\0\1\27"+
    "\5\0\15\27\1\200\5\27\51\0\1\201\33\0\1\27"+
    "\5\0\3\27\1\202\17\27\37\0\1\203\24\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3995];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\4\1\2\11\1\1\3\11\1\1\1\11"+
    "\2\1\1\11\20\1\2\11\3\1\1\11\1\0\1\1"+
    "\1\11\1\0\1\1\4\11\1\1\1\11\1\1\1\0"+
    "\14\1\2\11\3\0\2\1\1\0\1\11\1\1\1\0"+
    "\11\1\3\0\1\1\1\0\1\1\1\11\2\1\1\0"+
    "\5\1\1\0\1\1\2\0\1\1\1\0\2\1\1\11"+
    "\2\1\1\0\1\1\1\11\1\0\1\1\1\0\1\1"+
    "\1\0\2\1\1\11\1\0\1\1\1\11\1\1\1\0"+
    "\1\1\1\11\1\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[131];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    int RANGO_ENTERO = (int) (Math.pow(2, 16)-1);
    float RANGO_FLOAT = (float) (Math.pow(2, 32)-1);
    int RANGO_STRING = 30;
    int RANGO_IDENTIFICADOR = 256;
    private Symbol symbol(int type) {
          System.out.println("[LEX] TOKEN < " + Simbolos.terminalNames[type] + " > : " + yytext());
          return new Symbol(type, yyline, yycolumn, yytext());
    }
    private Symbol symbol(int type, Object value) {
          return new Symbol(type, yyline, yycolumn, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AnalizadorLexico(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(Simbolos.EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { System.out.println("Error Léxico: --> " + yytext() + " <-- Linea " + (yyline+1) + " Columna " + yycolumn);
            throw new Error("Error léxico");
            }
            // fall through
          case 43: break;
          case 2:
            { /* do nothing */
            }
            // fall through
          case 44: break;
          case 3:
            { return symbol(Simbolos.OP_NOT);
            }
            // fall through
          case 45: break;
          case 4:
            { return symbol(Simbolos.PAREN_OPEN);
            }
            // fall through
          case 46: break;
          case 5:
            { return symbol(Simbolos.PAREN_CLOSE);
            }
            // fall through
          case 47: break;
          case 6:
            { return symbol(Simbolos.OP_MULTI);
            }
            // fall through
          case 48: break;
          case 7:
            { return symbol(Simbolos.OP_PLUS);
            }
            // fall through
          case 49: break;
          case 8:
            { return symbol(Simbolos.COMA);
            }
            // fall through
          case 50: break;
          case 9:
            { return symbol(Simbolos.OP_MINUS);
            }
            // fall through
          case 51: break;
          case 10:
            { return symbol(Simbolos.OP_DIVISION);
            }
            // fall through
          case 52: break;
          case 11:
            { Integer constInt = Integer.parseInt(yytext());

                                    if(Math.abs(constInt) <= RANGO_ENTERO ){
                                          return symbol(Simbolos.CONSTANTE_ENTERA);
                                    }                                          
                                    else
                                    {
                                          System.err.println("La constante [" + yytext() + "] esta fuera del limite de los enteros. (Se obtuvo " + constInt + ", maximo " + RANGO_ENTERO + ")");
                                          System.in.read();
                                          throw new Error("La constante [" + yytext() + "] esta fuera del limite de los enteros. (Se obtuvo " + constInt + ", maximo " + RANGO_ENTERO + ")"); 
                                    }
            }
            // fall through
          case 53: break;
          case 12:
            { return symbol(Simbolos.PUNTO_COMA);
            }
            // fall through
          case 54: break;
          case 13:
            { return symbol(Simbolos.OP_LT);
            }
            // fall through
          case 55: break;
          case 14:
            { return symbol(Simbolos.OP_GT);
            }
            // fall through
          case 56: break;
          case 15:
            { String id = new String(yytext());
                                    int length = id.length();

                                    if(length <= RANGO_IDENTIFICADOR ){
                                          return symbol(Simbolos.IDENTIFICADOR); 
                                    }                                          
                                    else
                                    {
                                          System.err.println("El identificador [" + yytext() + "] esta fuera del limite de los los identificadores. (Se obtuvo " + length + ", maximo " + RANGO_IDENTIFICADOR + ")");                                    
                                          System.in.read();
                                          throw new Error("El identificador [" + yytext() + "] esta fuera del limite de los los identificadores. (Se obtuvo " + length + ", maximo " + RANGO_IDENTIFICADOR + ")");                                    
                                    }
            }
            // fall through
          case 57: break;
          case 16:
            { return symbol(Simbolos.CORCHETE_OPEN);
            }
            // fall through
          case 58: break;
          case 17:
            { return symbol(Simbolos.CORCHETE_CLOSE);
            }
            // fall through
          case 59: break;
          case 18:
            { return symbol(Simbolos.OP_NE);
            }
            // fall through
          case 60: break;
          case 19:
            { String constString = new String(yytext());
                                    // Restamos 2 por las comillas
                                    if (constString.length()-2 <= RANGO_STRING)
                                          return symbol(Simbolos.CONSTANTE_STRING); 
                                    else
                                    {
                                          System.err.println("La constante [" + yytext() + "] excede el largo permitido para un string. (Se obtuvo " + constString.length() + ", maximo " + RANGO_STRING + ")");
                                          System.in.read();
                                          throw new Error("La constante [" + yytext() + "] excede el largo permitido para un string. (Se obtuvo " + constString.length() + ", maximo " + RANGO_STRING + ")");
                                    }
            }
            // fall through
          case 61: break;
          case 20:
            { return symbol(Simbolos.OP_AND);
            }
            // fall through
          case 62: break;
          case 21:
            { Double constFloat = Double.parseDouble(yytext());
                                    if (Math.abs(constFloat) <= RANGO_FLOAT)
                                          return symbol(Simbolos.CONSTANTE_FLOAT);
                                    else
                                    {
                                          System.err.println("La constante [" + yytext() + "] esta fuera del limite de los flotantes. (Se obtuvo " + constFloat + ", maximo " + RANGO_FLOAT + ")");
                                          System.in.read();
                                          throw new Error("La constante [" + yytext() + "] esta fuera del limite de los flotantes. (Se obtuvo " + constFloat + ", maximo " + RANGO_FLOAT + ")");
                                    }
            }
            // fall through
          case 63: break;
          case 22:
            { return symbol(Simbolos.OP_ASIG);
            }
            // fall through
          case 64: break;
          case 23:
            { return symbol(Simbolos.OP_LTE);
            }
            // fall through
          case 65: break;
          case 24:
            { return symbol(Simbolos.OP_EQ);
            }
            // fall through
          case 66: break;
          case 25:
            { return symbol(Simbolos.OP_GTE);
            }
            // fall through
          case 67: break;
          case 26:
            { return symbol(Simbolos.OP_TIPO);
            }
            // fall through
          case 68: break;
          case 27:
            { return symbol(Simbolos.IF);
            }
            // fall through
          case 69: break;
          case 28:
            { return symbol(Simbolos.TO);
            }
            // fall through
          case 70: break;
          case 29:
            { return symbol(Simbolos.OP_OR);
            }
            // fall through
          case 71: break;
          case 30:
            { return symbol(Simbolos.DIM);
            }
            // fall through
          case 72: break;
          case 31:
            { return symbol(Simbolos.FOR);
            }
            // fall through
          case 73: break;
          case 32:
            { return symbol(Simbolos.GET);
            }
            // fall through
          case 74: break;
          case 33:
            { return symbol(Simbolos.ELSE);
            }
            // fall through
          case 75: break;
          case 34:
            { return symbol(Simbolos.LONG);
            }
            // fall through
          case 76: break;
          case 35:
            { return symbol(Simbolos.NEXT);
            }
            // fall through
          case 77: break;
          case 36:
            { return symbol(Simbolos.FLOAT_TYPE);
            }
            // fall through
          case 78: break;
          case 37:
            { return symbol(Simbolos.ENDIF);
            }
            // fall through
          case 79: break;
          case 38:
            { return symbol(Simbolos.WHILE);
            }
            // fall through
          case 80: break;
          case 39:
            { return symbol(Simbolos.STRING_TYPE);
            }
            // fall through
          case 81: break;
          case 40:
            { return symbol(Simbolos.DISPLAY);
            }
            // fall through
          case 82: break;
          case 41:
            { return symbol(Simbolos.INTEGER_TYPE);
            }
            // fall through
          case 83: break;
          case 42:
            { return symbol(Simbolos.ENDWHILE);
            }
            // fall through
          case 84: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
