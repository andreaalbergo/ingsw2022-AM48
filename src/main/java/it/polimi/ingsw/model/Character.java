/*
package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;

public enum Character {
    /*
    MONK(1, 1), INNKEEPER(2, 2), //PRINCE(3, 3),
    HERALD(3, 1), //GROCER(5, 2),
    CENTAUR(4, 3),
    JESTER(5, 1), KNIGHT(6, 2), MERCHANT(7, 3),
    //MINSTREL(10, 1),
    WARRIOR_PRINCESS(8, 2); //THIEF(12, 3)


    private final int idCharacter;
    private final int characterEffectCost;
    private ArrayList<Integer> values = new ArrayList<>();
    private Random rand = new Random();
    private ArrayList<Character> extractedCards = new ArrayList<>();
    private ArrayList<Integer> extractedCardsCost = new ArrayList<>();
    private int[] monkStudents;
    private int[] jesterStudents;
    private int[] warriorPrincessStudents;
    private boolean isActive;

    //connect character card to simpleboarmanager, bag and schoolboard

    Character(int idCharacter, int characterEffectCost) {

        this.idCharacter = idCharacter;
        this.characterEffectCost = characterEffectCost;
        //extractCards();
        //setExtractedCardsCost();
        isActive = false;

    }
/*
    public int getCharacterEffectCost() {
        return characterEffectCost;
    }


    /**
     * This methods is used to extract 3 random character cards from the 8/12 given
     */
    /*
    public void extractCards(){

        int amountOfCards = 3;
        int index;

        for(index = 0; index < 8; index++){
            values.add(index);
        }

        for(index = 0; index < amountOfCards; index++){
            int randomIndex = rand.nextInt(values.size());
            int randomElement = values.get(randomIndex);
            System.out.println(randomElement);
            values.remove(randomIndex);
            switch (randomElement) {
                case 0 -> extractedCards.add(MONK);
                case 1 -> extractedCards.add(INNKEEPER);
                case 2 -> extractedCards.add(HERALD);
                case 3 -> extractedCards.add(CENTAUR);
                case 4 -> extractedCards.add(JESTER);
                case 5 -> extractedCards.add(KNIGHT);
                case 6 -> extractedCards.add(MERCHANT);
                case 7 -> extractedCards.add(WARRIOR_PRINCESS);
                case 8 -> extractedCards.add(PRINCE);
                case 9 -> extractedCards.add(GROCER);
                case 10 -> extractedCards.add(MINSTREL);
                case 11 -> extractedCards.add(THIEF);

                default -> System.out.println("ERROR --> extractCards() didn't work as expected");
            }

            if(randomElement == 0){
                setUpCard(MONK);
            }

            if(randomElement == 4){
                setUpCard(JESTER);
            }

            if(randomElement == 7){
                setUpCard(WARRIOR_PRINCESS);
            }

        }
    }

/*
    //connect with Bag class
    private void setUpCard(Character card){

        if(card == MONK || card == WARRIOR_PRINCESS){

            int[] tempArray = new int[] {0,0,0,0,0};

            for(int numberOfStudents = 0; numberOfStudents < 4; numberOfStudents++){

                //tempArray[bag.getRandomColorFromBag()]++;

            }

            if(card == MONK) {
                monkStudents = tempArray;
            }

            else{
                warriorPrincessStudents = tempArray;
            }

        }

        if(card == JESTER){

            jesterStudents = new int[] {0,0,0,0,0};

            for(int numberOfStudents = 0; numberOfStudents < 6; numberOfStudents++){

                //jesterStudents[bag.getRandomColorFromBag()]++;

            }

        }


        Already in the first if --> with this case there are some code repetition

        if(card == WARRIOR_PRINCESS){

            warriorPrincessStudents = new int[] {0,0,0,0,0};

            for(int numberOfStudents = 0; numberOfStudents < 4; numberOfStudents++){

                warriorPrincessStudents[bag.getRandomColorFromBag()]++;

            }

        }



    }

    /*
    public void setExtractedCardsCost(){

        for(Character c : extractedCards){

            extractedCardsCost.add(c.getCharacterEffectCost());

        }

    }

    public void incrementCardCost(Character card){

        int index = 0;

        for(Character c : extractedCards){

            if(c.equals(card)){

                extractedCardsCost.set(index, extractedCardsCost.get(index) + 1);

            }

            index++;

        }

    }

/*
    public void chooseCharacterCard(Character card) {

        //incrementCardCost(card);
        setActive(true);

        switch (card) {
            case MONK -> monkEffect();
            case INNKEEPER -> innkeeperEffect();
            case HERALD -> heraldEffect();
            case CENTAUR -> centaurEffect();
            case JESTER -> jesterEffect();
            case KNIGHT -> knightEffect();
            case MERCHANT -> merchantEffect();
            case WARRIOR_PRINCESS -> warriorPrincessEffect();
            default -> System.out.println("Card not found");
        }

    }

    //use Bag and SchoolBoard
    private void monkEffect() {


        schoolBoard.addStudentToIsland(color, islandTile);
        monkStudents[color.getColorIndex()]--;
        monkStudents[bag.getRandomColorFromBag()]++;


    }

/*
    private void innkeeperEffect() {


        //simpleBoardManager.checkToAddProfessor(color, 1);

        //need to re assign the professor after this card

    }

    private void heraldEffect(){

        //simpleBoardManager.chooseStepsMotherNature(steps, 2);

    }



    private void centaurEffect(){

        //simpleBoardManager.checkInfluence(island, 1);

    }



    private void jesterEffect(){
        /*
        int studentsMoved = 0;
        int counter = 0;
        Color color;

        SchoolBoard schoolBoard;
        while(color != null) {
            schoolBoard.removeFromEntrance(color);
            studentsMoved++;
        }

        while(counter < studentsMoved){
            schoolBoard.addStudentToEntrance(color.getColorIndex());
            counter++;
            jesterStudents[color.getColorIndex()]--;
        }

        for(int index = 0; index < studentsMoved; index++){
            jesterStudents[bag.getRandomColorFromBag()]++;
        }


    }



    private void knightEffect(){

        //simpleBoardManager.checkInfluence(island, 2);

    }

    private void merchantEffect(){

        /*
        Color color;
        int effect = color.getColorIndex() + 3;

        simpleBoardManager.checkInfluence(island, effect);


    }

    private void warriorPrincessEffect(){
        /*
        SchoolBoard schoolBoard;
        Color color;
        schoolBoard.addStudentToDiningRoom(color);
        warriorPrincessStudents[color.getColorIndex()]--;
        warriorPrincessStudents[bag.getRandomColorFromBag()]++;

    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
*/
