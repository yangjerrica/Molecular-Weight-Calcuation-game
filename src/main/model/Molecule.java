package model;

import java.util.regex.Pattern;

public class Molecule {
    private int molarMass;
    private String formula;

    //EFFECTS: initialize the molar mass with 0, gets the formula, and calculates the molecular mass
    public Molecule(String formula) {
        molarMass = 0;
        this.formula = formula;
        calculateMolecularMass();
    }

    //MODIFIES: this
    //EFFECTS: calculates the molecular mass of the given molecule
    private void calculateMolecularMass() {

        int findNum;

        for (int i = 0; i < formula.length(); i++) {
            char element = formula.charAt(i);
            if (isNumeric(String.valueOf(element))) {
                continue;
            }
            if (i < formula.length() - 1) {
                String moleAndWeight = formula.substring(i, i + 2);
                if (isNumeric(moleAndWeight.substring(1, 2))) {
                    findNum = Character.getNumericValue(moleAndWeight.charAt(1));
                } else {
                    findNum = 1;
                }
            } else {
                findNum = 1;
            }

            computeMass(element, findNum);
        }
    }

    //MODIFIES: this
    //EFFECTS: A helper function of the calculateMolecularMass, inserts the element and number(numElements) found in
    // calculateMolecularMass and does the math
    public void computeMass(char element, int numElements) {
        switch (element) {
            case 'C':
                molarMass += numElements * 12;
                break;
            case 'N':
                molarMass += numElements * 14;
                break;
            case 'O':
                molarMass += numElements * 16;
                break;
            case 'H':
                molarMass += numElements * 1;
                break;
            case 'S':
                molarMass += numElements * 32;
                break;
            case 'F':
                molarMass += numElements * 19;
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: checks whether the String is a number
    // I got this from: https://www.baeldung.com/java-check-string-number
    public boolean isNumeric(String strNum) {

        Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(strNum).matches();

    }

    //EFFECTS: returns the molarMass
    public int getMolarMass() {
        return molarMass;
    }


    //EFFECTS: returns the formula
    public String getFormula() {
        return formula;
    }
}
