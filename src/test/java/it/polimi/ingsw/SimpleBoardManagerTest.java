package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//No implementation yet

//ANDREA: TO TEST THIS CLASS CREATE EVERYTHING IT NEEDS NOT JUST THE BOARDMANAGER, the tests give some problem with the way SimpleBoardManager is structured

class SimpleBoardManagerTest {
/*
    //Board board = new Board(3, true);
    BoardManager boardManager = new SimpleBoardManager(3,false);

    @Test
    void login() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        //tried using same nickname, works launching Exception as expected
        assertFalse(boardManager.getPlayers().isEmpty());
    }

    @Test
    void takeCoin() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        //works with every colorIndex and color
        for(int colorIndex = 0; colorIndex < 5; colorIndex++) {
            boardManager.getCurrentPlayer().getSchoolBoard().addStudentToDiningRoom(Color.PINK_FAIRIES);
        }

        boardManager.takeCoin();

        assertEquals(2, boardManager.getCurrentPlayer().getCoins());
    }

    @Test
    void getPlayerIndex() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        //tried with every index and passed every test
        assertEquals(2, boardManager.getPlayerIndex("Bozz"));

    }


    @Test
    void chooseStepsMotherNature() throws Exception {

        int motherNaturePosition;

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        boardManager.getTurn().chooseAssistantCard("Albus", AssistantCard.THREE);
        boardManager.getTurn().chooseAssistantCard("Barb", AssistantCard.FIVE);
        boardManager.getTurn().chooseAssistantCard("Bozz", AssistantCard.SEVEN);

        boardManager.chooseStepsMotherNature(2,0);
        motherNaturePosition = boardManager.getMotherNature().getPosition();

        //throws also the IllegalStateException
        assertEquals(3, motherNaturePosition);

    }
*/
    /*
    @Test
    void checkInfluence() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        for(int studentsNumber = 0; studentsNumber < 9; studentsNumber++) {
            boardManager.getCurrentPlayer().getSchoolBoard().addStudentToEntrance(0);
        }

        //how to send parameter Old_Island?
        boardManager.getCurrentPlayer().getSchoolBoard().addStudentToIsland(Color.YELLOW_GNOMES, Old_Island island);

        boardManager.getCurrentPlayer().getSchoolBoard().addProfessor(Color.YELLOW_GNOMES);

        assertEquals(true, boardManager.checkInfluence(Old_Island island, 0));
    }

 */
/*
    @Test
    void checkActiveCharacterCards() {

        CharacterCard card = new CharacterCard();

        assertEquals(3, card.getCharacterList().size());
        //assertFalse(card.getCharacterList().isEmpty());   //Test ok


 */
        /*to fix
        works only with few character

        card.chooseCharacterCard(Character.INNKEEPER);

        boardManager.getPlayer(0).getSchoolBoard().addProfessor(Color.YELLOW_GNOMES);
        for(int studentsNumber = 0; studentsNumber < 5; studentsNumber++) {
            boardManager.getPlayer(0).getSchoolBoard().addStudentToDiningRoom(Color.YELLOW_GNOMES);
        }
        for(int studentsNumber = 0; studentsNumber < 5; studentsNumber++) {
            boardManager.getPlayer(1).getSchoolBoard().addStudentToDiningRoom(Color.YELLOW_GNOMES);
        }

        assertTrue(boardManager.checkActiveCharacterCards());



    }

         */
/*
    @Test
    void checkToAddProfessor() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

         int firstPlayerIndex = boardManager.getPlayerIndex("Albus");
         int secondPlayerIndex = boardManager.getPlayerIndex("Barb");
         int thirdPlayerIndex = boardManager.getPlayerIndex("Bozz");

         for(int studentsNumber = 0; studentsNumber < 5; studentsNumber++) {
             boardManager.getPlayer(firstPlayerIndex).getSchoolBoard().addStudentToDiningRoom(Color.YELLOW_GNOMES);
         }
         for(int studentsNumber = 0; studentsNumber < 3; studentsNumber++){
             boardManager.getPlayer(secondPlayerIndex).getSchoolBoard().addStudentToDiningRoom(Color.YELLOW_GNOMES);
         }
        for(int studentsNumber = 0; studentsNumber < 5; studentsNumber++){
            boardManager.getPlayer(thirdPlayerIndex).getSchoolBoard().addStudentToDiningRoom(Color.YELLOW_GNOMES);
        }

        boardManager.checkToAddProfessor(Color.YELLOW_GNOMES, 0);

        assertTrue(boardManager.getPlayer(firstPlayerIndex).getSchoolBoard().getSingleProfessor(Color.YELLOW_GNOMES));
        //assertFalse(boardManager.getPlayer(secondPlayerIndex).getSchoolBoard().getSingleProfessor(Color.YELLOW_GNOMES)); //second case
        //change students number of the third player to 5
        //assertFalse(boardManager.getPlayer(firstPlayerIndex).getSchoolBoard().getSingleProfessor(Color.YELLOW_GNOMES));  //third case
        //assertFalse(boardManager.getPlayer(secondPlayerIndex).getSchoolBoard().getSingleProfessor(Color.YELLOW_GNOMES));  //third case.2
    }

 */

    //waiting for the implementation of CloudTile constructor which fills the cloud at the start
    //PROBLEM WITH THE CONSTRUCTOR OF Cloud()
    /*
    @Test
    void drawFromBag() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        boardManager.drawFromBagToClouds();

        //need to change the method checkEmptyCloud();
        assertFalse(boardManager.getCloud(0).checkEmptyCloud());

    }

     */
/*
    @Test
    void checkCloudsArray() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        assertFalse(boardManager.getClouds().isEmpty());

    }
*/
    //works, need to do assertException()
    /*
    @Test
    void checkNickname() throws Exception {

        //tried already in login()
        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Albus", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        //it throws an Exception as expected

    }

     */

//STILL 2 errors, need to re-control
    /*
    @Test
    void chooseCloudTile() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        boardManager.chooseCloudTile(boardManager.getCloud(0));

        assertTrue(boardManager.getCloud(0).checkEmptyCloud());
        //don't know what else i can add

    }

     */
/*
    @Test
    void buyCharacterCards() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        boardManager.getCurrentPlayer().addCoins(3);
        boardManager.buyCharacterCards(Character.THIEF);

        assertEquals(1,  boardManager.getCurrentPlayer().getCoins());

    }
    */
/*
    @Test
    void checkGameOver() throws Exception {

        boardManager.login("Albus", Wizard.WIZARD1, TowersColor.WHITE);
        boardManager.login("Barb", Wizard.WIZARD2, TowersColor.BLACK);
        boardManager.login("Bozz", Wizard.WIZARD3, TowersColor.GRAY);

        assertEquals(6, boardManager.getCurrentPlayer().getSchoolBoard().getTowers()); //start of the game, 3 players

        //boardManager.getCurrentPlayer().getSchoolBoard().addTowerToIsland(Island island, boardManager.getCurrentPlayer());
        //assertEquals(5, boardManager.getCurrentPlayer().getSchoolBoard().getTowers()); //start of the game, 3 players

        assertFalse(boardManager.checkGameOver()); //bag still full

    }

*/
}

