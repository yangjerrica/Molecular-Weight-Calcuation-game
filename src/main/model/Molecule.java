package model;

import java.util.Random;
import java.util.regex.Pattern;

public class Molecule {
    private int molarMass;
    private String formula;


    public Molecule(String formula) {
        molarMass = 0;
        this.formula = formula;
        calculateMolecularWeight();
    }

    private void calculateMolecularWeight() {

        char[] mole = formula.toCharArray();
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
                molarMass += numElements;
                break;
            case 'S':
                molarMass += numElements * 32;
                break;
            case 'F':
                molarMass += numElements * 19;
                break;
        }
    }


    public int getMolarMass() {
        return molarMass;
    }

    // I got this from: https://www.baeldung.com/java-check-string-number
    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        } else {
            return pattern.matcher(strNum).matches();
        }

    }
}
