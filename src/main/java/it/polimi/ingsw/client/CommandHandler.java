package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.messages.ChooseTowerColor;
import it.polimi.ingsw.server.messages.*;

import java.beans.PropertyChangeSupport;

public class CommandHandler {

    private final ClientView model;

    private final PropertyChangeSupport view = new PropertyChangeSupport(this);

    private CLI cli;

    public CommandHandler(ClientView model, CLI cli) {
        this.model = model;
        this.cli = cli;
        view.addPropertyChangeListener(cli);
    }

    public void inGamePhase(Answer answer){

    }

    public void answerHandler() {
        //System.out.println("MO SVILUPPA");
        Answer answer = model.getAnswer();
        if(model.getPhase() == 0){
            setupGame(answer);
        }
        //Inserire tutte le possibili risposte diverse dal movimento del gioco (vincita sconfitta ....)
        if (answer instanceof CustomMessage) {
            fireCustomMessage(answer);
        } else if (answer instanceof GameError) {
            fireError(answer);
        }

    }

    private void fireCustomMessage(Answer answer) {
        view.firePropertyChange("customMessage",null,answer.getMessage());
        model.setInputEnabler(((CustomMessage)answer).isEnabled());
    }

    private void fireError(Answer answer) {
        model.setInputEnabler(true);
        view.firePropertyChange("gameError",null, answer);
    }




    private void setupGame(Answer answer) {
        if(answer instanceof SetPlayersRequest){
            view.firePropertyChange("setup phase",null, "SetPlayersRequest");
        } else if (answer instanceof RequestWizard) {
            if(((RequestWizard) answer).getWizard() != null){
                view.firePropertyChange("setup phase", null, "RequestWizard");
            }else {
                model.setWizard(((RequestWizard) answer).getWizard());
            }

        } else if (answer instanceof TowerRequest) {
            if(((TowerRequest) answer).getTower() != null){
                view.firePropertyChange("setup phase", null, "TowerRequest");
            }else {
                model.setTower(((TowerRequest) answer).getTower());
            }
        }
        model.setPhase(1);
    }
}
