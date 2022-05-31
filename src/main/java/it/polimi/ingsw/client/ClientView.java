package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.Answer;

public class ClientView {

    private final CLI cli;
    private String nickname;

    private Wizard wizard;

    private Answer answer;

    private int phase = 0;

    private boolean inputEnabler;

    public void setWizard(String wizard) {
        switch (wizard){
            case "wizard1" : this.wizard = Wizard.WIZARD1;
            case "wizard2" : this.wizard = Wizard.WIZARD2;
            case "wizard3" : this.wizard = Wizard.WIZARD3;
            case "wizard4" : this.wizard = Wizard.WIZARD4;
        }
    }

    public void setTower(String tower) {
        String compare = tower.toLowerCase();
        switch (tower){
            case  "white": this.wizard = Wizard.WIZARD1;
            case  "black" : this.wizard = Wizard.WIZARD2;
            case  "grey" : this.wizard = Wizard.WIZARD3;
        }
    }


    public ClientView(CLI cli) {
        this.cli = cli;
    }


    public void setAnswer(Answer answer) {
        this.answer = answer;
    }


    public CLI getCli() {
        return cli;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }


    public Answer getAnswer() {
        return answer;
    }

    public boolean isInputEnabler() {
        return inputEnabler;
    }

    public void setInputEnabler(boolean inputEnabler) {
        this.inputEnabler = inputEnabler;
    }
}
