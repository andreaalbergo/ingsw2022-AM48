package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.exceptions.GameOverException;
import it.polimi.ingsw.exceptions.InvalidSelection;

import java.util.*;

public enum CharacterCard {
    MONK, INNKEEPER,
    HERALD, CENTAUR,
    JESTER, KNIGHT,
    MERCHANT, WARRIOR_PRINCESS;

    private static final Map<CharacterCard, Integer> chosen = new HashMap<>();
    private static int[] monkStudents = new int[5];
    private static int[] warriorPrincessStudents = new int[5];
    private static int[] jesterStudents = new int[5];
    private static boolean isActive = false;

    //board is null
    private static Board board;

    public static void chooseThreeRandomCharacters() throws GameOverException {
        List<CharacterCard> VALUES = new ArrayList<>(Arrays.asList(CharacterCard.values()));
        Collections.shuffle(VALUES);

        for (int i = 0; i < 3; i++) {
            addCharacterCard(VALUES.get(0));
            VALUES.remove(0);
        }
    }

    public static void addCharacterCard(CharacterCard characterCard) throws GameOverException {
        switch (characterCard) {
            case MONK, HERALD, JESTER -> chosen.put(characterCard, 1);
            case  INNKEEPER, KNIGHT, WARRIOR_PRINCESS -> chosen.put(characterCard, 2);
            default -> chosen.put(characterCard, 3);
        }

        switch (characterCard){
            case MONK -> setUpCards(MONK);
            case WARRIOR_PRINCESS -> setUpCards(WARRIOR_PRINCESS);
            case JESTER -> setUpCards(JESTER);
        }
    }

    public static Map<CharacterCard, Integer> getCharacterCardsList(){
        return chosen;
    }

    public static void setUpCards(CharacterCard characterCard) throws GameOverException {

        if(characterCard == MONK){
            for(int i = 0; i < 4; i++){
                monkStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;
            }
        }
        if(characterCard == JESTER){
            for(int i = 0; i < 6; i++){
                jesterStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;
            }
        }
        if(characterCard == WARRIOR_PRINCESS){
            for(int i = 0; i < 4; i++){
                warriorPrincessStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;
            }
        }

    }

    public static int getCost(CharacterCard characterCard){

        return chosen.get(characterCard);

    }

    public static void setCost(CharacterCard characterCard){
        chosen.replace(characterCard, chosen.get(characterCard) + 1);
    }

    public static void chosenCard(CharacterCard characterCard) throws GameOverException, InvalidSelection {

        setIsActive(true);
        setCost(characterCard);

        switch (characterCard){
            case MONK -> monkEffect();
            case INNKEEPER -> innkeeperEffect();
            case HERALD -> heraldEffect();
            case CENTAUR -> centaurEffect();
            case JESTER -> jesterEffect();
            case KNIGHT -> knightEffect();
            case MERCHANT -> merchantEffect();
            case WARRIOR_PRINCESS -> warriorPrincessEffect();
            default -> throw new InvalidSelection();
        }
    }

    public static void setIsActive(boolean value){
        isActive = value;
    }

    public static boolean getIsActive(){
        return isActive;
    }

    //color and islandTile are from input
    public static void monkEffect() throws GameOverException, InvalidSelection {

        Color color = null;
        IslandTile islandTile = null;

        board.getCurrentPlayer().getSchoolBoard().addStudentToIsland(color, islandTile);
        monkStudents[color.getColorIndex()]--;
        monkStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;


    }

    //connect with gameController
    public static void innkeeperEffect(){

        for(int i = 0; i < 5; i++) {
            //gameController.checkProfessor(Color.colorFromIndex(i), 1);
        }

    }

    //connect with board
    public static void heraldEffect() {

        int steps = 3;

        board.getBoardManager().getMotherNature().move(steps, 2);

    }

    //connect with gameController
    public static void centaurEffect(){

        //gameController.checkIsland(1);

    }

    //removeColorFromEntrance and moveColor are from input
    public static void jesterEffect() throws GameOverException {

        int studentsMoved = 0;
        int counter = 0;
        Color[] removeColorEntrance = null;
        Color[] moveColor = null;

        for(int i = 0; i < 3; i++){
            board.getCurrentPlayer().getSchoolBoard().getEntrance().remove(removeColorEntrance[i]);
            studentsMoved++;

            board.getCurrentPlayer().getSchoolBoard().addStudentToEntrance(moveColor[i].getColorIndex());
            counter++;
            jesterStudents[moveColor[i].getColorIndex()]--;
        }
        /*
        while(studentsMoved < 3){
            board.getCurrentPlayer().getSchoolBoard().getEntrance().remove(removeColorEntrance);
            studentsMoved++;

            if(counter < studentsMoved){
                board.getCurrentPlayer().getSchoolBoard().addStudentToEntrance(moveColor.getColorIndex());
                counter++;
                jesterStudents[moveColor.getColorIndex()]--;
            }
        }
         */

        for(int i = 0; i < counter; i++){
            jesterStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;
        }

    }

    //checkInfluence to connect
    public static void knightEffect(){

        //checkInfluence(island, 2);

    }

    //checkInfluence to connect
    //color is from input
    public static void merchantEffect(){

        Color color = null;
        int effect = color.getColorIndex() + 3;

        //checkInfluence(island, effect);
    }

    //color is from input
    public static void warriorPrincessEffect() throws InvalidSelection, GameOverException {

        Color color = null;

        board.getCurrentPlayer().getSchoolBoard().addStudentToDiningRoom(color);
        warriorPrincessStudents[color.getColorIndex()]--;
        warriorPrincessStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;
    }

    public static int[] getMonkStudents() {
        return monkStudents;
    }

    public static int[] getWarriorPrincessStudents() {
        return warriorPrincessStudents;
    }

    public static int[] getJesterStudents() {
        return jesterStudents;
    }
}
