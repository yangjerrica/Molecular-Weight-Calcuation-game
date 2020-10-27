package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns to the JSON object
    JSONObject toJson();
}
