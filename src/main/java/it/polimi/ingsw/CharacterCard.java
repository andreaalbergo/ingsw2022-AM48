package it.polimi.ingsw;

//HOW DO I CREATE CHARACTER CARDS RANDOMLY WITHOUT HAVING SIMILAR OBJECTS? NICE QUESTION...I SHOULD FIX THIS ISSUE

public class CharacterCard {
    private final Character character;
    private int characterEffectCost;
    /* BOZZ
    insert boolean parameter isActive for keeping track of when a card has been purchased
     */

    public CharacterCard() {
        this.character = Character.getRandomCharacter();
        this.characterEffectCost = Character.getCharacterEffectCost(this.character);
    }

    public int getCharacterEffectCost() {
        return characterEffectCost;
    }

    public void chooseCard(CharacterCard characterCard) {
        characterCard.incrementCharacterCost();
        switch(characterCard.character) {
            case MONK -> monkEffect();
            case INNKEEPER -> innkeeperEffect();
            case PRINCE -> princeEffect();
            case HERALD -> heraldEffect();
            case GROCER -> grocerEffect();
            case CENTAUR -> centaurEffect();
            case JESTER -> jesterEffect();
            case KNIGHT -> knightEffect();
            case MERCHANT -> merchantEffect();
            case MINSTREL -> minstrelEffect();
            case WARRIOR_PRINCESS -> warriorPrincessEffect();
            case THIEF -> thiefEffect();
            default -> System.out.println("ERROR --> You have chosen an unknown name!!!");
        }
    }

    private void incrementCharacterCost() {
        characterEffectCost++;
    }

    private void monkEffect() {
        //AT SETUP GAME: implementFourStudentsStorage() and getFromBag()
        //chooseStudentOnMonkCard()
        //chooseIslandId()
        //takeRandomStudentFromBagToMonkCard()
    }

    private void innkeeperEffect() {
        //checkIfSameStudentsOfOtherDiningRoom()
        //takeProfessorsChecked()
        //end of turn, possibly chooseCloud(), give back professors taken
    }

    private void princeEffect() {
        //chooseIslandId()
        //placePseudoMotherNature()
        //checkIslandInfluence()
        //then I can start turn normally by moving the real mother nature
    }

    private void heraldEffect() {
        //addPlusTwoStepsForMotherNature()
    }

    private void grocerEffect() {
        //AT SETUP GAME: implementFourProhibitionStorage()
        //chooseIslandId()
        //wait until mother nature goes on island on prohibition, if so call next method
        //placeProhibitionHolderBack()
        //continuous effect because Prohibition are held on islands, so modify IslandTile
    }

    private void centaurEffect() {
        //during the checkInfluence() phase:
        //nullifyTowerInfluence()
    }

    private void jesterEffect() {
        //AT SETUP GAME: implementSixStudentsStorage() and getFromBag()
        //chooseHowManyStudentsUpToThree()
        //switchEntranceWithThisCard
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
        //AT SETUP GAME: implementFourStudentsStorage() and getFromBag()
        //chooseStudentFromCard()
        //putChosenStudentOnDiningRoom()
        //takeStudentFromBagToThisCard()

    }

    private void thiefEffect() {
        //chooseColor()
        //everyPlayerTakeThreeStudentsFromDiningRoomToBag()
        //if someone has less than three students in Dining Room, take as much as possible
    }
}
