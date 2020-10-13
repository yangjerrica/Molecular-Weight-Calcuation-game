package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoleculeTest {
    private Molecule testMolecule;

    @BeforeEach
    void runBefore() {
        testMolecule = new Molecule("H2O");
    }

    @Test
    public void testConstructor(){
        assertEquals(18, testMolecule.getMolarMass());
    }

}
