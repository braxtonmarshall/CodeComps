package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;


public class TestStudent {

	String testName = "a name";
	int defaultLinesofCode = 0;
	int testLinesofCode = 100;
	int defaultVersion = -1;
	int testVersion = 99;
	Vector<File> blankFileList = new Vector<File>();
	File root = new File("root");
	Student blankStudent = new Student();
	
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();
	
	
	
 	/**
   	* @throws java.lang.Exception
   	*/
  	@Before
  	public void setUp() throws Exception {
  	
  	}

	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#Student()}
	*/
	@Test
	public final void testStudent() {
		Student student = new Student();
		assertEquals("", student.getName());
		assertEquals(null, student.getRoot());
		assertEquals(blankFileList, student.getFileList());
		assertEquals(defaultLinesofCode, student.getLinesofCode());
		assertEquals(defaultVersion, student.getVersion());
		assertEquals(student, blankStudent);
		assertEquals(student.toString(), blankStudent.toString());
	}

	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#Student(java.lang.String)}
	*/
	@Test
	public final void testStudentString() {
		Student student = new Student(testName);
		assertEquals(testName, student.getName());
		assertEquals(null, student.getRoot());
		assertEquals(blankFileList, student.getFileList());
		assertEquals(defaultLinesofCode, student.getLinesofCode());
		assertEquals(defaultVersion, student.getVersion());
		assertNotEquals(student, blankStudent);
		assertNotEquals(student.hashCode(), blankStudent.hashCode());
		String studentoutput = student.toString();
		assertTrue(studentoutput.contains(testName));
		assertTrue(studentoutput.contains("0"));
	}

	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#setName(java.lang.String)}
	*/
	@Test
	public final void testsetName()	{
		Student student = new Student(testName);
		student.setName("a new name");
		assertEquals("a new name", student.getName());
		assertEquals(null, student.getRoot());
		assertEquals(blankFileList, student.getFileList());
		assertEquals(defaultLinesofCode, student.getLinesofCode());
		assertEquals(defaultVersion, student.getVersion());
		assertNotEquals(student, blankStudent);
		assertNotEquals(student.hashCode(), blankStudent.hashCode());
		String studentoutput = student.toString();
		assertTrue(studentoutput.contains("0"));
		assertTrue(studentoutput.contains("a new name"));
	}
	
	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#setRoot(java.io.File)}
	*/
	@Test
	public final void testsetRoot()	{
		Student student = new Student(testName);
		student.setRoot(root);
		assertEquals("a name", student.getName());
		assertNotEquals(null, student.getRoot());
		assertEquals(root, student.getRoot());
		assertEquals(blankFileList, student.getFileList());
		assertEquals(defaultLinesofCode, student.getLinesofCode());
		assertEquals(defaultVersion, student.getVersion());
		assertNotEquals(student, blankStudent);
		assertNotEquals(student.hashCode(), blankStudent.hashCode());
		String studentoutput = student.toString();
		assertTrue(studentoutput.contains("0"));
		assertTrue(studentoutput.contains("a name"));
	}
	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#setLinesofCode(java.lang.Integer)}
	*/
	@Test
	public final void testsetLinesofCode()	{
		Student student = new Student(testName);
		student.setLinesofCode(testLinesofCode);
		assertEquals(testLinesofCode, student.getLinesofCode());
		assertEquals(defaultVersion, student.getVersion());
		assertEquals(testName, student.getName());
		assertEquals(null, student.getRoot());
		assertEquals(blankFileList, student.getFileList());
		assertNotEquals(student, blankStudent);
		assertNotEquals(student.hashCode(), blankStudent.hashCode());
		String studentoutput = student.toString();
		assertTrue(studentoutput.contains("0"));
		assertTrue(studentoutput.contains("100"));
		assertTrue(studentoutput.contains(testName));
	}

	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#setVersion(java.lang.Integer)}
	*/
	@Test
	public final void testsetVersion()	{
		Student student = new Student(testName);
		student.setVersion(testVersion);
		assertEquals(testVersion, student.getVersion());
		assertEquals(testName, student.getName());
		assertEquals(null, student.getRoot());
		assertEquals(blankFileList, student.getFileList());
		assertEquals(defaultLinesofCode, student.getLinesofCode());
		assertNotEquals(student, blankStudent);
		assertNotEquals(student.hashCode(), blankStudent.hashCode());
		String studentoutput = student.toString();
		assertTrue(studentoutput.contains("0"));
		assertTrue(studentoutput.contains(testName));
	}
	
	/**
	*	Test Method for {@link edu.odu.cs.cs350.Student#appendFile(java.io.File)}
	 * @throws IOException 
	*/
	@Test
	public final void testappendFile() throws IOException	{
		Student student = new Student(testName);
		assertEquals(blankFileList, student.getFileList());
		assertEquals(testName, student.getName());
		assertEquals(null, student.getRoot());
		assertEquals(defaultLinesofCode, student.getLinesofCode());
  		File testFileone = testFolder.newFile("testone.txt");
  		File testFiletwo = testFolder.newFile("testtwo.txt");
		student.appendFile(testFileone);
		assertEquals(1, student.getFileList().size());
		student.appendFile(testFileone);
		assertEquals(1, student.getFileList().size());
		student.appendFile(testFiletwo);
		assertEquals(2, student.getFileList().size());

		assertNotEquals(student, blankStudent);
		assertNotEquals(student.hashCode(), blankStudent.hashCode());
		assertNotEquals(student.getFileList(), blankStudent.getFileList());

		Student student0 = new Student(testName);
		assertEquals(student0.hashCode(), student.hashCode());

		String studentoutput = student.toString();
		assertTrue(studentoutput.contains("0"));
		assertTrue(studentoutput.contains("2"));
		assertTrue(studentoutput.contains(testName));

	}

}


    


