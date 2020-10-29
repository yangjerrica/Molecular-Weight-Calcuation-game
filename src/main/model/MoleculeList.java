package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a MoleculeList having a collection of MoleculeEntered
//cited from the JsonSerializationDemo
public class MoleculeList implements Writable {
    private String name;
    private List<MoleculeEntered> molecules;

    // EFFECTS: constructs molecule list with a name and empty list of molecules entered
    public MoleculeList(String name) {
        this.name = name;
        molecules = new ArrayList<>();
    }

    //EFFECTS: returns the name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds molecule entered to this molecule list
    public void addMolecule(MoleculeEntered moleculeEntered) {
        molecules.add(moleculeEntered);
    }

    // EFFECTS: returns an unmodifiable list of molecules in this molecule list
    public List<MoleculeEntered> getMolecules() {
        return Collections.unmodifiableList(molecules);
    }

    // EFFECTS: returns number of molecules in this molecule list
    public int numMolecule() {
        return molecules.size();
    }

    //EFFECTS:returns the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("molecules", moleculesToJson());
        return json;
    }

    // EFFECTS: returns molecules in this molecule list as a JSON array
    private JSONArray moleculesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MoleculeEntered m : molecules) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
