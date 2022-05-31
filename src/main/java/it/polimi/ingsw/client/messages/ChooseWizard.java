package it.polimi.ingsw.client.messages;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.Answer;

public class ChooseWizard implements Message {
    private final Wizard wizard;

    public ChooseWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public Wizard getWizard() {
        return wizard;
    }
}
