package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds;

public class TestTokenKinds 
{

	@Test
	public void testToString() 
	{
		assertEquals( "1" , TokenKinds.Java.BOOLEAN.toString() );
		assertEquals( "A" , TokenKinds.Java.FINAL.toString() );
		assertEquals( "%" , TokenKinds.Java.LT.toString() );
		assertEquals( "+" , TokenKinds.Java.PLUS.toString() );
		assertEquals( "Z" , TokenKinds.Java.XOREQ.toString() );
		
		assertEquals( "1" , TokenKinds.Cplusplus.BOOLEAN.toString() );
		assertEquals( "C" , TokenKinds.Cplusplus.EXPLICIT.toString() );
		assertEquals( "%" , TokenKinds.Cplusplus.LT.toString() );
		assertEquals( "+" , TokenKinds.Cplusplus.PLUS.toString() );
		assertEquals( "Z" , TokenKinds.Cplusplus.XOREQ.toString() );
	}
}
