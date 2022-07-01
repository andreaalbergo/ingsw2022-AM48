package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.AssistantCard;

/**
 * ChoiceAssistantCard class is used to create an action of choice assistant card when the active player (client) chooses
 * an assistant card from its own hand. After that we set the players' turn order.
 *
 * @author Andrea Albergo
 */
public class ChoiceAssistantCard implements UserCommand{
    public final AssistantCard card;
    public final Integer starting_Player;

    /**
     * Method getCard() is a getter.
     *
     * @return of type AssistantCard
     */
    public AssistantCard getCard() {
        return card;
    }

    /**
     * Constructor ChoiceAssistantCard creates its instance.
     *
     * @param card of type AssistantCard
     */
    public ChoiceAssistantCard(AssistantCard card) {
        this.card = card;
        this.starting_Player = null;
    }

    /**
     * Method toString translates this method into String form, useful to handle actions in another client classes.
     *
     * @return of type String.
     */
    public String toString(){
        return "ChoiceAssistantCard";
    }
}
