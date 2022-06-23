package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.server.servermessages.*;

import java.beans.PropertyChangeSupport;
import java.util.logging.Level;

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
        cli.logger.log(Level.SEVERE,"la answer che sta elaborando è " + answer + " e la fase è " + model.getPhase());
        if(model.getPhase() < 6 ){
            setupGame(answer);
        }
        //Inserire tutte le possibili risposte diverse dal movimento del gioco (vincita sconfitta ....)
        else if (answer instanceof ConnectionMessage) {
            if (cli != null ){
                view.firePropertyChange("connectionClosed",null,answer);
                cli.setActive(false);
            }

        }
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
            view.firePropertyChange("setup",null, "SetPlayersRequest");
        } else if (answer instanceof RequestWizard) {
            if(((RequestWizard) answer).getWizard() == null){
                view.firePropertyChange("setup", null, "RequestWizard");
            }else {
                model.setWizard(((RequestWizard) answer).getWizard());
            }

        } else if (answer instanceof TowerRequest) {
            if(((TowerRequest) answer).getTower() == null){
                view.firePropertyChange("setup", null, "TowerRequest");
            }else {
                model.setTower(((TowerRequest) answer).getTower());
            }
        } else if (answer instanceof MatchStarted) {
            view.firePropertyChange("matchStarted",null,null);
            model.setPhase(1);
        } else if (answer instanceof SetMode) {
                view.firePropertyChange("setup", null, "SetMode");
            }

    }
}
