/**
 * @author Yldraziw (Tyler W. Belair - 991561950)
 */

package SDF_WarProject;

import static SDF_WarProject.CardGame.hasPlayer1;
import static org.junit.Assert.*;

/**
 * Test Class - Use Cases implemented for safe Player Name string-literal inputs.
 */

public class CardGameTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void hasPlayer1Good() {
        System.out.println("Player 1 Name");
        String pass = "abcdefgh";
        boolean expResult = true;
        boolean result = hasPlayer1(pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void hasPlayer1Bad() {
        System.out.println("Player 1 Name");
        String pass = "123123";
        boolean expResult = false;
        boolean result = hasPlayer1(pass);
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void hasPlayer1Boundary() {
        System.out.println("Player 1 Name");
        String pass = "aABBBBBccccasdwewe";
        boolean expResult = true;
        boolean result = hasPlayer1(pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void hasPlayer2Good() {
        System.out.println("Player 2 Name");
        String pass = "abcdefgh";
        boolean expResult = true;
        boolean result = hasPlayer1(pass);
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void hasPlayer2Bad() {
        System.out.println("Player 2 Name");
        String pass = "123123";
        boolean expResult = false;
        boolean result = hasPlayer1(pass);
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void hasPlayer2Boundary() {
        System.out.println("Player 2 Name");
        String pass = "aABBBBBccccasdwewe";
        boolean expResult = true;
        boolean result = hasPlayer1(pass);
        assertEquals(expResult, result);
    }

}