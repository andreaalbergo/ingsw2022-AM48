package it.polimi.ingsw.model;

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

    //board is null --> to connect in some way
    private static Board board;

    public static void chooseThreeRandomCharacters() {
        List<CharacterCard> VALUES = new ArrayList<>(Arrays.asList(CharacterCard.values()));
        Collections.shuffle(VALUES);

        for (int i = 0; i < 3; i++) {
            addCharacterCard(VALUES.get(0));
            VALUES.remove(0);
        }
    }

    public static void addCharacterCard(CharacterCard characterCard) {
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

    public static void setUpCards(CharacterCard characterCard){

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

    public static void chosenCard(CharacterCard characterCard){

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

    public static void setIsActive(boolean value){
        isActive = value;
    }

    public static boolean getIsActive(){
        return isActive;
    }

    //color and islandTile are from input --> how to declare??
    public static void monkEffect(){

        Color color = null;
        IslandTile islandTile = null;

        board.getCurrentPlayer().getSchoolBoard().addStudentToIsland(color, islandTile);
        monkStudents[color.getColorIndex()]--;
        monkStudents[board.getBoardManager().getBag().getRandomColorFromBag()]++;

    }

    //checkProfessor() to connect --> where is
    //color is from input --> how to declare??
    public static void innkeeperEffect(){

        Color color = null;

        //checkToAddProfessor(color, 1);

    }

    //steps is from input --> how to declare??
    public static void heraldEffect() {

        int steps = 3;

        board.getBoardManager().getMotherNature().move(steps, 2);

    }

    //checkInfluence to connect --> where is
    //islandTile is from input --> how to declare??
    public static void centaurEffect(){

        //checkInfluence(island, 1);

    }

    //removeColorFromEntrance and moveColor are from input --> how to declare??
    public static void jesterEffect() {

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

    //checkInfluence to connect --> where is
    //color is from input --> how to declare??
    public static void merchantEffect(){

        Color color = null;
        int effect = color.getColorIndex() + 3;

        //checkInfluence(island, effect);
    }

    //color is from input --> how to declare??
    public static void warriorPrincessEffect(){

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
