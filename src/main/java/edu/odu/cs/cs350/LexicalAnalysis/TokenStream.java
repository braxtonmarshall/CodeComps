package edu.odu.cs.cs350.LexicalAnalysis;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds.Cplusplus;
import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds.Java;
import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds.LanguageTypes;


public class TokenStream implements Iterable<Token> , Cloneable
{
    
    // The list of tokens obtained from the actual scanner.
    private List<Token> tokens;
    
    // Language Type
    LanguageTypes lang;
    
   /**
    * Create a TokenStream from an input source.
    * @param input input source for this stream
    */
    public TokenStream(final Reader input, LanguageTypes lang) 
    {
        tokens = new LinkedList<Token>();
        this.lang = lang;
        
        if (this.lang == LanguageTypes.JAVA)
        {
        	JavaScanner scanner = new JavaScanner (input);
        	
        	try
        	{
        		Token token = scanner.yylex();
        		
        		while (token != null && token.getKind() != Java.EOF)
        		{
        			tokens.add(token);
        			token = scanner.yylex();
        		}
        	}
        	catch (IOException e)
        	{
        		// Not necessarily a problem - Let it continue to run.
        	}
        }
        else
        {
        	CppScanner scanner = new CppScanner(input);
        	
        	try
        	{
        		Token token = scanner.yylex();
        		
        		while (token != null && token.getKind() != Cplusplus.EOF)
        		{
        			tokens.add(token);
        			token = scanner.yylex();
        		}
        	}
        	catch (IOException e)
        	{
        		// Not Necessarily a problem - Let it continue to run.
        	}
        }
    }
    /*
     * Copy Constructor
     * @param tokenStream <b> TokenStream </b>
     */
    @SuppressWarnings("unchecked")
	public TokenStream(TokenStream tokenStream) 
    {
    	this.tokens = (List<Token>) ((LinkedList<Token>) tokenStream.tokens).clone();
    	this.lang = tokenStream.lang;
    }

	/**
     * Return an iterator over the list of tokens.
     * @see java.lang.Iterable#iterator()
     * @return iterator
     */
    @Override
    public final Iterator<Token> iterator() {
        return tokens.iterator();
    }
    
    /*
     * Method to retrieve the total number of tokens
     * @return <b> Integer </b> Total number of tokens in the stream
     */
    public int getTokenCount()
    {
    	return tokens.size();
    }
    
    /*
     * Method to retrieve the token stream as a String
     * @return <b> StringBuilder </b> Stream of tokens
     */
    public StringBuilder getTokenStream()
    {
    	StringBuilder result = new StringBuilder();
    	
    	for (Token t : tokens)
    	{
    		result.append(t.getKind().toString());
    	}
    	
    	return result;
    }
    /*
     * Override equals()
     * @param object <b> Object </b>
     * @return <b> boolean </b> Return true if the two object are equal, else return false
     */
    @Override
    public boolean equals(Object object)
    {
    	if (object == null)
    	{
    		return false;
    	}
    	
    	if ( !(object instanceof TokenStream) )
    	{
    		return false;
    	}
    	
    	if (this == object)
    	{
    		return true;
    	}
    	
    	TokenStream comparedObj = (TokenStream) object;
    	return this.tokens.equals(comparedObj.tokens) && this.lang.equals(comparedObj.lang);
    }
    
    /*
     * Override toString method
     * @return <b> String </b> The token stream as a String
     */
    @Override
    public String toString()
    {
    	return getTokenStream().toString();
    }
    
    /*
     * Override clone method
     * @return <b> Object </b> A deep copy of the object
     */
    @Override
    public Object clone()
    {
    	return new TokenStream( this );
    }
}
