//package ui;
//
//import model.*;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//import ui.tools.VisibilityPanel;
//import ui.tools.TitleScreenPanel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GUI extends JFrame {
//    private TitleScreenHandler tsHandler = new TitleScreenHandler();
//    private QuestionHandler quesHandler = new QuestionHandler();
//    private OptionsHandler optionsHandler = new OptionsHandler();
//    private MoleculeListHandler mlHandler = new MoleculeListHandler();
//    private TreatHandler treatHandler = new TreatHandler();
//    private VisibilityPanel visibility;
//    private TitleScreenPanel titleScreenPanel;
//    private MoleculeList moleculeList;
//    private Dog dog;
//    private Cat cat;
//    private Quokka quokka;
//    private List<Animals> animals;
//    private static final String JSON_STORE_ANIMALS = "./data/animals.json";
//    private static final String JSON_STORE = "./data/moleculeList.json";
//    private JsonReader jsonReaderAnimal;
//    private JsonWriter jsonWriter;
//    private JsonWriter jsonWriterAnimal;
//    private JsonReader jsonReader;
//    private static final int INTERVAL = 20;
//    protected Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
//    private MolarMassGame molarMassGame = new MolarMassGame();
//
//    public GUI() {
//        super("Molar Mass Calculation Game");
//        titleScreenPanel = new TitleScreenPanel();
//        visibility = new VisibilityPanel(molarMassGame);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        jsonWriterAnimal = new JsonWriter(JSON_STORE_ANIMALS);
//        jsonReaderAnimal = new JsonReader(JSON_STORE_ANIMALS);
//        dog = new Dog();
//        cat = new Cat();
//        quokka = new Quokka();
//        animals = new ArrayList<>();
//        moleculeList = new MoleculeList("My Molecule List");
//        animals.add(dog);
//        animals.add(cat);
//        animals.add(quokka);
//        titleScreenPanel.createTitleScreen(tsHandler);
//        visibility.showTitleScreen();
//        addTimer();
//
//    }
//
//    private void addTimer() {
//        Timer t = new Timer(INTERVAL, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                titleScreenPanel.update();
//                titleScreenPanel.repaint();
//                titleScreenPanel.update();
//            }
//        });
//
//        t.start();
//    }
//
//    public class TitleScreenHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//
//            String titleScreen = event.getActionCommand();
//            switch (titleScreen) {
//                case "start":
//                    visibility.toQuestions();
//                    break;
//                case "quit":
//                    visibility.goodbyeScreen();
//                    titleScreenPanel.titleLabel.setText("Goodbye!");
//                    break;
//                case "load":
//                    visibility.toQuestions();
//                    try {
//                        jsonReaderAnimal.readAnimal();
//                    } catch (IOException e) {
//                        titleScreenPanel.titleLabel.setText("Unable to read from file: " + JSON_STORE_ANIMALS);
//                    }
//                    break;
//            }
//        }
//    }
//
//    public class QuestionHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            String quesHandler = event.getActionCommand();
//            switch (quesHandler) {
//                case "yes":
//                    visibility.optionToSaveList();
//                    titleScreenPanel.titleLabel.setFont(normalFont);
//                    titleScreenPanel.titleLabel.setText("Please select a level for your Molecule.");
//                    break;
//                case "no":
//                    visibility.toQuestions();
//                    break;
//                case "save":
//                    visibility.treatsToOptions();
//                    try {
//                        jsonWriterAnimal.open();
//                        jsonWriterAnimal.write(animals);
//                        jsonWriterAnimal.close();
//                        titleScreenPanel.sideNotesLabel.setText("SAVED TO " + JSON_STORE_ANIMALS);
//                    } catch (FileNotFoundException e) {
//                        System.out.println("Unable to write to file: " + JSON_STORE_ANIMALS);
//                    }
//
//                    break;
//                case "exit":
//                    visibility.goodbyeScreen();
//                    titleScreenPanel.titleLabel.setFont(normalFont);
//                    titleScreenPanel.titleLabel.setText("Goodbye! Have a nice day :D!");
//                    break;
//                case "success":
//                    visibility.questionsToTreats();
//                    break;
//            }
//        }
//    }
//
//    public class MoleculeListHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            String mlHandler = event.getActionCommand();
//            switch (mlHandler) {
//                case "save" :
//                    try {
//                        jsonWriter.open();
//                        jsonWriter.write(moleculeList);
//                        jsonWriter.close();
//                        titleScreenPanel.sideNotesLabel.setText("Saved to " + JSON_STORE);
//                    } catch (FileNotFoundException e) {
//                        System.out.println("Unable to write to file: " + JSON_STORE);
//                    }
//                    break;
//                case "return":
//                    visibility.treatsToOptions();
//                    titleScreenPanel.titleLabel.setFont(normalFont);
//                    titleScreenPanel.titleLabel.setText("Do you want to add it to your molecule list?");
//                    break;
//            }
//        }
//    }
//
//    public class OptionsHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            String quesHandler = event.getActionCommand();
//            switch (quesHandler) {
//                case "add":
//                    break;
//                case "print":
//                    break;
//                case "save":
//                    break;
//                case "return":
//                    visibility.toQuestions();
//                    break;
//            }
//        }
//    }
//
//
//
//    public class TreatHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            visibility.treatsToOptions();
//            titleScreenPanel.titleLabel.setFont(normalFont);
//            titleScreenPanel.titleLabel.setText("Do you want to add it to your molecule list?");
//        }
//    }
//
//
//    private int animalPointsReturn() {
//        if (dog.getPoints() < 100) {
//            return dog.getPoints();
//        } else if (cat.getPoints() < 100) {
//            return cat.getPoints();
//        } else {
//            return quokka.getPoints();
//        }
//    }
//
//
//
//
//}
