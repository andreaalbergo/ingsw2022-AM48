package it.polimi.ingsw.server.messages;

import it.polimi.ingsw.client.SerializedMessage;

import java.io.Serializable;

public class SerializedAnswer implements Serializable {
    private Answer answer;

    public void setSerializedAnswer(Answer answer) {
        this.answer = answer;
    }
}
