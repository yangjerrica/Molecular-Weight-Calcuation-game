package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuokkaTest {
    private Quokka testQuokka;

    @BeforeEach
    void runBefore(){
        testQuokka = new Quokka();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testQuokka.getPoints());
    }

    @Test
    void testReward() {
        testQuokka.reward("apple");
        assertEquals(30,testQuokka.getPoints());
        testQuokka.reward("bone");
        assertEquals(35,testQuokka.getPoints());
        testQuokka.reward("leaf");
        assertEquals(70, testQuokka.getPoints());
        testQuokka.reward("tofu");
        assertEquals(70, testQuokka.getPoints());
    }

    @Test
    void testPrintPointsUnsatisfied() {
        testQuokka.reward("apple");
        testQuokka.reward("bone");

        assertEquals(35,testQuokka.getPoints());
        assertEquals("The current satisfaction is 35 %", testQuokka.printPoints());
    }

    @Test
    void testPrintPointsSatisfied() {
        testQuokka.reward("apple");
        testQuokka.reward("bone");
        testQuokka.reward("carrot");
        testQuokka.reward("leaf");
        testQuokka.reward("fish");

        assertEquals(100, testQuokka.getPoints());
        assertEquals("You have reached 100% satisfaction. Satisfied!", testQuokka.printPoints());
        assertEquals("kee---kee---kee---", testQuokka.sound());
    }
}
