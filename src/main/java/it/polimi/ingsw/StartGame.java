package it.polimi.ingsw;
//REMEMBER TO PUT IT INTO A DIFFERENT PACKAGE WITH BOARD MANAGER FOR SECURITY AND PRIVACY CONCERNS
//ONLY ONE ISTANCE SO I THINK IT'S GOOD TO MAKE A "SINGLETON"; OH YEAH! IT FEELS GOOD TO BE A COOL DESIGNER

public class StartGame {
    private static StartGame singleton = null;
    private static int n_players = 0;
    private static final int [] students = new int[5]; //we have 5 colors, so every cell it has 26 students at first
    private static boolean expert_mode = false;

    private StartGame() {} //private constructor

    public static StartGame getInstance() {
        if (singleton == null) singleton = new StartGame();
        return singleton;
    }

    public static void setPlayers(int n_players) {
        StartGame.n_players = n_players;
    }

    public static void setMode(boolean expert_mode) { //our chooseMode()
        StartGame.expert_mode = expert_mode;
    }

    private void drawCharCards() { //FACTORY TYPE
        //TODO
    }
}

/* CHECK IF THIS SINGLETON WITH PARAMETERS IS ACTUALLY CORRECT */