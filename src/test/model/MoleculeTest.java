package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoleculeTest {
    private Molecule testMolecule;

    @BeforeEach
    void runBefore() {
        testMolecule = new Molecule();
    }

    @Test
    public void testConstructor(){
        assertEquals(0, testMolecule.getMolecularWeight());
    }

    @Test
    public void testCalculateWeight(){
        testMolecule.calculateMolecularWeight();
        assertEquals(1, testMolecule.getMolecularWeight());
    }
}
