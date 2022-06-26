package it.polimi.ingsw.costanti;

/**
 * Blueprint class contains all grid assets for our CLI view.
 *
 * @author David Barb
 */
public class Blueprint {
    private static final String ASCII_176 = "░";
    private static final String ASCII_177 = "▒";
    private static final String ASCII_178 = "▓";
    private static final String BLACK_RECTANGLE = "█";
    private static final String UPPER_BOX= "┌───────────────────┐\n";
    private static final String LOWER_BOX = "└───────────────────┘\n";
    private static final String BLUE_UNICORN = "";
    private static final String RED_DRAGON = "";
    private static final String PINK_FAIRY = "";
    private static final String YELLOW_GNOME = "";
    private static final String GREEN_FROG = "";

    private static final String CLI_CLOUD =
             UPPER_BOX +
            "|       _____        |\n" +
            "|    _(       )__    |\n" +
            "|  _(            )   |\n" +
            "| (_______________)  |\n" +
            LOWER_BOX;

    public static final String CLI_BOARD = CLI_CLOUD;


    public static void main(String[] args) {
        System.out.println(Blueprint.CLI_BOARD);
    }
}
