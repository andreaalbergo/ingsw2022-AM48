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
import it.polimi.ingsw.server.servermessages.gamemessages.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GameController class is the main controller class, it calls and manages some handlers, like roundHandler, in order to
 * manage various states of the game itself.
 *
 * @author David Barb, Andrea Albergo
 * @see PropertyChangeListener
 */
public class GameController implements PropertyChangeListener {
    private final Board model;
    private final BoardHandler boardHandler;
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    private final RoundHandler roundHandler;


    private boolean isExpert;

    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Method GameController to create its own instances.
     *
     * @param model of type Board.
     * @param boardHandler of type BoardHandler.
     */
    public GameController(Board model, BoardHandler boardHandler) {
        this.model = model;
        this.boardHandler = boardHandler;
        roundHandler = new RoundHandler(this);
        listeners.addPropertyChangeListener("RoundHandler",roundHandler);

    }

    /**
     * Method isExpert is a getter.
     *
     * @return of type boolean
     */
    public boolean isExpert() {
        return isExpert;
    }

    /**
     * Method setMode is a setter.
     *
     * @param mode of type boolean.
     */
    public void setMode(boolean mode) {
        this.isExpert = mode;
    }


    /**
     * Method getBoard is a getter.
     *
     * @return of type Board.
     */
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

    /**
     * Method checkIsland checks all the possible evolutions in terms conquering the island, merging it with one or more
     * other islands or to check a possible game over state if there are three main groups of islands.
     *
     * @throws GameOverException to call game over.
     */
    private void checkIsland() throws GameOverException {
        if(boardHandler.getPhase()==3){
            return;
        }
        int position = model.getBoardManager().getMotherNature().getPosition();
        IslandTile island  = boardHandler.game().getBoardManager().getIslands().get(position);
        if(Objects.equals(island.getIslandOwner(), model.getCurrentPlayer())){
            return;
        }
        Player player = model.getCurrentPlayer();
        Player challenger = island.getIslandOwner();
        if(challenger == null){
            int count = 0;
            for(int i = 0; i < 5 ; i++){
                if (player.getSchoolBoard().checkProfessor(Color.colorFromIndex(i))){
                    System.out.println("Ho il professore del color " + Color.colorFromIndex(i));
                    count = count + island.getStudents()[i];
                }
            }

            System.out.println("Sull'isola di madre natura ho influenza "+ count);

            if(count > 0){
                try {
                    island.setIslandOwner(player);
                    //boardHandler.sendAll(new BuiltTower(island.getIslandID(),player.getSchoolBoard().getTowers(),player.getNickname()));
                } catch (GameOverException e) {
                    boardHandler.endGame(player.getNickname() + " has positioned all the towers, so he wins", player.getPlayerID());
                }
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
                //boardHandler.sendAll(new BuiltTower(island.getIslandID(),player.getSchoolBoard().getTowers(),player.getNickname()));
            } catch (GameOverException e) {
                boardHandler.endGame(player.getNickname() + " has positioned all the towers, so he wins",player.getPlayerID());
            }
            int towers_challenger = challenger.getSchoolBoard().getTowers();
            challenger.getSchoolBoard().setTowers(towers_challenger + island.howManyTowers());
        }

        checkMerge(island,position);
    }

    /**
     * Method checkThreeIslands controls if there are three remained archipelagos to call a game over.
     *
     * @throws GameOverException to go game over state.
     */
    private void checkThreeIslands() throws GameOverException {
        int count = 0;
        int id = 0;
        for (IslandTile islandTile : getBoard().getBoardManager().getIslands()){
            if(islandTile.getIslandID() != id ) count ++;
        }
        if(count == 3) throw new GameOverException();
    }

    /**
     * Method checkMerge is needed to check if current island with mother nature can be conquered.
     *
     * @param island of type IslandTile.
     * @param position of type int - the iterator to check if the island before or after the current one can be merged.
     * @throws GameOverException to call game over.
     */
    private void checkMerge(IslandTile island, int position) throws GameOverException {
        System.out.println("CheckMerge...");
        int positionBefore = position;
        int position_after = position;
        if(position == 0){
            positionBefore =  12;
        } else if (position == 11) {
            position_after = -1;
        }

        IslandTile islandbefore = boardHandler.game().getBoardManager().getIslands().get(positionBefore - 1);
        //System.out.println("L'owner dell'isola before è " + islandbefore.getIslandOwner().getNickname());
        IslandTile islandafter = boardHandler.game().getBoardManager().getIslands().get(position_after + 1);
        //System.out.println("L'owner dell'isola after è " + islandafter.getIslandOwner().getNickname());
        //System.out.println("Io sto controllando rispetto all'isola "+island.getIslandOwner().getNickname());
        if(islandbefore.getIslandOwner() != null && islandafter.getIslandOwner()!= null && island.getIslandOwner()!=null &&
                Objects.equals(islandbefore.getIslandOwner().getNickname(), island.getIslandOwner().getNickname()) && Objects.equals(islandafter.getIslandOwner().getNickname(), island.getIslandOwner().getNickname())){
            if(boardHandler.game().getBoardManager().getIslands().size() == 5){
                throw new GameOverException();
            }
            islandbefore.mergeIslands(island);
            model.getBoardManager().getMotherNature().setNewID(islandbefore.getIslandID(),island.getIslandID());
            islandbefore.mergeIslands(islandafter);
            model.getBoardManager().getMotherNature().setNewID(islandbefore.getIslandID(),islandafter.getIslandID());
            model.getBoardManager().getMotherNature().setPosition(model.getBoardManager().getIslands().indexOf(islandbefore));
            System.out.println("Nel check merge ho unito quella dopo a madrenatura e quella prima");
            boardHandler.sendtoPlayer(new MovedMotherNature(0,model.getBoardManager().getIslands().indexOf(islandbefore),true),model.getCurrentPlayerIndex());
            boardHandler.sendAllExcept(new MovedMotherNature(0,model.getBoardManager().getIslands().indexOf(islandbefore),false),model.getCurrentPlayerIndex());
            return;
        }
        else if(islandbefore.getIslandOwner() != null && island.getIslandOwner()!=null &&
                Objects.equals(islandbefore.getIslandOwner(), island.getIslandOwner())){
            islandbefore.mergeIslands(island);
            model.getBoardManager().getMotherNature().setNewID(islandbefore.getIslandID(),island.getIslandID());
            model.getBoardManager().getMotherNature().setPosition(model.getBoardManager().getIslands().indexOf(islandbefore));
            //model.getBoardManager().getIslands().remove(island);
            System.out.println("Nel check merge ho unito quella prima a madrenatura e quella prima");
            checkThreeIslands();
            boardHandler.sendtoPlayer(new MovedMotherNature(0,model.getBoardManager().getIslands().indexOf(islandbefore),true),model.getCurrentPlayerIndex());
            boardHandler.sendAllExcept(new MovedMotherNature(0,model.getBoardManager().getIslands().indexOf(islandbefore),false),model.getCurrentPlayerIndex());
            return;
        }
        else if (islandafter.getIslandOwner()!= null && island.getIslandOwner()!=null &&
                Objects.equals(islandafter.getIslandOwner(), island.getIslandOwner())){
            island.mergeIslands(islandafter);
            model.getBoardManager().getMotherNature().setNewID(island.getIslandID(),islandafter.getIslandID());
            model.getBoardManager().getMotherNature().setPosition(model.getBoardManager().getIslands().indexOf(island));
            //model.getBoardManager().getIslands().remove(islandafter);
            System.out.println("Nel check merge ho unito quella dopo a madrenatura");
            checkThreeIslands();
            boardHandler.sendtoPlayer(new MovedMotherNature(0,model.getBoardManager().getIslands().indexOf(island),true),model.getCurrentPlayerIndex());
            boardHandler.sendAllExcept(new MovedMotherNature(0,model.getBoardManager().getIslands().indexOf(island),false),model.getCurrentPlayerIndex());
            return;
        }
        System.out.println("Nel check merge non ho unito nulla");
    }

    /**
     * Method checkProfessor to check if island can be conquered by the active player, only if it has more influence
     * its owned professors.
     *
     * @param color of type Color.
     * @return of type boolean
     */
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

    /**
     * Method moveStudentToDiningRoom is self explanatory.
     *
     * @param moveStudentToDiningRoom of type MoveStudentToDiningRoom.
     */
    private void moveStudentToDiningRoom(MoveStudentToDiningRoom moveStudentToDiningRoom) {

        Player player = model.getCurrentPlayer();
        if(boardHandler.getPhase() == 5){
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"Hai ragginto il massimo numero di studenti"), player.getPlayerID());
            return;
        }
        Color color = moveStudentToDiningRoom.getColor();
        try{
            player.getSchoolBoard().addStudentToDiningRoom(color);
            if(player.getSchoolBoard().getMovedStudents()== 4 && model.getActivePlayers().size() == 3){
                boardHandler.setPhase(5);
            }
            if(player.getSchoolBoard().getMovedStudents() == 3 && model.getActivePlayers().size() == 2){
                boardHandler.setPhase(5);
            }
            updateAfterMovedStudentToDiningRoom();
        } catch (InvalidSelection e) {
            if (maxStudents()){
                boardHandler.setPhase(5);
                boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"Hai ragginto il massimo numero di studenti"), player.getPlayerID());
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

        updateAfterMovedStudentToDiningRoom();
    }

    /**
     * Method maxStudents checks if the active player has already moved max number of students during its own turn.
     *
     * @return of type boolean
     */
    private boolean maxStudents(){
        if ((model.getCurrentPlayer().getSchoolBoard().getMovedStudents() == 4 && model.getActivePlayers().size() ==3) ||
                (model.getCurrentPlayer().getSchoolBoard().getNumberOfPlayers() == 3 && model.getActivePlayers().size()==2)) {
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT, "You already moved the maximum amount of students"), getBoard().getCurrentPlayerIndex());
            return true;
        }
        return false;
    }

    /**
     * Method moveStudentToIsland is to check if moved student can make the active player conquer the island.
     *
     * @param moveStudentToIsland of type MoveStudentToIsland.
     */
    private void moveStudentToIsland(MoveStudentToIsland moveStudentToIsland) {
        System.out.println("SEI NEL MOVESTUDENTTOISLAND DEL CONTROLLER");
        Player player = model.getCurrentPlayer();
        if(boardHandler.getPhase() == 5){
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"Hai ragginto il massimo numero di studenti"), player.getPlayerID());
            return;
        }
        IslandTile islandTile = getBoard().getBoardManager().getIslands().get(moveStudentToIsland.getIslandTile().getIslandID());
        Color color = moveStudentToIsland.getColor();
        try {
            player.getSchoolBoard().addStudentToIsland(color,islandTile);
            if(player.getSchoolBoard().getMovedStudents() == 4 && model.getActivePlayers().size() == 3){
                boardHandler.setPhase(5);
            }
            if(player.getSchoolBoard().getNumberOfPlayers() == 3 && model.getActivePlayers().size() == 2){
                boardHandler.setPhase(5);
            }

        } catch (InvalidSelection e) {
            if (maxStudents()){
                boardHandler.setPhase(5);
                boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"Hai ragginto il massimo numero di studenti"), player.getPlayerID());
                return; //TODO
            }
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"The parameters of your action are incorrect, check again"), getBoard().getCurrentPlayerIndex());
        }


        try {
            if(getBoard().getBoardManager().getMotherNature().getPosition() == islandTile.getIslandID()) checkIsland();
        } catch (GameOverException e) {
            boardHandler.endGame("The game is ending... we have a winner", player.getPlayerID());
        }
        updateAfterMovedStudentToIsland();
    }

    /**
     * Method endPlayerTurn ends active players moves and checks for new game turn or sets for next active player.
     *
     * @param message of type EndTurn.
     */
    private void endPlayerTurn(EndTurn message) {
         int idCloud = message.getCloud().getiD();
         boolean check = false;
         Cloud mycloud = null;
         for(Cloud cloud : model.getBoardManager().getClouds()){
             if(cloud.getiD() == idCloud){
                 check = true;
                 mycloud = cloud;
             }
         }
        if(!check){
            boardHandler.sendtoPlayer(new GameError(Errors.INVALIDINPUT,"The Cloud is not available...pick another one"), getBoard().getCurrentPlayerIndex());
            return;
        }
        if(message.isLastPlayer()){
            mycloud.emptyCloud(model.getCurrentPlayer().getSchoolBoard());
            updateAfterCloudPick();
            changeRound();
            return;
        }
        mycloud.emptyCloud(model.getCurrentPlayer().getSchoolBoard());
        updateAfterCloudPick();
        boardHandler.sendAllExcept(new CustomMessage(model.getCurrentPlayer() + "'s turn now, please wait...",false),model.getCurrentPlayerIndex());
        model.setNextPlayer();
        boardHandler.sendtoPlayer(new StartTurnMessage(true), model.getCurrentPlayerIndex());


    }

    /**
     * Method moveMotherNature is used to move mother nature.
     *
     * @param motherNature of type MoveMotherNature.
     */
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
        System.out.println("vuoi fare "+ steps + " puoi massimo" + maxsteps);
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
            boardHandler.sendtoPlayer(new MovedMotherNature(0,null,true), model.getCurrentPlayerIndex());
        }
        System.out.println("Ho spostato madre natura nell'isola " + pawn.getPosition() + "dopo aver fatto " + steps+ "passi");
        //updateAfterMovedStudentToIsland();
        boardHandler.sendAllExcept(new MovedMotherNature(steps,pawn.getPosition(),false),model.getCurrentPlayerIndex());
        boardHandler.sendtoPlayer(new MovedMotherNature(steps,pawn.getPosition(),true), model.getCurrentPlayerIndex());
        boardHandler.setPhase(4);

    }

    /**
     * Method chooseAssistantCard is synchronized and checks all the players chose the assistant card.
     *
     * @param assistantCard of type AssistantCard.
     */
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
                if(model.getPlayersTurnOrder().indexOf(player1) == 0){
                    boardHandler.sendtoPlayer(new CustomMessage("You are the " + (model.getPlayersTurnOrder().indexOf(player1) + 1) +" player to play", true), player1.getPlayerID());
                }else boardHandler.sendtoPlayer(new CustomMessage("You are the " + (model.getPlayersTurnOrder().indexOf(player1) + 1) +" player to play", false), player1.getPlayerID());
            }
            return;
        }
        model.setNextPlayer();
    }

    /**
     * Method startRound is used to set a new game phase state and start the first active player chosen thanks by
     * drawing assistant cards.
     */
    public void startRound() {
        //boardHandler.setPhase(4);
        model.setPlayerOrderTurn();
        model.setStartedRound(true);
        Player playerToStart = model.getPlayersTurnOrder().get(0);
        boardHandler.sendtoPlayer(new StartTurnMessage(true),playerToStart.getPlayerID());
        boardHandler.sendAllExcept(new CustomMessage("It's " + playerToStart.getNickname() + "'s turn, wait for him to finish", false), playerToStart.getPlayerID());
    }

    /**
     * Method updateAfterCloudPick sets end turn after choosing a cloud. (synchronized)
     */
    private synchronized void updateAfterCloudPick(){
        boardHandler.sendAll(new MoveMessage(model.getBoardManager().getClouds(),model.getCurrentPlayer().getSchoolBoard().getEntrance(),model.getCurrentPlayer().getNickname()));
        boardHandler.sendtoPlayer(new CustomMessage("Your turn has ended"), model.getCurrentPlayerIndex());
    }

    /**
     * Method updateAfterMoverStudentToIsland to update the island at every move.
     */
    private synchronized void updateAfterMovedStudentToIsland(){
        System.out.println("Ha mosso " + model.getCurrentPlayer().getSchoolBoard().getMovedStudents() + " finora");
        boardHandler.sendAll(new MoveMessage(model.getCurrentPlayer().getSchoolBoard().getMovedStudents(),
                model.getBoardManager().getIslands(),model.getCurrentPlayer().getSchoolBoard().getEntrance(),
                model.getCurrentPlayer().getNickname()));
    }

    /**
     * Method updateAfterMovedStudentToDiningRoom is synchronized and updates the dining room after every move.
     */
    private synchronized void updateAfterMovedStudentToDiningRoom(){
        System.out.println("Ha mosso " + model.getCurrentPlayer().getSchoolBoard().getMovedStudents() + " finora");
        boardHandler.sendAll(new MoveMessage(model.getCurrentPlayer().getSchoolBoard().getMovedStudents(),
                model.getBoardManager().getIslands(), model.getCurrentPlayer().getSchoolBoard().getDiningRoom(), model.getCurrentPlayer().getSchoolBoard().getEntrance(),
                model.getCurrentPlayer().getNickname()));
    }


    /**
     * changes round after the choice of the cloud from the last of the players
     */
    public void changeRound(){
        listeners.firePropertyChange("endTurn",null,null);
        model.resetAssistantCards();
        model.resetMovedStudents();
        boardHandler.setClouds();
        //updateAfterCloudPick();
        model.setCurrentPlayer(model.getPlayersTurnOrder().get(0));
        logger.log(Level.INFO,"A new Round is starting, sceglie " + model.getPlayersTurnOrder().get(0).getNickname());
        boardHandler.setPhase(2);
        boardHandler.sendtoPlayer(new CustomMessage("It's a new Round",true), getBoard().getCurrentPlayer().getPlayerID());
        boardHandler.sendtoPlayer( new ChooseAssistantCard("Choose an Assistant Card for the new round\n>",
                        model.getCurrentPlayer().getNickname(),
                        model.getCurrentPlayer().getAssistantCards()),
                model.getCurrentPlayer().getPlayerID());
        boardHandler.sendAllExcept(new CustomMessage(model.getCurrentPlayer().getNickname() + " is choosing his assistant card...please wait", false), model.getCurrentPlayerIndex());

    }

    /**
     * sets player's wizard in the model
     * @param wizard Wizard
     * @param id int
     */
    public void setWizard(Wizard wizard, int id){
        model.getActivePlayers().get(id).setWizard(wizard);
    }
    /**
     * sets player's tower in the model
     * @param tower Wizard
     * @param id int
     */

    public void setTower(Tower tower, int id){
        model.getActivePlayers().get(id).setTower(tower);
    }


    /**
     * used to communicate that the game over
     */
    public void gameOver(){
        System.out.println("Last round finished, Game Over!!!");
        boardHandler.endGame("The number of rounds reached the maximum numbers");
    }
}
