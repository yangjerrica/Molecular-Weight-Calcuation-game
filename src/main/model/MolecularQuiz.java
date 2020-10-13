package model;

import java.util.List;
import java.util.Random;

public class MolecularQuiz {
    public Random random;
    public List<Molecule> molecules;

    public MolecularQuiz() {
        random = new Random();
        String[] list = new String[] {"CH3COOH", "P4O10", "C6H12O6", "C12H22O11", "CaO", "NaCl", "KNO3", "SiO2", "C2H4",
                "C6H6", "NO3", "N2O4", "HCN", "SF6", "H2SO4"};
        for (String formula : list) {
            molecules.add(new Molecule(formula));
        }
    }

    public Molecule getRandomMolecule() {
        Molecule randomMolecule;
        int moleNum = random.nextInt(molecules.size());
        randomMolecule = molecules.get(moleNum);
        return randomMolecule;
    }

}
