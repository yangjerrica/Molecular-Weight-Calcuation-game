package persistence;

import model.Category;
import model.Molecule;
import model.MoleculeEntered;
import model.MoleculeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            MoleculeList ml = new MoleculeList("My molecular list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMoleculeList() {
        try {
            MoleculeList ml = new MoleculeList("My molecule list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMoleculeList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMoleculeList.json");
            ml = reader.read();
            assertEquals("My molecule list", ml.getName());
            assertEquals(0, ml.numMolecule());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMoleculeList() {
        try {
            MoleculeList ml = new MoleculeList("My molecule list");
            ml.addMolecule(new MoleculeEntered("C8H9O2", Category.HARD));
            ml.addMolecule(new MoleculeEntered("SF6", Category.MEDIUM));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMoleculeList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMoleculeList.json");
            ml = reader.read();
            assertEquals("My molecule list", ml.getName());
            List<MoleculeEntered> molecules = ml.getMolecules();
            assertEquals(2, molecules.size());
            checkMoleculeEntered("C8H9O2", Category.HARD, molecules.get(0));
            checkMoleculeEntered("SF6", Category.MEDIUM, molecules.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
