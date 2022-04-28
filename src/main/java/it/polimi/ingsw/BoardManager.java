package it.polimi.ingsw;

import java.util.ArrayList;

public class BoardManager {

    private final int NUMBER_OF_ISLANDS = 12;
    private int remainingCoinCounter = 20; //BARB: Per ora lascio così, poi sistemo con isExpertMode
    private Bag bag;
    private ArrayList<Cloud> clouds;
    private ArrayList<IslandTile> islands;
    //private ArrayList<IslandTile> unifiedIsland;  NEVER USED
    private ArrayList<Player> players;
    private Round turn;
    private Player currentPlayer;
    private MotherNature motherNature;


    public BoardManager() {
        this.bag = new Bag();
        this.clouds = new ArrayList<>();
        this.islands = new ArrayList<>(NUMBER_OF_ISLANDS);
        //this.unifiedIsland = new ArrayList<>();
        this.players = new ArrayList<>(Board.getNumberOfPlayers());
        this.turn = new Round(); //BARB: riguarderò sta parte meglio
        this.currentPlayer = null;
        this.motherNature = new MotherNature();


        Cloud cloud1 = new Cloud();
        clouds.add(cloud1);
        Cloud cloud2 = new Cloud();
        clouds.add(cloud2);

        Player player1 = new Player(currentPlayer.getNickname(), 1,true, Wizards.WIZARD1, Board.getNumberOfPlayers(), TowersColor.WHITE, Board.isExpertMode());
        players.add(player1);
        Player player2 = new Player(currentPlayer.getNickname(), 2,false, Wizards.WIZARD2, Board.getNumberOfPlayers(), TowersColor.BLACK, Board.isExpertMode());
        players.add(player2);

        if(Board.getNumberOfPlayers() == 3){

            Cloud cloud3 = new Cloud();
            clouds.add(cloud3);
            Player player3 = new Player(currentPlayer.getNickname(), 3,false, Wizards.WIZARD3, Board.getNumberOfPlayers(), TowersColor.GRAY, Board.isExpertMode());
            players.add(player3);

        }

    }

    private int getPlayerIndex(Player givenPlayer) {

        int i = 0, index = 0;

        for(Player p : players){

            if(givenPlayer == p){
                index = i;
                break;
            }

            i++;

        }

        return index;
    }

    public void takeCoin() throws Exception {

        int counter = 0;

        for (int i : currentPlayer.getSchoolBoard().getDiningRoom()) {    //for every index of the array control if there's a student on a place with a coin
            if (currentPlayer.getSchoolBoard().getDiningRoom()[i] % 3 == 0)
                counter++;
        }

        if(remainingCoinCounter - counter >= 0) {
            remainingCoinCounter = remainingCoinCounter - counter;
        }

        if(remainingCoinCounter - counter <= 0 && remainingCoinCounter != 0){
            counter = remainingCoinCounter;
            remainingCoinCounter = 0;
        }

        else{
            throw new Exception("No coins available");
        }

        currentPlayer.addCoins(counter);

    }

    public void setupSchool(int numberOfPlayers) {

        for(Player p : players){
            new SchoolBoard(p.getNickname());   //Call SchoolBoard constructor
        }

    }

    //NO IMPLEMENTATION YET
    public void setupRound() {

        //random function gives idPlayer or nickname and sends it to Round.turnorder() arraylist

    } //choose who's the first to play and puts in the turnOrder's array the players

    public void chooseStepsMotherNature(int steps) {

        int index = getPlayerIndex(currentPlayer);

        if(steps >= 1 && steps <= turn.getChosenCards().get(index).getNumber_of_steps()){
            motherNature.move(steps);
        }
        //else
            //throw new IllegalStateException(System.out.println("You have to move Mother Nature between 1 and " + round.getChosenCards().get(index).getnumber_of_steps));
    }

    public boolean checkInfluence(IslandTile island) {

        ArrayList<Integer> Influencecounter = new ArrayList<>();

        for(Player p: players){

            int counter = 0;

            for (int j = 0; j < 5; j++) {        //for(int j:Player.SchoolBoard.professors[j])


                if (p.getSchoolBoard().getProfessors()[j]) {     //if player have the professor, == true

                    counter = counter + island.getStudents()[j];

                }

                if (p.getNickname().equals(island.getOwnerNickname())) {

                   counter++;

               }

                Influencecounter.add(counter);

            }

        }

        int indexmax = 0, valmax = Influencecounter.get(0);


        for(int i : Influencecounter){

            if(Influencecounter.get(i) > valmax){
                valmax = Influencecounter.get(i);
                indexmax = i;
            }

        }

        if(getPlayerIndex(currentPlayer) == indexmax){
            return true;
        }

        else{
            return false;
        }
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

    //public boolean checkActiveCharacterCards() {}

    public void checkToAddProfessor(Color givenColor) {

        int color = givenColor.getColorIndex();
        Player a = players.get(0);
        int maxindex = 0;
        int i, j = 0;

        for(i = 0; i+1 < Board.getNumberOfPlayers(); i++) {
            for (j = i + 1; j < Board.getNumberOfPlayers(); j++) {

                if(players.get(i).getSchoolBoard().getDiningRoom()[color] > players.get(j).getSchoolBoard().getDiningRoom()[color] && players.get(i).getSchoolBoard().getDiningRoom()[color] > a.getSchoolBoard().getDiningRoom()[color]){
                    a = players.get(i);
                    maxindex = i;
                }

                if(players.get(i).getSchoolBoard().getDiningRoom()[color] < players.get(j).getSchoolBoard().getDiningRoom()[color] && players.get(j).getSchoolBoard().getDiningRoom()[color] > a.getSchoolBoard().getDiningRoom()[color]){
                    a = players.get(j);
                    maxindex = j;
                }

                if(i != j && players.get(i).getSchoolBoard().getDiningRoom()[color] == players.get(j).getSchoolBoard().getDiningRoom()[color]){
                    maxindex = -1;
                }

            }

        }

        if(maxindex == -1){

            players.get(i).getSchoolBoard().removeProfessor(givenColor);
            players.get(j).getSchoolBoard().removeProfessor(givenColor);

        }

        else{
            a.getSchoolBoard().addProfessor(givenColor);
        }

    }

    public void drawFromBagToClouds() {

        bag.extractPawnToCloud();

    }

    public boolean checkNickname() {

        for(int i = 0; i+1 < Board.getNumberOfPlayers(); i++){
            for(int j = i+1; j < Board.getNumberOfPlayers(); j++) {

                if (players.get(i).getNickname().equals(players.get(j).getNickname()) && i != j) {
                    return false;
                }
            }
        }

        return true;

    }

    public void chooseCloudTile(Cloud cloud) {

        boolean waitToFill = true;

        cloud.emptyCloud(currentPlayer.getSchoolBoard(), cloud);

        for(Cloud c : clouds) {
            for (int i = 0; i < 5; i++) {

                //NEED getStudents() in Cloud

                //if (c.getStudents.get(i) == 0) {
                  //  waitToFill = false;
                //}
                //else{
                  //  waitToFill = true;
                //}

            }
        } //controls if all clouds are empty

        if(!waitToFill) {       // == false

            for(Cloud c : clouds) {

                c.fillStudents(bag, cloud);

            }
        }

    }

    public void buyCharacterCards(CharacterCard card) throws Exception {

        if (currentPlayer.getCoins() >= card.getCharacterEffectCost()){
            card.chooseCard(card);
            //NEED removeCoins() in Player
            //currentPlayer.removeCoin(card.getCharacterEffectCost());
        }

        else{
            throw new Exception("You don't have enough money");
        }

    }

    private boolean checkGameOver() {

        boolean emptyBag = false;

        if(currentPlayer.getSchoolBoard().getTowers() == 0){
            return true;
        } //if a player have placed all the towers


        //It controls only the currentPlayer
        for(int i = 0; i < 5; i++){

            if(bag.getStudents().get(i) == 0){
                emptyBag = true;
            }

            else{
                emptyBag = false;
            }
        }

        if(currentPlayer.getAssistantCards().isEmpty()){        // == true

            return true;

        }

        //count how many island there are on the board, if less than 3 then is gameover

        return emptyBag;
    }

}
