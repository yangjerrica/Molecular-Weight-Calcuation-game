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
    private static final int INTERVAL = 500;
    private static int WIDTH = 800;
    private static int HEIGHT = 800;

    private Font titleFont = new Font("Monospaced", Font.BOLD, 56);
    private Font normalFont = new Font("Helvetica", Font.PLAIN, 23);

    private static final String JSON_STORE = "./data/moleculeList.json";
    private static final String JSON_STORE_ANIMALS = "./data/animals.json";


    private ui.VisibilityTool visibility = new VisibilityTool(this);
    private TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
    private ChoiceHandler choiceHandler = new ChoiceHandler();
    private TreatHandler treatHandler = new TreatHandler();

    private Container container;
    public JPanel titlePanel;
    private JLabel titleLabel;
    public JPanel startButtonPanel;
    public JPanel playerPanel;
    private JLabel pointLabel;
    private JLabel pointLabelNum;
    public JPanel mainTextPanel;
    private JTextArea mainTextArea;
    private Molecule randomMolecule;
    private MolecularQuiz molecularQuiz;
    public JPanel choiceButtonPanel;
    private int playerPoints;
    private Dog dog;
    private Cat cat;
    private Quokka quokka;
    private MoleculeList moleculeList;
    private List<Animals> animals;
    private JLabel success;
    private JButton submitButton;
    public JPanel treatPanel;
    private JLabel sideNotesLabelSaveGame;
    public JPanel imagePanel;
    public JPanel saveButtonPanel;
    private DefaultListModel<String> listNames;
    public JPanel saveMoleculeListPanel;
    private JPanel quokkaPanel;


    public MolarMassGame() {
        super("MOLAR MASS GAME");

        setUp();
        listNames = new DefaultListModel<>();

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

    private void setUp() {
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();
        animals = new ArrayList<>();

        moleculeList = new MoleculeList("My Molecule List");
    }

    private void createTitleScreen() {
        titlePanel = new JPanel();
        titlePanel.setBounds(65, 100, 670, 75);
        titlePanel.setBackground(Color.pink);

        titleLabel = new JLabel("Hungry Animals @@!!");
        titleLabel.setForeground(Color.blue);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        container.add(titlePanel);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(65, 175, 670, 100);
        startButtonPanel.setBackground(Color.orange);
        startButtonPanel.setLayout(new GridLayout(1, 3));

        mainMenuButtons();

        quokkaPanel = new JPanel();
        quokkaPanel.setBounds(100, 260, 700, 700);
        quokkaPanel.setBackground(Color.lightGray);
        container.add(quokkaPanel);
        ImageIcon imageQuokka = new ImageIcon(getClass().getResource("/resources/images/quokka.png"));
        JLabel quokkaLabel = new JLabel(imageQuokka);
        quokkaPanel.add(quokkaLabel);
    }

    private void mainMenuButtons() {
        JButton startButton = new JButton("NEW GAME");
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
        choice.setForeground(Color.black);
        choice.setFont(normalFont);
    }

    public void createGameScreen() {
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        quokkaPanel.setVisible(false);

        createMainTextScreen();
        createTreatPanel();
        createChoiceScreen();
        createPlayerPanel();
        createSaveButtonPanel();
        createSavedMoleculeListPanel();
        createImage();

    }

    private void createSaveButtonPanel() {
        saveButtonPanel = new JPanel();
        saveButtonPanel.setBounds(500, 15, 200, 45);
        saveButtonPanel.setBackground(Color.pink);
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
        saveMoleculeListPanel.setBackground(Color.pink);
        container.add(saveMoleculeListPanel);

        JList savedMoleculeList = new JList(listNames);
        savedMoleculeList.setFont(normalFont);
        savedMoleculeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        JScrollPane moleculeListScroll = new JScrollPane(savedMoleculeList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        saveMoleculeListPanel.add(moleculeListScroll);

        removeButton(savedMoleculeList);
        saveAndReturnButton();
    }

    private void saveAndReturnButton() {
        JButton saveButton = new JButton("SAVE");
        choiceButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMoleculesToList();
            }
        });
        saveMoleculeListPanel.add(saveButton);

        JButton returnButton = new JButton("RETURN");
        choiceButton(returnButton);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visibility.toQuestions();
                newMainTextScreen();
            }
        });
        saveMoleculeListPanel.add(returnButton);
    }

    private void removeButton(JList savedMoleculeList) {
        JButton removeButton = new JButton("REMOVE");
        choiceButton(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = savedMoleculeList.getSelectedIndex();
                if (index >= 0) {
                    System.out.println(listNames.get(index));
                    listNames.remove(index);
                    moleculeList.getMolecules().remove(index);
                }
            }
        });
        saveMoleculeListPanel.add(removeButton);
    }

    private void createMainTextScreen() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 100, 650, 150);
        mainTextPanel.setBackground(Color.gray);
        container.add(mainTextPanel);

        molecularQuiz = new MolecularQuiz();
        randomMolecule = molecularQuiz.getRandomMolecule();

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.GRAY);
        mainTextArea.setForeground(Color.black);
        mainTextArea.setFont(normalFont);
        mainTextArea.setText("        Please enter the molecular weight of the molecule:                        "
                + "                      " + randomMolecule.getFormula());

        String currentMolecule = Integer.toString(randomMolecule.getMolarMass());
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        userInput(currentMolecule);

    }

    private void userInput(String currentMolecule) {
        JTextField userText = new JTextField(20);
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

    private void createImage() {
        imagePanel = new JPanel();
        imagePanel.setBounds(50, 100, 700, 700);
        imagePanel.setBackground(Color.lightGray);
        container.add(imagePanel);

        String text = "<html>Hello! <br/>Welcome to the molar mass calculation game!<br/>The goal of this game is "
                + "to feed three adorable<br/>  animals full. Their satisfaction rate is<br/> shown above! "
                + "You will be able to feed them a treat<br/> everytime you get an answer right!<br/>"
                + " The first one up is a cute little puppy!<br/> Good Luck!</html>";
        JLabel instructions = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
        
        instructions.setFont(normalFont);
        imagePanel.add(instructions);

        ImageIcon imageDog = new ImageIcon(getClass().getResource("/resources/images/IMG_5683.png"));
        JLabel dogLabel = new JLabel(imageDog);
        imagePanel.add(dogLabel);

        JButton choice1 = new JButton("LET'S BEGIN!");
        choice1.setBounds(350, 280, 50, 30);
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visibility.toQuestions();
            }
        });
        imagePanel.add(choice1);

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
        treatPanel.setBounds(175, 250, 430, 200);
        treatPanel.setBackground(Color.lightGray);
        treatPanel.setLayout(new GridLayout(6, 1));

        JLabel pickLabel = new JLabel(("Congratulations:D Please pick a treat!"));
        pickLabel.setBounds(100, 250, 20, 10);
        pickLabel.setFont(normalFont);
        pickLabel.setForeground(Color.black);
        treatPanel.add(pickLabel);
        container.add(treatPanel);

        treatButtonsWhole();

    }

    private void treatButtonsWhole() {
        JButton choiceApple = new JButton("APPLE");
        treatButtonsHelper(choiceApple, "apple");
        treatPanel.add(choiceApple);

        JButton choiceBone = new JButton("BONE");
        treatButtonsHelper(choiceBone, "bone");
        treatPanel.add(choiceBone);

        JButton choiceCarrot = new JButton("CARROT");
        treatButtonsHelper(choiceCarrot, "carrot");
        treatPanel.add(choiceCarrot);

        JButton choiceFish = new JButton("FISH");
        treatButtonsHelper(choiceFish, "fish");
        treatPanel.add(choiceFish);

        JButton choiceLeaf = new JButton("LEAF");
        treatButtonsHelper(choiceLeaf, "leaf");
        treatPanel.add(choiceLeaf);
    }

    private void treatButtonsHelper(JButton choiceCarrot, String carrot) {
        choiceButton(choiceCarrot);
        choiceCarrot.addActionListener(treatHandler);
        choiceCarrot.setActionCommand(carrot);
    }

    private void createChoiceScreen() {
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(200, 250, 400, 200);
        choiceButtonPanel.setBackground(Color.lightGray);
        choiceButtonPanel.setLayout(new GridLayout(6, 1));

        sideNotesLabelSaveGame = new JLabel();
        sideNotesLabelSaveGame.setBounds(475, 463, 20, 10);
        sideNotesLabelSaveGame.setFont(normalFont);
        sideNotesLabelSaveGame.setForeground(Color.black);
        choiceButtonPanel.add(sideNotesLabelSaveGame);
        container.add(choiceButtonPanel);

        optionMenuButtons();
    }

    private void optionMenuButtons() {
        JButton choice1 = new JButton("YES");
        choices(choice1, choice1, "yes");
        choiceButtonPanel.add(choice1);

        JButton choice2 = new JButton("NO");
        choices(choice2, choice2, "no");
        choiceButtonPanel.add(choice2);

        JButton choice3 = new JButton("SAVE GAME");
        choices(choice3, choice3, "save");
        choiceButtonPanel.add(choice3);

        JButton choice4 = new JButton("EXIT");
        choices(choice4, choice4, "exit");
        choiceButtonPanel.add(choice4);

        JButton choice5 = new JButton("RETURN TO MAIN MENU");
        choices(choice5, choice5, "return");
        choiceButtonPanel.add(choice5);
    }


    private void choices(JButton choice1, JButton choice12, String add) {
        choiceButton(choice1);
        choice12.addActionListener(choiceHandler);
        choice12.setActionCommand(add);
    }


    private void createPlayerPanel() {
        playerPanel = new JPanel();
        playerPanel.setBounds(50, 15, 350, 50);
        playerPanel.setBackground(Color.lightGray);
        playerPanel.setLayout(new GridLayout(1, 4));
        container.add(playerPanel);

        pointLabel = new JLabel("Satisfaction: ");
        pointLabel.setFont(normalFont);
        pointLabel.setForeground(Color.DARK_GRAY);
        playerPanel.add(pointLabel);
        pointLabelNum = new JLabel();
        pointLabelNum.setFont(normalFont);
        pointLabelNum.setForeground(Color.DARK_GRAY);
        playerPanel.add(pointLabelNum);
        playerSetUp();
    }

    public void playerSetUp() {
        playerPoints = animalPointsReturn();
        pointLabelNum.setText("" + playerPoints + " %");
        showQuestion();
    }

    public void showQuestion() {
        mainTextArea.setText("        Please enter the molecular weight of the molecule:                        "
                + "                      " + randomMolecule.getFormula());
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
                    visibility.startToInstructions();
                    reset();
                    break;
                case "quit":
                    visibility.goodbyeScreen();
                    titleLabel.setText("Goodbye!");
                    break;
                case "load":
                    visibility.toQuestions();
                    reset();
                    loadMolecules();
                    loadGame();
                    break;
            }
        }

        private void reset() {
            setUp();
            remove(playerPanel);
            createPlayerPanel();
            revalidate();
            repaint();
        }
    }

    private void saveGame() {

        animals.add(dog);
        animals.add(cat);
        animals.add(quokka);

        try {
            JsonWriter jsonWriterAnimal = new JsonWriter(JSON_STORE_ANIMALS);
            jsonWriterAnimal.open();
            jsonWriterAnimal.write(animals);
            jsonWriterAnimal.close();
            System.out.println("Saved to " + JSON_STORE_ANIMALS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ANIMALS);
        }
    }

    private void saveMoleculesToList() {
        MoleculeEntered moleculeEntered = new MoleculeEntered(randomMolecule.getFormula(), Category.EASY);

        if (!listNames.contains(moleculeEntered.getName())) {
            moleculeList.addMolecule(moleculeEntered);
            listNames.addElement(moleculeEntered.getName());

        }
        try {
            JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(moleculeList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            //
        }
    }

    private void loadGame() {
//TODO json reader
        try {
            JsonReader jsonReaderAnimal = new JsonReader(JSON_STORE_ANIMALS);
            List<Animals> read = jsonReaderAnimal.readAnimal();
            dog = (Dog) read.get(0);
            cat = (Cat) read.get(1);
            quokka = (Quokka) read.get(2);
            pointLabelNum.setText(animalPointsReturn() + " %");
        } catch (IOException e) {
            titleLabel.setText("Unable to read from file: " + JSON_STORE_ANIMALS);
        }
    }

    private void loadMolecules() {
        try {
            JsonReader jsonReader = new JsonReader(JSON_STORE);
            moleculeList = jsonReader.read();
            List<MoleculeEntered> molecules = moleculeList.getMolecules();
            for (int i = 0; i < molecules.size(); i++) {
                listNames.addElement(molecules.get(i).getName());
            }
        } catch (IOException e) {
            //
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
            titlePanel.setBackground(Color.lightGray);
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
                    saveMoleculesToList();
                    sideNotesLabelSaveGame.setText("Saved to My Molecule List!");
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
                case "return":
                    visibility.showTitleScreen();
                    returnHelper();
            }


        }

        private void returnHelper() {
            titleLabel.setFont(titleFont);
            titleLabel.setText("MOLAR MASS GAME");
        }
    }

}
