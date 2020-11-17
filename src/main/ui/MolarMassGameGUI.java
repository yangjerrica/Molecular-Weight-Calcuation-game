package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MolarMassGameGUI extends JFrame {
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    private Container container;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private Font titleFont = new Font("Times New Roman", Font.BOLD, 56);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private JPanel startButtonPanel;
    private JPanel playerPanel;
    private JLabel pointLabel;
    private JLabel pointLabelNum;
    private JPanel mainTextPanel;
    private JTextArea mainTextArea;
    private Molecule randomMolecule;
    private final MolecularQuiz molecularQuiz;
    private TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
    private JPanel choiceButtonPanel;
    private int playerPoints;
    private Dog dog;
    private Cat cat;
    private Quokka quokka;
    private ChoiceHandler choiceHandler = new ChoiceHandler();


    public MolarMassGameGUI() {
        super("MOLAR MASS GAME");

        molecularQuiz = new MolecularQuiz();
        randomMolecule = molecularQuiz.getRandomMolecule();
        dog = new Dog();
        cat = new Cat();
        quokka = new Quokka();

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);
        container = this.getContentPane();
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

        setVisible(true);

    }

    private void choiceButton(JButton choice) {
        choice.setBackground(Color.black);
        choice.setForeground(Color.blue);
        choice.setFont(normalFont);
    }

    public void createGameScreen() {
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);


        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 100, 650, 150);
        mainTextPanel.setBackground(Color.blue);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.white);
        mainTextArea.setForeground(Color.black);
        mainTextArea.setFont(normalFont);
        mainTextArea.setText("Please enter the molecular weight of the molecule:   "
                + randomMolecule.getFormula());
//        String currentMolecule = Integer.toString(randomMolecule.getMolarMass());
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(200, 263, 450, 200);
        choiceButtonPanel.setBackground(Color.red);
        choiceButtonPanel.setLayout(new GridLayout(5, 1));
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



    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            createGameScreen();

            String titleScreen = event.getActionCommand();
            switch (titleScreen) {
                case "start":
                    //visibility.toQuestions();
                    break;
                case "quit":
                    // visibility.goodbyeScreen();
                    //titleScreenPanel.titleLabel.setText("Goodbye!");
                    break;
                case "load":
//                    visibility.toQuestions();
//                    try {
//                        jsonReaderAnimal.readAnimal();
//                    } catch (IOException e) {
//                        titleScreenPanel.titleLabel.setText("Unable to read from file: " + JSON_STORE_ANIMALS);
//                    }
                    break;
            }
        }
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String quesHandler = e.getActionCommand();
            switch (quesHandler) {
                case "yes":
//                    visibility.optionToSaveList();
//                    changeTitleLabel("Please select a level for your Molecule.");
                    break;
                case "no":
                   // visibility.toQuestions();
                    break;
                case "save":
//                    visibility.treatsToOptions();
//                    try {
//                        saveToJson();
//                    } catch (FileNotFoundException e) {
//                        System.out.println("Unable to write to file: " + JSON_STORE_ANIMALS);
//                    }
                    break;
                case "exit":
//                    visibility.goodbyeScreen();
//                    changeTitleLabel("Goodbye! Have a nice day :D!");
                    break;
            }


        }
    }

}
