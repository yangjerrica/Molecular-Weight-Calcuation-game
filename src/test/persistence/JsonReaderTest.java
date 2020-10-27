//package persistence;
//
//import model.Category;
//import model.MoleculeEntered;
//import model.MoleculeList;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class JsonReaderTest extends JsonTest{
//
//    @Test
//    void testReaderNonExistentFile() {
//        JsonReader reader = new JsonReader("./data/noSuchFile.json");
//        try {
//            MoleculeList ml = reader.read();
//            fail("IOException expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testReaderEmptyMoleculeList() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyMoleculeList.json");
//        try {
//            MoleculeList ml = reader.read();
//            assertEquals("My molecule list", ml.getName());
//            assertEquals(0, ml.numMolecule());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    @Test
//    void testReaderGeneralMoleculeList() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralMoleculeList.json");
//        try {
//            MoleculeList ml = reader.read();
//            assertEquals("My molecule list", ml.getName());
//            List<MoleculeEntered> molecules = ml.getMolecules();
//            assertEquals(2, molecules.size());
//            checkMoleculeEntered("C8H9O2", Category.HARD, molecules.get(0));
//            checkMoleculeEntered("SF6", Category.MEDIUM, molecules.get(1));
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//}
