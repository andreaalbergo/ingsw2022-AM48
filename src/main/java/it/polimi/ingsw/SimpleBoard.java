/*
package it.polimi.ingsw;

import java.util.*;

public class SimpleBoard {
    private final List<Player> players;
    private final Bag bag;
    private final List<Cloud> clouds;
    private final Map<Integer, String> professorsInUse;
    private final MotherNature motherNature;
    private final List<IslandTile> islandTiles;
    private Round turn; //call this object in another way than Round
    private static Player currentPlayer;

    public SimpleBoard() {
        final int NUMBER_OF_ISLANDS = 12;
        this.players = new ArrayList<>(Board.getNumberOfPlayers());
        this.bag = new Bag();
        this.clouds = new ArrayList<>(Board.getNumberOfPlayers());
        this.professorsInUse = new HashMap<>();
        this.motherNature = new MotherNature();
        this.islandTiles = new ArrayList<>(NUMBER_OF_ISLANDS);

        for(int i=0; i<Board.getNumberOfPlayers(); i++){
            Cloud cloud = new Cloud(i);
            clouds.add(cloud);
        }
         /*
        for(Player p : players){
            players.add(new Player(p.getNickname(), TURN_ORDER , TURN_STATUS , WIZARD_NUMBER , Board.getNumberOfPlayers(), TOWER_COLOR , Board.isExpertMode()));
            cloud.add(new Cloud());
        }
         /*
        for(int i = 0; i < NUMBER_OF_ISLANDS; i++){
            islandTiles.add(new IslandTile());
        }

    }

    private int indexPlayerForTurn = 0;
    private static ArrayList<Integer> firstTurnSorted = new ArrayList<>(Board.getNumberOfPlayers());

    public void login(String nickname, Wizard chosenWizard, TowersColor towerColor) throws Exception {
*/
/*
        boolean playerTurn;
        Player player;

        if(indexPlayerForTurn == 0) {

            firstTurnSorted = sortFirstTurn();
        }

        else{
            SimpleBoardManager.checkNickname(nickname);
        }

        int idPlayerForTurn = firstTurnSorted.get(indexPlayerForTurn) + 1;

        if(idPlayerForTurn == 1){
            playerTurn = true;
        }
        else{
            playerTurn = false;
        }

        player = new Player(nickname, idPlayerForTurn, playerTurn, chosenWizard, Board.getNumberOfPlayers(), towerColor, Board.isExpertMode());

        players.add(player);

        System.out.println("You are the " + idPlayerForTurn + " player");

        if(idPlayerForTurn == 1){
            setCurrentPlayer(player);
        }

        indexPlayerForTurn++;

        if(players.size() == Board.getNumberOfPlayers()){

            this.turn = new Round();

        }

    }

    private ArrayList<Integer> sortFirstTurn(){

        ArrayList<Integer> givenList = new ArrayList<>(Board.getNumberOfPlayers());
        ArrayList<Integer> turnOrderList = new ArrayList<>(Board.getNumberOfPlayers());

        for(int i = 0; i < Board.getNumberOfPlayers(); i++){
            givenList.add(i);
        }

        Random rand = new Random();

        for(int i = 0; i < Board.getNumberOfPlayers(); i++){
            int randomIndex = rand.nextInt(givenList.size());
            int randomElement = givenList.get(randomIndex);
            givenList.remove(randomIndex);
            turnOrderList.add(randomElement);
        }

        return turnOrderList;

    }

    //called by Round class for changing turn
    public static void setCurrentPlayer(Player player){
        currentPlayer = player;
    }



    @Override
    public List<Player> getPlayers() {
        return this.players;
    }


    //method that fills all clouds with students from bag
    @Override
    public void drawFromBagToClouds() {
        for (Cloud cloud: this.clouds) {
            int randomColor = this.bag.getRandomColorFromBag();
            cloud.addStudentToCloud(randomColor);
        }
    }

    //method that fills all school entrances for the first time, in setup phase
    @Override
    public void setupAllSchoolEntrances() {
        for(Player player: this.players) {
            SchoolBoard schoolBoard = player.getSchoolBoard();
            this.bag.setupSchoolEntrance(schoolBoard);
        }
    }

    @Override
    public void checkProfessorAddition() {
        //LATER
    }

    @Override
    public void chooseStepsMotherNature(int chosenSteps) {
        if(chosenSteps>=1 && chosenSteps<=turn.getCurrentPlayersAssistantCard())
            this.motherNature.move(chosenSteps);
    }

    @Override
    public void tryConqueringIsland(IslandTile islandTile) {
        Map<String,Integer> playersInfluence = new HashMap<>();
        int tmpPlayersInfluence;
        String nicknameHighestInfluence = null;

        if (!islandTile.isIslandWithoutTower()) {
            playersInfluence.putIfAbsent(islandTile.getIslandOwner(), islandTile.howManyTowers());
        }

        for (int i = 0; i < 5; i++) {
            if(this.professorsInUse.get(i) != null && playersInfluence.containsKey(this.professorsInUse.get(i))) {
                tmpPlayersInfluence = playersInfluence.get(this.professorsInUse.get(i));
                playersInfluence.replace(this.professorsInUse.get(i),
                        tmpPlayersInfluence+ islandTile.getStudents()[i]);
            } else if (this.professorsInUse.get(i) != null) {
                playersInfluence.putIfAbsent(this.professorsInUse.get(i), islandTile.getStudents()[i]);
            }
        }

        tmpPlayersInfluence = 0;
        for(var entry: playersInfluence.entrySet()) {
            if(entry.getValue() > tmpPlayersInfluence){
                nicknameHighestInfluence = entry.getKey();
                tmpPlayersInfluence = entry.getValue();
            }
        }

        islandTile.setIslandOwner(nicknameHighestInfluence);
        checkMergingIslands(islandTile);
    }

    @Override
    public void checkMergingIslands(IslandTile islandTile) {
        List<IslandTile> adjacentIslands;
        adjacentIslands = getPreviousNextIsland(islandTile);

        if(adjacentIslands.get(0).getIslandOwner()==adjacentIslands.get(1).getIslandOwner()){
            islandTile.mergeIslands(adjacentIslands.get(0), adjacentIslands.get(1));
            this.islandTiles.remove(adjacentIslands.get(1));
        }

        if (adjacentIslands.get(0).getIslandOwner()==adjacentIslands.get(2).getIslandOwner()){
            islandTile.mergeIslands(adjacentIslands.get(0), adjacentIslands.get(2));
            this.islandTiles.remove(adjacentIslands.get(2));
        }
    }

    public List<IslandTile> getPreviousNextIsland(IslandTile islandTile) {
        int currentIslandIndex = this.islandTiles.indexOf(islandTile);
        List<IslandTile> sublistIsland = new ArrayList<>();

        if(currentIslandIndex==0) {
            sublistIsland.add(islandTile);
            sublistIsland.add(this.islandTiles.get(1));
            sublistIsland.add(this.islandTiles.get(this.islandTiles.size()-1));
        } else if(currentIslandIndex==this.islandTiles.size()-1) {
            sublistIsland.add(this.islandTiles.get(this.islandTiles.size()-2));
            sublistIsland.add(islandTile);
            sublistIsland.add(this.islandTiles.get(0));
        } else {
            sublistIsland.add(this.islandTiles.get(currentIslandIndex-1));
            sublistIsland.add(islandTile);
            sublistIsland.add(this.islandTiles.get(currentIslandIndex+1));
        }

        return sublistIsland;
    }

    @Override
    public void chooseCloudTile(Cloud cloud) {

    }

    @Override
    public void checkGameOverConditions() {

    }
}
*/