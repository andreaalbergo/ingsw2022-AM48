package it.polimi.ingsw.server.messages;

import java.io.Serializable;

public class SerializedAnswer implements Serializable {
    private Answer answer;

    public void setSerializedAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
