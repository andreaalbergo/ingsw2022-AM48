package it.polimi.ingsw;

import java.util.List;
import java.util.Random;

public enum Character {
    MONK(1), INNKEEPER(2), PRINCE(3),
    HERALD(1), GROCER(2), CENTAUR(3),
    JESTER(1), KNIGHT(2), MERCHANT(3),
    MINSTREL(1), WARRIOR_PRINCESS(2), THIEF(3);

    private final int characterEffectCost;
    private static final List<Character> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    Character(int characterEffectCost) {
        this.characterEffectCost = characterEffectCost;
    }

    public static int getCharacterEffectCost(Character character) {
        return character.characterEffectCost;
    }

    public static Character getRandomCharacter() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}