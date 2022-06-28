package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.BoardHandler;
import it.polimi.ingsw.server.servermessages.ChooseAssistantCard;
import it.polimi.ingsw.server.servermessages.CustomMessage;
import it.polimi.ingsw.server.servermessages.Errors;
import it.polimi.ingsw.server.servermessages.GameError;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GameController class is the main controller class, it calls and manages some handlers, like roundHandler, in order to
 * manage various states of the game itself.
 *
 * @author David Barb
 * @see PropertyChangeListener
 */
public class GameController implements PropertyChangeListener {
    private final Board model;
    private final BoardHandler boardHandler;
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    private final RoundHandler roundHandler;


    private boolean isExpert;

    private final Logger logger = Logger.getLogger(getClass().getName());

    public GameController(Board model, BoardHandler boardHandler) {
        this.model = model;
        this.boardHandler = boardHandler;
        roundHandler = new RoundHandler(this, new TurnHandler(boardHandler.isExpertMode(), model));
        listeners.addPropertyChangeListener("RoundHandler",roundHandler);

    }

    public boolean isExpert() {
        return isExpert;
    }

    public void setMode(boolean mode) {
        this.isExpert = mode;
    }


    public Board getBoard(){
        return model;
    }

    /**
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     * */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "ChooseAssistantCard" -> chooseAssistantCard((AssistantCard) evt.getNewValue());
            case "EndRound" -> changeTurn();
            case "MoveMotherNature" -> moveMotherNature(evt.getNewValue());
            default -> System.err.println("Unrecognized message!");
        }
    }

    private void moveMotherNature(Object newValue) {
    }

    private synchronized void chooseAssistantCard(AssistantCard assistantCard) {
        Player player = model.getCurrentPlayer();
        if(!player.getAssistantCards().contains(assistantCard)){
            boardHandler.sendtoPlayer(new GameError(Errors.ALREADYCHOSEN,"Your selection is invalid, try with a different pick"), player.getPlayerID());
            return;
        }
        model.setPlayerAssistantCardHashMap(assistantCard);
        boardHandler.sendAllExcept(new CustomMessage( player.getNickname() + " has chosen the assistant card <" +
                        assistantCard.getValue() + "> with a possibility of " + assistantCard.getNumber_of_steps() +
                        " steps." ),
                player.getPlayerID());
        if(boardHandler.getPhase() == 3){
            startRound();
            boardHandler.sendAll(new CustomMessage("The round was set..."));
            List<Player> players = boardHandler.game().getActivePlayers();
            for (Player player1 : players) {
                boardHandler.sendtoPlayer(new CustomMessage("You are the " + model.getPlayersTurnOrder().indexOf(player1) +" player to play"), player1.getPlayerID());
            }
            return;
        }
        model.setNextPlayer();
    }

    public void startRound() {
        model.setPlayerOrderTurn();
        model.setStartedRound(true);
    }

    public boolean placeTower(/*towerPlaceAction msg*/){
        return true;
    }

    public void changeTurn(){
        listeners.firePropertyChange("endTurn",null,null);
        model.resetAssistantCards();
        logger.log(Level.INFO,"Inizia a scegliere " + model.getPlayersTurnOrder().get(0).getNickname());
        boardHandler.setPhase(2);
        boardHandler.sendtoPlayer( new ChooseAssistantCard("Choose an Assistant Card for the new round\n>",
                        model.getCurrentPlayer().getNickname(),
                        model.getCurrentPlayer().getAssistantCards()),
                model.getCurrentPlayerIndex());
        boardHandler.sendAllExcept(new CustomMessage(model.getCurrentPlayer().getNickname() + " is choosing his assistant card...please wait"), model.getCurrentPlayerIndex());

    }

    public void setWizard(Wizard wizard, int id){
        model.getActivePlayers().get(id).setWizard(wizard);
    }
    public void setTower(Tower tower, int id){
        model.getActivePlayers().get(id).setTower(tower);
    }

    public void gameOver(){
        System.out.println("Last round finished, Game Over!!!");
        boardHandler.endGame("The number of rounds reached the maximum numbers");
    }
}
