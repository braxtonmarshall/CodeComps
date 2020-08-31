package edu.odu.cs.cs350.LexicalAnalysis;

%%

%public
%class CppScanner

%line
%column

%unicode
%type Token


%{
  StringBuilder string = new StringBuilder();
  
  private Token symbol(CTokenTypes type) {
    return new Token(type, yyline+1, yycolumn+1);
  }

  private Token symbol(CTokenTypes type, Object value) {
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

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* Preprocessor Directive */
Preprocessor = "#" {InputCharacter}* {LineTerminator}?

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
  "alignas"						 { return symbol(CTokenTypes.ALIGNAS); }
  "alignof"						 { return symbol(CTokenTypes.ALIGNOF); }
  "and"							 { return symbol(CTokenTypes.ANDAND); }
  "and_eq"						 { return symbol(CTokenTypes.ANDEQ); }
  "asm"							 { return symbol(CTokenTypes.ASM); }
  "atomic_cancel"				 { return symbol(CTokenTypes.ATOMIC_CANCEL); }
  "atomic_commit"				 { return symbol(CTokenTypes.ATOMIC_COMMIT); }
  "atomic_noexcept"				 { return symbol(CTokenTypes.ATOMIC_NOEXCEPT); }
  "auto"						 { return symbol(CTokenTypes.AUTO); }
  "bitand"						 { return symbol(CTokenTypes.AND); }
  "bitor"						 { return symbol(CTokenTypes.OR); }
  "bool"                         { return symbol(CTokenTypes.BOOLEAN); }
  "break"                        { return symbol(CTokenTypes.BREAK); }
  "case"                         { return symbol(CTokenTypes.CASE); }
  "catch"                        { return symbol(CTokenTypes.CATCH); }
  "char"                         { return symbol(CTokenTypes.CHAR); }
  "char16_t"					 { return symbol(CTokenTypes.CHAR16T); }
  "char32_t"					 { return symbol(CTokenTypes.CHAR32T); }
  "class"                        { return symbol(CTokenTypes.CLASS); }
  "compl"						 { return symbol(CTokenTypes.COMPL); }
  "concept"						 { return symbol(CTokenTypes.CONCEPT); }
  "const"                        { return symbol(CTokenTypes.CONST); }
  "constexpr"					 { return symbol(CTokenTypes.CONSTEXPR); }
  "const_cast"					 { return symbol(CTokenTypes.CONSTCAST); }
  "continue"                     { return symbol(CTokenTypes.CONTINUE); }
  "decltype"					 { return symbol(CTokenTypes.DECLTYPE); }
  "default"                      { return symbol(CTokenTypes.DEFAULT); }
  "delete"						 { return symbol(CTokenTypes.DELETE); }
  "do"                           { return symbol(CTokenTypes.DO); }
  "double"                       { return symbol(CTokenTypes.DOUBLE); }
  "dynamic_cast"				 { return symbol(CTokenTypes.DYNAMIC_CAST); }
  "else"                         { return symbol(CTokenTypes.ELSE); }
  "enum"						 { return symbol(CTokenTypes.ENUM); }
  "explicit"					 { return symbol(CTokenTypes.EXPLICIT); }
  "export"						 { return symbol(CTokenTypes.EXPORT); }
  "extern"					 	 { return symbol(CTokenTypes.EXTERN); }
  "float"                        { return symbol(CTokenTypes.FLOAT); }
  "for"                          { return symbol(CTokenTypes.FOR); }
  "friend"						 { return symbol(CTokenTypes.FRIEND); }
  "goto"                         { return symbol(CTokenTypes.GOTO); }
  "if"                           { return symbol(CTokenTypes.IF); }
  "import"						 { return symbol(CTokenTypes.IMPORT); }
  "inline"						 { return symbol(CTokenTypes.INLINE); }
  "int"                          { return symbol(CTokenTypes.INT); }
  "long"                         { return symbol(CTokenTypes.LONG); }
  "module"						 { return symbol(CTokenTypes.MODULE); }
  "mutable"						 { return symbol(CTokenTypes.MUTABLE); }
  "namespace"					 { return symbol(CTokenTypes.NAMESPACE); }
  "new"                          { return symbol(CTokenTypes.NEW); }
  "noexcept"					 { return symbol(CTokenTypes.NOEXCEPT); }
  "not"							 { return symbol(CTokenTypes.NOT); }
  "not_eq"						 { return symbol(CTokenTypes.NOTEQ); }
  "operator"					 { return symbol(CTokenTypes.OPERATOR); }
  "or"							 { return symbol(CTokenTypes.OROR); }
  "or_eq"						 { return symbol(CTokenTypes.OREQ); }
  "private"                      { return symbol(CTokenTypes.PRIVATE); }
  "protected"                    { return symbol(CTokenTypes.PROTECTED); }
  "public"                       { return symbol(CTokenTypes.PUBLIC); }
  "register"					 { return symbol(CTokenTypes.REGISTER); }
  "reinterpret_cast"		 	 { return symbol(CTokenTypes.REINTERPRET_CAST); }
  "requires"					 { return symbol(CTokenTypes.REQUIRES); }
  "return"                       { return symbol(CTokenTypes.RETURN); }
  "short"                        { return symbol(CTokenTypes.SHORT); }
  "signed"						 { return symbol(CTokenTypes.SIGNED); }
  "sizeof"						 { return symbol(CTokenTypes.SIZEOF); }
  "static"                       { return symbol(CTokenTypes.STATIC); }
  "static_assert"				 { return symbol(CTokenTypes.STATIC_ASSERT); }
  "static_cast"					 { return symbol(CTokenTypes.STATIC_CAST); }
  "struct"						 { return symbol(CTokenTypes.STRUCT); }
  "switch"                       { return symbol(CTokenTypes.SWITCH); }
  "synchronized"                 { return symbol(CTokenTypes.SYNCHRONIZED); }
  "template"					 { return symbol(CTokenTypes.TEMPLATE); }
  "this"                         { return symbol(CTokenTypes.THIS); }
  "thread_local"				 { return symbol(CTokenTypes.THREAD_LOCAL); }
  "throw"                        { return symbol(CTokenTypes.THROW); }
  "try"                          { return symbol(CTokenTypes.TRY); }
  "typedef"						 { return symbol(CTokenTypes.TYPEDEF); }
  "typeid"						 { return symbol(CTokenTypes.TYPEID); }
  "typename"					 { return symbol(CTokenTypes.TYPENAME); }
  "union"						 { return symbol(CTokenTypes.UNION); }
  "unsigned"					 { return symbol(CTokenTypes.UNSIGNED); }
  "using"						 { return symbol(CTokenTypes.USING); }
  "virtual"						 { return symbol(CTokenTypes.VIRTUAL); }
  "void"                         { return symbol(CTokenTypes.VOID); }
  "volatile"                     { return symbol(CTokenTypes.VOLATILE); }
  "wchar_t"						 { return symbol(CTokenTypes.WCHART); }
  "while"                        { return symbol(CTokenTypes.WHILE); }
  "xor"							 { return symbol(CTokenTypes.XOR); }
  "xor_eq"						 { return symbol(CTokenTypes.XOREQ); }
  
  /* boolean literals */
  "true"                         { return symbol(CTokenTypes.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(CTokenTypes.BOOLEAN_LITERAL, false); }
  
  /* null literal */
  "null"                         { return symbol(CTokenTypes.NULL_LITERAL); }
  "NULL"						 { return symbol(CTokenTypes.NULL_LITERAL); }
  "nullptr"						 { return symbol(CTokenTypes.NULL_LITERAL); }
  
  /* separators */
  "("                            { return symbol(CTokenTypes.LPAREN); }
  ")"                            { return symbol(CTokenTypes.RPAREN); }
  "{"                            { return symbol(CTokenTypes.LBRACE); }
  "}"                            { return symbol(CTokenTypes.RBRACE); }
  "["                            { return symbol(CTokenTypes.LBRACK); }
  "]"                            { return symbol(CTokenTypes.RBRACK); }
  ";"                            { return symbol(CTokenTypes.SEMICOLON); }
  ","                            { return symbol(CTokenTypes.COMMA); }
  "."                            { return symbol(CTokenTypes.DOT); }
  
  /* operators */
  "="                            { return symbol(CTokenTypes.EQ); }
  ">"                            { return symbol(CTokenTypes.GT); }
  "<"                            { return symbol(CTokenTypes.LT); }
  "!"                            { return symbol(CTokenTypes.NOT); }
  "~"                            { return symbol(CTokenTypes.COMP); }
  "?"                            { return symbol(CTokenTypes.QUESTION); }
  ":"                            { return symbol(CTokenTypes.COLON); }
  "=="                           { return symbol(CTokenTypes.EQEQ); }
  "<="                           { return symbol(CTokenTypes.LTEQ); }
  ">="                           { return symbol(CTokenTypes.GTEQ); }
  "!="                           { return symbol(CTokenTypes.NOTEQ); }
  "&&"                           { return symbol(CTokenTypes.ANDAND); }
  "||"                           { return symbol(CTokenTypes.OROR); }
  "++"                           { return symbol(CTokenTypes.PLUSPLUS); }
  "--"                           { return symbol(CTokenTypes.MINUSMINUS); }
  "+"                            { return symbol(CTokenTypes.PLUS); }
  "-"                            { return symbol(CTokenTypes.MINUS); }
  "*"                            { return symbol(CTokenTypes.MULT); }
  "/"                            { return symbol(CTokenTypes.DIV); }
  "&"                            { return symbol(CTokenTypes.AND); }
  "|"                            { return symbol(CTokenTypes.OR); }
  "^"                            { return symbol(CTokenTypes.XOR); }
  "%"                            { return symbol(CTokenTypes.MOD); }
  "<<"                           { return symbol(CTokenTypes.LSHIFT); }
  ">>"                           { return symbol(CTokenTypes.RSHIFT); }
  ">>>"                          { return symbol(CTokenTypes.URSHIFT); }
  "+="                           { return symbol(CTokenTypes.PLUSEQ); }
  "-="                           { return symbol(CTokenTypes.MINUSEQ); }
  "*="                           { return symbol(CTokenTypes.MULTEQ); }
  "/="                           { return symbol(CTokenTypes.DIVEQ); }
  "&="                           { return symbol(CTokenTypes.ANDEQ); }
  "|="                           { return symbol(CTokenTypes.OREQ); }
  "^="                           { return symbol(CTokenTypes.XOREQ); }
  "%="                           { return symbol(CTokenTypes.MODEQ); }
  "<<="                          { return symbol(CTokenTypes.LSHIFTEQ); }
  ">>="                          { return symbol(CTokenTypes.RSHIFTEQ); }
  ">>>="                         { return symbol(CTokenTypes.URSHIFTEQ); }
  "->"							 { return symbol(CTokenTypes.MEMBERACCESS); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(CTokenTypes.INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(CTokenTypes.INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return symbol(CTokenTypes.INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(CTokenTypes.INTEGER_LITERAL, new Integer((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(CTokenTypes.INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(CTokenTypes.INTEGER_LITERAL, new Integer((int) parseLong(0, yylength(), 8))); }  
  {OctLongLiteral}               { return symbol(CTokenTypes.INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(CTokenTypes.FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(CTokenTypes.FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(CTokenTypes.FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(CTokenTypes.IDENTIFIER, yytext()); }  
  
  /* Preprocessor Directive */
  {Preprocessor}				 { /* ignore */ }
}


  
<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(CTokenTypes.STRING_LITERAL, string.toString()); }
  
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
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(CTokenTypes.CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(CTokenTypes.CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { /* throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); */ }
  {LineTerminator}               { /* throw new RuntimeException("Unterminated character literal at end of line"); */ }
}

/* error fallback */
.|\n                             { /* throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); */ }
<<EOF>>                          { return symbol(CTokenTypes.EOF); }