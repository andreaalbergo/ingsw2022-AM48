package it.polimi.ingsw;

import java.util.ArrayList;

public class ChosenCharacterCards {
    private final ArrayList<CharacterCard> characterCards;

    public ChosenCharacterCards() {
        this.characterCards = new ArrayList<>(3);
        chooseThreeRandomCharacters();
    }

    private void chooseThreeRandomCharacters() {
        //TODO
    }
}
