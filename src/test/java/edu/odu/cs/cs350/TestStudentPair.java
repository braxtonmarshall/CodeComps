package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import edu.odu.cs.cs350.Interface.Teacher;
import edu.odu.cs.cs350.Interface.StudentPair;

public class TestStudentPair 
{
	private Teacher teacher;
	private File submissionDirectory;
	private StudentPair Alicebob;
	private StudentPair AliceJimbob;
	
	
	public TestStudentPair()
	{
		teacher = new Teacher("ostream");
		submissionDirectory = new File( "./src/test/data/testSubmissionDirectory" );
		teacher.acceptStudentSubmission(submissionDirectory);
		
		Alicebob = teacher.getStudentPair( "Alice" , "Bob" );
		AliceJimbob = teacher.getStudentPair( "Alice" , "Jimbob" );
	}
	
	@Test
	public void testStudentPairStudentStudent() 
	{
		
		assertEquals( "0.0" , String.format("%.1f",Alicebob.getRawScore()));
		assertEquals( "0.0" , String.format("%.1f",Alicebob.getZScore()));
		
		assertFalse(Alicebob.isRawScoreCalc());
		assertFalse(Alicebob.isZScoreCalc());
		
		assertEquals("Alice", Alicebob.getFirstStudent());
		assertEquals("Bob", Alicebob.getSecondStudent());
		
		assertEquals("( Alice , Bob )", Alicebob.toString());
		assertEquals("( Alice , Jimbob )", AliceJimbob.toString());
		
		assertTrue(Alicebob.equals(Alicebob));
		assertFalse(Alicebob.equals(AliceJimbob));
	
	}

	@Test
	public void testStudentPairStudentPair() 
	{
		StudentPair CopyAliceBob = new StudentPair(Alicebob);
		
		assertNotSame(CopyAliceBob , Alicebob);
		
		assertEquals(CopyAliceBob.toString() , Alicebob.toString());
		assertEquals(CopyAliceBob.getFirstStudent(), Alicebob.getFirstStudent());
		

		assertEquals("Alice", Alicebob.getFirstStudent());
		assertEquals("Bob", Alicebob.getSecondStudent());
		assertEquals("Alice", CopyAliceBob.getFirstStudent());
		assertEquals("Bob", CopyAliceBob.getSecondStudent());
		
		
		assertEquals("Alice", Alicebob.getFirstStudent());
		assertEquals("Bob", Alicebob.getSecondStudent());
		assertEquals("Alice", CopyAliceBob.getFirstStudent());
		assertEquals("Bob", CopyAliceBob.getSecondStudent());
		
		
		assertEquals( "0.0" , String.format("%.1f",CopyAliceBob.getRawScore()));
		assertEquals( "0.0" , String.format("%.1f",CopyAliceBob.getZScore()));
		
		assertFalse(CopyAliceBob.isRawScoreCalc());
		assertFalse(CopyAliceBob.isZScoreCalc());
	}

	@Test
	public void testGetRawScore() 
	{
		assertEquals("0.0" , String.format("%.1f",Alicebob.getRawScore()));
		assertEquals("0.0" , String.format("%.1f",AliceJimbob.getRawScore()));
	}

	@Test
	public void testGetZScore() 
	{
		assertEquals( "0.0" , String.format("%.1f",Alicebob.getZScore()));
		assertEquals( "0.0" , String.format("%.1f",AliceJimbob.getZScore()));
	}

	@Test
	public void testGetFirstStudentName() 
	{
		assertEquals("Alice", Alicebob.getFirstStudent());
		assertEquals("Alice", AliceJimbob.getFirstStudent());		
	}

	@Test
	public void testGetSecondStudentName() 
	{
		assertEquals("Bob", Alicebob.getSecondStudent());
		assertEquals("Jimbob", AliceJimbob.getSecondStudent());
	}

	@Test
	public void testIsRawScoreCalculated() 
	{
		StudentPair tempPair = new StudentPair(Alicebob);
		assertFalse(tempPair.isRawScoreCalc());
	}

	@Test
	public void testIsZScoreCalculated() 
	{
		StudentPair tempPair = new StudentPair(Alicebob);
		assertFalse(tempPair.isZScoreCalc());
	}

	@Test
	public void testCalculateRawScore() 
	{
		Teacher teacher = new Teacher("output");
		teacher.acceptStudentSubmission(submissionDirectory);
		teacher.parseSubmissionDirectory();
		teacher.analyze();
		StudentPair Alicebob = teacher.getStudentPair( "Alice" , "Bob" );
		
		double combinedLength = 21 + 269;
		double numerator = 4 * 2;
		double denominator = Math.pow( combinedLength, 2.0);
		double rawScore = numerator / denominator;
		
		assertEquals(String.format("%.1f", rawScore) , String.format("%.1f", Alicebob.getRawScore()));
	}

	@Test
	public void testCalculateZScore() 
	{	
		Teacher instructor = new Teacher("ostream");
		teacher.acceptStudentSubmission(submissionDirectory);

		teacher.parseSubmissionDirectory();
		teacher.analyze();
		StudentPair Alicebob = instructor.getStudentPair( "Alice" , "Bob" );
		
		assertEquals( "-0.4" , String.format("%.1f", Alicebob.getZScore()));
	}

	@Test
	public void testToString() 
	{
		assertEquals( "( Alice , Bob )" , Alicebob.toString());
		assertEquals( "( Alice , Jimbob )" , AliceJimbob.toString() );			
	}

	@Test
	public void testEqualsObject() 
	{
		
		assertTrue(Alicebob.equals(Alicebob));
		assertFalse(Alicebob.equals(AliceJimbob));
		
		StudentPair copyAliceBob = new StudentPair(Alicebob);
		
		assertTrue(Alicebob.equals(Alicebob) && copyAliceBob.equals(Alicebob));
		assertFalse(Alicebob.equals(AliceJimbob) || AliceJimbob.equals(Alicebob));
	}

	@Test
	public void testCompareTo() 
	{
		
		assertTrue(Alicebob.compareTo(Alicebob) == 0);
		assertTrue(Alicebob.compareTo(AliceJimbob) < 0);
		assertTrue(AliceJimbob.compareTo(Alicebob) > 0);
		
		assertFalse(Alicebob.compareTo(AliceJimbob) == 0);
		assertFalse(AliceJimbob.compareTo(Alicebob) < 0);
		assertFalse(Alicebob.compareTo(Alicebob) > 0);
	}

	@Test
	public void testClone() 
	{
		StudentPair ClonedAliceBob = new StudentPair(Alicebob);
		
		assertNotSame(ClonedAliceBob , Alicebob);
		
		assertEquals(ClonedAliceBob.toString() , Alicebob.toString());
		assertEquals(ClonedAliceBob.getFirstStudent(), Alicebob.getFirstStudent());
		

		assertEquals("Alice", Alicebob.getFirstStudent());
		assertEquals("Bob", Alicebob.getSecondStudent());
		assertEquals("Alice", ClonedAliceBob.getFirstStudent());
		assertEquals("Bob", ClonedAliceBob.getSecondStudent());
		
		
		assertEquals("Alice", Alicebob.getFirstStudent());
		assertEquals("Bob", Alicebob.getSecondStudent());
		assertEquals("Alice", ClonedAliceBob.getFirstStudent());
		assertEquals("Bob", ClonedAliceBob.getSecondStudent());
		
		
		assertEquals( "0.0" , String.format("%.1f",ClonedAliceBob.getRawScore()));
		assertEquals( "0.0" , String.format("%.1f",ClonedAliceBob.getZScore()));
		
		assertFalse(ClonedAliceBob.isRawScoreCalc());
		assertFalse(ClonedAliceBob.isZScoreCalc());
	}
}
