package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;
import it.polimi.ingsw.client.gameBoard.GameBoard;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.Answer;

import java.lang.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Class ClientView is the personal view of a player in which he gets the printed CLI game board during every change
 * of the main game board.
 *
 * @author Andrea Albergo, David Barb
 */
public class ClientView {

    private boolean firstStart = true;
    private final CLI cli;
    private final GUI gui;
    private String nickname;
    private String currentPlayer;
    private Wizard wizard;
    private List<IslandTile> islands;
    private List<Cloud> clouds;
    private HashMap<String,Tower> nameToTower;
    private Answer answer;
    private int phase;
    private int turnPhase;
    private boolean inputEnabler;
    private Tower tower;
    private GameBoard gameBoard;
    private boolean[] professor;

    private ArrayList<AssistantCard> assistantCards;
    private boolean characterCardActive;
    private String characterCardInfo;
    private boolean turnActive;
    private HashMap<String,List<Color>> nameToEntrance;
    private HashMap<String,int[]> nameToDining;
    private List<Color> entrance;
    private AssistantCard chosenCard = null;

    /**
     * Method setEntrance is a setter.
     *
     * @param entrance of type List<> - list of color from entrance hall.
     */
    public void setEntrance(List<Color> entrance) {
        this.entrance = entrance;
        System.out.println("Check da client view: Ho settato l'entrance");
    }

    /**
     * Method getEntrance is a getter.
     *
     * @return of type List<> - list of color form entrance hall.
     */
    public List<Color> getEntrance() {
        return entrance;
    }

    /**
     * Method setAssistantCards is a setter. No input needed.
     */
    public void setAssistantCards() {
        Collections.addAll(this.assistantCards, AssistantCard.values());
    }


    /**
     * Method getProfessor is a getter.
     *
     * @return of type boolean[].
     */
    public boolean[] getProfessor() {
        return professor;
    }

    /**
     * Method setProfessor is a setter.
     *
     * @param index of type int - index of the array.
     * @param check of type boolean - true if we need to assign professor to the player.
     */
    public void setProfessor(int index, boolean check) {
        this.professor[index] = check;
        assert cli != null;
        cli.getGameBoard().getSchoolFromNickname(getNickname()).setProfessor(professor[index], index);
        cli.getGameBoard().printCLI();
    }



    /**
     * Method getChosenCard is a getter.
     *
     * @return of type AssistantCard.
     */
    public AssistantCard getChosenCard() {
        return chosenCard;
    }

    /**
     * Method setChosenCard is a setter.
     *
     * @param chosenCard of type AssistantCard.
     */
    public void setChosenCard(AssistantCard chosenCard) {
        if(chosenCard == null){
            setAssistantCards();
        }
        this.chosenCard = chosenCard;
        assistantCards.remove(chosenCard);
    }

    /**
     * Method getIslands is a getter.
     *
     * @return of type List<> - list of islands.
     */
    public List<IslandTile> getIslands() {
        return islands;
    }

    /**
     * Method setIsland is setter.
     *
     * @param islands of type List<> - list of islands.
     */
    public void setIslands(List<IslandTile> islands) {
        this.islands = islands;
        cli.getGameBoard().setArchipelagoGrid(islands);
    }

    /**
     * Method getClouds is a getter.
     *
     * @return of type List<> - list of clouds.
     */
    public List<Cloud> getClouds() {
        return clouds;
    }

    /**
     * Method setClouds is a setter.
     *
     * @param clouds of type List<> - list of clouds.
     */
    public void setClouds(List<Cloud> clouds) {
        this.clouds = clouds;
        assert cli != null;
        cli.getGameBoard().setCloudGrid(clouds);
    }

    /**
     * Method setWizard is a setter.
     *
     * @param wizard of type Wizard.
     */
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

    public HashMap<String, int[]> getNameToDining() {
        return nameToDining;
    }

    /**
     * Method updateDining updates the dining room of a given player and array of int.
     *
     * @param nickname of type String.
     * @param dining of type int[].
     */
    public void updateDining(String nickname, int[] dining){
        if(!nameToDining.containsKey(nickname))
            nameToDining.put(nickname, dining);
        else {
            nameToDining.replace(nickname, dining);
        }
        cli.getGameBoard().getSchoolFromNickname(nickname).setDining(dining);
    }

    /**
     * Method updateEntrance updates the entrance hall of a given player and list of colors.
     *
     * @param nickname of type String.
     * @param entrance of type List<> - list of colors.
     */
    public void updateEntrance(String nickname, List<Color> entrance){
        if(!nameToEntrance.containsKey(nickname)) {
            nameToEntrance.put(nickname,entrance);
        }else nameToEntrance.replace(nickname, entrance);
        cli.getGameBoard().getSchoolFromNickname(nickname).setEntrance(entrance);
    }

    /**
     * Method setTower is a setter.
     *
     * @param tower of type String.
     */
    public void setTower(String tower) {
        String compare = tower.toLowerCase();
        switch (compare){
            case  "white": this.tower = Tower.WHITE;
            case  "black" : this.tower = Tower.BLACK;
            case  "grey" : this.tower = Tower.GREY;
        }
        System.out.println("Check da client view: Ho settato la torre");
    }

    /**
     * Constructor ClientView that creates its instance.
     *
     * @param cli of type CLI - given player's CLI.
     */
    public ClientView(CLI cli) {
        this.cli = cli;
        //gameBoard = new GameBoard();
        gui = null;
        nameToEntrance = new HashMap<>();
        nameToDining = new HashMap<>();
        nameToTower = new HashMap<>();
        professor = new boolean[5];
        assistantCards = new ArrayList<>();
    }
/*
    public ClientView(GUI gui) {
        this.gui = gui;
        //gameBoard = new GameBoard();
        cli = null;
    }


 */

    /**
     * Method getAnswer is a getter.
     *
     * @return of type Answer.
     */
    public Answer getAnswer() {
        return answer;
    }

    /**
     * Method setAnswer is a setter.
     *
     * @param answer of type Answer.
     */
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    /**
     * Method getCLI is a getter.
     *
     * @return of type CLI.
     */
    public CLI getCli() {
        return cli;
    }

    /**
     * Method getGUI is a getter.
     *
     * @return of type GUI.
     */
    public GUI getGui() {return gui; }

    /**
     * Method setName is a setter.
     *
     * @param name of type String.
     */
    public void setName(String name) {
        this.nickname = name;
    }

    /**
     * Method getNickname is a getter.
     *
     * @return of type String.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method getPhase is a getter.
     *
     * @return of type int.
     */
    public int getPhase() {
        return phase;
    }

    /**
     * Method setPhase is a setter.
     *
     * @param phase of type int.
     */
    public void setPhase(int phase) {
        this.phase = phase;
    }

    /**
     * Method getTurnPhase is a getter.
     *
     * @return of type int.
     */
    public int getTurnPhase() { return turnPhase; }

    /**
     * Method setTurnPhase is a setter.
     *
     * @param turnPhase of type int.
     */
    public void setTurnPhase(int turnPhase) { this.turnPhase = turnPhase; }

    /**
     * Method isInputEnabler is a getter.
     *
     * @return of type boolean.
     */
    public boolean isInputEnabler() {
        return inputEnabler;
    }

    /**
     * Method setInputEnabler is a setter.
     *
     * @param inputEnabler of type boolean.
     */
    public void setInputEnabler(boolean inputEnabler) {
        this.inputEnabler = inputEnabler;
    }

    /**
     * Method getTower is a getter.
     *
     * @return of type Tower.
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Method getWizard is a getter.
     *
     * @return of Wizard.
     */
    public Wizard getWizard() {
        return wizard;
    }

    /**
     * Method setTurnActive is a setter.
     *
     * @param turnActive of type boolean.
     */
    public void setTurnActive(boolean turnActive) { this.turnActive = turnActive; }

    /**
     * Method updateNameToTower updates tha nameToTower HashMap.
     *
     * @param name of type String.
     * @param tower of type Tower.
     */
    public void updateNameToTower(String name, Tower tower) {
        if(!nameToTower.containsKey(name)) {
            nameToTower.put(name, tower);

        } else nameToTower.replace(name,tower);
        cli.getGameBoard().insertNicknameToTower(name, tower);
    }

    /*
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

    public boolean isCharacterCardActive() { return characterCardActive; }

    public void setCharacterCardActive(boolean characterCardActive) { this.characterCardActive = characterCardActive; }

    public String getCurrentPlayer() { return currentPlayer; }

    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }

    public HashMap<String, Tower> getNameToTower() {
        return nameToTower;
    }*/
}
