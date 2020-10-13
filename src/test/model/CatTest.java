package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatTest {
    private Cat testCat;

    @BeforeEach
    void runBefore(){
        testCat = new Cat();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCat.getPoints());
    }

    @Test
    void testAward() {
        testCat.reward("apple");
        assertEquals(15,testCat.getPoints());
        testCat.reward("bone");
        assertEquals(20,testCat.getPoints());
    }

    @Test
    void testPrintPointsUnsatisfied() {
        testCat.reward("apple");
        testCat.reward("bone");

        assertEquals(20,testCat.getPoints());
        assertEquals("The current satisfaction is 20 %", testCat.printPoints());
    }

    @Test
    void testPrintPointsSatisfied() {
        testCat.reward("fish");
        testCat.reward("fish");
        testCat.reward("apple");
        testCat.reward("bone");

        assertEquals(100, testCat.getPoints());
        assertEquals("Satisfied!", testCat.printPoints());
    }
}
