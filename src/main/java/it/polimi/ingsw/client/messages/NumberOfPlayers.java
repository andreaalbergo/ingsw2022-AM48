package it.polimi.ingsw.client.messages;

public class NumberOfPlayers implements Message{

    public final int NumberOfPlayers;

    public NumberOfPlayers(int numberOfPlayers){
        this.NumberOfPlayers = numberOfPlayers;
    }
}
