// Generated by JFlex 1.9.2 http://jflex.de/  (tweaked for IntelliJ platform)
// source: _TerraformTemplateLexer.flex

package org.intellij.terraform.template.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.intellij.terraform.hil.HILElementTypes.*;
import static org.intellij.terraform.hil.psi.TerraformTemplateTokenTypes.DATA_LANGUAGE_TOKEN_UNPARSED;

public class _TerraformTemplateLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int TEMPLATE_SEGMENT_SELECTOR = 2;
  public static final int DATA_LANGUAGE_SEGMENT = 4;
  public static final int TEMPLATE_LANGUAGE_SEGMENT = 6;
  public static final int IL_EXPRESSION = 8;
  public static final int QUOTED_STRING = 10;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\25\u0100\1\u0200\11\u0100\1\u0300\17\u0100\1\u0400\u10cf\u0100";

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
    "\11\0\5\1\22\0\1\1\1\2\1\3\1\0\1\4"+
    "\1\5\1\6\1\0\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\11\20\1\21\1\0\1\22"+
    "\1\23\1\24\1\25\1\0\4\26\1\27\14\26\1\30"+
    "\10\26\1\31\1\0\1\32\1\0\1\26\1\0\1\33"+
    "\2\26\1\34\1\35\1\36\2\26\1\37\2\26\1\40"+
    "\1\26\1\41\1\42\2\26\1\43\1\44\1\45\1\46"+
    "\2\26\1\47\2\26\1\50\1\51\1\52\1\53\6\0"+
    "\1\1\32\0\1\1\u01df\0\1\1\177\0\13\1\35\0"+
    "\2\1\5\0\1\1\57\0\1\1\240\0\1\1\377\0";

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
    "\5\0\1\1\1\2\2\1\2\3\1\4\1\5\1\6"+
    "\1\4\1\7\1\4\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\2\20\1\21\1\22\1\23\1\24"+
    "\1\25\2\26\1\27\1\30\5\26\1\4\1\31\1\32"+
    "\1\33\1\34\1\35\1\36\1\0\1\37\1\40\2\0"+
    "\3\26\1\41\1\42\1\43\4\26\1\44\1\45\2\26"+
    "\1\46\1\33\1\34\1\35\1\47\1\20\1\0\1\20"+
    "\3\26\1\50\2\26\1\0\1\20\1\51\3\26\1\52"+
    "\1\53\1\26\1\54\1\55\1\56";

  private static int [] zzUnpackAction() {
    int [] result = new int[93];
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
    "\0\0\0\54\0\130\0\204\0\260\0\204\0\204\0\334"+
    "\0\u0108\0\204\0\u0134\0\204\0\u0160\0\u018c\0\u01b8\0\204"+
    "\0\u01e4\0\204\0\204\0\204\0\204\0\204\0\204\0\u0210"+
    "\0\204\0\u023c\0\u0268\0\204\0\u0294\0\u02c0\0\u02ec\0\204"+
    "\0\u0318\0\u0344\0\204\0\204\0\u0370\0\u039c\0\u03c8\0\u03f4"+
    "\0\u0420\0\u044c\0\204\0\u0478\0\u04a4\0\u04d0\0\u04fc\0\204"+
    "\0\u0528\0\204\0\204\0\u0554\0\u0580\0\u05ac\0\u05d8\0\u0604"+
    "\0\204\0\204\0\204\0\u0630\0\u065c\0\u0688\0\u06b4\0\u0318"+
    "\0\u0318\0\u06e0\0\u070c\0\204\0\204\0\204\0\204\0\204"+
    "\0\u0738\0\u0764\0\u0790\0\u07bc\0\u07e8\0\u0814\0\u0318\0\u0840"+
    "\0\u086c\0\u0898\0\u0764\0\u0318\0\u08c4\0\u08f0\0\u091c\0\u0318"+
    "\0\u0318\0\u0948\0\u0318\0\u0318\0\u0318";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[93];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\3\6\1\7\1\10\1\11\46\6\3\12\1\7\2\13"+
    "\46\12\1\14\1\15\1\16\1\17\1\14\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\2\41"+
    "\1\42\1\43\1\44\2\41\1\45\1\46\1\47\1\41"+
    "\1\50\3\41\1\51\2\41\1\14\1\52\1\53\1\54"+
    "\54\0\3\12\1\6\50\12\50\0\1\55\53\0\1\56"+
    "\53\0\1\57\4\0\1\15\26\0\1\15\46\0\1\60"+
    "\30\0\3\61\1\62\50\61\6\0\1\63\62\0\1\64"+
    "\53\0\1\65\1\0\2\33\5\0\1\66\1\67\1\66"+
    "\2\0\2\66\1\67\11\66\1\70\21\0\1\65\1\0"+
    "\2\33\5\0\1\66\1\67\1\66\2\0\2\66\1\67"+
    "\12\66\27\0\1\71\53\0\1\72\53\0\1\73\41\0"+
    "\1\41\2\0\1\41\2\0\2\41\5\0\3\41\2\0"+
    "\15\41\5\0\1\15\7\0\1\41\2\0\1\41\2\0"+
    "\2\41\5\0\2\41\1\42\2\0\15\41\15\0\1\41"+
    "\2\0\1\41\2\0\2\41\5\0\3\41\2\0\5\41"+
    "\1\74\1\75\6\41\15\0\1\41\2\0\1\41\2\0"+
    "\2\41\5\0\3\41\2\0\1\76\6\41\1\77\5\41"+
    "\15\0\1\41\2\0\1\41\2\0\2\41\5\0\3\41"+
    "\2\0\3\41\1\100\2\41\1\101\6\41\15\0\1\41"+
    "\2\0\1\41\2\0\2\41\5\0\3\41\2\0\13\41"+
    "\1\102\1\41\15\0\1\41\2\0\1\41\2\0\2\41"+
    "\5\0\3\41\2\0\10\41\1\103\4\41\55\0\1\104"+
    "\54\0\1\53\54\0\1\105\53\0\1\106\53\0\1\107"+
    "\3\0\1\62\65\0\1\110\55\0\2\111\52\0\2\66"+
    "\5\0\3\66\2\0\15\66\16\0\1\112\1\0\1\112"+
    "\2\0\2\113\5\0\3\66\2\0\15\66\23\0\2\33"+
    "\5\0\3\66\2\0\15\66\15\0\1\41\2\0\1\41"+
    "\2\0\2\41\5\0\3\41\2\0\11\41\1\114\3\41"+
    "\15\0\1\41\2\0\1\41\2\0\2\41\5\0\3\41"+
    "\2\0\1\41\1\115\13\41\15\0\1\41\2\0\1\41"+
    "\2\0\2\41\5\0\3\41\2\0\5\41\1\116\7\41"+
    "\15\0\1\41\2\0\1\41\2\0\2\41\5\0\3\41"+
    "\2\0\10\41\1\117\4\41\15\0\1\41\2\0\1\41"+
    "\2\0\2\41\5\0\3\41\2\0\5\41\1\120\7\41"+
    "\15\0\1\41\2\0\1\41\2\0\2\41\5\0\3\41"+
    "\2\0\13\41\1\121\1\41\23\0\2\111\6\0\1\122"+
    "\5\0\1\122\35\0\2\123\52\0\2\113\5\0\3\66"+
    "\2\0\15\66\15\0\1\41\2\0\1\41\2\0\2\41"+
    "\5\0\3\41\2\0\2\41\1\124\12\41\15\0\1\41"+
    "\2\0\1\41\2\0\2\41\5\0\3\41\2\0\3\41"+
    "\1\125\1\126\10\41\15\0\1\41\2\0\1\41\2\0"+
    "\2\41\5\0\3\41\2\0\11\41\1\127\3\41\15\0"+
    "\1\41\2\0\1\41\2\0\2\41\5\0\3\41\2\0"+
    "\5\41\1\130\7\41\15\0\1\41\2\0\1\41\2\0"+
    "\2\41\5\0\3\41\2\0\2\41\1\131\12\41\16\0"+
    "\1\112\1\0\1\112\2\0\2\123\44\0\1\41\2\0"+
    "\1\41\2\0\2\41\5\0\3\41\2\0\7\41\1\132"+
    "\5\41\15\0\1\41\2\0\1\41\2\0\2\41\5\0"+
    "\3\41\2\0\3\41\1\133\11\41\15\0\1\41\2\0"+
    "\1\41\2\0\2\41\5\0\3\41\2\0\2\41\1\134"+
    "\12\41\15\0\1\41\2\0\1\41\2\0\2\41\5\0"+
    "\3\41\2\0\10\41\1\135\4\41\4\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[2420];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
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


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\10\1\0\2\11\2\1\1\11\1\1\1\11"+
    "\3\1\1\11\1\1\6\11\1\1\1\11\2\1\1\11"+
    "\3\1\1\11\2\1\2\11\6\1\1\11\4\1\1\11"+
    "\1\0\2\11\2\0\3\1\3\11\10\1\5\11\1\1"+
    "\1\0\7\1\1\0\13\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[93];
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

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
  public _TerraformTemplateLexer() {
    this(null);
  }

  private int braceBalance = 0;
  private boolean isLastBrace() { return braceBalance <= 0; }
  private void incrementBraceBalance() { braceBalance++; }
  private void decrementBraceBalance() { braceBalance--; assert braceBalance >= 0 : "Invalid braces balance, consider lexer adjustments"; }
  private void resetBraceBalance() { braceBalance = 0; }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _TerraformTemplateLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
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
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  resetBraceBalance();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

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
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
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
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
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
            switch (zzLexicalState) {
            case DATA_LANGUAGE_SEGMENT: {
              resetBraceBalance();
                                      yybegin(YYINITIAL);
                                      return DATA_LANGUAGE_TOKEN_UNPARSED;
            }  // fall though
            case 94: break;
            case QUOTED_STRING: {
              resetBraceBalance();
                                      yybegin(YYINITIAL);
                                      return DATA_LANGUAGE_TOKEN_UNPARSED;
            }  // fall though
            case 95: break;
            default:
        return null;
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { yybegin(DATA_LANGUAGE_SEGMENT);
            }
          // fall through
          case 47: break;
          case 2:
            { yybegin(QUOTED_STRING);
            }
          // fall through
          case 48: break;
          case 3:
            { 
            }
          // fall through
          case 49: break;
          case 4:
            { return BAD_CHARACTER;
            }
          // fall through
          case 50: break;
          case 5:
            { return WHITE_SPACE;
            }
          // fall through
          case 51: break;
          case 6:
            { return OP_NOT;
            }
          // fall through
          case 52: break;
          case 7:
            { return OP_MOD;
            }
          // fall through
          case 53: break;
          case 8:
            { return L_PAREN;
            }
          // fall through
          case 54: break;
          case 9:
            { return R_PAREN;
            }
          // fall through
          case 55: break;
          case 10:
            { return OP_MUL;
            }
          // fall through
          case 56: break;
          case 11:
            { return OP_PLUS;
            }
          // fall through
          case 57: break;
          case 12:
            { return COMMA;
            }
          // fall through
          case 58: break;
          case 13:
            { return OP_MINUS;
            }
          // fall through
          case 59: break;
          case 14:
            { return OP_DOT;
            }
          // fall through
          case 60: break;
          case 15:
            { return OP_DIV;
            }
          // fall through
          case 61: break;
          case 16:
            { return NUMBER;
            }
          // fall through
          case 62: break;
          case 17:
            { return OP_COLON;
            }
          // fall through
          case 63: break;
          case 18:
            { return OP_LESS;
            }
          // fall through
          case 64: break;
          case 19:
            { return EQUALS;
            }
          // fall through
          case 65: break;
          case 20:
            { return OP_GREATER;
            }
          // fall through
          case 66: break;
          case 21:
            { return OP_QUEST;
            }
          // fall through
          case 67: break;
          case 22:
            { return ID;
            }
          // fall through
          case 68: break;
          case 23:
            { return L_BRACKET;
            }
          // fall through
          case 69: break;
          case 24:
            { return R_BRACKET;
            }
          // fall through
          case 70: break;
          case 25:
            { decrementBraceBalance();
                                      if (isLastBrace()) {
                                        resetBraceBalance();
                                        yybegin(YYINITIAL);
                                      }
                                      return R_CURLY;
            }
          // fall through
          case 71: break;
          case 26:
            { return TILDA;
            }
          // fall through
          case 72: break;
          case 27:
            { incrementBraceBalance();
                                      yybegin(TEMPLATE_LANGUAGE_SEGMENT);
                                      return INTERPOLATION_START;
            }
          // fall through
          case 73: break;
          case 28:
            { incrementBraceBalance();
                                      yybegin(TEMPLATE_LANGUAGE_SEGMENT);
                                      return TEMPLATE_START;
            }
          // fall through
          case 74: break;
          case 29:
            { yypushback(yylength());
                                      resetBraceBalance();
                                      yybegin(TEMPLATE_SEGMENT_SELECTOR);
                                      return DATA_LANGUAGE_TOKEN_UNPARSED;
            }
          // fall through
          case 75: break;
          case 30:
            { return OP_NOT_EQUAL;
            }
          // fall through
          case 76: break;
          case 31:
            { return DOUBLE_QUOTED_STRING;
            }
          // fall through
          case 77: break;
          case 32:
            { return OP_AND_AND;
            }
          // fall through
          case 78: break;
          case 33:
            { return OP_LESS_OR_EQUAL;
            }
          // fall through
          case 79: break;
          case 34:
            { return OP_EQUAL;
            }
          // fall through
          case 80: break;
          case 35:
            { return OP_GREATER_OR_EQUAL;
            }
          // fall through
          case 81: break;
          case 36:
            { return IF_KEYWORD;
            }
          // fall through
          case 82: break;
          case 37:
            { return IN_KEYWORD;
            }
          // fall through
          case 83: break;
          case 38:
            { return OP_OR_OR;
            }
          // fall through
          case 84: break;
          case 39:
            { return OP_ELLIPSIS;
            }
          // fall through
          case 85: break;
          case 40:
            { return FOR_KEYWORD;
            }
          // fall through
          case 86: break;
          case 41:
            { return ELSE_KEYWORD;
            }
          // fall through
          case 87: break;
          case 42:
            { return NULL;
            }
          // fall through
          case 88: break;
          case 43:
            { return TRUE;
            }
          // fall through
          case 89: break;
          case 44:
            { return ENDIF_KEYWORD;
            }
          // fall through
          case 90: break;
          case 45:
            { return FALSE;
            }
          // fall through
          case 91: break;
          case 46:
            { return ENDFOR_KEYWORD;
            }
          // fall through
          case 92: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
