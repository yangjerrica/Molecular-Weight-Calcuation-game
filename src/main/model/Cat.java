package model;

import org.json.JSONObject;
import persistence.Writable;

//represents a Cat
public class Cat extends Animals {

    //EFFECTS: initialize the Cat with zero points and gives different points to each treat according to cat
    public Cat() {
        super();
        getApple = 15;
        getBone = 5;
        getCarrot = 20;
        getFish = 40;
        getLeaf = 10;
    }

    //EFFECTS: return the sound made by a cat
    @Override
    public String sound() {
        String sound = "meow~ meow! meow~";
        return sound;
    }


    //MODIFIES: this
    //EFFECTS:returns the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "Cat");
        json.put("points", getPoints());
        return json;
    }
}
