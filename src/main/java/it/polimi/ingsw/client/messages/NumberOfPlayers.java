package it.polimi.ingsw.client.messages;

/**
 * Class NumberOfPlayers make the host of the lobby choose the number of players for the upcoming game start. It can be
 * between 2 or 3.
 */
public class NumberOfPlayers implements Message{
    public final int NumberOfPlayers;

    /**
     * Constructor NumberOfPlayers creates its own instance.
     *
     * @param numberOfPlayers of type int.
     */
    public NumberOfPlayers(int numberOfPlayers){
        NumberOfPlayers = numberOfPlayers;
    }
}
