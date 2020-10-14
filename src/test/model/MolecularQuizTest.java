package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MolecularQuizTest {
    private MolecularQuiz testMolecularQuiz;

    @BeforeEach
    void runBefore(){
    testMolecularQuiz = new MolecularQuiz();
    }

    @Test
    void testGetRandomMoleculeIsInList(){
        System.out.println(testMolecularQuiz.list.size());
        assertTrue(testMolecularQuiz.list.contains(testMolecularQuiz.getRandomMolecule().getFormula()));
    }


}
