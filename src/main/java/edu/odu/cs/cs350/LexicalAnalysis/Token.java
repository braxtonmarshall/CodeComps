package edu.odu.cs.cs350.LexicalAnalysis;


/**
 * Token class, stores the token type, the lexeme (the actual character string)
 * ant the location (column and line numbers).
 * 
 *	This interface's design is based on Dr. Steven Zeil's jflexdemo repository.
 */
public class Token implements Cloneable{

    // The kind of Token
    private TokenKinds kind;

    // For variables & literals, the original lexical string.
    private String lexeme;
    
    // The corresponding line number for the lexeme
    private int lineNumber;
    
    // The corresponding column number for the lexeme
    private int columnNumber;
    

    /**
     * Create a basic token with no explicit lexeme.
     * @param theKind the kind of token
     * @param line line number where token was found
     * @param column column number where token begins
     */
    public Token (final TokenKinds theKind, final int line, final int column) {
        kind = theKind;
        lexeme = "";
        lineNumber = line;
        columnNumber = column;
    }

    /**
     * Create a token.
     * @param theKind <b> TokenKinds </b> what kind of token
     * @param line <b> integer </b> line number where token was found
     * @param column <b> integer </b> column number where token begins
     * @param theLexeme <b> String </b> the original lexeme
     */
    public Token (final TokenKinds theKind, final int line, final int column,
            final Object theLexeme) {
        kind = theKind;
        lexeme = theLexeme.toString();
        lineNumber = line;
        columnNumber = column;
    }

    /**
     * Representation of the token for debugging purposes.
     */
    @Override
    public final String toString() {
        if (getLexeme().length() > 0) {
            return getKind() + ":" + getLexeme();
        } else {
            return getKind().toString();
        }
    }

    /**
     * Return the token kind
     * @return <b> TokenKinds </b> the kind
     */
    public final TokenKinds getKind() {
        return kind;
    }


    /**
     * Return the lexeme
     * @return <b> String </b> the lexeme
     */
    public final String getLexeme() {
        return lexeme;
    }

    /**
     * Return the line number
     * @return <b> Integer </b> the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }


    /**
     * Return the column number
     * @return <b> Integer </b> the columnNumber
     */
    public int getColumnNumber() {
        return columnNumber;
    }



}
