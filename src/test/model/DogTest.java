package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DogTest {
    private Dog testDog;

    @BeforeEach
    void runBefore(){
        testDog = new Dog();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDog.getPoints());
    }

    @Test
    void testReward() {
        testDog.reward("apple");
        assertEquals(30,testDog.getPoints());
        testDog.reward("bone");
        assertEquals(55,testDog.getPoints());
        testDog.reward("leaf");
        assertEquals(60,testDog.getPoints());
        testDog.reward("tofu");
        assertEquals(60, testDog.getPoints());

    }

    @Test
    void testPrintPointsUnsatisfied() {
        testDog.reward("apple");
        testDog.reward("bone");

        assertEquals(55,testDog.getPoints());
        assertEquals("The current satisfaction is 55 %", testDog.printPoints());
    }

    @Test
    void testPrintPointsSatisfied() {
        testDog.reward("apple");
        testDog.reward("bone");
        testDog.reward("carrot");
        testDog.reward("leaf");
        testDog.reward("fish");
        testDog.reward("apple");

        assertEquals(120, testDog.getPoints());
        assertEquals("You have reached 100% satisfaction. Satisfied!", testDog.printPoints());
        assertEquals("woof! woof! woof! ", testDog.sound());
    }
}
