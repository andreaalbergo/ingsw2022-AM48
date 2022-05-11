package it.polimi.ingsw;

import java.util.ArrayList;

public class CharacterCard {

    private Character character;
    private int characterEffectCost;
    private ArrayList<Character> characterList;
    private ArrayList<Integer> characterCostList = new ArrayList<>(3);
    private boolean isActive = false;
    private Bag bag;
    private Color color;
    private int firstMonk = 0, firstJester = 0, firstWarriorPrincess = 0; //look if the cost is equal to the initial cost

    public CharacterCard(){

        characterList = Character.extractCards();

        for(int indexExtractedCard = 0; indexExtractedCard < 3; indexExtractedCard++){

            characterCostList.add(characterList.get(indexExtractedCard).getCharacterEffectCost());

        }

        character = characterList.get(0);
        characterEffectCost = characterCostList.get(0);

    }

    private int extractedCardIndex(Character card) throws Exception {

        for(int i = 0; i < 3; i++){

            if(card.equals(characterList.get(i))){

                character = characterList.get(i);
                characterEffectCost = characterCostList.get(i);
                return i;

            }

        }

        throw new Exception("Card not found");

    }


    public void chooseCharacterCard(Character card) throws Exception {

        int index = extractedCardIndex(card); //throws error if the card chosen isn't in extractedCards arrayList
        incrementCharacterCost(index);
        setActive(true);
        switch(character) {
            //case MONK -> monkEffect();
            case INNKEEPER -> innkeeperEffect();
            //case PRINCE -> princeEffect();
            //case HERALD -> heraldEffect();
            //case GROCER -> grocerEffect();
            //case CENTAUR -> centaurEffect();
            //case JESTER -> jesterEffect();
            case KNIGHT -> knightEffect();
            case MERCHANT -> merchantEffect();
            case MINSTREL -> minstrelEffect();
            case WARRIOR_PRINCESS -> warriorPrincessEffect();
            case THIEF -> thiefEffect();
            default -> System.out.println("ERROR --> You have chosen an unknown name!!!");
        }

    }

    private void incrementCharacterCost(int index) {

        characterCostList.set(index, characterEffectCost++);

    }

    //finished
    private void monkEffect(Color color, IslandTile islandTile) {

        int[] studentMonk = new int[5];

        for(int i = 0; i < 5; i++) {
            studentMonk[i] = 0;
        }

        if(firstMonk == 0){
            for(int i = 0; i < 4; i++) {
                //studentMonk[color.getColorIndex(bag.extractSinglePawn())]++;
            }
            firstMonk = 1;
        }

        studentMonk[color.getColorIndex()]--;
        //idIsland.getStudents()[color.getColorIndex()]++;
        //studentMonk[color.getColorIndex(bag.extractSinglePawn())]++;
    }

    //finished
    private void innkeeperEffect() {

        //controls if currentPlayer can receive the professor
        for(int i = 0; i < 5; i++){

            //create the link between this class and BoardManager class
            //SimpleBoardManager.checkToAddProfessor(Color.colorFromIndex(i), 1);

        }
        //these passages below should be done by checkToAddProfessor() in BOARDMANAGER
        //takeProfessorsChecked()
        //end of turn, possibly chooseCloud(), give back professors taken
    }

    //TO RELOOK, MISSING SOMETHING
    private void princeEffect(IslandTile islandTile) {

        boolean influence;

        //chooseIslandId()
        //placePseudoMotherNature()
        //influence = SimpleBoardManager.checkInfluence(island, 1);
        //then I can start turn normally by moving the real mother nature
    }

    //finished
    private void heraldEffect(int steps) {
        //oldBoardManager.chooseStepsMotherNature(steps, 2);
        //effect = 2 is a parameter needed in BoardManager class
    }

    //need to redo this method
    private void grocerEffect(IslandTile islandTile) throws Exception {

        int noEntryTileCounter = 4;

        if(noEntryTileCounter != 0) {
            //island.setNoEntryTile(true, island);
            noEntryTileCounter--;
            //continue doing the game as usual
        }
        else{
            throw new Exception("There are no more No Entry Tile");
        }

        //wait until mother nature goes on island on prohibition, if so call next method --> work on MotherNature Class with the parameter in IslandTile
        //placeProhibitionHolderBack()
        //continuous effect because Prohibition are held on islands, so modify IslandTile
    }

    //finished
    private void centaurEffect(IslandTile islandTile) {
        //SimpleBoardManager.checkInfluence(island, 3);
    }

    private void jesterEffect() throws Exception {

        int[] studentJester = new int[5];
        int studentsToMove = 0; //to change

        for(int i = 0; i < 5; i++) {
            studentJester[i] = 0;
        }

        if(firstJester == 0) {
            for (int i = 0; i < 6; i++) {
                //studentJester[bag.extractSinglePawn()]++;
            }
            firstJester = 1;
        }


        if(studentsToMove > 3){
            throw new Exception("You can't switch more than 3 students");
        }

        for(int i = 0; i < studentsToMove; i++){

            //temporary space
            //moveFromEntrance() to the temporary space
            //moveFromCard() to the entrance
            //moveFromTemporary() to the card

        }

    }

    private void knightEffect() {
        //during checkInfluence() phase:
        //addPlusTwoInfluenceForPlayer()
    }

    private void merchantEffect() {
        //during checkInfluence() phase:
        //selectColor()
        //nullifySelectedColorInfluence()
    }

    private void minstrelEffect() {
        //chooseUpToTwoStudents
        //switchEntranceAndDiningRoom()
    }

    private void warriorPrincessEffect() {

        int[] studentWarriorPrincess = new int[5];

        for(int i = 0; i < 5; i++){
            studentWarriorPrincess[i] = 0;
        }

        if(firstWarriorPrincess == 0){
            for(int i = 0; i < 4; i++) {
                //studentWarriorPrincess[bag.extractSinglePawn()]++;
            }
            firstWarriorPrincess = 1;
        }

        //chooseStudentFromCard()
        //putChosenStudentOnDiningRoom()
        //takeStudentFromBagToThisCard()

    }

    private void thiefEffect() {
        //chooseColor()
        //everyPlayerTakeThreeStudentsFromDiningRoomToBag()
        //if someone has less than three students in Dining Room, take as much as possible
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCharacterEffectCost(CharacterCard card) throws Exception {

        for(int i = 0; i < 3; i++) {
            if(characterList.equals(card)){
                return character.getCharacterEffectCost();
            }
        }

        throw new Exception ("Card played wasn't in game");
    }

    public int getCharacterCostList(Character card) throws Exception {
        return characterCostList.get(extractedCardIndex(card));
    }

    public ArrayList<Character> getCharacterList() {
        return characterList;
    }
}

