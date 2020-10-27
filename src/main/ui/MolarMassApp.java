package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//
public class MolarMassApp {

    private MolecularQuiz molecularQuiz;
    private Scanner input;
    private Dog dog;
    private Cat cat;
    private Quokka quokka;
    private boolean keepGoing = true;
    private static final String JSON_STORE = "./data/moleculeList.json";
    private MoleculeList moleculeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the Molar Mass application
    public MolarMassApp() {
        input = new Scanner(System.in);
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        molecularQuiz = new MolecularQuiz();
        moleculeList = new MoleculeList("My Molecule List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMoleculeGame();
    }

    private void runMoleculeGame() {
        boolean keepGoingMain = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoingMain) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoingMain = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tstart -> start playing the game!");
        System.out.println("\tq -> quit");
    }

    private void displayMenu2() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Molecules according to different levels");
        System.out.println("\tp -> print all my molecules saved");
        System.out.println("\ts -> save molecule to file");
        System.out.println("\tl -> load molecule from file");
        System.out.println("\tq -> back to the game!");
    }

    private void processCommand(String command) {
        if (command.equals("start")) {
            getAndCheckQuestions();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void processInnerCommand(String command) {
        if (command.equals("a")) {
            addMolecules();
        } else if (command.equals("p")) {
            printMoleculesEntered();
        } else if (command.equals("s")) {
            saveMolecules();
        } else if (command.equals("l")) {
            loadMolecules();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void runOptions() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        if (input.next().equals("Y")) {
            while (keepGoing) {
                displayMenu2();
                command = input.next();
                command = command.toLowerCase();

                if (command.equals("q")) {
                    keepGoing = false;
                } else {
                    processInnerCommand(command);
                }
            }
        } else if (input.next().equals("N")) {
            System.out.println("Continue...:)");
        } else {
            System.out.println("Please enter Y or N");
            runOptions();
        }

    }

    private void addMolecules() {
        Category category = readCategory();
        System.out.println("Please enter the molecule: ");
        String name = input.next();
        moleculeList.addMolecule(new MoleculeEntered(name, category));
    }

    // EFFECTS: prompts user to select category and returns it
    private Category readCategory() {
        System.out.println("Please select a level for your Molecule");

        int menuLabel = 1;
        for (Category c : Category.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return Category.values()[menuSelection - 1];
    }

    private void printMoleculesEntered() {
        List<MoleculeEntered> molecules = moleculeList.getMolecules();

        for (MoleculeEntered me : molecules) {
            System.out.println(me);
        }
    }

    private void saveMolecules() {
        try {
            jsonWriter.open();
            jsonWriter.write(moleculeList);
            jsonWriter.close();
            System.out.println("Saved " + moleculeList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadMolecules() {
        try {
            moleculeList = jsonReader.read();
            System.out.println("Loaded " + moleculeList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
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
            System.out.println("Do you want to add this to your molecule list? Y for Yes, N for No.");
            runOptions();
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

    //MODIFIES: this ans animals
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
