package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.odu.cs.cs350.LexicalAnalysis.TokenKinds;
import edu.odu.cs.cs350.LexicalAnalysis.Token;
import edu.odu.cs.cs350.LexicalAnalysis.TokenStream;
import java.io.*;

public class TestTokenStream 
{
	@Test
	public void testTokenSequenceReaderLanguageTypes() 
	{
		int count = 0;
		Reader j = new StringReader("public class");
		TokenStream TS = new TokenStream(j, TokenKinds.LanguageTypes.JAVA);
		for (@SuppressWarnings("unused") Token T : TS)
		{
			count++;
		}
		assertEquals(count, 2);
		assertEquals(TS.getTokenCount(), 2);
		assertEquals(TS.toString(),"j6");
		assertEquals(TS.getTokenStream().toString(), "j6");
	}

	@Test
	public void testTokenSequenceTokenSequence() 
	{
		Reader j = new StringReader("public class");
		TokenStream TS = new TokenStream(j,TokenKinds.LanguageTypes.JAVA);
		TokenStream copyTS = new TokenStream(TS);
		assertEquals(copyTS, TS);
		assertNotSame(copyTS,TS);
		assertEquals(copyTS.getTokenCount(),TS.getTokenCount());
		assertEquals(copyTS.toString(),TS.toString());
		assertEquals(copyTS.getTokenStream().toString(),TS.getTokenStream().toString());
	}

	@Test
	public void testGetTokenCount() 
	{
		Reader j = new StringReader("public class bobIsAmazing{}");
		TokenStream TS= new TokenStream(j,TokenKinds.LanguageTypes.JAVA);
		assertEquals(TS.getTokenCount(), 5);
	}
	
	@Test
	public void testGetTokenSequence() 
	{
		Reader j = new StringReader("public class int");
		TokenStream TS = new TokenStream(j, TokenKinds.LanguageTypes.JAVA);
		assertEquals(TS.getTokenStream().toString(), "j6f");
	}

	@Test
	public void testEqualsObject() 
	{
		Reader j = new StringReader("public class bobIsAmazing{}");
		TokenStream TS 	= new TokenStream(j,TokenKinds.LanguageTypes.JAVA);
		j = new StringReader("public class bobIsAmazing{}");
		TokenStream TS1	= new TokenStream(j,TokenKinds.LanguageTypes.JAVA);
		TokenStream TS2	= new TokenStream(j,TokenKinds.LanguageTypes.CPLUSPLUS);
		assertTrue(TS1.equals(TS));
		assertFalse(TS1.equals(TS2));
	}

	@Test
	public void testToString() 
	{
		Reader j = new StringReader("public class int");
		TokenStream TS = new TokenStream(j,TokenKinds.LanguageTypes.JAVA);
		assertEquals(TS.getTokenStream().toString(), "j6f");
	}

	@Test
	public void testClone() 
	{
		Reader j = new StringReader("public class");
		TokenStream TS = new TokenStream(j,TokenKinds.LanguageTypes.JAVA);
		TokenStream copyTS = (TokenStream)TS.clone();
		assertEquals(copyTS, TS);
		assertNotSame(copyTS,TS);
		assertEquals(copyTS.getTokenCount(),TS.getTokenCount());
		assertEquals(copyTS.toString(),TS.toString());
		assertEquals(copyTS.getTokenStream().toString(),TS.getTokenStream().toString());
	}
}
