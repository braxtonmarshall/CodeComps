package edu.odu.cs.cs350;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class JavaSymbolTest {
    private JavaSymbol instance;
    public JavaSymbolTest() {
     
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
     instance=  new JavaSymbol(42, 1, 1, "IF");
    }
    
    @After
    public void tearDown() {
       
    }

    /**
     * Test of getLine method, of class JavaSymbol.
     */
    @Test
    public void testGetLine() {
        System.out.println("getLine");
        //JavaSymbol instance = null;
        int expResult = 1;
        int result = instance.getLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumn method, of class JavaSymbol.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        //JavaSymbol instance = null;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class JavaSymbol.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        //JavaSymbol instance = null;
        String expResult = "line 1, column 1, sym: 42, value: 'IF'";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
