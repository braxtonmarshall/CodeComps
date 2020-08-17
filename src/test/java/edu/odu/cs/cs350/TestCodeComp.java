package edu.odu.cs.cs350;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * @author braxton marshall
 * @author hannah.holloway
 */


public class TestCodeComp {
    
	
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();
	public TemporaryFolder testFolderone = new TemporaryFolder();
	private File testDir;
	private File testdirone;
	private File testFileone;
	private File testFileoneone;
	private File testFileonetwo;
	private File testFiletwo;
	private File testFiletwoone;
	private File testFiletwotwo;
	private File testFilethree;
	private File testFilethreeone;
	private File testFilethreetwo;
	private File testFilethreethree;
	private File testFilefour;
	private File testFilefourone;
	private File testFilefourtwo;
	private File testFilefive;
	private File testFilefiveone;
	private int loc;
	private File tmp;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
 	/**
   	* @throws java.lang.Exception
   	*/
  	@Before
  	public void setUp() throws Exception {
  		
  		// System Output
  		//System.setOut(originalOut);
  	    //System.setErr(originalErr);
 
  			
  		// STUDENT TWO FILES
  	
  		testFiletwoone = new File(testFiletwo, "foo.cpp");
  		testFiletwotwo = new File(testFiletwo, "baz.h");
  			
  		// STUDENT THREE FILES
  	
  		File testFilethreepackage = new File(testFilethree, "package");
  		testFilethreepackage.mkdir();
  		File testFilethreesubpackageone = new File(testFilethreepackage, "subpackage1");
  		testFilethreesubpackageone.mkdir();
  		File testFilethreesubpackagetwo = new File(testFilethreepackage, "subpackage2");
  		testFilethreesubpackagetwo.mkdir();
  		testFilethreeone = new File(testFilethreesubpackageone, "Foo.java");
  		testFilethreetwo = new File(testFilethreesubpackagetwo, "Bar.java");
  		testFilethreethree = new File(testFilethreesubpackagetwo, "Baz.java");
  			
  		// STUDENT FOUR FILES
  		
  		File testFilefourpackage = new File(testFilefour, "package");
  		testFilefourpackage.mkdir();
  		File testFilefoursubpackage = new File(testFilefourpackage, "subpackage");
  		testFilefoursubpackage.mkdir();
  		testFilefourone = new File(testFilefoursubpackage, "FooBar.java");
  		testFilefourtwo = new File(testFilefoursubpackage, "Baz.java");
  			
  
  		testFilefiveone = new File(testFilefive, "Foo.java");
  		

  	}
	/**
	 * Test method for {@link edu.odu.cs.cs350.CodeComp#FindAllStudents()}.
	 * @throws IOException 
	 */
	@Test
	public final void testFindAllStudents() throws IOException {
		
		// SETUP TEST FILE STRUCTURE
  		testDir = testFolder.getRoot();
  		// STUDENT ONE FILES
  		testFileone = testFolder.newFolder("jdoe.0");
  		// STUDENT TWO FILES
  		testFiletwo = testFolder.newFolder("jdoe.1");
  		// STUDENT THREE FILES
  		testFilethree = testFolder.newFolder("ssmith");
  		// STUDENT FOUR FILES
  		testFilefour = testFolder.newFolder("ssmith.99");
		// STUDENT FIVE FILES
		testFilefive = testFolder.newFolder("jdoe.99");
		
		assertTrue(testDir.isDirectory());
		assertTrue(testFileone.isDirectory());
		assertTrue(testFiletwo.isDirectory());
		assertTrue(testFilethree.isDirectory());
		assertTrue(testFilefour.isDirectory());
		assertTrue(testFilefive.isDirectory());
		CodeComp codecomp = new CodeComp();
  		codecomp.setRootDirectory(testDir);
		codecomp.FindAllStudents();
		Vector<Student> testStudentList = new Vector<Student>();
		testStudentList = codecomp.getStudentList();
		assertNotNull(testStudentList);
		assertEquals(testStudentList.size(), 2);
		assertEquals(testStudentList.get(0).getName(), "jdoe");
		assertEquals(testStudentList.get(0).getVersion(), 99);
		assertEquals(testStudentList.get(1).getName(), "ssmith");
		assertEquals(testStudentList.get(1).getVersion(), -1);
		
	}
	/**
	 * Test method for {@link edu.odu.cs.cs350.CodeComp#listSourceFiles(java.lang.String)}
	 * @throws IOException 
	 * 
	 */
	@Test
	public final void testlistSourceFiles() throws IOException {
		
		// Student One Folder
		File tempfldrone = new File("./src/jdoe");
		tempfldrone.mkdir();
		File child11 = File.createTempFile("foo", ".cpp", tempfldrone);
		File child12 = File.createTempFile("bar", ".cpp", tempfldrone);
  		
		// Student Two Folder
		File tempfldrtwo = new File("./src/jdoe.1");
		tempfldrtwo.mkdir();
		File child21 = File.createTempFile("foo", ".cpp", tempfldrtwo);
		File child22 = File.createTempFile("baz", ".baz", tempfldrtwo);
		
		// Student Three Folder
		File tempfldrthree = new File("./src/ssmith/package");
		tempfldrthree.mkdirs();
		File tempfldrthree1 = new File("./src/ssmith/package/subpackage1");
		tempfldrthree1.mkdir();
		File child31 = File.createTempFile("foo", ".java", tempfldrthree1);
		File tempfldrthree2 = new File("./src/ssmith/package/subpackage2");
		tempfldrthree2.mkdir();
		File child32 = File.createTempFile("Bar", ".java", tempfldrthree2);
		File child33 = File.createTempFile("Baz", ".java", tempfldrthree2);
		
		assertTrue(tempfldrone.isDirectory());
		assertTrue(tempfldrtwo.isDirectory());
		assertTrue(tempfldrthree.isDirectory());
		assertTrue(tempfldrthree1.isDirectory());
		assertTrue(tempfldrthree2.isDirectory());
		assertTrue(child11.exists());
		assertTrue(child12.exists());
		assertTrue(child21.exists());
		assertTrue(child22.exists());
		assertTrue(child31.exists());
		assertTrue(child32.exists());
		assertTrue(child33.exists());
		
  		// SETUP STUDENTS
  		Student studentone = new Student();
  		studentone.setRoot(tempfldrone);
  		Student studenttwo = new Student();
  		studenttwo.setRoot(tempfldrtwo);
  		Student studentthree = new Student();
  		studentthree.setRoot(tempfldrthree);
  		
  		
		CodeComp codecomp = new CodeComp();
		// Sub-directory One
  		codecomp.listSrcFiles(studentone);
  		assertEquals(codecomp.getsrcList().size(), 2);
  		assertFalse(codecomp.getsrcList().contains(tempfldrone));
  		assertTrue(codecomp.getsrcList().contains(child11));
  		assertTrue(codecomp.getsrcList().contains(child12));
  		
  		// Sub-directory Two
  		codecomp.listSrcFiles(studenttwo);
  		assertEquals(codecomp.getsrcList().size(), 2);
  		assertFalse(codecomp.getsrcList().contains(tempfldrtwo));
  		assertTrue(codecomp.getsrcList().contains(child21));
  		assertTrue(codecomp.getsrcList().contains(child22));
  		
  		// Sub-directory Three
  		codecomp.listSrcFiles(studentthree);
  		assertEquals(codecomp.getsrcList().size(), 3);
  		assertFalse(codecomp.getsrcList().contains(tempfldrthree));
  		assertTrue(codecomp.getsrcList().contains(child31));
  		assertTrue(codecomp.getsrcList().contains(child32));
  		assertTrue(codecomp.getsrcList().contains(child33));
  		
  		child12.deleteOnExit();
  		child11.deleteOnExit();
  		tempfldrone.deleteOnExit();
  		child22.deleteOnExit();
  		child21.deleteOnExit();
  		tempfldrtwo.deleteOnExit();
  		child33.deleteOnExit();
  		child32.deleteOnExit();
  		child31.deleteOnExit();
  		tempfldrthree1.deleteOnExit();
  		tempfldrthree2.deleteOnExit();
  		tempfldrthree.delete();
  		
	}
	/**
	 * Test method for {@link edu.odu.cs.cs350.CodeComp#countLinesOfCode(java.io.File)}
	 * @throws IOException 
	 */
	@Test
	public final void testcountLinesOfCode() throws IOException {
		
		loc = 0;
		CodeComp codecomp = new CodeComp();
		File file = File.createTempFile( "temp", ".txt", new File("./src"));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	    bw.write("This is the temporary file content");
	    bw.newLine();
	    bw.write("This is line 2");
	    bw.close();
	    loc = codecomp.countLinesOfCode(file);
	    assertEquals(loc, 2);
	    file.deleteOnExit();
	}
    /**
     * Test of main method, of class CodeComp.
     */
    @Test
    public final void testMain() {
        String str="-h help -d rootdirectory";
        String[] args = str.split(" ");
        CodeComp.main(args);
        assertEquals("missing -h",args[0],"-h");
        assertEquals("missing help",args[1],"help");
        assertEquals("missing -d",args[2],"-d");
        assertEquals("missing rootdirectory",args[3],"rootdirectory");
    }
    /**
     * Test of setRootDirectory method, of class CodeComp.
     */
    @Test
    public final void testSetRootDirectory() {
        CodeComp instance = new CodeComp();
        StringBuffer sb=new StringBuffer("java -jar filename -d rootdirectoryname");
        String name=sb.substring(sb.indexOf("d"),sb.length());
        String filename="";
        filename=name.substring(name.indexOf(" "),name.length());
        filename=filename.replace("\\","\\\\");  
        instance.setRootDirectory( new File(filename.trim()));
        File expResult = new File(filename.trim());
        File result = instance.getRootDirectory();
        assertEquals(expResult, result);
    }
    

}
