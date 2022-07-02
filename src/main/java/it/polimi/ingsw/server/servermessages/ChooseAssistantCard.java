package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.AssistantCard;

import java.util.List;

/**
 * Message you to invite the player at choosing an AssistantCard
 *
 * @author andrea albergo
 */
public class ChooseAssistantCard implements Answer{

    private final String message;
    private String nickname = "";

    public List<AssistantCard> getAvailable_cards() {
        return available_cards;
    }

    private final List<AssistantCard> available_cards;

    /**
     * Constructor
     *
     * @param message String
     * @param name String
     * @param deck List
     *
     */
    public ChooseAssistantCard(String message, String name, List<AssistantCard> deck) {
        this.message = message;
        this.nickname = name;
        this.available_cards = deck;
    }

    public ChooseAssistantCard(String message, List<AssistantCard> deck) {
        this.message = message;
        this.available_cards = deck;
    }

    /**
     * Getter of variable nickname
     *
     * @return String
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Override of the Answer method
     *
     * @return Object
     */
    @Override
    public Object getMessage() {
        return message;
    }
}
