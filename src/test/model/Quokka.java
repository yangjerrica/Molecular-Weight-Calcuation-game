package model;

public class Quokka extends Animals{

    private int GET_APPLE = 30;
    private int GET_BONE = 5;
    private int GET_CARROT = 25;
    private int GET_FISH = 5;
    private int GET_LEAF = 35;

    public Quokka(){
        super();
    }

    //MODIFIES: this
    //EFFECTS: shows the total points, adding the rewarding points
    public void reward(String treat) {
        if (treat == apple) {
             points = points + GET_APPLE;
        } else if (treat == bone) {
             points = points + GET_BONE;
        } else if (treat == carrot) {
             points = points + GET_CARROT;
        } else if (treat == fish) {
             points = points + GET_FISH;
        } else if (treat == leaf) {
             points = points + GET_LEAF;
        }
    }



}
