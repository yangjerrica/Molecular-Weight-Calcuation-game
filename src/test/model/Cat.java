package model;

public class Cat extends Animals{

    private int GET_APPLE = 15;
    private int GET_BONE = 5;
    private int GET_CARROT = 20;
    private int GET_FISH = 40;
    private int GET_LEAF = 10;

    public Cat(){
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
        } else if (treat == leaf){
             points = points + GET_LEAF;
        }
    }


}
