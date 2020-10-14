package ui;

import model.*;

import java.util.Scanner;


public class MolarMassApp {

    private MolecularQuiz molecularQuiz;
    private Scanner input = new Scanner(System.in);
    private Dog dog;
    private Cat cat;
    private Quokka quokka;
    private boolean keepGoing = true;


    public MolarMassApp() {
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        molecularQuiz = new MolecularQuiz();
        getQuestions();
    }

    private void getQuestions() {

        Molecule randomMolecule = molecularQuiz.getRandomMolecule();
        while (keepGoing) {
            System.out.println(
                    "Please enter the molecular weight of the molecule: " + randomMolecule.getFormula());
            correctAnswer(randomMolecule);
            getQuestions();
        }
    }

    private void correctAnswer(Molecule molecule) {

        int userAnswer;
        userAnswer = input.nextInt();
        if (userAnswer == molecule.getMolarMass()) {
            System.out.println("Congratulations! Please pick a treat from below!");
            pickTreat();
        } else {
            System.out.println("Wrong! Please answer the question again.");
            correctAnswer(molecule);
        }
    }


    private void pickTreat() {

        if (dog.getPoints() < 100) {
            dogTreat();
        } else if (cat.getPoints() < 100) {
            catTreat();
        } else if (quokka.getPoints() < 100) {
            quokkaTreat();
        }
    }

    private void dogTreat() {
        dog.getPoints();
        System.out.println("Please choose a treat for the Dog: apple, bone, carrot, fish, leaf");
        String treat = input.next();
        int currentPoints = dog.getPoints();
        dog.reward(treat);
        if (currentPoints == dog.getPoints()) {
            System.out.println("Please choose a treat in the list above.");
            String treat2 = input.next();
            dog.reward(treat2);
        } else {
            dog.printPoints();
        }
        if (dog.getPoints() < 100) {
            System.out.println("The current satisfaction is " + dog.getPoints() + " %");
        } else {
            System.out.println("You have reached 100% satisfaction. Satisfied!");
        }

    }

    private void catTreat() {
        cat.getPoints();
        System.out.println("Please choose a treat for the Cat: apple, bone, carrot, fish, leaf");
        String treat = input.next();
        int currentPoints = cat.getPoints();
        cat.reward(treat);
        if (currentPoints == cat.getPoints()) {
            System.out.println("Please choose a treat in the list above.");
            String treat2 = input.next();
            cat.reward(treat2);
        } else {
            cat.printPoints();
        }
        if (cat.getPoints() < 100) {
            System.out.println("The current satisfaction is " + cat.getPoints() + " %");
        } else {
            System.out.println("You have reached 100% satisfaction. Satisfied!");
        }
    }

    private void quokkaTreat() {
        quokka.getPoints();
        System.out.println("Please choose a treat for the Quokka: apple, bone, carrot, fish, leaf");
        String treat = input.next();
        int currentPoints = quokka.getPoints();
        quokka.reward(treat);
        if (currentPoints == quokka.getPoints()) {
            System.out.println("Please choose a treat in the list above.");
            String treat2 = input.next();
            quokka.reward(treat2);
        } else {
            quokka.printPoints();
        }
        if (quokka.getPoints() < 100) {
            System.out.println("The current satisfaction is " + quokka.getPoints() + " %");
        } else {
            System.out.println("You have reached 100% satisfaction. Satisfied!");
            System.out.println("All animals are satisfied!");
            keepGoing = false;
        }

    }


}
