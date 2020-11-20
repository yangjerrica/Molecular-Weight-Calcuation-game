package ui;


import model.Molecule;
import sun.security.krb5.internal.CredentialsUtil;
import ui.MolarMassGame;

import javax.swing.*;

//represents the visibility of each screen
public class VisibilityTool {

    private MolarMassGame molarMassGame;

    //EFFECTS: changes the visible panel for different phases
    public VisibilityTool(MolarMassGame molarMassGame) {
        this.molarMassGame = molarMassGame;
    }

    //MODIFIES: this
    //EFFECTS: shows the title screen only
    public void showTitleScreen() {
        molarMassGame.titlePanel.setVisible(true);
        molarMassGame.startButtonPanel.setVisible(true);
        molarMassGame.imagePanel.setVisible(false);
        molarMassGame.saveButtonPanel.setVisible(false);
        molarMassGame.quokkaPanel.setVisible(true);

        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(false);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);

    }

    //MODIFIES: this
    //EFFECTS: shows the goodbye screen only
    public void goodbyeScreen() {
        molarMassGame.titlePanel.setVisible(true);
        molarMassGame.startButtonPanel.setVisible(false);
        molarMassGame.imagePanel.setVisible(false);
        molarMassGame.quokkaPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(false);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
        molarMassGame.saveButtonPanel.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: changes to the question screen
    public void toQuestions() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);
        molarMassGame.imagePanel.setVisible(false);
        molarMassGame.saveButtonPanel.setVisible(true);
        molarMassGame.quokkaPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(true);
        molarMassGame.choiceButtonPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: shows the screen for the treats
    public void questionsToTreats() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);
        molarMassGame.imagePanel.setVisible(false);
        molarMassGame.saveButtonPanel.setVisible(true);
        molarMassGame.quokkaPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(true);
        molarMassGame.choiceButtonPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(true);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: shows the option screen
    public void treatsToOptions() {
        molarMassGame.titlePanel.setVisible(true);
        molarMassGame.startButtonPanel.setVisible(false);
        molarMassGame.imagePanel.setVisible(false);
        molarMassGame.saveButtonPanel.setVisible(true);
        molarMassGame.quokkaPanel.setVisible(false);
        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(true);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);

    }

    //MODIFIES: this
    //EFFECTS: shows the instruction screen
    public void startToInstructions() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);
        molarMassGame.imagePanel.setVisible(true);
        molarMassGame.saveButtonPanel.setVisible(false);
        molarMassGame.quokkaPanel.setVisible(false);
        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(false);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);

    }

    //MODIFIES: this
    //EFFECTS: changes to the saved molecule list screen
    public void selectSavedList() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);
        molarMassGame.imagePanel.setVisible(false);
        molarMassGame.saveButtonPanel.setVisible(true);

        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(true);
    }
}

