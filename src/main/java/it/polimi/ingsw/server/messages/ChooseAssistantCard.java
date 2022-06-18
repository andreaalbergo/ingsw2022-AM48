package it.polimi.ingsw.server.messages;

import it.polimi.ingsw.model.AssistantCard;

public class ChooseAssistantCard implements Answer{

    private final String message;
    private final Integer idClient;

    public ChooseAssistantCard(String message, Integer idClient) {
        this.message = message;
        this.idClient = idClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}