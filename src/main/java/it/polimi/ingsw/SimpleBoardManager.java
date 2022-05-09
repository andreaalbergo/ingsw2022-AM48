package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleBoardManager implements BoardManager{

    private final int NUMBER_OF_ISLANDS = 12;
    private static int remainingCoinCounter = 20; //BARB: Per ora lascio così, poi sistemo con isExpertMode
    private final Bag bag;
    private int numberofPlayers;
    private boolean mode;
    private ArrayList<Cloud> clouds;
    private ArrayList<IslandTile> islands;
    //private ArrayList<Old_Island> unifiedIsland;  NEVER USED
    private ArrayList<Player> players;
    private Round turn;
    private static Player currentPlayer;
    private MotherNature motherNature;
    private CharacterCard characterCard;
    private Board board;

    public SimpleBoardManager(int numberOfPlayers, boolean mode) {

        this.bag = new Bag(numberOfPlayers);
        this.clouds = new ArrayList<>();
        this.islands = new ArrayList<>(NUMBER_OF_ISLANDS);
        //this.unifiedIsland = new ArrayList<>();
        this.players = new ArrayList<>();
        this.motherNature = new MotherNature();
        //this.board = board;
        this.numberofPlayers = numberOfPlayers;
        this.mode = mode;


        for(int numberOfIslands = 0; numberOfIslands < NUMBER_OF_ISLANDS; numberOfIslands++){
            islands.add(new IslandTile());
        }

    }

    //for the setUp of the first turn
    private int indexPlayerForTurn = 0;
    private ArrayList<Integer> firstTurnSorted = new ArrayList<>(numberofPlayers);

    //Maybe a SetUp players class with login() and sortFirstTurn(), checkNickname()

    @Override
    public void login(String nickname, Wizard chosenWizard, TowersColor towerColor) throws Exception {

        boolean playerTurn;
        Player player;

        if(indexPlayerForTurn == 0) {

            firstTurnSorted = sortFirstTurn();
        }

        else{
            checkNickname(nickname);
        }

        int idPlayerForTurn = firstTurnSorted.get(indexPlayerForTurn) + 1;

        playerTurn = (idPlayerForTurn == 1);

        player = new Player(nickname, idPlayerForTurn, playerTurn, chosenWizard, numberofPlayers, towerColor, mode);

        players.add(player);

        System.out.println("You are the " + idPlayerForTurn + " player");

        if(idPlayerForTurn == 1){
            setCurrentPlayer(player);
        }

        indexPlayerForTurn++;

        if(players.size() == numberofPlayers){

            this.turn = new Round(board);
            for(int numberOfClouds = 0; numberOfClouds < numberofPlayers; numberOfClouds++){
                clouds.add(new Cloud(numberOfClouds, board));
            }

        }

    }

    @Override
    public ArrayList<Integer> sortFirstTurn(){

        ArrayList<Integer> givenList = new ArrayList<>(numberofPlayers);
        ArrayList<Integer> turnOrderList = new ArrayList<>(numberofPlayers);

        for(int i = 0; i < numberofPlayers; i++){
            givenList.add(i);
        }

        Random rand = new Random();

        for(int i = 0; i < numberofPlayers; i++){
            int randomIndex = rand.nextInt(givenList.size());
            int randomElement = givenList.get(randomIndex);
            givenList.remove(randomIndex);
            turnOrderList.add(randomElement);
        }

        return turnOrderList;

    }

    //called by Round class for changing turn
    @Override
    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    @Override
    public int getPlayerIndex(String givenPlayer) {

        int i, index = -1;

        for(i = 0; i < numberofPlayers; i++){

            if(givenPlayer.equals(players.get(i).getNickname())){
                index = i;
            }

        }

        return index;
    }

    //calls when all students have been moved from entrance to diningRoom
    //basic implementation of the for, maybe to recheck

    //need help with this one
    @Override
    public void chooseStepsMotherNature(int steps, int effect) {
        //effect is set = 2 if it's called by the characterCard heraldEffect(), otherwise is 0
        int index = getPlayerIndex(currentPlayer.getNickname());

        if (steps >= 1 && steps <= turn.getCurrentPlayersAssistantCard() + effect) {
            motherNature.move(steps);
        }

        else {
            throw new IllegalStateException("You have to move Mother Nature between 1 and " + turn.getCurrentPlayersAssistantCard());
        }
    }

    //called by addStudentToIsland() method in SchoolBoard
    @Override
    public boolean checkInfluence(Old_Island island, int effect) {
        //effect = 0 if it's called by the normal game
        //effect = 1 if it's called by princeEffect() --> to relook, there's something missing
        //effect = 2 if it's called by grocerEffect()
        //effect = 3 if it's called by centaurEffect()

        /*
        if(effect == 2)
            if(island.getNoEntryTile[getIdIsland(island)] == true){
                return false;
                //throw new Expection("Grocer card has been played, you can't build a tower here");
        }
         */

        ArrayList<Integer> influenceCounterList = new ArrayList<>();

        for(Player p: players){

            int influenceCounter = 0;

            for (int colorIndex = 0; colorIndex < 5; colorIndex++) {        //for(int j:Player.SchoolBoard.professors[j])


                if (p.getSchoolBoard().getProfessors()[colorIndex]) {     //if player have the professor, == true

                    influenceCounter = influenceCounter + island.getStudents()[colorIndex]; //influence of the students

                }

                if (p.getNickname().equals(island.getOwnerNickname()) && effect != 3) {    //influence of the tower

                    influenceCounter++;

                }

                influenceCounterList.add(influenceCounter);

            }

        }

        int indexMax = 0, valMax = influenceCounterList.get(indexMax);


        for(int indexForInfluence : influenceCounterList){

            if(influenceCounterList.get(indexForInfluence) > valMax){
                valMax = influenceCounterList.get(indexForInfluence);
                indexMax = indexForInfluence;
            }

        }

        return getPlayerIndex(currentPlayer.getNickname()) == indexMax;
    }

    /*
    mergeIsland() to fix

    private void checkToMergeIslands(IslandTile island) {

        int position = motherNature.getPosition();

        if(position == 1){
            if(islands.get(position).getOwnerNickname() == currentPlayer.getNickname() && islands.get(position+1).getOwnerNickname() == currentPlayer.getNickname()){ //next island
                island.mergeIsland(position+1);
            }
            if(islands.get(position).getOwnerNickname() == currentPlayer.getNickname() && islands.get(12).getOwnerNickname() == currentPlayer.getNickname()){  //previous island
                island.mergeIsland(12);
            }
        }

        if(position == 12){
            if(islands.get(position).getOwnerNickname() == currentPlayer.getNickname() && islands.get(1).getOwnerNickname() == currentPlayer.getNickname()){ //next island
                island.mergeIsland(1);
            }
            if(islands.get(position).getOwnerNickname() == currentPlayer.getNickname() && islands.get(position-1).getOwnerNickname() == currentPlayer.getNickname()){  //previous island
                island.mergeIsland(position-1);
            }
        }

        else{
            if(islands.get(position).getOwnerNickname() == currentPlayer.getNickname() && islands.get(position+1).getOwnerNickname() == currentPlayer.getNickname()){ //next island
                island.mergeIsland(position+1);
            }
            if(islands.get(position).getOwnerNickname() == currentPlayer.getNickname() && islands.get(position-1).getOwnerNickname() == currentPlayer.getNickname()){  //previous island
                islands.mergeIsland(position-1);
            }
        }
    }

     */

    //is this method useful?
    @Override
    public boolean checkActiveCharacterCards() {

        return characterCard.isActive();

    }

    //called by addStudentToDiningRoom() in SchoolBoard
    @Override
    public void checkToAddProfessor(Color givenColor, int effect) {
        //effect is set to 1 if it's called by a CharacterCard, otherwise is 0

        int color = givenColor.getColorIndex();
        Player a = players.get(0);
        int isEqual = 0;

        for(Player firstPointer : players) {
            for (Player secondPointer : players) {
                if(firstPointer != secondPointer) {
                    if (firstPointer.getSchoolBoard().getDiningRoom()[color] > secondPointer.getSchoolBoard().getDiningRoom()[color] && firstPointer.getSchoolBoard().getDiningRoom()[color] > a.getSchoolBoard().getDiningRoom()[color]) {
                        a = firstPointer;
                    }

                    if (firstPointer.getSchoolBoard().getDiningRoom()[color] < secondPointer.getSchoolBoard().getDiningRoom()[color] && secondPointer.getSchoolBoard().getDiningRoom()[color] > a.getSchoolBoard().getDiningRoom()[color]) {
                        a = secondPointer;
                    }

                    if (firstPointer.getSchoolBoard().getDiningRoom()[color] == secondPointer.getSchoolBoard().getDiningRoom()[color]) {
                        isEqual = -1;
                    }

                    if (isEqual == -1) {

                        firstPointer.getSchoolBoard().removeProfessor(givenColor);
                        secondPointer.getSchoolBoard().removeProfessor(givenColor);

                        //if inkeeperEffect() is active
                        if (effect == 1) {

                            if (currentPlayer == firstPointer || currentPlayer == secondPointer) {
                                currentPlayer.getSchoolBoard().addProfessor(givenColor);
                            }

                        }
                        isEqual = 0;
                    }
                }

            }

        }

        a.getSchoolBoard().addProfessor(givenColor);


    }

    //called by assignNextTurn() in Round
    @Override
    public void drawFromBagToClouds() {

        extractPawnsToCloud(clouds);

    }

    //to implements
    @Override
    public void extractPawnsToCloud(ArrayList<Cloud> clouds) {

        int random_number;
        Random random = new Random();

        for(Cloud c : clouds){

            //is it only for 1 time or it fills four students?
            random_number = random.nextInt(1,6);
            c.addStudentToCloud(random_number);

        }

    }

    @Override
    public void checkNickname(String givenNickname) throws Exception {

        for (Player player : players) {

            if (givenNickname.equals(player.getNickname())) {
                throw new Exception("Please choose a different Nickname, this one already exists");
            }
        }

    }

    //To add commands when GameOver = true
    //called after motherNature moved or after all students have been moved from entrance
    @Override
    public void chooseCloudTile(Cloud cloud) throws Exception {

        boolean gameOver;

        cloud.emptyCloud(currentPlayer.getSchoolBoard(), cloud);

        if(cloud.checkEmptyCloud()) {       // == true, controls if was the last cloud
            extractPawnsToCloud(clouds);
        }

        gameOver = checkGameOver();
        if(!gameOver){
            turn.assignNextRound();
        }

        else{
            //add something
            System.out.println("GameOver");
        }

    }

    //called when?
    @Override
    public void buyCharacterCards(Character card) throws Exception {

        if (currentPlayer.getCoins() >= card.getCharacterEffectCost(card)){
            characterCard.chooseCharacterCard(card);
            currentPlayer.removeCoin(card.getCharacterEffectCost(card));
        }

        else{
            throw new Exception("You don't have enough money");
        }

    }

    @Override
    public boolean checkGameOver() {

        boolean emptyBag = false;

        for(Player p : players) {
            if (p.getSchoolBoard().getTowers() == 0) {
                return true;
            } //if a player have placed all the towers
        }


        //controls if the bag is empty
        for(int colorIndex = 0; colorIndex < 5; colorIndex++){

            emptyBag = (bag.getStudents()[colorIndex] == 0);
        }

        if(!emptyBag) {

            if (turn.getRoundNumber() == 10) {        //if all the AssistantCards have been used
                return true;
            }

            //count how many island there are on the board, if less than 3 then is gameover
            //still to implements

        }

        return emptyBag;

    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public MotherNature getMotherNature() {
        return motherNature;
    }

    @Override
    public Round getTurn() {
        return turn;
    }

    @Override
    public ArrayList<Integer> getFirstTurnSorted() {
        return firstTurnSorted;
    }

    @Override
    public CharacterCard getCard() {
        return characterCard;
    }

    @Override
    public Player getPlayer(int index){
        return players.get(index);
    }

    @Override
    public ArrayList<Cloud> getClouds() {
        return clouds;
    }

    @Override
    public Cloud getCloud(int index){
        return clouds.get(index);
    }

    @Override
    public void checkMergingIslands(IslandTile islandTile) {
        List<IslandTile> adjacentIslands;
        adjacentIslands = getPreviousNextIsland(islandTile);

        //usa equals se devi comparare due stringhe non gli ==
        if(adjacentIslands.get(1).getIslandOwner().equals(adjacentIslands.get(0).getIslandOwner())){
            islandTile.mergeIslands(adjacentIslands.get(0), adjacentIslands.get(1));
            this.islands.remove(adjacentIslands.get(1));
        }

        if (adjacentIslands.get(0).getIslandOwner().equals(adjacentIslands.get(2).getIslandOwner())){
            islandTile.mergeIslands(adjacentIslands.get(0), adjacentIslands.get(2));
            this.islands.remove(adjacentIslands.get(2));
        }
    }

    @Override
    public List<IslandTile> getPreviousNextIsland(IslandTile islandTile) {
        int currentIslandIndex = this.islands.indexOf(islandTile);
        List<IslandTile> sublistIsland = new ArrayList<>();

        if(currentIslandIndex==0) {
            sublistIsland.add(islandTile);
            sublistIsland.add(this.islands.get(1));
            sublistIsland.add(this.islands.get(this.islands.size()-1));
        } else if(currentIslandIndex==this.islands.size()-1) {
            sublistIsland.add(this.islands.get(this.islands.size()-2));
            sublistIsland.add(islandTile);
            sublistIsland.add(this.islands.get(0));
        } else {
            sublistIsland.add(this.islands.get(currentIslandIndex-1));
            sublistIsland.add(islandTile);
            sublistIsland.add(this.islands.get(currentIslandIndex+1));
        }

        return sublistIsland;
    }

    @Override
    public Board getBoard(){
        return this.board;
    }
}


