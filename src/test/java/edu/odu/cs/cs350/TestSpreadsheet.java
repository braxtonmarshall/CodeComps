package edu.odu.cs.cs350;

import java.io.File;
import java.util.Date;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.odu.cs.cs350.SpreadSheet;
import edu.odu.cs.cs350.Student;

import static org.junit.Assert.*;


public class TestSpreadsheet {

	    public void SpreedSheetTest() {
	    }
	    
	    @BeforeClass
	    public static void setUpClass() {
	    }
	    
	    @AfterClass
	    public static void tearDownClass() {
	    }
	    
	    @Before
	    public void setUp() {
	    }
	    
	    @After
	    public void tearDown() {
	    }

	    /**
	     * Test of report method, of class SpreedSheet.
	     */
	    @Test
	    public void testReport() {
	        System.out.println("report");
	        Date d=new Date();
	        Vector<Student> studentlist = new Vector<Student>();
	        Student s1=new Student();
	        //s1.getFileList().add(new File("test.java"));
	        Student s2=new Student();
	        //s2.getFileList().add(new File("test.java"));
	        s1.setName("Student1");
	        s2.setName("Student2");
	        SpreadSheet instance = new SpreadSheet();
	        instance.report(studentlist);
	        File f=new File("report.xls");
	        Date td=new Date(f.lastModified());
	        d.setSeconds(0);
	        td.setSeconds(0);
	        d.setMinutes(0);
	        assertEquals("Timestamp does not match",d.toString(),td.toString());
	    }
	    
	}
