package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MolarMassGame extends JFrame {
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    private Container container;
    public JPanel titlePanel;
    private JLabel titleLabel;
    private Font titleFont = new Font("Times New Roman", Font.BOLD, 56);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    public JPanel startButtonPanel;
    public JPanel playerPanel;
    private JLabel pointLabel;
    private JLabel pointLabelNum;
    public JPanel mainTextPanel;
    private JTextArea mainTextArea;
    private Molecule randomMolecule;
    private MolecularQuiz molecularQuiz;
    private TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
    public JPanel choiceButtonPanel;
    private int playerPoints;
    private Dog dog;
    private Cat cat;
    private Quokka quokka;
    private ChoiceHandler choiceHandler = new ChoiceHandler();
    private static final String JSON_STORE = "./data/moleculeList.json";
    private static final String JSON_STORE_ANIMALS = "./data/animals.json";
    private MoleculeList moleculeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Animals> animals;
    private JTextField userText;
    private JLabel success;
    private JButton submitButton;
    private TreatHandler treatHandler = new TreatHandler();
    public JPanel treatPanel;
    private JLabel pickLabel;
    private JLabel sideNotesLabel;
    private JLabel sideNotesLabelSaveGame;
    public JPanel moleculeListPanel;
    private MoleculeListHandler moleculeListHandler = new MoleculeListHandler();
    private ui.VisibilityTool visibility = new VisibilityTool(this);
    private JsonReader jsonReaderAnimal;
    private JsonWriter jsonWriterAnimal;
    private static final int INTERVAL = 500;
    public JPanel saveButtonPanel;
    private JList savedMoleculeList;
    public JPanel saveMoleculeListPanel;
    private JScrollPane moleculeListScroll;
    private String[] strings = {"djsi", "dsmkm", "dlmfd", "djsi", "dsmkm", "dlmfd", "djsi", "dsmkm", "dlmfd"};


    public MolarMassGame() {
        super("MOLAR MASS GAME");

        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        animals = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriterAnimal = new JsonWriter(JSON_STORE_ANIMALS);
        jsonReaderAnimal = new JsonReader(JSON_STORE_ANIMALS);
        moleculeList = new MoleculeList("My Molecule List");


        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);
        container = this.getContentPane();
        createTitleScreen();
        addTimer();


        setVisible(true);

    }

    private void createTitleScreen() {
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 130);
        titlePanel.setBackground(Color.pink);

        titleLabel = new JLabel("MOLAR MASS GAME");
        titleLabel.setForeground(Color.blue);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        container.add(titlePanel);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(100, 400, 600, 100);
        startButtonPanel.setBackground(Color.orange);
        startButtonPanel.setLayout(new GridLayout(1, 3));

        JButton startButton = new JButton("START");
        choiceButton(startButton);
        startButton.addActionListener(titleScreenHandler);
        startButton.setActionCommand("start");
        startButtonPanel.add(startButton);

        JButton loadButton = new JButton("LOAD GAME");
        choiceButton(loadButton);
        loadButton.addActionListener(titleScreenHandler);
        loadButton.setActionCommand("load");
        startButtonPanel.add(loadButton);

        JButton endButton = new JButton("QUIT");
        choiceButton(endButton);
        endButton.addActionListener(titleScreenHandler);
        endButton.setActionCommand("quit");
        startButtonPanel.add(endButton);
        container.add(startButtonPanel);
    }

    private void choiceButton(JButton choice) {
        choice.setBackground(Color.black);
        choice.setForeground(Color.blue);
        choice.setFont(normalFont);
    }

    public void createGameScreen() {
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        createMainTextScreen();
        createTreatPanel();
        createChoiceScreen();
        createPlayerPanel();
        createAddMoleculeListPanel();
        createSaveButtonPanel();
        createSavedMoleculeListPanel();

    }

    private void createSaveButtonPanel() {
        saveButtonPanel = new JPanel();
        saveButtonPanel.setBounds(500, 15, 300, 50);
        saveButtonPanel.setBackground(Color.blue);
        container.add(saveButtonPanel);

        JButton saveButton = new JButton("MY MOLECULE LIST");
        saveButton.setBounds(350, 280, 50, 50);
        saveButton.setPreferredSize(new Dimension(200, 40));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visibility.selectSavedList();
            }
        });
        saveButton.setActionCommand("success");
        saveButtonPanel.add(saveButton);

    }

    private void createSavedMoleculeListPanel() {
        saveMoleculeListPanel = new JPanel();
        saveMoleculeListPanel.setBounds(75, 250, 600, 300);
        saveMoleculeListPanel.setBackground(Color.red);
        container.add(saveMoleculeListPanel);

        savedMoleculeList = new JList(strings);
        savedMoleculeList.setFont(normalFont);
        moleculeListScroll = new JScrollPane(savedMoleculeList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        saveMoleculeListPanel.add(moleculeListScroll);

    }

    private void createMainTextScreen() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 100, 650, 150);
        mainTextPanel.setBackground(Color.blue);
        container.add(mainTextPanel);

        molecularQuiz = new MolecularQuiz();
        randomMolecule = molecularQuiz.getRandomMolecule();

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.white);
        mainTextArea.setForeground(Color.black);
        mainTextArea.setFont(normalFont);
        mainTextArea.setText("Please enter the molecular weight of the molecule:     "
                + randomMolecule.getFormula());
        String currentMolecule = Integer.toString(randomMolecule.getMolarMass());
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        userText = new JTextField(20);
        userText.setBounds(350, 250, 300, 200);

        mainTextPanel.add(userText);
        success = new JLabel("");
        success.setBounds(400, 280, 120, 30);
        mainTextPanel.add(success);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(350, 280, 50, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                if (user.equals(currentMolecule)) {
                    success.setText("CONGRATULATIONS!");
                    submitButton.setVisible(false);
                    nextButton();
                } else {
                    success.setText("Wrong, please re-enter..");
                }

            }
        });
        mainTextPanel.add(submitButton);


    }

    private void nextButton() {
        JButton nextButton = new JButton("NEXT");
        nextButton.setBounds(350, 280, 50, 30);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visibility.questionsToTreats();
            }
        });
        nextButton.setActionCommand("success");
        mainTextPanel.add(nextButton);
    }

    private void createTreatPanel() {
        treatPanel = new JPanel();
        treatPanel.setBounds(75, 250, 600, 200);
        treatPanel.setBackground(Color.red);
        treatPanel.setLayout(new GridLayout(6, 1));

        pickLabel = new JLabel(("\"Congratulations! \n Please pick a treat from below!\""));
        pickLabel.setBounds(100, 250, 20, 10);
        pickLabel.setFont(normalFont);
        pickLabel.setForeground(Color.black);
        treatPanel.add(pickLabel);
        container.add(treatPanel);

        JButton choiceApple = new JButton("APPLE");
        choiceButton(choiceApple);
        choiceApple.addActionListener(treatHandler);
        choiceApple.setActionCommand("apple");
        treatPanel.add(choiceApple);

        JButton choiceBone = new JButton("BONE");
        choiceButton(choiceBone);
        choiceBone.addActionListener(treatHandler);
        choiceBone.setActionCommand("bone");
        treatPanel.add(choiceBone);

        JButton choiceCarrot = new JButton("CARROT");
        choiceButton(choiceCarrot);
        choiceCarrot.addActionListener(treatHandler);
        choiceCarrot.setActionCommand("carrot");
        treatPanel.add(choiceCarrot);

        JButton choiceFish = new JButton("FISH");
        choiceButton(choiceFish);
        choiceFish.addActionListener(treatHandler);
        choiceFish.setActionCommand("fish");
        treatPanel.add(choiceFish);

        JButton choiceLeaf = new JButton("LEAF");
        choiceButton(choiceLeaf);
        choiceLeaf.addActionListener(treatHandler);
        choiceLeaf.setActionCommand("leaf");
        treatPanel.add(choiceLeaf);

    }

    private void createChoiceScreen() {
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(200, 263, 450, 200);
        choiceButtonPanel.setBackground(Color.red);
        choiceButtonPanel.setLayout(new GridLayout(5, 1));

        sideNotesLabelSaveGame = new JLabel();
        sideNotesLabelSaveGame.setBounds(475, 463, 20, 10);
        sideNotesLabelSaveGame.setFont(normalFont);
        sideNotesLabelSaveGame.setForeground(Color.black);
        choiceButtonPanel.add(sideNotesLabelSaveGame);
        container.add(choiceButtonPanel);

        JButton choice1 = new JButton("YES");
        choices(choice1, choice1, "yes");
        choiceButtonPanel.add(choice1);

        JButton choice2 = new JButton("NO");
        choices(choice2, choice2, "no");
        choiceButtonPanel.add(choice2);

        JButton choice3 = new JButton("SAVE");
        choices(choice3, choice3, "save");
        choiceButtonPanel.add(choice3);

        JButton choice4 = new JButton("EXIT");
        choices(choice4, choice4, "exit");
        choiceButtonPanel.add(choice4);
    }



    private void choices(JButton choice1, JButton choice12, String add) {
        choiceButton(choice1);
        choice12.addActionListener(choiceHandler);
        choice12.setActionCommand(add);
    }


    private void createPlayerPanel() {
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 350, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        container.add(playerPanel);

        pointLabel = new JLabel("Satisfaction: ");
        pointLabel.setFont(normalFont);
        pointLabel.setForeground(Color.white);
        playerPanel.add(pointLabel);
        pointLabelNum = new JLabel();
        pointLabelNum.setFont(normalFont);
        pointLabelNum.setForeground(Color.white);
        playerPanel.add(pointLabelNum);
        playerSetUp();
    }

    public void playerSetUp() {
        playerPoints = animalPointsReturn();
        pointLabelNum.setText("" + playerPoints + " %");
        showQuestion();
    }

    public void showQuestion() {
        mainTextArea.setText("Please enter the molecular weight of the molecule:\n"
                + randomMolecule.getFormula());
    }

    private int animalPointsReturn() {
        if (dog.getPoints() < 100) {
            return dog.getPoints();
        } else if (cat.getPoints() < 100) {
            return cat.getPoints();
        } else {
            return quokka.getPoints();
        }
    }

    private Animals returnAnimal() {
        if (dog.getPoints() < 100) {
            return dog;
        } else if (cat.getPoints() < 100) {
            return cat;
        } else {
            return quokka;
        }
    }




    private void createAddMoleculeListPanel() {
        moleculeListPanel = new JPanel();
        moleculeListPanel.setBounds(200, 263, 450, 200);
        moleculeListPanel.setBackground(Color.red);
        moleculeListPanel.setLayout(new GridLayout(5, 1));

        sideNotesLabel = new JLabel();
        sideNotesLabel.setBounds(475, 463, 20, 10);
        sideNotesLabel.setFont(normalFont);
        sideNotesLabel.setForeground(Color.black);
        moleculeListPanel.add(sideNotesLabel);
        container.add(moleculeListPanel);

        JButton choice1 = new JButton("EASY");
        choiceButton(choice1);
        choice1.addActionListener(moleculeListHandler);
        choice1.setActionCommand("easy");
        moleculeListPanel.add(choice1);

        JButton choice2 = new JButton("MEDIUM");
        choiceButton(choice2);
        choice2.addActionListener(moleculeListHandler);
        choice2.setActionCommand("medium");
        moleculeListPanel.add(choice2);

        JButton choice3 = new JButton("HARD");
        choiceButton(choice3);
        choice3.addActionListener(moleculeListHandler);
        choice3.setActionCommand("hard");
        moleculeListPanel.add(choice3);

        JButton choice4 = new JButton("RETURN");
        choiceButton(choice4);
        choice4.addActionListener(moleculeListHandler);
        choice4.setActionCommand("return");
        moleculeListPanel.add(choice4);
    }

    private void newMainTextScreen() {
        remove(mainTextPanel);
        createMainTextScreen();
        revalidate();
        repaint();
    }

    private void addTimer() {
        final Timer t = new Timer(INTERVAL, null);
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (quokka.getPoints() >= 100) {
                    t.stop();
                }
            }
        });

        t.start();
    }


    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            createGameScreen();

            String titleScreen = event.getActionCommand();
            switch (titleScreen) {
                case "start":
                    visibility.toQuestions();
                    break;
                case "quit":
                    visibility.goodbyeScreen();
                    //titleScreenPanel.titleLabel.setText("Goodbye!");
                    break;
                case "load":
                    visibility.toQuestions();
                    try {
                        jsonReaderAnimal.readAnimal();
                        pointLabelNum.setText(animalPointsReturn() + " %");
                    } catch (IOException e) {
                        titleLabel.setText("Unable to read from file: " + JSON_STORE_ANIMALS);
                    }
                    break;
            }
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


    public class TreatHandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            visibility.treatsToOptions();
            if (animalPointsReturn() < 100) {
                Animals a = returnAnimal();
                a.reward(e.getActionCommand());
            }
            titleLabel.setFont(normalFont);
            titleLabel.setText("Do you want to add it to your molecule list?");
            pointLabelNum.setText(animalPointsReturn() + " %");

        }
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String quesHandler = e.getActionCommand();
            switch (quesHandler) {
                case "yes":
                    visibility.optionToSaveList();
                    titleLabel.setText("Please select a level for your Molecule.");
                    break;
                case "no":
                    visibility.toQuestions();
                    newMainTextScreen();
                    break;
                case "save":
                    visibility.treatsToOptions();
                    saveGame();
                    sideNotesLabelSaveGame.setText("Saved!");
                    break;
                case "exit":
                    visibility.goodbyeScreen();
                    titleLabel.setText("Goodbye! Have a nice day :D!");
                    break;
            }


        }
    }



    public class MoleculeListHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String mlHandler = e.getActionCommand();
            switch (mlHandler) {

                //TODO: separate each enum
                case "easy":
                    saveMolecules();
                    break;
                case "medium":
                    //saveMolecules();
                    break;
                case "hard":
                   // saveMolecules();
                    break;
                case "return":
                    visibility.treatsToOptions();
                    titleLabel.setFont(normalFont);
                    titleLabel.setText("Do you want to add it to your molecule list?");
            }
        }
    }
}
