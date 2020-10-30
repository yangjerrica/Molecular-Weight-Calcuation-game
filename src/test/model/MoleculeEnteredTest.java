package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoleculeEnteredTest {
    private MoleculeEntered moleculeEntered;

    @BeforeEach
    public void beforeEach() {
        moleculeEntered = new MoleculeEntered("CO2", Category.EASY);
    }

    @Test
    public void testToString() {
        assertEquals("EASY: CO2", moleculeEntered.toString());
    }

    @Test
    public void testToJson() {
        assertEquals("{\"name\":\"CO2\",\"category\":\"EASY\"}", moleculeEntered.toJson().toString());

    }
}
