package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoleculeTest {
    private Molecule testMolecule;
    private Molecule testMolecule2;
    private Molecule testMolecule3;
    private Molecule testMolecule4;

    @BeforeEach
    void runBefore() {
        testMolecule = new Molecule("H2O");
        testMolecule2 = new Molecule("CH3COOH");
        testMolecule3 = new Molecule("NO");
        testMolecule4 = new Molecule("SF6");

    }

    @Test
    public void testConstructor(){
        assertEquals(18, testMolecule.getMolarMass());
        assertEquals(60,testMolecule2.getMolarMass());
        assertEquals(30, testMolecule3.getMolarMass());
        assertEquals(146, testMolecule4.getMolarMass());

        assertEquals("H2O", testMolecule.getFormula());
        assertEquals("NO",testMolecule3.getFormula());
    }




}
