package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatTest {
    private Animals testAnimal;

    @BeforeEach
    void runBefore(){
        testAnimal = new Cat();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testAnimal.getPoints());
    }

    @Test
    void testReward() {
        testAnimal.reward("apple");
        assertEquals(15, testAnimal.getPoints());
        testAnimal.reward("bone");
        assertEquals(20, testAnimal.getPoints());
        testAnimal.reward("leaf");
        assertEquals(30, testAnimal.getPoints());
        testAnimal.reward("tofu");
        assertEquals(30, testAnimal.getPoints());
    }

    @Test
    void testPrintPointsUnsatisfied() {
        testAnimal.reward("apple");
        testAnimal.reward("bone");

        assertEquals(20, testAnimal.getPoints());
        assertEquals("The current satisfaction is 20 %", testAnimal.printPoints());
    }

    @Test
    void testPrintPointsSatisfied() {
        testAnimal.reward("fish");
        testAnimal.reward("carrot");
        testAnimal.reward("apple");
        testAnimal.reward("bone");
        testAnimal.reward("leaf");
        testAnimal.reward("fish");

        assertEquals(130, testAnimal.getPoints());
        assertEquals("You have reached 100% satisfaction. Satisfied!", testAnimal.printPoints());
    }
}
