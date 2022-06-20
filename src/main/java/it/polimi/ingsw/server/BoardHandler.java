package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.*;

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
    public void setup(){
        if(setupWizard()){
            setupTower();
        }
    }
    public Boolean setupWizard() {
        if(!isStarted)
            startGame();
        RequestWizard wizardReq = new RequestWizard("Please choose a wizard: ");
        wizardReq.updateRemaining(Wizard.getAvailable());
        if(numberOfPlayers==2 && Wizard.getAvailable().size()>2){
            String player = board.getActivePlayers().get(numberOfPlayers - Wizard.getAvailable().size() + 2).getNickname();
            sendtoPlayer(wizardReq,server.getNametoIdMap().get(player));
            sendAllExcept(new CustomMessage(player + "is choosing his wizard"),server.getNametoIdMap().get(player));
        } else if (numberOfPlayers==3 && Wizard.getAvailable().size()>1) {
            String player = board.getActivePlayers().get(numberOfPlayers - Wizard.getAvailable().size() + 1).getNickname();
            sendtoPlayer(wizardReq,server.getNametoIdMap().get(player));
            sendAllExcept(new CustomMessage(player + "is choosing his wizard"),server.getNametoIdMap().get(player));
        }
        return true;
    }

    public void setupTower() {
        TowerRequest request = new TowerRequest("Please choose a tower: ");
        request.setRemaining_towers(Tower.available);
        String player = null;
        if(Tower.available.size()>1) {
            if(numberOfPlayers == 2){
                player = board.getActivePlayers().get(numberOfPlayers - Tower.available.size() + 1).getNickname();
            } else if (numberOfPlayers == 3) {
                player = board.getActivePlayers().get(numberOfPlayers - Tower.available.size()).getNickname();
            }
            sendtoPlayer(request, server.getNametoIdMap().get(player));
            sendAllExcept(new CustomMessage(player + "is choosing his Tower"), server.getNametoIdMap().get(player));

        }

    }


        private void sendtoPlayer( Answer answer, int id) {
        server.getIdtoClientMap().get(id).send(answer);
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
        board.createNewPlayer(new Player(nickname,iDclient));
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
