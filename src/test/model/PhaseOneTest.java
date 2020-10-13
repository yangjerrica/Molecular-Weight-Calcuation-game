package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhaseOneTest {
    private PhaseOne testMolecule;

    @BeforeEach
    void runBefore() {
        testMolecule = new PhaseOne();
    }

    @Test
    public void testConstructor(){
        assertEquals(0, testMolecule.getMolecularWeight());
    }

    @Test
    public void testCalculateWeight(){
        testMolecule.calculateWeight();
        assertEquals(1, testMolecule.getMolecularWeight());
    }
}
