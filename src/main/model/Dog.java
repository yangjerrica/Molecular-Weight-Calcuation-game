package model;

import org.json.JSONObject;

//represents a Dog
public class Dog extends Animals {

    //EFFECTS: initialize the Dog with zero points and gives different points to each treat according to dog
    public Dog() {
        super();
        getApple = 30;
        getBone = 25;
        getCarrot = 15;
        getFish = 15;
        getLeaf = 5;

    }

    //EFFECTS: return the sound made by a dog
    @Override
    public String sound() {
        String sound = "woof! ";
        String soundMade = sound + sound + sound;
        return soundMade;
    }

    //MODIFIES: this
    //EFFECTS:returns the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "Dog");
        json.put("points", getPoints());
        return json;
    }





}
