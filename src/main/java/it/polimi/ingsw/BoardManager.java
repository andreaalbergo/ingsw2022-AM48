package it.polimi.ingsw;

import java.util.ArrayList;

public class BoardManager {

    private int remainingCoinCounter = 20;
    private Bag bag;
    //private ArrayList<Cloud> clouds;
    //private ArrayList<Island> islands;
    //private ArrayList<Island> unifiedIsland;
    private ArrayList<Player> players;
    private ArrayList<Boolean> professors;
    //private ArrayList<CharacterDecorator> characters;
    private Round turn;
    private Player currentPlayer;
    private MotherNature motherNature;
    private int numberOfPlayers;
    private boolean expertMode;
/*
    public void BoardManager(int numberOfPlayers, CharacterCards[] characters, ArrayList<Player> players, boolean expertMode, ArrayList<Island> island, ArrayList<Island> unifiedIsland) {

        this.numberOfPlayers = numberOfPlayers;
        //initialize CharacterCards[] characters
        players = new ArrayList<Player>(numberOfPlayers); //recheck
        this.expertMode = expertMode;
        //initialize Island island
        //initialize Island unifiedIsland

    }

 */

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

    /*public void takeCoin() throws Exception {

        int counter = 0;

        for (int i : getSchoolBoard().getDiningRoom()) {    //for every index of the array control if there's a student on a place with a coin
            if (currentPlayer.getDiningRoom().get(i) % 3 == 0)
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

        currentPlayer.addCoin(counter);

    }
     */
/*
    public void setupSchool(int numberOfPlayers) {

        for(Player p : players){
           p  = new SchoolBoard(p.getNickname(), expertMode);   //Call SchoolBoard constructor
        }

    }
*/
    public void setupRound() {



    }

    public void chooseStepsMotherNature(int steps) {

        int index = getPlayerIndex(currentPlayer);

        if(steps >= 1 && steps <= turn.getChosenCards().get(index).getNumber_of_steps()){
            motherNature.setPosition(steps);
        }
        //else
            //throw new IllegalStateException(System.out.println("You have to move Mother Nature between 1 and " + Round.chosenCards.get(index).number_of_steps));
    }
/*
    public boolean checkInfluence(IslandTile island) {

         ArrayList<Integer> Influencecounter = new ArrayList<Integer>();

        for(Player p: players){

            int counter = 0;

            for (int j = 0; j < 5; j++) {        //for(int j:Player.SchoolBoard.professors[j])

                if (p.getSchoolBoard().getprofessors().get(j) == true) {     //if player have the professor

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

        if(getCurrentPlayerIndex() == indexmax){
            return true;
        }

        else{
            return false;
        }
    }
*//*
    private void checkToMergeIslands(IslandTile island) {

        int position = motherNature.getPosition();

        if(position == 1){
            if(islands.get(position).getowner() == currentPlayer && islands.get(position+1).getowner() == currentPlayer){ //next island
                island.mergeIsland(position+1);
            }
            if(islands.get(position).getowner() == currentPlayer && islands.get(12).getowner() == currentPlayer){  //previous island
                island.mergeIsland(12);
            }
        }

        if(position == 12){
            if(islands.get(position).getowner() == currentPlayer && islands.get(1).getowner() == currentPlayer){ //next island
                island.mergeIsland(1);
            }
            if(islands.get(position).getowner() == currentPlayer && islands.get(position-1).getowner() == currentPlayer){  //previous island
                island.mergeIsland(position-1);
            }
        }

        else{
            if(islands.get(position).getowner() == currentPlayer && islands.get(position+1).getowner() == currentPlayer){ //next island
                island.mergeIsland(position+1);
            }
            if(islands.get(position).getowner() == currentPlayer && islands.get(position-1).getowner() == currentPlayer){  //previous island
                island.mergeIsland(position-1);
            }
        }
    }
*/
    //public boolean checkActiveCharacterCards() {}

/*
    public void checkToAddProfessor(Color givenColor) {

        int color = givenColor.getIndex();
        Player a = players.get(0);
        int maxindex = 0;
        int i, j = 0;

        for(i = 0; i+1 < numberOfPlayers; i++) {
            for (j = i + 1; j < numberOfPlayers; j++) {

                if(players.get(i).getSchoolBoard().getDiningRoom().get(color) > players.get(j).getSchoolBoard().getDiningRoom().get(color) && players.get(i).getSchoolBoard().getDiningRoom().get(color) > a.getScoolBoard().getDiningRoom().get(color)){
                    a = players.get(i);
                    maxindex = i;
                }

                if(players.get(i).getSchoolBoard().getDiningRoom().get(color) < players.get(j).getSchoolBoard().getDiningRoom().get(color) && players.get(j).getSchoolBoard().getDiningRoom().get(color) > a.getScoolBoard().getDiningRoom().get(color)){
                    a = players.get(j);
                    maxindex = j;
                }

                if(i != j && players.get(i).getSchoolBoard().getDiningRoom().get(color) == players.get(j).getSchoolBoard().getDiningRoom().get(color)){
                    maxindex = -1;
                }

            }

        }

        if(maxindex == -1){

            players.get(i).getSchoolBoard().removeProfessor(color);
            players.get(j).getSchoolBoard().removeProfessor(color);

        }

        else{
            a.getSchoolBoard().addProfessor(color);
        }

    }
*/
    public void drawFromBag() {}

    //public boolean checkNickname() {}
/*
    public void chooseCloudTile(CloudTile cloud) {}
*//*
    public void buyCharacterCards(CharacterCard card) {}
*/
    //private boolean checkGameOver() {}

}
