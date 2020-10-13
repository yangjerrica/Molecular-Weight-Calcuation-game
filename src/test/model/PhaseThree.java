package model;

public class PhaseThree extends Phase{

    public PhaseThree() {
        super();
        list= new String[]{"CH3COOH", "P4O10", "C6H12O6", "C12H22O11", "CaO", "NaCl", "KNO3", "SiO2"};
    }

    public void calculateWeight() {
        String mole = getRandomMolecule();

        if (mole.contains("CH3COOH")) {
            molecularWeight = 60;
        } else if (mole.contains("P4O10")) {
            molecularWeight = 284;
        } else if (mole.contains("C6H12O6")) {
            molecularWeight = 180;
        } else if (mole.contains("C12H22O11")) {
            molecularWeight = 342;
        } else if (mole.contains("CaO") ){
            molecularWeight = 56;
        } else if (mole.contains("NaCl")) {
            molecularWeight = 58;
        } else if (mole.contains("KNO3")) {
            molecularWeight = 101;
        } else if (mole.contains("SiO2")) {
            molecularWeight = 60;
        }
    }

}

