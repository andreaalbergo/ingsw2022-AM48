package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * is the server message send when is requested a wizard
 */
public class RequestWizard implements Answer{
    private final String message;
    private final String wizard;

    private List<Wizard> remainingWizards = new ArrayList<>();

    /**
     * Class constructor
     * @param message is the message
     * @param wizard is the wizard
     */
    public RequestWizard(String message, String wizard) {
        this.message = message;
        this.wizard = wizard;
    }

    /**
     * class constructor
     * @param message
     */
    public RequestWizard(String message) {
        this.message = message;
        this.wizard = null;
    }

    public String getWizard(){
        return wizard;
    }

    /**
     * update the remaining wizards
     * @param wizards list of the remaining wizards
     */
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
