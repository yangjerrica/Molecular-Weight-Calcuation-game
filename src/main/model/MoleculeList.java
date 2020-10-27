package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoleculeList implements Writable {
    private String name;
    private List<MoleculeEntered> molecules;

    // EFFECTS: constructs molecule list with a name and empty list of thingies
    public MoleculeList(String name) {
        this.name = name;
        molecules = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this molecule list
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
