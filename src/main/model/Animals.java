package model;

public abstract class Animals {

    protected String apple = "apple";
    protected String bone = "bone";
    protected String carrot = "carrot";
    protected String fish = "fish";
    protected String leaf = "leaf";
    protected int getApple;
    protected int getBone;
    protected int getCarrot;
    protected int getFish;
    protected int getLeaf;
    protected int points;

    //EFFECTS: initialize the animal with zero points
    public Animals() {
        this.points = 0;
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
        } else {
            points = points;
        }
    }

    //MODIFIES: this
    //EFFECTS: if the points are less than a hundred, then it returns the current value; otherwise
    //         returns "Satisfied!"
    public String printPoints() {
        if (points < 100) {
            String pointsStr = "The current satisfaction is " + String.valueOf(points) + " %";
            return pointsStr;
        }
        String satisfiedStr = "You have reached 100% satisfaction. Satisfied!";
        return satisfiedStr;
    }

    //EFFECTS: returns the current satisfaction points
    public int getPoints() {
        return points;
    }
}
