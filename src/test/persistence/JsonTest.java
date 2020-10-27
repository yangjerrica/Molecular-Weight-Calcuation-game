package persistence;

import model.Category;
import model.MoleculeEntered;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMoleculeEntered(String name, Category category, MoleculeEntered molecule) {
        assertEquals(name, molecule.getName());
        assertEquals(category, molecule.getCategory());
    }
}
