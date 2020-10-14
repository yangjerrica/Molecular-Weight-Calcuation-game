package model;

public abstract class Animals {

    protected String apple = "apple";
    protected String bone = "bone";
    protected String carrot = "carrot";
    protected String fish = "fish";
    protected String leaf = "leaf";
    protected int points;

    //EFFECTS: initialize the animal with zero points
    public Animals() {
        this.points = 0;
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
