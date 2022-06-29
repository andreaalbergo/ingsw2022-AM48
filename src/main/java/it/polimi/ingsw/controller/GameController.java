package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.actions.EndTurn;
import it.polimi.ingsw.client.actions.MoveMotherNature;
import it.polimi.ingsw.client.actions.MoveStudentToDiningRoom;
import it.polimi.ingsw.client.actions.MoveStudentToIsland;
import it.polimi.ingsw.exceptions.GameOverException;
import it.polimi.ingsw.exceptions.InvalidSelection;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.BoardHandler;
import it.polimi.ingsw.server.servermessages.ChooseAssistantCard;
import it.polimi.ingsw.server.servermessages.CustomMessage;
import it.polimi.ingsw.server.servermessages.Errors;
import it.polimi.ingsw.server.servermessages.GameError;
import it.polimi.ingsw.server.servermessages.gamemessages.MoveMessage;
import it.polimi.ingsw.server.servermessages.gamemessages.MovedMotherNature;
import it.polimi.ingsw.server.servermessages.gamemessages.ProfessorAnswer;
import it.polimi.ingsw.server.servermessages.gamemessages.StartTurnMessage;

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
            case "ChoiceAssistantCard" -> chooseAssistantCard((AssistantCard) evt.getNewValue());
            case "MoveMotherNature" -> moveMotherNature((MoveMotherNature) evt.getNewValue());
            case "MoveStudentToIsland" -> moveStudentToIsland((MoveStudentToIsland) evt.getNewValue());
            case "EndTurn" -> endPlayerTurn((EndTurn) evt.getNewValue());
            case "MoveStudentToDiningRoom" -> moveStudentToDiningRoom((MoveStudentToDiningRoom) evt.getNewValue());
            //case "BuyPower" -> buyPower();
            default -> System.err.println("Unrecognized message!");
        }
    }

    private void checkIsland() throws GameOverException {
        int position = model.getBoardManager().getMotherNature().getPosition();
        IslandTile island  = boardHandler.game().getBoardManager().getIslands().get(position);
        if(island.getIslandOwner().equals(model.getCurrentPlayer())){
            return;
        }
        Player player = model.getCurrentPlayer();
        Player challenger = island.getIslandOwner();
        if(challenger == null){
            int count = 0;
            for(int i = 0; i < 5 ; i++){
                if (player.getSchoolBoard().checkProfessor(Color.colorFromIndex(i))){
                    count = count + island.getStudents()[i];
                    //riga da aggiungere in caso di potere
                }
            }
            if(count > 0){
                try {
                    island.setIslandOwner(player);
                } catch (GameOverException e) {
                    boardHandler.endGame(player.getNickname() + " has positioned all the towers, so he wins", player.getPlayerID());
                }
                player.getSchoolBoard().setTowers();
            }
            checkMerge(island,position);
            return;
        }

        int player_count = 0;
        int challenger_count = island.howManyTowers();
        for(int i = 0; i < 5 ; i++){
            if(challenger.getSchoolBoard().checkProfessor(Color.colorFromIndex(i))){
                challenger_count = challenger_count + island.getStudents()[i];
                //righe da aggiungere in caso di potere
            }
        }
        for(int i = 0; i < 5 ; i++){
            if (player.getSchoolBoard().checkProfessor(Color.colorFromIndex(i))){
                player_count = player_count + island.getStudents()[i];
                //riga da aggiungere in caso di potere
            }
        }
        if(player_count > challenger_count){
            try {
                island.setIslandOwner(player);
            } catch (GameOverException e) {
                boardHandler.endGame(player.getNickname() + " has positioned all the towers, so he wins",player.getPlayerID());
            }
            int towers_challenger = challenger.getSchoolBoard().getTowers();
            player.getSchoolBoard().setTowers();
            challenger.getSchoolBoard().setTowers(towers_challenger + island.howManyTowers());
        }

        checkMerge(island,position);
    }

    private void checkThreeIslands() throws GameOverException {
        if(boardHandler.game().getBoardManager().getIslands().size() == 3) {
            throw new GameOverException();
        }
    }

    private void checkMerge(IslandTile island, int position) throws GameOverException {
        IslandTile islandbefore = boardHandler.game().getBoardManager().getIslands().get(position - 1);
        IslandTile islandafter = boardHandler.game().getBoardManager().getIslands().get(position + 1);
        if(islandbefore.getIslandOwner().equals(island.getIslandOwner()) && islandafter.getIslandOwner().equals(island.getIslandOwner())){
            if(boardHandler.game().getBoardManager().getIslands().size() == 5){
                throw new GameOverException();
            }
            islandbefore.mergeIslands(island);
            islandbefore.mergeIslands(islandafter);
            int number_of_islands = model.getBoardManager().getMotherNature().getNumberofIslands();
            model.getBoardManager().getMotherNature().setNumberofIslands(number_of_islands - island.howManyTowers() - islandafter.howManyTowers());
            model.getBoardManager().getIslands().remove(island);
            model.getBoardManager().getIslands().remove(islandafter);


        }
        else if( islandbefore.getIslandOwner().equals(island.getIslandOwner())){
            islandbefore.mergeIslands(island);
            int number_of_islands = model.getBoardManager().getMotherNature().getNumberofIslands();
            model.getBoardManager().getMotherNature().setNumberofIslands(number_of_islands - island.howManyTowers());
            model.getBoardManager().getIslands().remove(island);
            checkThreeIslands();
        }
        else if (islandafter.getIslandOwner().equals(island.getIslandOwner())){
            island.mergeIslands(islandafter);
            int number_of_islands = model.getBoardManager().getMotherNature().getNumberofIslands();
            model.getBoardManager().getMotherNature().setNumberofIslands(number_of_islands - islandafter.howManyTowers());
            model.getBoardManager().getIslands().remove(islandafter);
            checkThreeIslands();
        }
    }

    private boolean checkProfessor(Color color){
        Player currentPlayer = model.getCurrentPlayer();
        Player playerWithProfessor = currentPlayer;
        for (Player player: model.getActivePlayers()) {
            if(player.getPlayerID() != currentPlayer.getPlayerID()){
                if(player.getSchoolBoard().getDiningRoom()[color.getColorIndex()] >
                        currentPlayer.getSchoolBoard().getDiningRoom()[color.getColorIndex()]){
                    playerWithProfessor = player;
                }else if(player.getSchoolBoard().getDiningRoom()[color.getColorIndex()] <
                        currentPlayer.getSchoolBoard().getDiningRoom()[color.getColorIndex()] && player.getSchoolBoard().checkProfessor(color)){
                    player.getSchoolBoard().removeProfessor(color);
                } else if (player.getSchoolBoard().getDiningRoom()[color.getColorIndex()] ==
                        currentPlayer.getSchoolBoard().getDiningRoom()[color.getColorIndex()] && player.getSchoolBoard().checkProfessor(color)) {
                    player.getSchoolBoard().removeProfessor(color);
                    return false;
                }
            }
        }
        if(playerWithProfessor.equals(currentPlayer)){
            boardHandler.sendtoPlayer(new ProfessorAnswer(color,true), currentPlayer.getPlayerID());
            boardHandler.sendAllExcept(new ProfessorAnswer(color,false), currentPlayer.getPlayerID());
            return true;
        }
        return false;

    }

    private void moveStudentToDiningRoom(MoveStudentToDiningRoom moveStudentToDiningRoom) {
        Player player = model.getCurrentPlayer();
        Color color = moveStudentToDiningRoom.getColor();
        try{
            player.getSchoolBoard().addStudentToDiningRoom(color);
            if(player.getSchoolBoard().getMovedstudents() == 4 && model.getActivePlayers().size() == 2){
                boardHandler.setPhase(5);
            }
            if(player.getSchoolBoard().getMovedstudents() == 3 && model.getActivePlayers().size() == 3){
                boardHandler.setPhase(5);
            }
            updateAfterMovedStudentToDiningRoom();
        } catch (InvalidSelection e) {
            if (maxStudents()){
                return;
            }
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"The parameters of your action are incorrect, check again"), getBoard().getCurrentPlayerIndex());
        }

        if(checkProfessor(color)){
            player.getSchoolBoard().addProfessor(color);
            try {
                checkIsland();
            } catch (GameOverException e) {
                boardHandler.endGame("The game is ending... we have a winner", player.getPlayerID());
            }
        }
    }

    private boolean maxStudents(){
        if ((model.getCurrentPlayer().getSchoolBoard().getMovedstudents() == 4 && model.getActivePlayers().size() ==2) ||
                (model.getCurrentPlayer().getSchoolBoard().getMovedstudents() == 3 && model.getActivePlayers().size()==3)) {
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT, "You already moved the maximum amount of students"), getBoard().getCurrentPlayerIndex());
            return true;
        }
        return false;
    }

    private void moveStudentToIsland(MoveStudentToIsland moveStudentToIsland) {

        Player player = model.getCurrentPlayer();
        IslandTile islandTile = moveStudentToIsland.getIslandTile();
        Color color = moveStudentToIsland.getColor();
        try {
            player.getSchoolBoard().addStudentToIsland(color,islandTile);
            if(player.getSchoolBoard().getMovedstudents() == 4 && model.getActivePlayers().size() == 2){
                boardHandler.setPhase(5);
            }
            if(player.getSchoolBoard().getMovedstudents() == 3 && model.getActivePlayers().size() == 3){
                boardHandler.setPhase(5);
            }

        } catch (InvalidSelection e) {
            if (maxStudents()){
                return;
            }
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"The parameters of your action are incorrect, check again"), getBoard().getCurrentPlayerIndex());
        }

        if(checkProfessor(color)){
            player.getSchoolBoard().addProfessor(color);
            try {
                checkIsland();
            } catch (GameOverException e) {
                boardHandler.endGame("The game is ending... we have a winner", player.getPlayerID());
            }
        }

        updateAfterMovedStudentToIsland();
    }

    private void endPlayerTurn(EndTurn message) {
        if(!model.getBoardManager().getClouds().contains(message.getCloud())){
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"The Cloud is not available...pick another one"), getBoard().getCurrentPlayerIndex());
            return;
        }
        if(message.isLastplayer()){
            message.getCloud().emptyCloud(model.getCurrentPlayer().getSchoolBoard());
            changeRound();
            return;
        }
        message.getCloud().emptyCloud(model.getCurrentPlayer().getSchoolBoard());
        model.getBoardManager().getClouds().remove(message.getCloud());
        updateAfterCloudPick();
        model.setNextPlayer();
        boardHandler.sendAllExcept(new CustomMessage(model.getCurrentPlayer() + "'s turn now, please wait...",false),model.getCurrentPlayerIndex());
        boardHandler.sendtoPlayer(new StartTurnMessage(true), model.getCurrentPlayerIndex());


    }

    private void moveMotherNature(MoveMotherNature motherNature) {
        if(boardHandler.getPhase() != 3){
            boardHandler.sendtoPlayer(new GameError(
                            Errors.INVALIDINPUT,
                            "Not in correct game phase to perform this command!"),
                    model.getCurrentPlayerIndex());
        }
        int steps = motherNature.getNumber_of_steps();
        MotherNature pawn = model.getBoardManager().getMotherNature();
        int maxsteps = model.getCurrentPlayer().getChosenCard().getNumber_of_steps();
        try{
            if (steps <= maxsteps){
                pawn.move(steps);
            }
            try {
                checkIsland();
            } catch (GameOverException e) {
                boardHandler.endGame("The game is ending... we have a winner", model.getCurrentPlayer().getPlayerID());
            }
        }catch (IllegalStateException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            boardHandler.sendtoPlayer(new MovedMotherNature(0,null,false), model.getCurrentPlayerIndex());
        }
        boardHandler.sendtoPlayer(new MovedMotherNature(steps,pawn.getPosition(),true), model.getCurrentPlayerIndex());
        boardHandler.setPhase(4);
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
                        " steps." ,false),
                player.getPlayerID());
        if(boardHandler.getPhase() == 3){
            boardHandler.sendAll(new CustomMessage("The round order is set...\n",false));
            startRound();
            List<Player> players = boardHandler.game().getActivePlayers();
            for (Player player1 : players) {
                boardHandler.sendtoPlayer(new CustomMessage("You are the " + model.getPlayersTurnOrder().indexOf(player1) +" player to play", false), player1.getPlayerID());
            }
            return;
        }
        model.setNextPlayer();
    }

    public void startRound() {
        //boardHandler.setPhase(4);
        model.setPlayerOrderTurn();
        model.setStartedRound(true);
        Player playerToStart = model.getPlayersTurnOrder().get(0);
        boardHandler.sendtoPlayer(new StartTurnMessage(true),playerToStart.getPlayerID());
        boardHandler.sendAllExcept(new CustomMessage("It's " + playerToStart.getNickname() + "'s turn, wait for him to finish", false), playerToStart.getPlayerID());
    }

    private synchronized void updateAfterCloudPick(){
        boardHandler.sendAll(new MoveMessage(model.getBoardManager().getClouds(),model.getCurrentPlayer().getSchoolBoard().getEntrance(),model.getCurrentPlayer().getNickname()));
        boardHandler.sendtoPlayer(new CustomMessage("Your turn has ended"), model.getCurrentPlayerIndex());
    }

    private synchronized void updateAfterMovedStudentToIsland(){
        boardHandler.sendAll(new MoveMessage(model.getCurrentPlayer().getSchoolBoard().getMovedstudents(),
                model.getBoardManager().getIslands(),model.getCurrentPlayer().getSchoolBoard().getEntrance(),
                model.getCurrentPlayer().getNickname()));
    }

    private synchronized void updateAfterMovedStudentToDiningRoom(){
        boardHandler.sendAll(new MoveMessage(model.getCurrentPlayer().getSchoolBoard().getMovedstudents(),
                model.getBoardManager().getIslands(), model.getCurrentPlayer().getSchoolBoard().getDiningRoom(), model.getCurrentPlayer().getSchoolBoard().getEntrance(),
                model.getCurrentPlayer().getNickname()));
    }


    public void changeRound(){
        listeners.firePropertyChange("endTurn",null,null);
        model.resetAssistantCards();
        model.resetMovedStudents();
        boardHandler.setClouds();
        updateAfterCloudPick();
        model.setCurrentPlayer(model.getPlayersTurnOrder().get(0));
        logger.log(Level.INFO,"A new Round is starting, sceglie " + model.getPlayersTurnOrder().get(0).getNickname());
        boardHandler.setPhase(2);
        boardHandler.sendtoPlayer( new ChooseAssistantCard("Choose an Assistant Card for the new round\n>",
                        model.getCurrentPlayer().getNickname(),
                        model.getCurrentPlayer().getAssistantCards()),
                model.getCurrentPlayerIndex());
        boardHandler.sendAllExcept(new CustomMessage(model.getCurrentPlayer().getNickname() + " is choosing his assistant card...please wait", false), model.getCurrentPlayerIndex());

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
