package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.server.messages.Answer;

public class ClientView {

    private final CLI cli;
    private String nickname;

    private Answer answer;

    private int phase = 0;

    public ClientView(CLI cli) {
        this.cli = cli;
    }


    public void setAnswer(Answer answer) {
        this.answer = answer;
    }





    public CLI getCli() {
        return cli;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }







}
