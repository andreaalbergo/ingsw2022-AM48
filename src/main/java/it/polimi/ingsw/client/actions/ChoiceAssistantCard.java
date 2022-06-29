package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.AssistantCard;

public class ChoiceAssistantCard implements UserCommand{
    public final AssistantCard card;
    public final Integer starting_Player;

    public Integer getStarting_Player() {
        return starting_Player;
    }

    public AssistantCard getCard() {
        return card;
    }

    public ChoiceAssistantCard(AssistantCard card) {
        this.card = card;
        this.starting_Player = null;
    }

    public ChoiceAssistantCard(Integer starting_Player) {
        this.card = null;
        this.starting_Player = starting_Player;
    }

    public ChoiceAssistantCard(AssistantCard card, Integer starting_Player) {
        this.card = card;
        this.starting_Player = starting_Player;
    }

    public String toString(){
        return "ChoiceAssistantCard";
    }
}
