package it.polimi.ingsw.client.messages;

/**
 * ChooseMode class is a message sent by the host of the lobby to its own server socket to set the game mode between
 * normal or expert.
 */
public class ChooseMode implements Message{
    private final Boolean mode;

    /**
     * Constructor ChooseMode creates its instance.
     *
     * @param mode of type boolean - true if expert mode chosen.
     */
    public ChooseMode(Boolean mode) {
        this.mode = mode;
    }

    /**
     * Method getMode is a getter.
     *
     * @return of type boolean.
     */
    public Boolean getMode() {
        return mode;
    }
}
