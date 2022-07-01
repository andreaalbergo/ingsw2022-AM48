package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;
import it.polimi.ingsw.server.servermessages.*;
import it.polimi.ingsw.server.servermessages.gamemessages.*;

import java.beans.PropertyChangeSupport;
import java.util.Objects;
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

/*
    public CommandHandler(ClientView model, GUI gui) {
        this.model = model;
        this.gui = gui;
        view.addPropertyChangeListener(gui);
    }
 */

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

        }else if (answer instanceof MatchStarted) {
            if (((MatchStarted) answer).getName().equals(model.getNickname())) {
                System.out.println("Mi è arrivato il match started con i miei dati");
                model.setIslands(((MatchStarted) answer).getIslands());
                model.setClouds(((MatchStarted) answer).getClouds());

            }
            System.out.println("Mi è arrivato il match started con i suoi dati");
            model.updateEntrance(((MatchStarted) answer).getName(), ((MatchStarted) answer).getEntrance());
        }
        else if (answer instanceof StartTurnMessage) {
            //model.setPhase(3);
            view.firePropertyChange("startTurnMessage",null, answer);

        }
        if(answer instanceof ChooseAssistantCard){
            fireChoiceAssistantCard(answer);
        }
        if(answer instanceof MovedMotherNature){
            fireMotherNatureMoved((MovedMotherNature) answer);
        }
        if (answer instanceof CustomMessage) {
            fireCustomMessage(answer);

        } else if (answer instanceof GameError) {
            fireError(answer);

        } else if (answer instanceof ProfessorAnswer) {
            fireProfessor(answer);

        } else if (answer instanceof MoveMessage) {
            view.firePropertyChange("moveMessage",null,answer);

        } else if (answer instanceof GameOver){
            view.firePropertyChange("gameOver",null,answer);
        }

        //else if ( answer instanceof BuiltTower){
            //fireBuiltTower((BuiltTower) answer);
        //}

    }

    /*
    private void fireBuiltTower(BuiltTower message) {
        if(message.getPlayer().equals(model.getNickname())){
            int index = message.getId_island();
            IslandTile islandTile = model.getIslands().get(index);
        }
    }
    */


    private void fireMotherNatureMoved(MovedMotherNature motherNature) {
        if(motherNature.isCheck()){
            System.out.println("INFO : era la mia mossa");
            if(motherNature.getIslandTile() == null){
                System.out.println("The number of steps you chose is invalid, try again");
                model.setInputEnabler(true);
                return;
            }
        }
        view.firePropertyChange("MovedMotherNature",null,motherNature);
    }

    private void fireProfessor(Answer answer) {
        ProfessorAnswer professorAnswer = (ProfessorAnswer) answer;
        if(professorAnswer.isGained()){
            model.setProfessor(professorAnswer.getColor().getColorIndex(),true);
            return;
        }
        model.setProfessor(professorAnswer.getColor().getColorIndex(),false);

    }

    private void fireChoiceAssistantCard(Answer answer) {
        view.firePropertyChange("chooseAssistantCard",null,answer);
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
        } else if (answer instanceof CustomMessage) {
            fireCustomMessage(answer);
        } else if (answer instanceof SetDetails message) {
            model.setPhase(1);
            if(message.getNum_players() != 0 && cli.getGameBoard()==null){
                cli.createGameBoard(message.getNum_players());
            }
            if(message.getWizard() == null){
                view.firePropertyChange("setup", null, "SetDetails");
            }else {
                if(Objects.equals(message.getName(), model.getNickname())){
                    model.setWizard(((SetDetails) answer).getWizard().toString());
                    model.setTower(((SetDetails) answer).getTower().toString());
                }
                if(message.getName() != null){
                    model.updateNameToTower(message.getName(), message.getTower());
                }

            }
        } else if (answer instanceof SetMode) {
                view.firePropertyChange("setup", null, "SetMode");
        } else if (answer instanceof MatchStarted) {
            if(((MatchStarted)answer).getName().equals(model.getNickname())){
                System.out.println("Mi è arrivato il match started con i miei dati");
                model.setIslands(((MatchStarted) answer).getIslands());
                model.setClouds(((MatchStarted) answer).getClouds());
            }
            System.out.println("Metto nella entrance");
            model.updateEntrance(((MatchStarted)answer).getName(),((MatchStarted)answer).getEntrance());
            model.setPhase(2);
        }

    }


}
