package model;

import org.json.JSONObject;

//represents a quokka
public class Quokka extends Animals {


    //EFFECT: initialize the Quokka with zero points and gives different points to each treat according to quokka
    public Quokka() {
        super();
        getApple = 30;
        getBone = 5;
        getCarrot = 25;
        getFish = 5;
        getLeaf = 35;

    }

    //EFFECTS:returns the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", "Quokka");
        json.put("points", getPoints());
        return json;
    }
}
