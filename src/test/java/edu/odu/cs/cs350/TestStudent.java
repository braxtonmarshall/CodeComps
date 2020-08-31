package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import java.io.File;
import edu.odu.cs.cs350.Interface.*;
import org.junit.Test;

public class TestStudent
{
	private Teacher teacher;
	private File submissionDir;
	private Student Bob;
	private Student Jimbob;
	private Student Alice;
	
	public TestStudent()
	{
		teacher = new Teacher("ostream");
		
		submissionDir = new File("./src/test/data/submissionsTest");
		teacher.acceptStudentSubmission(submissionDir);
		
		Alice = teacher.getStudent("Alice");
		Bob = teacher.getStudent("Bob");
		Jimbob = teacher.getStudent("Jimbob");
		
	}
	
	@Test
	public void testStudent()
	{
		assertEquals( "Alice" , Alice.toString() );
		assertTrue( Alice.equals(Alice) );
		assertFalse( Alice.equals(Bob) );
		assertFalse( Alice.equals(Jimbob) );
		assertEquals( 1 , Alice.getTotalSubmissions() );
		assertEquals( "Alice.1" , Alice.getPrioritySubmission().toString() );
		assertEquals( 2 , Alice.getTotalFileCount() );
		assertEquals( 13 , Alice.getTotalLineCount() );
				
		assertEquals( "Bob" , Bob.toString() );
		assertTrue( Bob.equals(Bob) );
		assertFalse( Bob.equals(Alice) );
		assertFalse( Bob.equals(Jimbob) );
		assertEquals( 2 , Bob.getTotalSubmissions() );
		assertEquals( "Bob" , Bob.getPrioritySubmission().toString() );
		assertEquals( 2 , Bob.getTotalFileCount() );
		assertEquals( 56 , Bob.getTotalLineCount() );
				
		assertEquals( "Jimbob" , Jimbob.toString() );
		assertTrue( Jimbob.equals(Jimbob) );
		assertFalse( Jimbob.equals(Alice) );
		assertFalse( Jimbob.equals(Bob) );
		assertEquals( 3 , Jimbob.getTotalSubmissions() );
		assertEquals( "Jimbob" , Jimbob.getPrioritySubmission().toString() );
		assertEquals( 2 , Jimbob.getTotalFileCount() );
		assertEquals( 171 , Jimbob.getTotalLineCount() );
				
	}
	
	@Test
	public void testStudentStudent() 
	{
		Student clonedAlice = new Student( Alice );
		assertEquals( clonedAlice , Alice);
		
		// Make sure that they aren't referring to same object
		assertNotSame( clonedAlice, Alice );
		
		// Verify that two objects have the same state
		assertEquals( clonedAlice.toString() , Alice.toString() );
		assertEquals( clonedAlice.getTotalSubmissions() , Alice.getTotalSubmissions() );
		assertEquals( clonedAlice.getPrioritySubmission() , Alice.getPrioritySubmission() );
		assertEquals( clonedAlice.getTotalFileCount() , Alice.getTotalFileCount() );
		assertEquals( clonedAlice.getTotalLineCount() , Alice.getTotalLineCount() );
		assertTrue( clonedAlice.equals(Alice) );
	}

	@Test
	public void testAddSubmission() 
	{
		assertEquals( "Alice" , Alice.toString() );
		assertTrue( Alice.equals(Alice) );
		assertFalse( Alice.equals(Bob) );
		assertFalse( Alice.equals(Jimbob) );
		assertEquals( 1 , Alice.getTotalSubmissions() );
		assertEquals( "Alice.1" , Alice.getPrioritySubmission().toString() );
		assertEquals( 1 , Alice.getTotalFileCount() );
		assertEquals( 13 , Alice.getTotalLineCount() );
		
		assertEquals( "Bob" , Bob.toString() );
		assertTrue( Bob.equals(Bob) );
		assertFalse( Bob.equals(Alice) );
		assertFalse( Bob.equals(Jimbob) );
		assertEquals( 2 , Bob.getTotalSubmissions() );
		assertEquals( "Bob" , Bob.getPrioritySubmission().toString() );
		assertEquals( 2 , Bob.getTotalFileCount() );
		assertEquals( 56 , Bob.getTotalLineCount() );
		
		assertEquals( "Jimbob" , Jimbob.toString() );
		assertTrue( Jimbob.equals(Jimbob) );
		assertFalse( Jimbob.equals(Alice) );
		assertFalse( Jimbob.equals(Bob) );
		assertEquals( 3 , Jimbob.getTotalSubmissions() );
		assertEquals( "Jimbob" , Jimbob.getPrioritySubmission().toString() );
		assertEquals( 2 , Jimbob.getTotalFileCount() );
		assertEquals( 171 , Jimbob.getTotalLineCount() );
	}
	
	@Test
	public void testGetSubmission() 
	{
		assertEquals( null , Alice.getSubmission("ThisDoesNotExist") );
		assertEquals( null , Bob.getSubmission("ThisDoesNotExist") );
		assertEquals( null , Jimbob.getSubmission("ThisDoesNotExist") );
		
		assertEquals( Alice.getPrioritySubmission() , Alice.getSubmission("Asa.1") );
		assertEquals( Bob.getPrioritySubmission() , Bob.getSubmission("Bob") );
		assertEquals( Jimbob.getPrioritySubmission() , Jimbob.getSubmission("Jimbob") );
	}
	
	@Test
	public void testGetSubmissionCount() 
	{
		assertEquals( 1 , Alice.getTotalSubmissions() );
		assertEquals( 2 , Bob.getTotalSubmissions() );
		assertEquals( 3 , Jimbob.getTotalSubmissions() );
	}
	
	@Test
	public void testGetPrioritySubmission() 
	{
		assertEquals( "Alice.1" , Alice.getPrioritySubmission().toString() );
		assertEquals( "Bob" , Bob.getPrioritySubmission().toString() );
		assertEquals( "Jimbob" , Jimbob.getPrioritySubmission().toString() );
	}
	
	@Test
	public void testGetTotalCodeFileCount() 
	{
		assertEquals( 1 , Alice.getTotalFileCount() );
		assertEquals( 2 , Bob.getTotalFileCount() );
		assertEquals( 2 , Jimbob.getTotalFileCount() );	
	}
	
	@Test
	public void testGetTotalCodeLineCount() 
	{
		assertEquals( 13 , Alice.getTotalLineCount() );
		assertEquals( 56 , Bob.getTotalLineCount());
		assertEquals( 171 , Jimbob.getTotalLineCount());
	}
	
	@Test
	public void testGetTokenSequence() 
	{
		// Parse the codes
		teacher.parseSubmissionDirectory();
		
		StringBuilder aliceSeq = Alice.getTokenStream();
		StringBuilder bobSeq = Bob.getTokenStream();
		StringBuilder jimbobSeq = Jimbob.getTokenStream();

		assertTrue( 0 < aliceSeq.length() );
		assertTrue( 0 < bobSeq.length() );
		assertTrue( 0 < jimbobSeq.length() );
	}
	
	@Test
	public void testEqualsObject() 
	{

		assertTrue( Alice.equals(Alice) );
		assertFalse( Alice.equals(Bob) );
		assertFalse( Alice.equals(Jimbob) );
		
		assertTrue( Bob.equals(Bob) );
		assertFalse( Bob.equals(Alice) );
		assertFalse( Bob.equals(Jimbob) );
		
		assertTrue( Jimbob.equals(Jimbob) );
		assertFalse( Jimbob.equals(Alice) );
		assertFalse( Jimbob.equals(Bob) );
	}

	@Test
	public void testCompareTo() 
	{
		assertTrue( Alice.compareTo(Alice) == 0 );
		assertTrue( Alice.compareTo(Bob) < 0 );
		assertTrue( Alice.compareTo(Jimbob) < 0 );
		
		assertTrue( Bob.compareTo(Alice) > 0 );
		assertTrue( Bob.compareTo(Bob) == 0 );
		assertTrue( Bob.compareTo(Jimbob) < 0 );
		
		assertTrue( Jimbob.compareTo(Alice) > 0 );
		assertTrue( Jimbob.compareTo(Bob) > 0 );
		assertTrue( Jimbob.compareTo(Jimbob) == 0 );
	}

	@Test
	public void testToString() 
	{
		assertEquals( "Alice" , Alice.toString() );

		assertEquals( "Bob" , Bob.toString() );
		
		assertEquals( "Jimbob" , Jimbob.toString() );	
	}

	@Test
	public void testClone() 
	{
		Student clonedJimbob = (Student) Jimbob.clone();
		assertEquals( clonedJimbob , Jimbob);
		
		// Make sure that they aren't referring to same object (Ensure that object has been deep-copied)
		assertNotSame( clonedJimbob , Jimbob );
		
		// Verify that two objects have the same state
		assertEquals( clonedJimbob.toString() , Jimbob.toString() );
		assertEquals( clonedJimbob.getTotalSubmissions() , Jimbob.getTotalSubmissions() );
		assertEquals( clonedJimbob.getPrioritySubmission() , Jimbob.getPrioritySubmission() );
		assertEquals( clonedJimbob.getTotalFileCount() , Jimbob.getTotalFileCount() );
		assertEquals( clonedJimbob.getTotalLineCount() , Jimbob.getTotalLineCount() );
		assertTrue( clonedJimbob.equals(Jimbob) );
	}
	
}