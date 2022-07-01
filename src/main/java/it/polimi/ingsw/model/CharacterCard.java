package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.GameOverException;
import it.polimi.ingsw.exceptions.InvalidSelection;

import java.util.*;

/**
 * Enumeration CharacterCard, it is the abstract representation of 8 out of 12 character cards.
 * SORRY BUT I ALREADY KNOW IT. IT IS BADLY IMPLEMENTED, LIKE A LOT!
 *
 * @author Barb David, Edoardo Bozzini
 */
public enum CharacterCard {
    MONK, INNKEEPER,
    HERALD, CENTAUR,
    JESTER, KNIGHT,
    MERCHANT, WARRIOR_PRINCESS;


    private static final Map<CharacterCard, Integer> chosen = new HashMap<>();
    private static final int[] monkStudents = new int[5];
    private static final int[] warriorPrincessStudents = new int[5];
    private static final int[] jesterStudents = new int[5];
    private static boolean isActive = false;
    private static Board board;

    /**
     * Method chooseThreeRandomCharacters gets three random character cards
     * @throws GameOverException when game over is called.
     */
    public static void chooseThreeRandomCharacters() throws GameOverException {
        List<CharacterCard> VALUES = new ArrayList<>(Arrays.asList(CharacterCard.values()));
        Collections.shuffle(VALUES);

        for (int i = 0; i < 3; i++) {
            addCharacterCard(VALUES.get(0));
            VALUES.remove(0);
        }
    }

    /**
     * Method addCharacterCard is used to add a new character card to the chosen Map.
     * @param characterCard of type CharacterCard.
     * @throws GameOverException when game over is called.
     */
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

    /**
     * Method getCharacterCardList is a getter.
     *
     * @return of type Map<>.
     */
    public static Map<CharacterCard, Integer> getCharacterCardsList(){
        return chosen;
    }

    /**
     * Method setUpCards
     * @param characterCard
     * @throws GameOverException
     */
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

    /**
     * Method getCost is a getter.
     *
     * @param characterCard of type CharacterCard.
     * @return of type int.
     */
    public static int getCost(CharacterCard characterCard){

        return chosen.get(characterCard);

    }

    /**
     * Method setCost is a setter.
     *
     * @param characterCard of type CharacterCard.
     */
    public static void setCost(CharacterCard characterCard){
        chosen.replace(characterCard, chosen.get(characterCard) + 1);
    }

    /**
     * Method chosenCard
     *
     * @param characterCard of type CharacterCard.
     *
     * @throws GameOverException when game over is called.
     * @throws InvalidSelection when wrong selection.
     */
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
            default -> System.out.println("Error \nCard not found");
        }
    }

    /**
     * Method setActive is a setter.
     *
     * @param value of type boolean.
     */
    public static void setIsActive(boolean value){
        isActive = value;
    }

    /**
     * Method getIsActive is a getter.
     *
     * @return of type boolean.
     */
    public static boolean getIsActive(){
        return isActive;
    }

    /**
     * Method monkEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
    public static void monkEffect() throws GameOverException, InvalidSelection {

        Color color = null;
        IslandTile islandTile = null;

        board.getCurrentPlayer().getSchoolBoard().addStudentToIsland(color, islandTile);
        monkStudents[color.getColorIndex()]--;
        monkStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;

    }

    /**
     * Method innkeeperEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
    public static void innkeeperEffect(){

        Color color = null;

        //checkToAddProfessor(color, 1);

    }

    /**
     * Method heraldEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
    public static void heraldEffect() {

        int steps = 3;

        board.getBoardManager().getMotherNature().move(steps, 2);

    }

    /**
     * Method centaurEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
    public static void centaurEffect(){

        //checkInfluence(island, 1);

    }

    /**
     * Method jesterEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
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

    //checkInfluence to connect --> where is
    public static void knightEffect(){

        //checkInfluence(island, 2);

    }

    /**
     * Method merchantEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
    public static void merchantEffect(){

        Color color = null;
        int effect = color.getColorIndex() + 3;

        //checkInfluence(island, effect);
    }

    /**
     * Method warriorPrincessEffect.
     *
     * @throws GameOverException when game is over.
     * @throws InvalidSelection when input is invalid.
     */
    public static void warriorPrincessEffect() throws InvalidSelection, GameOverException {

        Color color = null;

        board.getCurrentPlayer().getSchoolBoard().addStudentToDiningRoom(color);
        warriorPrincessStudents[color.getColorIndex()]--;
        warriorPrincessStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;
    }

    public static int[] getMonkStudents() {
        return monkStudents;
    }

    /**
     * Method getWarriorPrincessStudents is a getter.
     *
     * @return of type int[].
     */
    public static int[] getWarriorPrincessStudents() {
        return warriorPrincessStudents;
    }

}
