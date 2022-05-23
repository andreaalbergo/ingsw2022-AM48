package it.polimi.ingsw.model;

import java.util.*;

public enum CharacterCard {
    MONK, INNKEEPER, PRINCE,
    HERALD, GROCER, CENTAUR,
    JESTER, KNIGHT, MERCHANT,
    MINSTREL, WARRIOR_PRINCESS, THIEF;

    private static final Map<CharacterCard, Integer> chosen = new HashMap<>();

    public static void chooseThreeRandomCharacters() {
        List<CharacterCard> VALUES = new ArrayList<>(Arrays.asList(CharacterCard.values()));
        Collections.shuffle(VALUES);

        for (int i = 0; i < 3; i++) {
            addCharacterCard(VALUES.get(0));
            VALUES.remove(0);
        }
    }

    private static void addCharacterCard(CharacterCard characterCard) {
        switch (characterCard) {
            case MONK, HERALD, JESTER, MINSTREL -> chosen.put(characterCard, 1);
            case  INNKEEPER, GROCER, KNIGHT, WARRIOR_PRINCESS -> chosen.put(characterCard, 2);
            default -> chosen.put(characterCard, 3);
        }
    }

    public static Map<CharacterCard, Integer> getCharacterCardsList(){
        return chosen;
    }
}
