package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

public class RequestWizard implements Answer{
    private final String message;
    private final String wizard;

    private List<Wizard> remainingWizards = new ArrayList<>();

    public RequestWizard(String message, String wizard) {
        this.message = message;
        this.wizard = wizard;
    }

    public RequestWizard(String message) {
        this.message = message;
        this.wizard = null;
    }

    public String getWizard(){
        return wizard;
    }

    public void updateRemaining(List<Wizard> wizards){
        remainingWizards = wizards;
    }

    public List<Wizard> getRemainingWizards(){
        return remainingWizards;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
