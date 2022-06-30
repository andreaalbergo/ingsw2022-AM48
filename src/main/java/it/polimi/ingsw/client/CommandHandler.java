package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;
import it.polimi.ingsw.server.servermessages.*;
import it.polimi.ingsw.server.servermessages.gamemessages.GameOver;
import it.polimi.ingsw.server.servermessages.gamemessages.MoveMessage;
import it.polimi.ingsw.server.servermessages.gamemessages.ProfessorAnswer;
import it.polimi.ingsw.server.servermessages.gamemessages.StartTurnMessage;

import java.beans.PropertyChangeSupport;
import java.util.logging.Level;

public class CommandHandler {
    private final ClientView model;
    private final PropertyChangeSupport view = new PropertyChangeSupport(this);
    private CLI cli;
    private GUI gui;

    public CommandHandler(ClientView model, CLI cli) {
        this.model = model;
        this.cli = cli;
        view.addPropertyChangeListener(cli);
    }


    public CommandHandler(ClientView model, GUI gui) {
        this.model = model;
        this.gui = gui;
        view.addPropertyChangeListener(gui);
    }

    public void inGamePhase(Answer answer){

    }

    public void answerHandler() {
        //System.out.println("MO SVILUPPA");
        Answer answer = model.getAnswer();
        cli.logger.log(Level.SEVERE,"la answer che sta elaborando è " + answer + " e la fase è " + model.getPhase());
        if(model.getPhase()<2){
            setupGame(answer);
        }
        //Inserire tutte le possibili risposte diverse dal movimento del gioco (vincita sconfitta ....)
        else if (answer instanceof ConnectionMessage) {
            if (cli != null){
                view.firePropertyChange("connectionClosed",null,answer);
                cli.setActive(false);
            }

        } else if (answer instanceof StartTurnMessage) {
            model.setPhase(3);
            view.firePropertyChange("StartTurnMessage",null, answer);

        }
        if(answer instanceof ChooseAssistantCard){
            fireChoiceAssistantCard(answer);
        }
        if (answer instanceof CustomMessage) {
            fireCustomMessage(answer);

        } else if (answer instanceof GameError) {
            fireError(answer);

        } else if (answer instanceof ProfessorAnswer) {
            fireProfessor(answer);

        } else if (answer instanceof MoveMessage) {
            view.firePropertyChange("MoveMessage",null,answer);


        } else if (answer instanceof GameOver){
            view.firePropertyChange("gameOver",null,answer);
        }

    }

    private void fireProfessor(Answer answer) {
        ProfessorAnswer professorAnswer = (ProfessorAnswer) answer;
        if(professorAnswer.isGained()){
            //TODO
            return;
        }
        //TODO
    }

    private void fireChoiceAssistantCard(Answer answer) {
        view.firePropertyChange("ChooseAssistantCard",null,answer);
        model.setInputEnabler(true);
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
        } else if (answer instanceof SetDatails) {
            model.setPhase(1);
            if(((SetDatails) answer).getWizard() == null){
                view.firePropertyChange("setup", null, "SetDetails");
            }else {
                model.setWizard(((SetDatails) answer).getWizard().toString());
                model.setTower(((SetDatails) answer).getTower().toString());
            }
        } else if (answer instanceof SetMode) {
                view.firePropertyChange("setup", null, "SetMode");
        } else if (answer instanceof MatchStarted) {
            model.setIslands(((MatchStarted) answer).getIslands());
            model.setClouds(((MatchStarted) answer).getClouds());
            model.setEntrance(((MatchStarted) answer).getEntrance());
            model.setPhase(2);
        }

    }


}
