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
    //EFFECTS: starts giving treats in the order of dog -> cat -> quokka
    private void giveTreat() {
        if (dog.getPoints() < 100) {
            System.out.println("Please choose a treat for the Dog: apple, bone, carrot, fish, leaf");
            selectTreat(dog);
        } else if (cat.getPoints() < 100) {
            System.out.println("Please choose a treat for the Cat: apple, bone, carrot, fish, leaf");
            selectTreat(cat);
        } else if (quokka.getPoints() < 100) {
            System.out.println("Please choose a treat for the Cat: apple, bone, carrot, fish, leaf");
            selectTreat(quokka);
        }
    }

    //MODIFIES: this
    //EFFECTS: if the points of the animal doesn't increase, the user will have to re-enter a treat that is in the list.
    // If it increases, then it will print out the current satisfaction percentage. If the percentage >=100, it will
    // show that the animal is satisfied.
    private void selectTreat(Animals animals) {
        String treat = input.next();
        int currentPoints = dog.getPoints();
        animals.reward(treat);
        while (currentPoints == animals.getPoints()) {
            System.out.println("Please choose a treat in the list above.");
            String treat2 = input.next();
            animals.reward(treat2);
        }
        animals.printPoints();
        if (animals == quokka) {
            quokkaTreat();
        } else {
            if (animals.getPoints() < 100) {
                System.out.println("The current satisfaction is " + animals.getPoints() + " %");
            } else {
                System.out.println("You have reached 100% satisfaction. Satisfied!");
            }
        }

    }


    //MODIFIES: this
    //EFFECTS: If the points increases, then it will print out the current satisfaction percentage.
    // Otherwise if the percentage >=100, it will show that Quokka is satisfied and all the other animals are satisfied.
    // Last, it will stop the game.
    private void quokkaTreat() {

        if (quokka.getPoints() < 100) {
            System.out.println("The current satisfaction is " + quokka.getPoints() + " %");
        } else {
            System.out.println("You have reached 100% satisfaction. Satisfied!");
            System.out.println("All animals are satisfied!");
            keepGoing = false;
        }

    }


}
