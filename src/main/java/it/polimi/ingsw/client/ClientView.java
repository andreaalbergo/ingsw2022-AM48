package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.BoardHandler;
import it.polimi.ingsw.server.Client;
import it.polimi.ingsw.server.servermessages.Answer;
import it.polimi.ingsw.server.servermessages.CustomMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientView {

    private final CLI cli;
    //private final GUI gui;
    private String nickname;

    private Wizard wizard;

    private List<IslandTile> islands;


    private List<Cloud> clouds;

    private Answer answer;

    private int phase = 0;

    private boolean inputEnabler;
    private Tower tower;

    public void setAssistantCards() {
        Collections.addAll(this.assistantCards, AssistantCard.values());
    }

    private ArrayList<AssistantCard> assistantCards;

    private AssistantCard chosenCard = null;

    public AssistantCard getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(AssistantCard chosenCard) {
        if(chosenCard == null){
            setAssistantCards();
        }
        this.chosenCard = chosenCard;
        assistantCards.remove(chosenCard);
    }

    public List<IslandTile> getIslands() {
        return islands;
    }

    public void setIslands(List<IslandTile> islands) {
        this.islands = islands;
    }

    public List<Cloud> getClouds() {
        return clouds;
    }

    public void setClouds(List<Cloud> clouds) {
        this.clouds = clouds;
    }

    public void setWizard(String wizard) {
        String magician = wizard.toLowerCase();
        //System.out.println("I AM IN SETWIZARD METHOD");
        //System.out.println("THIS IS THE WIZARD CHOSEN: "+wizard);
        switch (magician){
            case "druid" : this.wizard = Wizard.DRUID;
            case "witch" : this.wizard = Wizard.WITCH;
            case "emir" : this.wizard = Wizard.EMIR;
            case "samurai" : this.wizard = Wizard.SAMURAI;
        }

        this.wizard = Wizard.parseInput(wizard);
    }

    public void setTower(String tower) {
        String compare = tower.toLowerCase();
        switch (compare){
            case  "white": this.tower = Tower.WHITE;
            case  "black" : this.tower = Tower.BLACK;
            case  "grey" : this.tower = Tower.GREY;
        }
    }

    public ClientView(CLI cli) {
        this.cli = cli;
    }
    /*
    public ClientView(GUI gui){
        this.gui = gui;
    }

     */


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


    public Tower getTower() {
        return tower;
    }

    public Wizard getWizard() {
        return wizard;
    }
}
