package ui;

import model.*;

import java.util.Scanner;


public class MolarMassApp {

    private MolecularQuiz molecularQuiz;
    private Scanner input = new Scanner(System.in);
    private Dog dog;
    private Cat cat;
    private Quokka quokka;

    public MolarMassApp() {
        getQuestions();
        molecularQuiz = new MolecularQuiz();
    }

    private void getQuestions() {
        System.out.println("Please enter the molecular weight of the molecule: " + molecularQuiz.getRandomMolecule());
        correctAnswer();
    }

    private void correctAnswer() {
        Molecule randomMolecule = molecularQuiz.getRandomMolecule();
        if (String.valueOf(randomMolecule.getMolarMass()) == input.next()) {
            System.out.println("Congratulations! Please pick a treat from below!");
            pickTreat();
        } else {
            System.out.println("Wrong! Please answer the question again.");
            correctAnswer();
        }
    }

    private void pickTreat() {
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        System.out.println("Please choose a treat: Apple, Bone, Carrot, Fish, Leaf");
        if (dog.getPoints() <= 100) {
            String treat = input.next();
            dog.reward(treat);
            dog.printPoints();
        } else if (cat.getPoints() <= 100) {
            String treat = input.next();
            cat.reward(treat);
            cat.printPoints();
        } else if (quokka.getPoints() <= 100) {
            String treat = input.next();
            quokka.reward(treat);
            quokka.printPoints();
            System.out.println("Congratulations! All animals are satisfied!");
        }

    }

}
