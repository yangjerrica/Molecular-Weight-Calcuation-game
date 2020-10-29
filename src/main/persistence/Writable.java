package persistence;

import org.json.JSONObject;

//cited from the JsonSerializationDemo
public interface Writable {
    //EFFECTS: returns to the JSON object
    JSONObject toJson();
}
