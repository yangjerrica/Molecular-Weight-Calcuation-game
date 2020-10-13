package model;

public abstract class Animals {

    protected String apple = "apple";
    protected String bone = "bone";
    protected String carrot = "carrot";
    protected String fish = "fish";
    protected String leaf = "leaf";
    protected int points;
    protected int rewardPoints;

    public Animals() {
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public String printPoints() {
        if (points < 100) {
            String pointsStr = "The current satisfaction is " + String.valueOf(points) + " %";
            System.out.println(pointsStr);
            return pointsStr;
        }
        return "Satisfied!";
    }
}
