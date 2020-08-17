package edu.odu.cs.cs350;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class JavaParserTest {
    JavaParser instance;
    public JavaParserTest() {
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
     * Test of parse method, of class JavaParser.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        File src = null;
        instance = new JavaParser();
        String expResult = "";
        String result = instance.parse(src);
        assertEquals(expResult, result);
    }
    
}
