package it.polimi.ingsw;

import java.util.ArrayList;

public class BoardManager {
    private final int NUMBER_OF_ISLANDS = 12;
    private int remainingCoinCounter = 20; //BARB: Per ora lascio così, poi sistemo con isExpertMode
    private Bag bag;
    private ArrayList<CloudTile> clouds;
    private ArrayList<IslandTile> islands;
    private ArrayList<IslandTile> unifiedIsland;
    private ArrayList<Player> players;
    private ArrayList<Boolean> professors;
    //private ArrayList<CharacterDecorator> characters;
    private Round turn;
    private Player currentPlayer;
    private MotherNature motherNature;

    public BoardManager() {
        this.bag = new Bag();
        this.clouds = new ArrayList<>(Board.getNumberOfPlayers());
        this.islands = new ArrayList<>(NUMBER_OF_ISLANDS);
        this.unifiedIsland = new ArrayList<>();
        this.players = new ArrayList<>(Board.getNumberOfPlayers());
        this.professors = new ArrayList<>();
        this.turn = new Round(); //BARB: riguarderò sta parte meglio
        this.currentPlayer = null;
        this.motherNature = new MotherNature();
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
    public void setupRound() {} //choose whos the first to play and puts in the turnOrder's array the players

    public void chooseStepsMotherNature(int steps) {

        int index = getPlayerIndex(currentPlayer);

        if(steps >= 1 && steps <= turn.getChosenCards().get(index).getNumber_of_steps()){
            motherNature.move(steps);
        }
        //else
            //throw new IllegalStateException(System.out.println("You have to move Mother Nature between 1 and " + round.getChosenCards().get(index).getnumber_of_steps));
    }

    //WAITING ISLANDTILE CLASS
    /*
    public boolean checkInfluence(IslandTile island) {

         ArrayList<Integer> Influencecounter = new ArrayList<Integer>();

        for(Player p: players){

            int counter = 0;

            for (int j = 0; j < 5; j++) {        //for(int j:Player.SchoolBoard.professors[j])

                if (p.getSchoolBoard().getProfessors()[j] == true) {     //if player have the professor

                    counter = counter + island.getStudents().get(j);

                }

                if (p == island.getOwner()) {

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

     */
    /*

    //WAITING ISLANDTILE CLASS
    private void checkToMergeIslands(IslandTile island) {

        int position = motherNature.getPosition();

        if(position == 1){
            if(islands.get(position).getOwner() == currentPlayer && islands.get(position+1).getOwner() == currentPlayer){ //next island
                island.mergeIsland(position+1);
            }
            if(islands.get(position).getOwner() == currentPlayer && islands.get(12).getOwner() == currentPlayer){  //previous island
                island.mergeIsland(12);
            }
        }

        if(position == 12){
            if(islands.get(position).getOwner() == currentPlayer && islands.get(1).getOwner() == currentPlayer){ //next island
                island.mergeIsland(1);
            }
            if(islands.get(position).getOwner() == currentPlayer && islands.get(position-1).getOwner() == currentPlayer){  //previous island
                island.mergeIsland(position-1);
            }
        }

        else{
            if(islands.get(position).getOwner() == currentPlayer && islands.get(position+1).getOwner() == currentPlayer){ //next island
                island.mergeIsland(position+1);
            }
            if(islands.get(position).getOwner() == currentPlayer && islands.get(position-1).getOwner() == currentPlayer){  //previous island
                island.mergeIsland(position-1);
            }
        }
    }

     */

    //NO IMPLEMENTATION YET
    //public boolean checkActiveCharacterCards() {}

    public void checkToAddProfessor(Color givenColor) {

        int color = givenColor.getIndex();
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
/*
    public void drawFromBag() {

        bag.extractPawnsToCloud(clouds);

    } //called by CloudTile.fillStudents() connects CloudTile with Bag


 */
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

    // WAITING CLOUDTILE CLASS
    /*
    public void chooseCloudTile(CloudTile cloud) {

        int index = cloud.getIdCloud();
        boolean waitToFill = true;

        cloud.emptyCloud(currentPlayer, index);

        for(CloudTile c : clouds) {
            for (int i = 0; i < 5; i++) {

                if (c.getStudents.get(i) == 0) {
                    waitToFill = false;
                }
                else{
                    waitToFill = true;
                }

            }
        } //controls if all clouds are empty

        if(waitToFill == false) {

            for(CloudTile c : clouds) {

                c.fillStudents(bag);

            }
        }

    }

     */
/*
    //WAITING CHARACTERCARD CLASS
    public void buyCharacterCards(CharacterCard card) throws Exception {

        if (currentPlayer.getCoins() >= card.getCost()){
            card.activateCard();
            currentPlayer.removeCoins(card.getCost());
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


        //Missing control of the last player
        for(int i = 0; i < 5; i++){

            if(bag.getStudents().get(i) == 0){
                emptyBag = true;
            }

            else{
                emptyBag = false;
            }
        }

        if(emptyBag == true){
            return true;
        } //if the bag is empty

        //count how many island groups there are (if <= 3 --> gameover)
        //controls if the last AssistantCard has been played

    }

 */

}
