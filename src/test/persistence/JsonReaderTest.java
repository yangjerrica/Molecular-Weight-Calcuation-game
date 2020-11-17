package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MoleculeList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMoleculeList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMoleculeList.json");
        try {
            MoleculeList ml = reader.read();
            assertEquals("My molecule list", ml.getName());
            assertEquals(0, ml.numMolecule());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMoleculeList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMoleculeList.json");
        try {
            MoleculeList ml = reader.read();
            assertEquals("My molecule list", ml.getName());
            List<MoleculeEntered> molecules = ml.getMolecules();
            assertEquals(2, molecules.size());
            checkMoleculeEntered("C8H9O2", Category.HARD, molecules.get(0));
            checkMoleculeEntered("SF6", Category.MEDIUM, molecules.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderAnimals() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAnimal.json");
        try {
            Animals dog = new Dog();
            Animals cat = new Cat();
            Animals quokka = new Quokka();
            reader.readAnimal();
            checkAnimal("Dog", 0, dog);
            assertEquals(0, dog.getPoints());
            checkAnimal("Cat", 0, cat);
            assertEquals(0, cat.getPoints());
            checkAnimal("Quokka", 0, quokka);
            assertEquals(0, quokka.getPoints());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
