package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Character {
    MONK(1, 1), INNKEEPER(2, 2), PRINCE(3, 3),
    HERALD(4, 1), GROCER(5, 2), CENTAUR(6, 3),
    JESTER(7, 1), KNIGHT(8, 2), MERCHANT(9, 3),
    MINSTREL(10, 1), WARRIOR_PRINCESS(11, 2), THIEF(12, 3);

    private final int idCharacter;
    private final int characterEffectCost;
    private static ArrayList<Integer> values = new ArrayList<>();
    private static Random rand = new Random();
    private static ArrayList<Character> extractedCards = new ArrayList<>();

    Character(int idCharacter, int characterEffectCost) {

        this.idCharacter = idCharacter;
        this.characterEffectCost = characterEffectCost;

    }

    public int getCharacterEffectCost(Character character) {
        return character.characterEffectCost;
    }

    public static ArrayList<Character> extractCards(){

        int amountOfCards = 3;

        for(int i = 0; i < 12; i++){
            values.add(i);
        }

        for(int i = 0; i < amountOfCards; i++){
            int randomIndex = rand.nextInt(values.size());
            int randomElement = values.get(randomIndex);
            System.out.println(randomElement);
            values.remove(randomIndex);
            switch (randomElement) {
                case 0 -> extractedCards.add(MONK);
                case 1 -> extractedCards.add(INNKEEPER);
                case 2 -> extractedCards.add(PRINCE);
                case 3 -> extractedCards.add(HERALD);
                case 4 -> extractedCards.add(GROCER);
                case 5 -> extractedCards.add(CENTAUR);
                case 6 -> extractedCards.add(JESTER);
                case 7 -> extractedCards.add(KNIGHT);
                case 8 -> extractedCards.add(MERCHANT);
                case 9 -> extractedCards.add(MINSTREL);
                case 10 -> extractedCards.add(WARRIOR_PRINCESS);
                case 11 -> extractedCards.add(THIEF);
                default -> System.out.println("ERROR --> extractCards() didn't work as expected");
            }
        }

        return extractedCards;

    }

    public int getCharacterEffectCost() {
        return characterEffectCost;
    }

    public static ArrayList<Character> getExtractedCards(){
        return extractedCards;
    }

}
