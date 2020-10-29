package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoleculeListTest {
    private MoleculeList moleculeList;

    @BeforeEach
    public void beforeEach() {
        moleculeList = new MoleculeList("My molecule list");
    }

    @Test
    public void testAddMolecule() {
        moleculeList.addMolecule(new MoleculeEntered("SO2", Category.HARD));
        moleculeList.addMolecule(new MoleculeEntered("H2O", Category.EASY));
        moleculeList.addMolecule(new MoleculeEntered("CF2", Category.MEDIUM));
        assertEquals(3, moleculeList.numMolecule());
        assertEquals("My molecule list", moleculeList.getName());
//        assertEquals("HARD: SO2, EASY: H2O, MEDIUM: CF2", moleculeList.getMolecules());
    }
}
