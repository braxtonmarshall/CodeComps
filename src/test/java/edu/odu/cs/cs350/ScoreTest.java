package edu.odu.cs.cs350;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreTest {
    
    public ScoreTest() {
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
     * Test of score method, of class Score.
     */
    @Test
    public void testScore() {
        System.out.println("score");
        Student s1 = new Student();
        Student s2 = new Student();
        Score instance = new Score();
        instance.score(s1, s2);
    }

    /**
     * Test of getRawScore method, of class Score.
     */
    @Test
    public void testGetRawScore() {
        System.out.println("getRawScore");
        Score instance = new Score();
        Double expResult = 0.0;
        Double result = instance.getRawScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRawScore method, of class Score.
     */
    @Test
    public void testSetRawScore() {
        System.out.println("setRawScore");
        Double rawScore = 0.0;
        Score instance = new Score();
        instance.setRawScore(rawScore);
    }

    /**
     * Test of getAvg method, of class Score.
     */
    @Test
    public void testGetMean() {
        System.out.println("getMean");
        Score instance = new Score();
        Double expResult = 0.0;
        Double result = instance.getMean();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAvg method, of class Score.
     */
    @Test
    public void testSetAvg() {
        System.out.println("setMean");
        Double avg = 1.0;
        Score instance = new Score();
        instance.setMean(avg);
        Double result = instance.getMean();
        assertEquals(avg,result);
    }

    /**
     * Test of getZscore method, of class Score.
     */
    @Test
    public void testGetZscore() {
        System.out.println("getZscore");
        Score instance = new Score();
        Double expResult =0.0;
        Double result = instance.getZscore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setZscore method, of class Score.
     */
    @Test
    public void testSetZscore() {
        System.out.println("setZscore");
        Double zscore = 1.0;
        Score instance = new Score();
        instance.setZscore(zscore);
        Double result=instance.getZscore();
        assertEquals(zscore,result);
    }

    /**
     * Test of getStandardDev method, of class Score.
     */
    @Test
    public void testGetStandardDev() {
        System.out.println("getStandardDev");
        Score instance = new Score();
        Double expResult = 0.0;
        Double result = instance.getStandardDev();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStandardDev method, of class Score.
     */
    @Test
    public void testSetStandardDev() {
        System.out.println("setStandardDev");
        Double standardDev = 1.0;
        Score instance = new Score();
        instance.setStandardDev(standardDev);
        Double result=instance.getStandardDev();
        assertEquals(standardDev,result);
    }
    
}
