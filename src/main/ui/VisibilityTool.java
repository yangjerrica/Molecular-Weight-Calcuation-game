package ui;


import model.Molecule;
import sun.security.krb5.internal.CredentialsUtil;
import ui.MolarMassGame;

import javax.swing.*;

public class VisibilityTool {

    private MolarMassGame molarMassGame;

    public VisibilityTool(MolarMassGame molarMassGame) {
        this.molarMassGame = molarMassGame;
    }

//    public void showTitleScreen() {
//        molarMassGame.titlePanel.setVisible(true);
//        molarMassGame.startButtonPanel.setVisible(true);
//
//        molarMassGame.mainTextPanel.setVisible(false);
//        molarMassGame.choiceButtonPanel.setVisible(false);
//        molarMassGame.innerOptionPanel.setVisible(false);
//        molarMassGame.playerPanel.setVisible(false);
//        molarMassGame.treatPanel.setVisible(false);
//        molarMassGame.moleculeListPanel.setVisible(false);
//
//    }

    public void goodbyeScreen() {
        molarMassGame.titlePanel.setVisible(true);
        molarMassGame.startButtonPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(false);
    //    molarMassGame.innerOptionPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(false);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
        molarMassGame.moleculeListPanel.setVisible(false);
    }

    public void toQuestions() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(true);
        molarMassGame.choiceButtonPanel.setVisible(false);
  //      molarMassGame.innerOptionPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
        molarMassGame.moleculeListPanel.setVisible(false);
    }

    public void questionsToTreats() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(true);
        molarMassGame.choiceButtonPanel.setVisible(false);
//        molarMassGame.innerOptionPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(true);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
        molarMassGame.moleculeListPanel.setVisible(false);
    }

    public void treatsToOptions() {
        molarMassGame.titlePanel.setVisible(true);
        molarMassGame.startButtonPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(true);
//        molarMassGame.innerOptionPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
        molarMassGame.moleculeListPanel.setVisible(false);

    }

    public void optionToSaveList() {
        molarMassGame.titlePanel.setVisible(true);
        molarMassGame.startButtonPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(false);
        molarMassGame.choiceButtonPanel.setVisible(false);
     //   molarMassGame.innerOptionPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(false);
        molarMassGame.moleculeListPanel.setVisible(true);

    }

    public void selectSavedList() {
        molarMassGame.titlePanel.setVisible(false);
        molarMassGame.startButtonPanel.setVisible(false);

        molarMassGame.mainTextPanel.setVisible(true);
        molarMassGame.choiceButtonPanel.setVisible(false);
        //      molarMassGame.innerOptionPanel.setVisible(false);
        molarMassGame.playerPanel.setVisible(true);
        molarMassGame.treatPanel.setVisible(false);
        molarMassGame.saveMoleculeListPanel.setVisible(true);
        molarMassGame.moleculeListPanel.setVisible(false);
    }
}

