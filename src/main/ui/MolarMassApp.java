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

    //EFFECTS: runs the Molar Mass application
    public MolarMassApp() {
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        molecularQuiz = new MolecularQuiz();
        getAndCheckQuestions();
    }

    //MODIFIES: this
    //EFFECTS: gets the question and checks whether the answer is correct or not. Will keep on going till all the
    // animals are satisfied
    private void getAndCheckQuestions() {
        Molecule randomMolecule = molecularQuiz.getRandomMolecule();
        while (keepGoing) {
            System.out.println(
                    "Please enter the molecular weight of the molecule: " + randomMolecule.getFormula());
            correctAnswer(randomMolecule);
            getAndCheckQuestions();
        }
    }

    //MODIFIES: this and molecule
    //EFFECTS: if the user gets the answer correct, it will print :Congratulations! Please pick a treat from below!
    // otherwise it will print: "Wrong! Please answer the question again." and ca continue only when the user gets
    //the correct answer.
    private void correctAnswer(Molecule molecule) {
        int userAnswer;
        userAnswer = input.nextInt();
        if (userAnswer == molecule.getMolarMass()) {
            System.out.println("Congratulations! Please pick a treat from below!");
            giveTreat();
        } else {
            System.out.println("Wrong! Please answer the question again.");
            correctAnswer(molecule);
        }
    }


    //MODIFIES:this
    //EFFECTS: starts giving treats in the order of dog->cat->quokka
    private void giveTreat() {
        if (dog.getPoints() < 100) {
            dogTreat();
        } else if (cat.getPoints() < 100) {
            catTreat();
        } else if (quokka.getPoints() < 100) {
            quokkaTreat();
        }
    }

    //MODIFIES: this
    //EFFECTS: if the points of the Dog doesn't increase, the user will have to re-enter a treat that is in the list.
    // If it increases, then it will print out the current satisfaction percentage. If the percentage >=100, it will
    // show that Dog is satisfied.
    private void dogTreat() {
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

    //MODIFIES: this
    //EFFECTS: if the points of the Cat doesn't increase, the user will have to re-enter a treat that is in the list.
    // If it increases, then it will print out the current satisfaction percentage. If the percentage >=100, it will
    // show that Cat is satisfied.
    private void catTreat() {
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

    //MODIFIES: this
    //EFFECTS: if the points of the Quokka doesn't increase, the user will have to re-enter a treat that is in the list.
    // If it increases, then it will print out the current satisfaction percentage. If the percentage >=100, it will
    // show that Quokka is satisfied and all the other animals are satisfied. Last, it will stop the game.
    private void quokkaTreat() {
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
