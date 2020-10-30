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
        moleculeList.addMolecule(new MoleculeEntered("SO2", Category.HARD));
        moleculeList.addMolecule(new MoleculeEntered("H2O", Category.EASY));
        moleculeList.addMolecule(new MoleculeEntered("CF2", Category.MEDIUM));
    }

    @Test
    public void testAddMolecule() {

        assertEquals(3, moleculeList.numMolecule());
        assertEquals("My molecule list", moleculeList.getName());
        assertEquals("[HARD: SO2, EASY: H2O, MEDIUM: CF2]", moleculeList.getMolecules().toString());
    }

    @Test
    public void testMoleculeToJason() {

        assertEquals("{\"molecules\":[{\"name\":\"SO2\"," + "\"category\":\"HARD\"}," +
                "{\"name\":\"H2O\",\"category\":\"EASY\"},{\"name\":\"CF2\",\"category\":\"MEDIUM\"}],\"name\":" +
                        "\"My molecule list\"}", moleculeList.toJson().toString());
    }
}
