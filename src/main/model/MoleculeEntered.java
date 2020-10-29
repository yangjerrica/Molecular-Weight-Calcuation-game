package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a molecule having a name and a category
//cited from the JsonSerializationDemo
public class MoleculeEntered implements Writable {
    private String name;
    private Category category;

    // EFFECTS: constructs a molecule with a name and category
    public MoleculeEntered(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    //EFFECTS: returns the name
    public String getName() {
        return name;
    }

    //EFFECTS: returns the category
    public Category getCategory() {
        return category;
    }

    // EFFECTS: returns string representation of this molecule
    public String toString() {
        return category + ": " + name;
    }

    //EFFECTS:returns the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        return json;
    }
}
