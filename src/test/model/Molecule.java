package model;

import java.util.Random;

public class Molecule {
    private String[] list;
    private int molecularWeight;
    private Random molecule;
    private String randomMolecule;
    private char[] c;

    public Molecule() {
        molecularWeight = 0;
        molecule = new Random();
        list = new String[]{"CuSO4", "C6H6", "NO3", "N2O4", "HCN", "SF6", "IF7", "H2SO4"};
    }

    public void calculateMolecularWeight(){
        int c1 = 0;
        getRandomMolecule();
        c = randomMolecule.toCharArray();
        for (int i = 0; i < randomMolecule.length() - 1; i++) {
            if(c[i] > '9'||c[i] < '0') {
                c1 = 0;
            } else {
                for(int j = i + 1; j < randomMolecule.length() && '0'<= c[j] && c[j] <= '9'; j++) {
                    c1 = c1 * 10 + (c[j] - '0');
                }
            }
        }
        int n = randomMolecule.length() - 1;
        switch(c[n]) {
            case 'C':
                molecularWeight += c1 * 12;
                break;
            case 'N':
                molecularWeight += c1 * 14;
                break;
            case 'O':
                molecularWeight += c1 * 16;
                break;
            case 'H':
                molecularWeight += c1 * 1;
                break;
            case 'S':
                molecularWeight += c1 * 32;
                break;
            case 'F':
                molecularWeight += c1 * 19;
                break;
            case 'I':
                molecularWeight += c1 * 127;

        }

    }

    public String getNumInMolecular(String str){
        if(str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }

        return sb.toString();
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
