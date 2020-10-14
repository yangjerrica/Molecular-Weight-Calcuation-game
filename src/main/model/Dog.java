package model;

public class Dog extends Animals {

    private int getApple = 30;
    private int getBone = 25;
    private int getCarrot = 15;
    private int getFish = 15;
    private int getLeaf = 5;

    //EFFECTS: initialize the Dog with zero points
    public Dog() {
        super();
    }

    //MODIFIES: this
    //EFFECTS: shows the total points, adding the rewarding points
    public void reward(String treat) {
        if (treat.equals(apple)) {
            points += getApple;
        } else if (treat.equals(bone)) {
            points += getBone;
        } else if (treat.equals(carrot)) {
            points += getCarrot;
        } else if (treat.equals(fish)) {
            points += getFish;
        } else if (treat.equals(leaf)) {
            points += getLeaf;
        }
    }




}
