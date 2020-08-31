
package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.odu.cs.cs350.LexicalAnalysis.Token;
import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds;


public class TestToken 
{
	@Test
	public void testTokenTokenKindsIntIntObject() 
	{
		Token testToken = new Token (TokenKinds.Java.ABSTRACT, 1 , 1 );
		assertEquals(TokenKinds.Java.ABSTRACT, testToken.getKind());
		assertEquals("" , testToken.getLexeme());

		assertEquals(1, testToken.getLineNumber());
		assertEquals(1, testToken.getColumnNumber());
		assertEquals("" , testToken.toString());
	}

	@Test
	public void testTokenTokenTypesIntInt() 
	{
		Token testToken = new Token(TokenKinds.Cplusplus.PUBLIC, 2000 , 5, "words");
		assertEquals(TokenKinds.Cplusplus.PUBLIC, testToken.getKind());
		assertEquals("words",testToken.getLexeme());
		assertEquals(2000, testToken.getLineNumber());
		assertEquals(5,testToken.getColumnNumber());
		assertEquals("words", testToken.toString());
	}

	@Test
	public void testgetKind() 
	{
		Token testToken = new Token(TokenKinds.Cplusplus.PRIVATE,1,1);
		assertEquals(TokenKinds.Cplusplus.PRIVATE, testToken.getKind());
	}

	@Test
	public void testGetLexeme() 
	{
		Token testToken = new Token(TokenKinds.Java.PRIVATE,1 ,1, "var");
		assertEquals("var", testToken.getLexeme());
		

		Token testToken1 = new Token(TokenKinds.Java.PRIVATE,1,1);
		assertEquals("", testToken1.getLexeme());
	}

	@Test
	public void testGetLineNumber() 
	{
		Token testToken = new Token(TokenKinds.Cplusplus.PRIVATE,2 ,2);
		assertEquals(2,testToken.getLineNumber());
	}

	@Test
	public void testGetColumnNumber() 
	{
		Token testToken = new Token(TokenKinds.Cplusplus.PRIVATE,2,2);
		assertEquals(2,testToken.getColumnNumber());
	}

	@Test
	public void testEqualsObject() 
	{
		Object obj = null;
		Token testToken = new Token(TokenKinds.Java.PRIVATE,2,2);
		Token testToken1 = new Token(TokenKinds.Java.PRIVATE,2,2);

		int x = 5;
		assertFalse(testToken.equals(obj));
		assertFalse(testToken.equals(x));
		assertTrue(testToken.equals(testToken1));
	}

	@Test
	public void testToString() 
	{
		Token testToken = new Token(TokenKinds.Java.ABSTRACT,3,3,"testwords");
		
		assertEquals("testwords", testToken.toString());
	}
}

