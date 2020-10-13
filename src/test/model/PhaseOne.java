package model;

public class PhaseOne extends Phase{

    public PhaseOne() {
        super();
        list= new String[]{"CuSO4", "C6H6", "NO3", "N2O4", "HCN", "SF6", "IF7", "H2SO4"};
    }

    public void calculateWeight() {
        String mole = getRandomMolecule();

        if (mole.contains("CuSO4")) {
            molecularWeight = 160;
        } else if (mole.contains("C6H6")) {
            molecularWeight = 78;
        } else if (mole.contains("NO3")) {
            molecularWeight = 62;
        } else if (mole.contains("N2O4")) {
            molecularWeight = 92;
        } else if (mole.contains("HCN") ){
            molecularWeight = 27;
        } else if (mole.contains("SF6")) {
            molecularWeight = 146;
        } else if (mole.contains("IF7")) {
            molecularWeight = 260;
        } else if (mole.contains("H2SO4")) {
            molecularWeight = 98;
        }
    }

}

