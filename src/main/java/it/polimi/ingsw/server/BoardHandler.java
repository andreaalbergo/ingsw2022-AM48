package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.Answer;
import it.polimi.ingsw.server.messages.CustomMessage;
import it.polimi.ingsw.server.messages.RequestWizard;
import it.polimi.ingsw.server.messages.TowerRequest;

import java.beans.PropertyChangeSupport;

public class BoardHandler {
    private final MultiplayerServer server;
    private final GameController controller;
    private final Board board;
    private int numberOfPlayers;

    private final PropertyChangeSupport controllerListener = new PropertyChangeSupport(this);

    private boolean isExpertMode;
    //private int currentPlayer;
    private boolean isStarted;

    public GameController getController() {
        return controller;
    }

    public BoardHandler(MultiplayerServer server) {
        this.server = server;
        isStarted = false;
        board = new Board();
        controller = new GameController(board, this);
        controllerListener.addPropertyChangeListener(controller);

    }

    /**
     * startGame method notifies the server that notifies all players in the lobby that a new game is started, also the
     * controller is notified by this event.
     */
    public void startGame(){
        isStarted = true;
    }

    public void setExpertMode(boolean expertMode) {
        isExpertMode = expertMode;
    }

    public boolean isExpertMode() {
        return isExpertMode;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void unregisterPlayer(Integer idClient) {
        //TODO
        //call end game
    }

    public void setup() {
        if(!isStarted)
            startGame();
        RequestWizard wizardReq = new RequestWizard("Please choose a wizard: ");
        TowerRequest towerReq = new TowerRequest("Please choose a tower's color: ");

        if (numberOfPlayers==2 && Tower.available().size()>1){
            //TODO
        } else if (numberOfPlayers==3 && Tower.available().size()>0) {
            //TODO
        } else{
            //TODO
            //Do i need else? bohhh
        }
    }

    public void sendAll(CustomMessage customMessage) {
        for(Player player: board.getActivePlayers())
            server.getIdtoClientMap().get(player.getPlayerID()).send(customMessage);
    }

    public void sendAllExcept(Answer answer, Integer idClient) {
        for (Player player: board.getActivePlayers()) {
            if(player.getPlayerID()!=idClient){
                server.getIdtoClientMap().get(idClient).send(answer);
            }
        }
    }


    public void setupPlayer(String nickname, Integer iDclient) {
        board.createNewPlayer(new Player(nickname,iDclient, Wizard.WIZARD1, Tower.BLACK));
    }

    public void endGame(String s) {
        //TODO
    }

    public void endGame(Integer player){
        //TODO
    }

    public boolean isStarted() {
        return isStarted;
    }
}
