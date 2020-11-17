package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the molar mass application
public class MolarMassApp {

    private MolecularQuiz molecularQuiz;
    private Scanner input;
    private Dog dog;
    private Cat cat;
    private Quokka quokka;
    private boolean keepGoing = true;
    private boolean keepGoingMain = true;
    private static final String JSON_STORE = "./data/moleculeList.json";
    private static final String JSON_STORE_ANIMALS = "./data/animals.json";
    private MoleculeList moleculeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Animals> animals;

    //EFFECTS: runs the Molar Mass application
    public MolarMassApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        animals = new ArrayList<>();
        molecularQuiz = new MolecularQuiz();
        moleculeList = new MoleculeList("My Molecule List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMoleculeGame();
    }

    // MODIFIES: this
    //EFFECTS: runs the molecule game
    //cited from the JsonSerializationDemo
    private void runMoleculeGame() {

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

        System.out.println("\nHave a nice day!");
    }


    //EFFECTS: displays the menu of the beginning of the game; containing start and quit game
    private void displayMenu() {
        System.out.println("\nHello! Welcome to the Molar Mass Calculating Game! Please select one from below:");
        System.out.println("\tstart -> start playing the game!");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: displays the menu inside the game. Will be able to add, print, save ,load molecules entered and
    // back to the game
    private void displayMenu2() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Molecules according to different levels");
        System.out.println("\tp -> print all my molecules saved");
        System.out.println("\ts -> save molecule to file");
        System.out.println("\tl -> load molecule from file");
        System.out.println("\tq -> back to the game!");
    }

    // MODIFIES: this
    //EFFECTS: this process the command user entered in the beginning of the game
    //cited from the JsonSerializationDemo
    private void processCommand(String command) {
        if (command.equals("start")) {
            getAndCheckQuestions();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    //EFFECTS: this process the command user entered during the game
    //cited from the JsonSerializationDemo
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
            if (command.equals("q")) {
                System.out.println("Back to the game!");
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    //EFFECTS: this runs the options user entered during the game
    //cited from the JsonSerializationDemo
    private void runOptions() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);
        command = input.next();

        if (command.equals("Y")) {
            continueYes(keepGoing);
        } else if (command.equals("N")) {
            selectNoRespond();

        } else if (command.equals("S")) {
            saveGame();

        } else if (command.equals("E")) {
            this.keepGoing = false;
            System.out.println("Bye bye :)");
            keepGoingMain = false;
        } else {
            System.out.println("Please enter Y for Yes: see options, N for No: continue, S for Save,"
                    + " or E for Exit: Exit game");
            runOptions();
        }

    }

    private void continueYes(boolean keepGoing) {
        String command;
        while (keepGoing) {
            displayMenu2();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            }
            processInnerCommand(command);

        }
    }

    private void saveGame() {
        animals.add(dog);
        animals.add(cat);
        animals.add(quokka);
        try {
            jsonWriter.open();
            jsonWriter.write(animals);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE_ANIMALS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ANIMALS);
        }
    }

    //EFFECTS: this is a helper function, it gives responds to two different situation. If keepGoing == false, responds
    //"Congratulation :D ! We will redirect you to the home page"; otherwise "Great! Lets continue...:)".
    private void selectNoRespond() {
        if (!this.keepGoing) {
            System.out.println("Congratulation :D ! You won!");
            System.out.println("-END-");

        } else {
            System.out.println("Great! Lets continue...:)");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for name and category of moleculesEntered and adds to the moleculeList
    //cited from the JsonSerializationDemo
    private void addMolecules() {
        Category category = readCategory();
        System.out.println("Please enter the molecule: ");
        String name = input.next();
        moleculeList.addMolecule(new MoleculeEntered(name, category));

    }

    // EFFECTS: prompts user to select category and returns it
    //cited from the JsonSerializationDemo
    private Category readCategory() {
        System.out.println("Please select a level for your Molecule. Enter 1, 2, or 3");

        int menuLabel = 1;
        for (Category c : Category.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }
        int menuSelection = input.nextInt();
        return Category.values()[menuSelection - 1];
    }

    // EFFECTS: prints all the moleculesEntered in moleculeList to the console
    //cited from the JsonSerializationDemo
    private void printMoleculesEntered() {
        List<MoleculeEntered> molecules = moleculeList.getMolecules();

        for (MoleculeEntered me : molecules) {
            System.out.println(me);
        }
    }

    // EFFECTS: saves the moleculesList to files
    //cited from the JsonSerializationDemo
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

    // MODIFIES: this
    // EFFECTS: loads moleculeList from file
    //cited from the JsonSerializationDemo
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
    // otherwise it will print: "Wrong! Please answer the question again." and can continue only when the user gets
    //the correct answer. If the user enters a string that is not in the form of numbers, it will be caught by
    //NumberFormatException.
    private void correctAnswer(Molecule molecule) {
        String userAnswer;
        userAnswer = input.next();
        try {
            if (Integer.parseInt(userAnswer) == molecule.getMolarMass()) {
                System.out.println("Congratulations! Please pick a treat from below!");
                giveTreat();
                System.out.println("Do you want to add it to your molecule list? Y for Yes, N for No, E for Exit game");
                runOptions();
            } else {
                System.out.println("Wrong! Please answer the question again.");
                correctAnswer(molecule);
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not a number!");
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
        int currentPoints = animals.getPoints();
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