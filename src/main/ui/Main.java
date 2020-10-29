package ui;

import model.Molecule;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MolarMassApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
