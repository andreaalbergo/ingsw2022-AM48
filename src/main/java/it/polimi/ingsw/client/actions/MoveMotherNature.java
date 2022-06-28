package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.client.messages.Message;

import javax.swing.*;

public class MoveMotherNature implements UserCommand {

    private final int number_of_steps;

    public MoveMotherNature(String[] in) {
        this.number_of_steps = Integer.parseInt(in[1]);
    }
}
