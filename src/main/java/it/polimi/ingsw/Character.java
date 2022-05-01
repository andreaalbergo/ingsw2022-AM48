package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Character {
    MONK(1), INNKEEPER(2), PRINCE(3),
    HERALD(1), GROCER(2), CENTAUR(3),
    JESTER(1), KNIGHT(2), MERCHANT(3),
    MINSTREL(1), WARRIOR_PRINCESS(2), THIEF(3);

    private final int characterEffectCost;
    private List<Character> VALUES = List.of(values());
    private static Random rand = new Random();
    private ArrayList<Character> extractedCards = new ArrayList<>(3);

    Character(int characterEffectCost) {

        this.characterEffectCost = characterEffectCost;

    }

    public static int getCharacterEffectCost(Character character) {
        return character.characterEffectCost;
    }

    public void extractCards(){

        int amountOfCards = 3;

        for(int i = 0; i < amountOfCards; i++){
            int randomIndex = rand.nextInt(VALUES.size());
            Character randomElement = VALUES.get(randomIndex);
            VALUES.remove(randomIndex);
            switch(randomElement) {
                case MONK: extractedCards.add(MONK);
                case INNKEEPER: extractedCards.add(INNKEEPER);
                case PRINCE: extractedCards.add(PRINCE);
                case HERALD: extractedCards.add(HERALD);
                case GROCER: extractedCards.add(GROCER);
                case CENTAUR: extractedCards.add(CENTAUR);
                case JESTER: extractedCards.add(JESTER);
                case KNIGHT: extractedCards.add(KNIGHT);
                case MERCHANT: extractedCards.add(MERCHANT);
                case MINSTREL: extractedCards.add(MINSTREL);
                case WARRIOR_PRINCESS: extractedCards.add(WARRIOR_PRINCESS);
                case THIEF: extractedCards.add(THIEF);
                default: System.out.println("ERROR --> extractCards() didn't work as expected");
            }
        }

    }

    public int getCharacterEffectCost() {
        return characterEffectCost;
    }

    public Character getExtractedCards(int index){
        return extractedCards.get(index);
    }
}