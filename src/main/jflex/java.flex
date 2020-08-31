package edu.odu.cs.cs350.LexicalAnalysis;

%%

%public
%class JavaScanner

%line
%column

%unicode
%type Token


%{
  StringBuilder string = new StringBuilder();
  
  private Token symbol(Java type) {
    return new Token(type, yyline+1, yycolumn+1);
  }

  private Token symbol(Java type, Object value) {
    return new Token(type, yyline+1, yycolumn+1, value);
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

Annotation = "@" {InputCharacter}* {LineTerminator}?

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]
    
/* floating point literals */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "abstract"                     { return symbol(Java.ABSTRACT); }
  "boolean"                      { return symbol(Java.BOOLEAN); }
  "break"                        { return symbol(Java.BREAK); }
  "byte"                         { return symbol(Java.BYTE); }
  "case"                         { return symbol(Java.CASE); }
  "catch"                        { return symbol(Java.CATCH); }
  "char"                         { return symbol(Java.CHAR); }
  "class"                        { return symbol(Java.CLASS); }
  "const"                        { return symbol(Java.CONST); }
  "continue"                     { return symbol(Java.CONTINUE); }
  "do"                           { return symbol(Java.DO); }
  "double"                       { return symbol(Java.DOUBLE); }
  "else"                         { return symbol(Java.ELSE); }
  "enum"						 { return symbol(Java.ENUM); }
  "extends"                      { return symbol(Java.EXTENDS); }
  "final"                        { return symbol(Java.FINAL); }
  "finally"                      { return symbol(Java.FINALLY); }
  "float"                        { return symbol(Java.FLOAT); }
  "for"                          { return symbol(Java.FOR); }
  "default"                      { return symbol(Java.DEFAULT); }
  "implements"                   { return symbol(Java.IMPLEMENTS); }
  "import"                       { return symbol(Java.IMPORT); }
  "instanceof"                   { return symbol(Java.INSTANCEOF); }
  "int"                          { return symbol(Java.INT); }
  "interface"                    { return symbol(Java.INTERFACE); }
  "long"                         { return symbol(Java.LONG); }
  "native"                       { return symbol(Java.NATIVE); }
  "new"                          { return symbol(Java.NEW); }
  "goto"                         { return symbol(Java.GOTO); }
  "if"                           { return symbol(Java.IF); }
  "public"                       { return symbol(Java.PUBLIC); }
  "short"                        { return symbol(Java.SHORT); }
  "super"                        { return symbol(Java.SUPER); }
  "switch"                       { return symbol(Java.SWITCH); }
  "synchronized"                 { return symbol(Java.SYNCHRONIZED); }
  "package"                      { return symbol(Java.PACKAGE); }
  "private"                      { return symbol(Java.PRIVATE); }
  "protected"                    { return symbol(Java.PROTECTED); }
  "transient"                    { return symbol(Java.TRANSIENT); }
  "return"                       { return symbol(Java.RETURN); }
  "void"                         { return symbol(Java.VOID); }
  "static"                       { return symbol(Java.STATIC); }
  "while"                        { return symbol(Java.WHILE); }
  "this"                         { return symbol(Java.THIS); }
  "throw"                        { return symbol(Java.THROW); }
  "throws"                       { return symbol(Java.THROWS); }
  "try"                          { return symbol(Java.TRY); }
  "volatile"                     { return symbol(Java.VOLATILE); }
  "strictfp"                     { return symbol(Java.STRICTFP); }
  
  /* boolean literals */
  "true"                         { return symbol(Java.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(Java.BOOLEAN_LITERAL, false); }
  
  /* null literal */
  "null"                         { return symbol(Java.NULL_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(Java.LPAREN); }
  ")"                            { return symbol(Java.RPAREN); }
  "{"                            { return symbol(Java.LBRACE); }
  "}"                            { return symbol(Java.RBRACE); }
  "["                            { return symbol(Java.LBRACK); }
  "]"                            { return symbol(Java.RBRACK); }
  ";"                            { return symbol(Java.SEMICOLON); }
  ","                            { return symbol(Java.COMMA); }
  "."                            { return symbol(Java.DOT); }
  
  /* operators */
  "="                            { return symbol(Java.EQ); }
  ">"                            { return symbol(Java.GT); }
  "<"                            { return symbol(Java.LT); }
  "!"                            { return symbol(Java.NOT); }
  "~"                            { return symbol(Java.COMP); }
  "?"                            { return symbol(Java.QUESTION); }
  ":"                            { return symbol(Java.COLON); }
  "=="                           { return symbol(Java.EQEQ); }
  "<="                           { return symbol(Java.LTEQ); }
  ">="                           { return symbol(Java.GTEQ); }
  "!="                           { return symbol(Java.NOTEQ); }
  "&&"                           { return symbol(Java.ANDAND); }
  "||"                           { return symbol(Java.OROR); }
  "++"                           { return symbol(Java.PLUSPLUS); }
  "--"                           { return symbol(Java.MINUSMINUS); }
  "+"                            { return symbol(Java.PLUS); }
  "-"                            { return symbol(Java.MINUS); }
  "*"                            { return symbol(Java.MULT); }
  "/"                            { return symbol(Java.DIV); }
  "&"                            { return symbol(Java.AND); }
  "|"                            { return symbol(Java.OR); }
  "^"                            { return symbol(Java.XOR); }
  "%"                            { return symbol(Java.MOD); }
  "<<"                           { return symbol(Java.LSHIFT); }
  ">>"                           { return symbol(Java.RSHIFT); }
  ">>>"                          { return symbol(Java.URSHIFT); }
  "+="                           { return symbol(Java.PLUSEQ); }
  "-="                           { return symbol(Java.MINUSEQ); }
  "*="                           { return symbol(Java.MULTEQ); }
  "/="                           { return symbol(Java.DIVEQ); }
  "&="                           { return symbol(Java.ANDEQ); }
  "|="                           { return symbol(Java.OREQ); }
  "^="                           { return symbol(Java.XOREQ); }
  "%="                           { return symbol(Java.MODEQ); }
  "<<="                          { return symbol(Java.LSHIFTEQ); }
  ">>="                          { return symbol(Java.RSHIFTEQ); }
  ">>>="                         { return symbol(Java.URSHIFTEQ); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(Java.INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(Java.INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return symbol(Java.INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(Java.INTEGER_LITERAL, new Integer((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(Java.INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(Java.INTEGER_LITERAL, new Integer((int) parseLong(0, yylength(), 8))); }  
  {OctLongLiteral}               { return symbol(Java.INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(Java.FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(Java.FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(Java.FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(Java.IDENTIFIER, yytext()); } 
  
  {Annotation}					 { /* ignore */ } 
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(Java.STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases */
  \\.                            { /* throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); */ }
  {LineTerminator}               { /* throw new RuntimeException("Unterminated string at end of line"); */ }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(Java.CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(Java.CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { /* throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); */ }
  {LineTerminator}               { /* throw new RuntimeException("Unterminated character literal at end of line"); */ }
}

/* error fallback */
.|\n                             { /* throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); */ }

<<EOF>>                          { return symbol(Java.EOF); }