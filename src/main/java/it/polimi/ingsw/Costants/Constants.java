package it.polimi.ingsw.Costants;

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
