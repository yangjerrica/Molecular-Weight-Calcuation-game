package model;

public class Cat extends Animals {

    private final int getApple = 15;
    private final int getBone = 5;
    private final int getCarrot = 20;
    private final int getFish = 40;
    private final int getLeaf = 10;

    //EFFECTS: initialize the Cat with zero points
    public Cat() {
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
