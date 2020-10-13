package model;

import java.util.Random;

public abstract class Phase {

    protected String[] list;
    protected int molecularWeight;
    protected Random molecule;
    protected String randomMolecule;

    public Phase() {
        molecularWeight = 0;
        molecule = new Random();
    }

    public String getRandomMolecule() {
        int moleNum = molecule.nextInt(list.length);
        randomMolecule = list[moleNum];
        return randomMolecule;
    }

    public int getMolecularWeight() {
        return molecularWeight;
    }
}
