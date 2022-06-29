package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.BoardHandler;
import it.polimi.ingsw.server.Client;
import it.polimi.ingsw.client.gameBoard.GameBoard;
import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.Answer;
import it.polimi.ingsw.server.servermessages.CustomMessage;

import java.lang.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientView {
    private final CLI cli;
    private final GUI gui;
    private String nickname;
    private String currentPlayer;
    private Wizard wizard;
    private List<IslandTile> islands;
    private List<Cloud> clouds;

    private Answer answer;
    private int phase;
    private int turnPhase;
    private boolean inputEnabler;
    private Tower tower;

    public void setAssistantCards() {
        Collections.addAll(this.assistantCards, AssistantCard.values());
    }

    private ArrayList<AssistantCard> assistantCards;
    private boolean characterCardActive;
    //private GameBoard gameBoard;
    private String characterCardInfo;
    private boolean turnActive;


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
        //gameBoard = new GameBoard();
        gui = null;
    }

    public ClientView(GUI gui) {
        this.gui = gui;
        //gameBoard = new GameBoard();
        cli = null;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public CLI getCli() {
        return cli;
    }

    public GUI getGui() {return gui; }

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

    public int getTurnPhase() { return turnPhase; }

    public void setTurnPhase(int turnPhase) { this.turnPhase = turnPhase; }

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

    public boolean isTurnActive() { return turnActive; }

    public void setTurnActive(boolean turnActive) { this.turnActive = turnActive; }

    public void setCharacterEffectInfo(String characterCardInfo) {
        if (characterCardInfo.length() > 100) {
            String tmpOne = characterCardInfo.substring(0, 100);
            String tmpTwo = characterCardInfo.substring(100);
            if (Character.toString(tmpTwo.charAt(0)).equals(" ")) {
                characterCardInfo = tmpOne + "\n" + tmpTwo.substring(1);
            } else {
                characterCardInfo = tmpOne + "-" + "\n" + tmpTwo;
            }
        }
        this.characterCardInfo = characterCardInfo;
    }

    public String getCharacterCardInfo() {
        return characterCardInfo;
    }

   /* public synchronized GameBoard getBoard() { return gameBoard; }*/

    public boolean isCharacterCardActive() { return characterCardActive; }

    public void setCharacterCardActive(boolean characterCardActive) { this.characterCardActive = characterCardActive; }

    public String getCurrentPlayer() { return currentPlayer; }

    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }
}
