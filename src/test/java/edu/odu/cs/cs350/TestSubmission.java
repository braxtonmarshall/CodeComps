package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import edu.odu.cs.cs350.Interface.*;
import java.util.ArrayList;

public class TestSubmission {

	private Teacher teacher;
	private File submissionDirectory;
	
	private Submission Alicesub;
	private Submission Bobsub;
	private Submission Jimbobsub;
	
	public TestSubmission()
	{
		teacher = new Teacher( "ostream" );
		
		submissionDirectory = new File( "./src/test/data/testSubmissionDirectory" );
		teacher.acceptStudentSubmission(submissionDirectory);
		
		// Initialize Submissions
		Alicesub = teacher.getStudent("Alice").getPrioritySubmission();
		Bobsub = teacher.getStudent("Bob").getPrioritySubmission();
		Jimbobsub = teacher.getStudent("Jimbob").getPrioritySubmission();
	}
	
	@Test
	public void testSubmission() 
	{
		assertFalse( Alicesub.isTokenized() );
		assertEquals( 1 , Alicesub.getTotalSrcFiles() );
		assertEquals( 13 , Alicesub.getTotalLOC() );
		assertEquals( "Alice.1" , Alicesub.toString() );
		
		assertFalse ( Bobsub.isTokenized() );
		assertEquals( 2 , Bobsub.getTotalSrcFiles() );
		assertEquals( 56 , Bobsub.getTotalLOC() );
		assertEquals( "Bob" , Bobsub.toString() );
		
		assertFalse ( Jimbobsub.isTokenized() );
		assertEquals( 2 , Jimbobsub.getTotalSrcFiles() );
		assertEquals( 171 , Jimbobsub.getTotalLOC() );
		assertEquals( "Jimbob" , Jimbobsub.toString() );	
	}

	@Test
	public void testSubmissionSubmission() 
	{
		Submission clonedBob = new Submission( Bobsub );
		
		assertEquals( clonedBob , Bobsub );
		assertNotSame( clonedBob , Bobsub );
		
		assertEquals( clonedBob.isTokenized() , Bobsub.isTokenized() );
		assertEquals( clonedBob.getTotalSrcFiles() , Bobsub.getTotalSrcFiles() );
		assertEquals( clonedBob.getTotalLOC() , Bobsub.getTotalLOC() );
		assertEquals( clonedBob.getsrcFiles() , Bobsub.getsrcFiles() );
		assertEquals( clonedBob.getTokenCount() , Bobsub.getTokenCount() );
		assertEquals( clonedBob.getTokenStream().toString() , Bobsub.getTokenStream().toString() );
		assertEquals( clonedBob.toString() , Bobsub.toString() );
	}

	@Test
	public void testFindAllSrcFiles() 
	{	
		ArrayList<SourceFile> allFiles = Alicesub.getsrcFiles();
		
		File file1 = new File( "./src/test/data/testSubmissionDirectory/Alice.1/code4.cpp" );
		File file2 = new File( "./src/test/data/testSubmissionDirectory/Alice.1/subDir/codeFile.c" );
		File file3 = new File( "./src/test/data/testSubmissionDirectory/Alice.1/subDir/codeFile1.java" );
		File file4 = new File( "./src/test/data/testSubmissionDirectory/Alice.1/subDir/nat15.java" );
		File file5 = new File( "./src/test/data/testSubmissionDirectory/Alice.1/subDir/subDir/codeFile3.cpp" );
		File file6 = new File( "./src/test/data/testSubmissionDirectory/Alice.1/subDir/subDir/nat20.java" );
		
		assertTrue( allFiles.contains(file1) );
		assertTrue( allFiles.contains(file2) );
		assertTrue( allFiles.contains(file3) );
		assertTrue( allFiles.contains(file4) );
		assertTrue( allFiles.contains(file5) );
		assertTrue( allFiles.contains(file6) );
		assertEquals( 6 , allFiles.size() );	
	}
	
	@Test
	public void testgetsrcFiles() 
	{
		assertEquals( 1 , Alicesub.getsrcFiles().size() );
		assertEquals( 2 , Bobsub.getsrcFiles().size() );
		assertEquals( 2 , Jimbobsub.getsrcFiles().size() );
	}
	
	@Test
	public void testTokenizeSubmission() 
	{
		assertFalse( Alicesub.isTokenized() );
		assertFalse( Bobsub.isTokenized() );
		assertFalse( Jimbobsub.isTokenized() );
		
		Submission clonedAlice = new Submission( Alicesub );
		Submission clonedBob = new Submission( Bobsub );
		Submission clonedJimbob = new Submission( Jimbobsub );
		
		clonedAlice.tokenizeSubmission();
		clonedBob.tokenizeSubmission();
		clonedJimbob.tokenizeSubmission();
		
		assertTrue( clonedAlice.isTokenized() );
		assertTrue( clonedBob.isTokenized() );
		assertTrue( clonedJimbob.isTokenized() );
	}

	@Test
	public void testgetTokenCount() 
	{
		Submission clonedAlice = new Submission( Alicesub );
		clonedAlice.tokenizeSubmission();
		
		Submission clonedBob = new Submission( Bobsub );
		clonedBob.tokenizeSubmission();
		
		Submission clonedJimbob = new Submission( Jimbobsub );
		clonedJimbob.tokenizeSubmission();
		
		assertEquals( 21 , clonedAlice.getTokenCount() );
		assertEquals( 269 , clonedBob.getTokenCount() );
		assertEquals( 611 , clonedJimbob.getTokenCount() );
	}

	@Test
	public void testGetTokenStream() 
	{
		Submission clonedAlice = new Submission( Alicesub );
		clonedAlice.tokenizeSubmission();
		
		Submission clonedBob = new Submission( Bobsub );
		clonedBob.tokenizeSubmission();
		
		Submission clonedJimbob = new Submission( Jimbobsub );
		clonedJimbob.tokenizeSubmission();
		
		assertEquals( 21 , clonedAlice.getTokenStream().length() );
		assertEquals( 269 , clonedBob.getTokenStream().length() );
		assertEquals( 611 , clonedJimbob.getTokenStream().length() );
	}
	
	@Test
	public void testgetTotalLOC() 
	{
		assertEquals( 13 , Alicesub.getTotalLOC() );
		assertEquals( 56 , Bobsub.getTotalLOC() );
		assertEquals( 171 , Jimbobsub.getTotalLOC() );		
	}

	@Test
	public void testCompareTo() 
	{
		Submission JimBob = teacher.getStudent("Jimbob").getSubmission("Jimbob");
		Submission JimBob1 = teacher.getStudent("Jimbob").getSubmission("Jimbob.1");
		Submission JimBob2 = teacher.getStudent("Jimbob").getSubmission("Jimbob.2");
		
		assertTrue( JimBob.compareTo(JimBob) == 0 );
		assertTrue( JimBob.compareTo(JimBob1) < 0 );
		assertTrue( JimBob.compareTo(JimBob2) < 0 );
		
		assertTrue( JimBob1.compareTo(JimBob) > 0 );
		assertTrue( JimBob1.compareTo(JimBob1) == 0 );
		assertTrue( JimBob1.compareTo(JimBob2) < 0 );
		
		assertTrue( JimBob2.compareTo(JimBob) > 0 );
		assertTrue( JimBob2.compareTo(JimBob1) > 0 );
		assertTrue( JimBob2.compareTo(JimBob2) == 0 );	
	}

	@Test
	public void testToString() 
	{
		assertEquals( "Alice.1" , Alicesub.toString() );
		assertEquals( "Bob" , Bobsub.toString() );
		assertEquals( "Jimbob" , Jimbobsub.toString() );
	}
	
	@Test
	public void testEquals() 
	{
		assertTrue( Alicesub.equals(Alicesub) );
		assertFalse( Alicesub.equals(Bobsub) );
		assertFalse( Alicesub.equals(Jimbobsub) );
		
		assertTrue( Bobsub.equals(Bobsub) );
		assertFalse( Bobsub.equals(Alicesub) );
		assertFalse( Bobsub.equals(Jimbobsub) );
		
		assertTrue( Jimbobsub.equals(Jimbobsub) );
		assertFalse( Jimbobsub.equals(Alicesub) );
		assertFalse( Jimbobsub.equals(Bobsub) );	
	}
	
	@Test
	public void testClone() 
	{
		Submission clonedAlice = (Submission) Alicesub.clone();
		
		assertEquals( clonedAlice , Alicesub);
		assertNotSame( clonedAlice , Alicesub );
		
		assertEquals( clonedAlice.isTokenized() , Alicesub.isTokenized() );
		assertEquals( clonedAlice.getTotalSrcFiles() , Alicesub.getTotalSrcFiles() );
		assertEquals( clonedAlice.getTotalLOC() , Alicesub.getTotalLOC() );
		assertEquals( clonedAlice.getsrcFiles() , Alicesub.getsrcFiles() );
		assertEquals( clonedAlice.getTokenCount() , Alicesub.getTokenCount() );
		assertEquals( clonedAlice.getTokenStream().toString() , Alicesub.getTokenStream().toString() );
		assertEquals( clonedAlice.toString() , Alicesub.toString() );
	}
}

