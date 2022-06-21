package it.polimi.ingsw.costanti;

public class Constants {

    /**
     * creates a new Instance of Constants
     */
    private Constants(){}

    private static String address;
    private static int port;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 3; // We could up it to 4 for AF
    public static final int MIN_PORT = 0;
    public static final int MAX_PORT = 1024;
    public static final String ANSI_UNDERLINE = "\033[4m";
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_PURPLE = "\033[35m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_WHITE = "\033[37m";
    public static final String ANSI_BACKGROUND_BLACK = "\033[40m";
    public static final String ANSI_BACKGROUND_PURPLE = "\033[45m";

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
