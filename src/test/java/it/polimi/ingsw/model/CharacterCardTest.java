package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCardTest {
    @BeforeEach
    void initialize(){
        CharacterCard.chooseThreeRandomCharacters();
    }

    @Test
    void simpleTest(){
        assertEquals(3 , CharacterCard.getCharacterCardsList().size());
        for (CharacterCard name : CharacterCard.getCharacterCardsList().keySet()) {
            System.out.print(name+" ");
            System.out.println(CharacterCard.getCharacterCardsList().get(name));
        }

    }
}