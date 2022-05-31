package it.polimi.ingsw.client.messages;

public class PlayersNumber implements Message{

    public final int playersNumber;


    public PlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }
}
