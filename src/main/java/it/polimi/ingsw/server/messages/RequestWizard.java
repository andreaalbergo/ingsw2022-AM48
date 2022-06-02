package it.polimi.ingsw.server.messages;

import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

public class RequestWizard implements Answer{

    private final String message;

    private final String wizard;

    private List<Wizard> remaining_wizards = new ArrayList<>();

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
        remaining_wizards = wizards;
    }

    public List<Wizard> getRemaining_wizards(){
        return remaining_wizards;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
