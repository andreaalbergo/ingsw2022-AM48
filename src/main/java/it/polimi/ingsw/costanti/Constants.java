package it.polimi.ingsw.costanti;

public class Constants {

    /**
     * creates a new Instance of Constants
     */
    private Constants(){}

    private static String address;
    private static int port;
    public static final int CLOUD_MIN_PLAYERS = 3;
    public static final int CLOUD_MAX_PLAYERS = 4;
    public static final int TOWERS_MIN_PLAYERS = 8;
    public static final int TOWERS_MAX_PLAYERS = 6;
    public static final int HALL_MIN_PLAYERS = 7;
    public static final int HALL_MAX_PLAYERS = 9;
    public static final int NUMBER_OF_ISLANDS = 12;
    public static final int NUMBER_OF_COLOR_TYPES = 5;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 3; // We could up it to 4 for AF
    public static final int MIN_PORT = 0;
    public static final int MAX_PORT = 1024;
    public static final String ANSI_UNDERLINE = "\033[4m";
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED = "\033[91m";
    public static final String ANSI_BLACK= "\033[30m";
    public static final String ANSI_GREEN = "\033[92m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_PINK = "\033[38;5;206m";
    public static final String ANSI_PURPLE = "\033[35m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_WHITE = "\033[37m";
    public static final String ANSI_BACKGROUND_BLACK = "\033[40m";
    public static final String ANSI_BACKGROUND_YELLOW = "\033[43m";
    public static final String ANSI_BACKGROUND_PURPLE = "\033[45m";
    public static final String ANSI_BACKGROUND_GREY = "\033[47m";
    public static final String RECTANGLE = "\033[51m";

    public static final String GAME_RULES = "\n Click the following link in order to read Eriantys' rules: " +
            "https://craniointernational.com/2021/wp-content/uploads/2021/06/Eriantys_rules_small.pdf";

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Constants.address = address;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Constants.port = port;
    }
}
