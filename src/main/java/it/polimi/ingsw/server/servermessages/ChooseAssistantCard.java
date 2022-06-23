package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.AssistantCard;

import java.util.List;

public class ChooseAssistantCard implements Answer{

    private final String message;
    private final String nickname;

    public List<AssistantCard> getAvailable_cards() {
        return available_cards;
    }

    private List<AssistantCard> available_cards;

    public ChooseAssistantCard(String message, String name, List<AssistantCard> deck) {
        this.message = message;
        this.nickname = name;
        this.available_cards = deck;
    }
 
    public String getNickname() {
        return nickname;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
