package edu.odu.cs.cs350.LexicalAnalysis;


/**
 * All possible token types for C++ and Java
 *
 */
public interface TokenKinds 
{
	public String toString();
	
	public enum LanguageTypes
	{
		CPLUSPLUS,
		JAVA
	}
	
	public enum Java implements TokenKinds
	{
		// Shared with C++
		BOOLEAN ( "1" ),
		BREAK ( "2" ),
		CASE ( "3" ),
		CATCH ( "4" ),
		CHAR ( "5" ),
		CLASS ( "6" ),
		CONTINUE ( "7" ),
		DO ( "8" ),
		DOUBLE ( "9" ),
		ELSE ( "a" ),
		ENUM ( "b" ),
		FLOAT ( "c" ),
		FOR ( "d" ),
		DEFAULT ( "e" ),
		INT ( "f" ),
		LONG ( "g" ),
		NEW ( "h" ),
		IF ( "i" ),
		PUBLIC ( "j" ),
		SHORT ( "k" ),
		SWITCH ( "l" ),
		PRIVATE ( "m" ),
		PROTECTED ( "n" ),
		RETURN ( "o" ),
		VOID ( "p" ),
		STATIC ( "q" ),
		WHILE ( "r" ),
		THIS ( "s" ),
		THROW ( "t" ),
		TRY ( "u" ),
		BOOLEAN_LITERAL ( "v" ),
		NULL_LITERAL ( "w" ),
		
		// Common Java Reserved Words
		ABSTRACT ("x"),
		EXTENDS ( "y" ),
		FINAL ( "A" ),
		FINALLY ( "B" ),
		IMPLEMENTS ( "C" ),
		IMPORT ( "D" ),
		INSTANCEOF ( "E" ),
		INTERFACE ( "F" ),
		SUPER ( "G" ),
		PACKAGE ( "H" ),
		THROWS ( "I" ),
		
		// Uncommon Reserved Words
		BYTE ( "z" ),
		CONST ( "z" ),
		NATIVE ( "z" ),
		GOTO ( "z" ),
		SYNCHRONIZED ( "z" ),
		TRANSIENT ( "z" ),
		VOLATILE ( "z" ),
		STRICTFP ( "z" ),
		
		// Common Operators
		EQ ( "~" ),
		NOTEQ ( "!" ),
		EQEQ ( "@" ),
		GT ( "#" ),
		GTEQ ( "$" ),
		LT ( "%" ),
		LTEQ ( "^" ),
		NOT ( "&" ),
		DIV ( "/" ), 
		MULT ( "*" ),
		MINUS ( "-" ),
		MINUSMINUS ( "`"),
		PLUS ( "+" ),
		PLUSPLUS ( ":" ),
		OR ( "|" ),
		PLUSEQ ( "S" ),
		MINUSEQ ( "T" ),
		MULTEQ ( "U" ),
		MOD ( "V" ),
		ANDAND ( "W" ),
		AND ( "X" ),
		OROR ( "Y" ),
		
		// Uncommon Operators
		COMP ( "Z" ),
		QUESTION ( "Z" ),
		COLON ( "Z" ),
		XOR ( "Z" ),
		LSHIFT ( "Z" ),
		RSHIFT ( "Z" ),
		URSHIFT ( "Z" ),
		XOREQ ( "Z" ),
		LSHIFTEQ ( "Z" ),
		RSHIFTEQ ( "Z" ),
		URSHIFTEQ ( "Z" ),
		MODEQ ( "Z" ),
		DIVEQ ( "Z" ),
		ANDEQ ( "Z" ),
		OREQ ( "Z" ),
		
		// Separators
		LPAREN ( "(" ),
		RPAREN ( ")" ),
		LBRACE ( "[" ),
		RBRACE ( "]" ),
		LBRACK ( "{" ),
		RBRACK ( "}" ),
		SEMICOLON ( ";" ),
		COMMA ( "," ),
		DOT ( "." ),
		
		// Identifier
		IDENTIFIER( "=" ),
		
		// Literals
		INTEGER_LITERAL ( "?" ),
		FLOATING_POINT_LITERAL ( "\\" ),
		STRING_LITERAL ( "\"" ),
		CHARACTER_LITERAL ( "'" ),
		
		// End of File
		EOF ( "0" );
		
		/*
		 *  Token Identifier
		 */
		private final String identifier;
		
		/*
		 *  Java Constructor
		 */
		private Java(String string) 
		{
			this.identifier = string;
		}
		
		/*
		 * Return the identifier as a String
		 * @return <b> String </b> identifier
		 */
		@Override
		public String toString()
		{
			return identifier;
		}
	}
	
	public enum Cplusplus implements TokenKinds
	{
		// Shared with Java
		BOOLEAN ( "1" ),
		BREAK ( "2" ),
		CASE ( "3" ),
		CATCH ( "4" ),
		CHAR ( "5" ),
		CLASS ( "6" ),
		CONTINUE ( "7" ),
		DO ( "8" ),
		DOUBLE ( "9" ),
		ELSE ( "a" ),
		ENUM ( "b" ),
		FLOAT ( "c" ),
		FOR ( "d" ),
		DEFAULT ( "e" ),
		INT ( "f" ),
		LONG ( "g" ),
		NEW ( "h" ),
		IF ( "i" ),
		PUBLIC ( "j" ),
		SHORT ( "k" ),
		SWITCH ( "l" ),
		PRIVATE ( "m" ),
		PROTECTED ( "n" ),
		RETURN ( "o" ),
		VOID ( "p" ),
		STATIC ( "q" ),
		WHILE ( "r" ),
		THIS ( "s" ),
		THROW ( "t" ),
		TRY ( "u" ),
		BOOLEAN_LITERAL ( "v" ),
		NULL_LITERAL ( "w" ),
		
		// Common C++ Reserved Words
		AUTO ( "x" ),
		CONST ( "y" ),
		DELETE ( "A" ),
		DYNAMIC_CAST ( "B" ),
		EXPLICIT ( "C" ),
		EXTERN ( "D" ),
		FRIEND ( "E" ),
		INLINE ( "F" ),
		NAMESPACE ( "G" ),
		OPERATOR ( "H" ),
		SIGNED ( "I" ),
		SIZEOF ( "J" ),
		STATIC_CAST ( "K" ),
		STRUCT ( "L" ),
		TEMPLATE ( "M" ),
		TYPEDEF ( "N" ),
		TYPENAME ( "O" ),
		UNSIGNED ( "P" ),
		USING ( "Q" ),
		VIRTUAL ( "R" ),
		
		// Uncommon C++ Reserved Words
		ALIGNAS ( "z" ),
		ALIGNOF ( "z" ),
		ASM ( "z" ),
		ATOMIC_CANCEL ( "z" ),
		ATOMIC_COMMIT ( "z" ),
		ATOMIC_NOEXCEPT ( "z" ),
		CHAR16T ( "z" ),
		CHAR32T ( "z" ),
		COMPL ( "z" ),
		CONCEPT ( "z" ),
		CONSTEXPR ( "z" ),
		CONSTCAST ( "z" ),
		DECLTYPE ( "z" ),
		EXPORT ( "z" ),
		GOTO ( "z" ),
		IMPORT ( "z" ),
		NOEXCEPT ( "z" ),
		MODULE ( "z" ),
		MUTABLE ( "z" ),
		REGISTER ( "z" ),
		REINTERPRET_CAST ( "z" ),
		REQUIRES ( "z" ),
		STATIC_ASSERT ( "z" ),
		SYNCHRONIZED ( "z" ),
		TYPEID ( "z" ),
		THREAD_LOCAL ( "z" ),
		UNION ( "z" ),
		VOLATILE ( "z" ),
		WCHART ( "z" ),

		// Common Operators
		EQ ( "~" ),
		NOTEQ ( "!" ),
		EQEQ ( "@" ),
		GT ( "#" ),
		GTEQ ( "$" ),
		LT ( "%" ),
		LTEQ ( "^" ),
		NOT ( "&" ),
		DIV ( "/" ), 
		MULT ( "*" ),
		MINUS ( "-" ),
		MINUSMINUS ( "`"),
		PLUS ( "+" ),
		PLUSPLUS ( ":" ),
		OR ( "|" ),
		PLUSEQ ( "S" ),
		MINUSEQ ( "T" ),
		MULTEQ ( "U" ),
		MOD ( "V" ),
		ANDAND ( "W" ),
		AND ( "X" ),
		OROR ( "Y" ),
		
		// Uncommon Operators
		COMP ( "Z" ),
		QUESTION ( "Z" ),
		COLON ( "Z" ),
		XOR ( "Z" ),
		LSHIFT ( "Z" ),
		RSHIFT ( "Z" ),
		URSHIFT ( "Z" ),
		XOREQ ( "Z" ),
		LSHIFTEQ ( "Z" ),
		RSHIFTEQ ( "Z" ),
		URSHIFTEQ ( "Z" ),
		MODEQ ( "Z" ),
		DIVEQ ( "V" ),
		ANDEQ ( "Z" ),
		OREQ ( "Z" ),
		MEMBERACCESS ( "Z" ),
		
		// Separators
		LPAREN ( "(" ),
		RPAREN ( ")" ),
		LBRACE ( "[" ),
		RBRACE ( "]" ),
		LBRACK ( "{" ),
		RBRACK ( "}" ),
		SEMICOLON ( ";" ),
		COMMA ( "," ),
		DOT ( "." ),
		
		// Identifier
		IDENTIFIER( "=" ),
		
		// Literals
		INTEGER_LITERAL ( "?" ),
		FLOATING_POINT_LITERAL ( "\\" ),
		STRING_LITERAL ( "\"" ),
		CHARACTER_LITERAL ( "'" ),
		
		// End of File
		EOF ( "0" );
		
		/*
		 * C++ Identifier
		 */
		private final String identifier;
		
		/*
		 * C++ Constructor
		 */
		private Cplusplus(String string) 
		{
			this.identifier = string;
		}
		
		/*
		 * Return the identifier as a String
		 * @return <b> String </b> identifier
		 */
		@Override
		public String toString()
		{
			return identifier;
		}
	}
}