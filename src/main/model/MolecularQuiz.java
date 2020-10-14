package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MolecularQuiz {
    public Random random;
    public List<Molecule> molecules;
    public ArrayList<String> list;

    //EFFECTS: gives a random molecule provided from the list
    public MolecularQuiz() {
        random = new Random();
        list = new ArrayList<>(Arrays.asList("CH3COOH",  "C2H5OH", "C4H8", "HF",
                "HNO3", "C2H4", "C6H6", "NO3", "N2O4", "HCN", "SF6", "H2SO4", "N2", "H2O", "F2", "H2", "O2",
                "C6H5CH2CH3", "N2O", "CH3CH2CH2CH2CH3", "C6H8O2", "C5H8O4", "SO2", "SO3", "CF2"));

        molecules = new ArrayList<>();
        for (String formula : list) {
            molecules.add(new Molecule(formula));
        }
    }

    //MODIFIES: this
    //EFFECTS: returns a random molecule from the list of molecules
    public Molecule getRandomMolecule() {

        Molecule randomMolecule;
        int moleNum = random.nextInt(molecules.size());
        randomMolecule = molecules.get(moleNum);
        return randomMolecule;
    }

}
