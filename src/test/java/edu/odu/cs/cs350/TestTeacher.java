package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.odu.cs.cs350.Interface.*;
import java.io.File;

public class TestTeacher {

	private Teacher teacher;
	private File submissionDirectory;
	
	public TestTeacher()
	{
		teacher = new Teacher( "ostream" );
		submissionDirectory = new File( "./src/test/data/testSubmissionDirectory" );
	}
	
	@Test
	public void testInstructor() 
	{
		
		teacher = new Teacher( "ostream" );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );	
	}

	@Test
	public void testInstructorInstructor()
	{
		Teacher copyTeacher = new Teacher( teacher );
		assertEquals( teacher , copyTeacher );
		assertNotSame( teacher , copyTeacher );
	}
	
	@Test
	public void testIsTemplateSpecified() 
	{
		teacher.setTemplate( "templateName" );
		assertTrue( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "templateName" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}
	
	@Test
	public void testIsSheetNameSpecified() 
	{
		
		teacher = new Teacher( "ostream" );
		teacher.setSheetName( "sheetName" );
		assertFalse( teacher.isTemplated() );
		assertTrue( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "sheetName" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	} 
	
	@Test
	public void testGetTemplate() 
	{

		teacher = new Teacher( "ostream" );
		teacher.setTemplate( "templateName" );
		assertTrue( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "templateName" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}

	@Test
	public void testGetSheetName() 
	{
		teacher = new Teacher( "ostream" );
		teacher.setSheetName( "sheetName" );
		assertFalse( teacher.isTemplated() );
		assertTrue( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "sheetName" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}

	@Test
	public void testSetTemplate() 
	{
		teacher = new Teacher( "ostream" );
		teacher.setTemplate( "templateName" );
		assertTrue( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "templateName" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}
	
	@Test
	public void testSetSheetName() 
	{
		teacher = new Teacher( "ostream" );
		teacher.setSheetName( "sheetName" );
		assertFalse( teacher.isTemplated() );
		assertTrue( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "sheetName" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}
	
	@Test
	public void testIsSubmissionReceived() 
	{
		teacher = new Teacher( "ostream" );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );

		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}

	@Test
	public void testIsSubmissionParsed() 
	{
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		teacher.parseSubmissionDirectory();
		
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertTrue( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}

	@Test
	public void testIsTokenSequenceAnalyzed() 
	{
		teacher = new Teacher( "output" );
		teacher.acceptStudentSubmission( submissionDirectory );
		teacher.parseSubmissionDirectory();
		
		assertFalse( teacher.isTokenStreamAnalyzed() );
		teacher.analyze();
		assertTrue( teacher.isTokenStreamAnalyzed() );
	}

	@Test
	public void testGetStudent() 
	{
		teacher = new Teacher( "output" );
		teacher.acceptStudentSubmission( submissionDirectory );
		
		Student Alice = teacher.getStudent( "Alice" );
		Student Jimbob = teacher.getStudent( "Jimbob" );
		Student Bob = teacher.getStudent( "Bob" );
		
		assertEquals( "Alice" , Alice.toString() );
		assertEquals( "Jimbob" , Jimbob.toString() );
		assertEquals( "Bob" , Bob.toString() );

		assertTrue( Bob.compareTo(Bob) == 0 );
		assertTrue( Bob.compareTo(Alice) < 0 );
		assertTrue( Bob.compareTo(Jimbob) < 0 );
		assertTrue( Alice.compareTo(Jimbob) < 0 );
		assertTrue( Jimbob.compareTo(Alice) > 0 );		
	}
	
	@Test
	public void testGetStudentPair()
	{
		teacher = new Teacher( "output" );
		teacher.acceptStudentSubmission( submissionDirectory );
		
		assertTrue( teacher.getStudentPair("Alice", "Bob") != null &&
				teacher.getStudentPair("Bob", "Alice") != null &&
						teacher.getStudentPair("Alice", "Bob").equals(teacher.getStudentPair("Bob", "Alice")));
		
		assertTrue( teacher.getStudentPair("Alice", "Jimbob") != null &&
				teacher.getStudentPair("Jimbob", "Alice") != null &&
						teacher.getStudentPair("Alice", "Jimbob").equals(teacher.getStudentPair("Jimbob", "Alice")));
		
		assertTrue( teacher.getStudentPair("Alice", "Josan") == null );
	}
	
	@Test
	public void testGetTotalStudentCount() 
	{
		teacher = new Teacher( "ostream" );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );	

		
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );	
	}
	
	@Test
	public void testGetTotalStudentPairCount() 
	{
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );	
	} 
	
	@Test
	public void testAcceptStudentSubmissions() 
	{
		teacher = new Teacher( "ostream" );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertFalse( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 0 , teacher.getTotalStudentCount() );
		assertEquals( 0 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );

		
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertFalse( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );		
	}
	
	@Test
	public void testParseSubmissions() 
	{
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		teacher.parseSubmissionDirectory();
		
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertTrue( teacher.isSubmissionParsed() );
		assertFalse( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );
	}

	@Test
	public void testAnalyze() 
	{
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		teacher.parseSubmissionDirectory();
		teacher.analyze();
		
		assertFalse( teacher.isTemplated() );
		assertFalse( teacher.isSheetNamed() );
		assertEquals( "" , teacher.getTemplate() );
		assertEquals( "" , teacher.getSheetName() );
		assertTrue( teacher.isSubmissionRecieved() );
		assertTrue( teacher.isSubmissionParsed() );
		assertTrue( teacher.isTokenStreamAnalyzed() );
		assertEquals( 4 , teacher.getTotalStudentCount() );
		assertEquals( 6 , teacher.getTotalStudentPairCount() );
		assertEquals( "ostream" , teacher.toString() );
	}

	@Test
	public void testClone()
	{
		Teacher copyTeacher = ( Teacher ) teacher.clone();
		assertEquals( teacher , copyTeacher );
		assertNotSame( teacher , copyTeacher );
	}
	
	@Test
	public void testEquals()
	{
		teacher = new Teacher( "ostream" );
		teacher.acceptStudentSubmission( submissionDirectory );
		Teacher copyteacher = ( Teacher ) teacher.clone();
		
		assertTrue( teacher.equals(copyteacher) ); 
		teacher.parseSubmissionDirectory();
		assertFalse( teacher.equals(copyteacher) ); 
	}
	
	@Test
	public void testToString() 
	{
		teacher = new Teacher( "ostream" );
		assertEquals( "ostream" , teacher.toString() );
	
		teacher = new Teacher( "testOutput" );
		assertEquals( "testOutput" , teacher.toString() );
	}
}

