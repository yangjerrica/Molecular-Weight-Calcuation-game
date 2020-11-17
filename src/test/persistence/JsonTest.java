package persistence;

import model.Animals;
import model.Category;
import model.MoleculeEntered;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMoleculeEntered(String name, Category category, MoleculeEntered molecule) {
        assertEquals(name, molecule.getName());
        assertEquals(category, molecule.getCategory());
    }

    protected void checkAnimal(String type, int points, Animals animals) {
        assertEquals(type, animals.toJson().getString("type"));
        assertEquals(points, animals.toJson().getInt("points"));
    }
}
