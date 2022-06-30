package it.polimi.ingsw.costanti;

import it.polimi.ingsw.client.gameBoard.GameBoard;
import it.polimi.ingsw.client.gameBoard.SchoolGrid;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.SchoolBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Blueprint class contains all grid assets for our CLI view.
 *
 * @author David Barb
 */
public class Blueprint {
    private final HashMap<String, ArrayList<Integer>> assistantCardUsed = new LinkedHashMap<>();
    private final HashMap<String, Integer> characterCards = new LinkedHashMap<>();

    private  final String ASCII_157 = "Ø";
    private  final String ASCII_179 = "│";
    private  final String ASCII_185 = "║";
    private  final String ASCII_187 = "╗";
    private  final String ASCII_188 = "╝";
    private  final String ASCII_191 = "┐";
    private  final String ASCII_192 = "└";
    private  final String ASCII_200 = "╚";
    private final String ASCII_201 = "╔";
    private final String ASCII_217 = "┘";
    private final String ASCII_218 = "┌";

    /**
     * Method getUpperBoxCloud gets the upper part of the cloud body.
     *
     * @param cloudId of type int - the cloud ID.
     * @return of type String.
     */
    private String getUpperBoxCloud(Integer cloudId) {
        return "┌──────" + Constants.RECTANGLE + " CLOUD_" + cloudId.toString() + " " + Constants.ANSI_RESET + "──────┐";
    }

    private final String LOWER_BOX_CLOUD = "└─────────────────────┘";
    private final String BOX_LINE_ONE = "═══════════════";
    private final String BOX_LINE_TWO = "══════════════════════════════════════════════════";
    private final String EMPTY_LINE_ONE = "               ";
    private final String EMPTY_LINE_TWO = "                                                  ";
    private final String BLUE_PROFESSOR = Constants.RECTANGLE + Constants.ANSI_CYAN + "◯" + Constants.ANSI_RESET;
    private final String RED_PROFESSOR = Constants.RECTANGLE + Constants.ANSI_RED + "◯" + Constants.ANSI_RESET;
    private final String PINK_PROFESSOR = Constants.RECTANGLE + Constants.ANSI_PINK + "◯" + Constants.ANSI_RESET;
    private final String YELLOW_PROFESSOR = Constants.RECTANGLE + Constants.ANSI_YELLOW + "◯" + Constants.ANSI_RESET;
    private final String GREEN_PROFESSOR = Constants.RECTANGLE + Constants.ANSI_GREEN + "◯" + Constants.ANSI_RESET;
    private final String EMPTY_PROFESSOR = "◯";
    private final String[] PROFESSORS = new String[]{YELLOW_PROFESSOR, BLUE_PROFESSOR, GREEN_PROFESSOR,
            RED_PROFESSOR, PINK_PROFESSOR, EMPTY_PROFESSOR};

    private final String BLUE_UNICORN = Constants.RECTANGLE + Constants.ANSI_CYAN + "◍" + Constants.ANSI_RESET;
    private final String RED_DRAGON = Constants.RECTANGLE + Constants.ANSI_RED + "◍" + Constants.ANSI_RESET;
    private final String PINK_FAIRY = Constants.RECTANGLE + Constants.ANSI_PINK + "◍" + Constants.ANSI_RESET;
    private final String YELLOW_GNOME = Constants.RECTANGLE + Constants.ANSI_YELLOW + "◍" + Constants.ANSI_RESET;
    private final String GREEN_FROG = Constants.RECTANGLE + Constants.ANSI_GREEN + "◍" + Constants.ANSI_RESET;
    private final String EMPTY_STUDENT_SLOT = "◍";
    private final String NO_ENTRY_TILE = Constants.ANSI_RED + ASCII_157 + Constants.ANSI_RESET;
    private final String[] STUDENTS = new String[]{YELLOW_GNOME, BLUE_UNICORN, GREEN_FROG, RED_DRAGON,
            PINK_FAIRY, EMPTY_STUDENT_SLOT};

    private final String MENU_TAB_MIN_PLAYERS = "══════════════════════════════" + "\n";

    private final String MENU_TAB_MAX_PLAYERS = "══════════════════════════════" + "\n";

    public String getCloudsMinPlayers(int[] studentsRandom) throws IllegalArgumentException {
        if (studentsRandom.length != 6) throw new IllegalArgumentException();
        return getUpperBoxCloud(1) + "\t\t" + getUpperBoxCloud(2) +
                "                                                                     " +
                "═════════════════════" + Constants.RECTANGLE + " LEGEND " + Constants.ANSI_RESET + "═══════════════════" + "\n" +
                "|       _______       |\t\t|       _______       |" + "                                                                    " +
                ASCII_185 + " -Empty student cell: " + EMPTY_STUDENT_SLOT + "                        " + ASCII_185 + "\n" +
                "     _(   " + STUDENTS[studentsRandom[0]] + "    )___  \t\t     _(   " + STUDENTS[studentsRandom[3]] + "    )___  " +
                "                                                                        " + "-Students: UNICORN->" + BLUE_UNICORN +
                "\\" + Constants.ANSI_BLUE + "(B)" + Constants.ANSI_RESET + "  GNOME->" + YELLOW_GNOME + "\\" + Constants.ANSI_YELLOW +
                "(Y)" + Constants.ANSI_RESET + "\n" +
                "   _(  " + STUDENTS[studentsRandom[1]] + "     " + STUDENTS[studentsRandom[2]] + "     ) \t\t   _(  " +
                STUDENTS[studentsRandom[4]] + "     " + STUDENTS[studentsRandom[5]] + "     ) " +
                "                                                                        " + "   FAIRY->" + PINK_FAIRY + "\\" +
                Constants.ANSI_PINK + "(P)" + Constants.ANSI_RESET + "  FROG->" + GREEN_FROG + "\\" + Constants.ANSI_GREEN +
                "(G)" + Constants.ANSI_RESET + "  DRAGON->" + RED_DRAGON + "\\" + Constants.ANSI_RED + "(R)" + Constants.ANSI_RESET + "\n" +
                "| (_________________) |\t\t| (_________________) |" + "                                                                    " +
                ASCII_185 + " -Empty professor cell: " + EMPTY_PROFESSOR + "                       " + ASCII_185 + "\n" +
                LOWER_BOX_CLOUD + "\t\t" + LOWER_BOX_CLOUD + "                                                                      " +
                "-Towers: " + " WHITE->" + WHITE_TOWER + " BLACK->" + BLACK_TOWER + " (for 3p)GREY->" + GREY_TOWER + "\n";
    }

    public String getCloudsMaxPlayers(int[] studentsRandom) throws IllegalArgumentException {
        if (studentsRandom.length != 12) throw new IllegalArgumentException();
        return getUpperBoxCloud(1) + "\t\t" + getUpperBoxCloud(2) + "\t\t" + getUpperBoxCloud(3) +
                "                                         " + "═════════════════════" + Constants.RECTANGLE + " LEGEND " + Constants.ANSI_RESET + "═══════════════════" + " \n" +
                "|       _______       |\t\t|       _______       |\t\t|       _______       |" + "                                        " +
                ASCII_185 + " -Empty student cell: " + EMPTY_STUDENT_SLOT + "                        " + ASCII_185 + "\n" +
                "     _(  " +STUDENTS[studentsRandom[0]]+ "     )___  \t\t     _(  " +STUDENTS[studentsRandom[4]]+
                "     )___  \t\t     _(  " + STUDENTS[studentsRandom[8]] + "     )___  " + "                                            " +
                "-Students: UNICORN->" + BLUE_UNICORN + "\\" + Constants.ANSI_BLUE + "(B)" + Constants.ANSI_RESET + "  GNOME->" + YELLOW_GNOME +
                "\\" + Constants.ANSI_YELLOW + "(Y)" + Constants.ANSI_RESET + "\n" +
                "   _( " + STUDENTS[studentsRandom[1]] + "     " + STUDENTS[studentsRandom[2]] + "   "+STUDENTS[studentsRandom[3]]+"  ) \t\t   _( " +
                STUDENTS[studentsRandom[5]] + "     " + STUDENTS[studentsRandom[6]] + "   "+STUDENTS[studentsRandom[7]]+"  ) \t\t   _( " +
                STUDENTS[studentsRandom[9]] + "     " + STUDENTS[studentsRandom[10]] + "   "+STUDENTS[studentsRandom[11]]+"  ) " + "                                            " +
                "   FAIRY->" + PINK_FAIRY + "\\" + Constants.ANSI_PINK + "(P)" + Constants.ANSI_RESET + "  FROG->" + GREEN_FROG + "\\" + Constants.ANSI_GREEN +
                "(G)" + Constants.ANSI_RESET + "  DRAGON->" + RED_DRAGON + "\\" + Constants.ANSI_RED + "(R)" + Constants.ANSI_RESET + "\n" +
                "| (_________________) |\t\t| (_________________) |\t\t| (_________________) |" + "                                        " +
                ASCII_185 + " -Empty professor cell: " + EMPTY_PROFESSOR + "                       " + ASCII_185 + "\n" +
                LOWER_BOX_CLOUD + "\t\t" + LOWER_BOX_CLOUD + "\t\t" + LOWER_BOX_CLOUD + "                                          " +
                "-Towers: " + " WHITE->" + WHITE_TOWER + " BLACK->" + BLACK_TOWER + " (for 3p)GREY->" + GREY_TOWER + "\n";
    }

    private final String BLACK_TOWER = Constants.ANSI_BACKGROUND_BLACK + "╦╦" + Constants.ANSI_RESET;
    private final String GREY_TOWER = Constants.ANSI_BACKGROUND_GREY + Constants.ANSI_BLACK + "╬╬" +
            Constants.ANSI_RESET;
    private final String WHITE_TOWER = Constants.RECTANGLE + "╦╦" + Constants.ANSI_RESET;
    private final String EMPTY_TOWER_ISLAND = "  ";
    private final String EMPTY_TOWER_SCHOOL = "ØØ";
    private final String[] TOWERS = new String[]{WHITE_TOWER, BLACK_TOWER, GREY_TOWER, EMPTY_TOWER_ISLAND,
            EMPTY_TOWER_SCHOOL};

    private final String UPPER_MOTHER_NATURE = Constants.ANSI_BLACK + Constants.ANSI_BACKGROUND_YELLOW + "╔═╗" +
            Constants.ANSI_RESET;
    private final String MIDDLE_MOTHER_NATURE = Constants.ANSI_BLACK + Constants.ANSI_BACKGROUND_YELLOW + "║M║" +
            Constants.ANSI_RESET;
    private final String LOWER_MOTHER_NATURE = Constants.ANSI_BLACK + Constants.ANSI_BACKGROUND_YELLOW + "╚═╝" +
            Constants.ANSI_RESET;
    private final String EMPTY_MOTHER_NATURE = "   ";

    private String getUpperMotherNature(int motherConfig) {
        if (motherConfig==1)
            return UPPER_MOTHER_NATURE;
        return EMPTY_MOTHER_NATURE;
    }

    private String getMiddleMotherNature(int motherConfig) {
        if (motherConfig==1)
            return MIDDLE_MOTHER_NATURE;
        return EMPTY_MOTHER_NATURE;
    }

    private String getLowerMotherNature(int motherConfig) {
        if (motherConfig==1)
            return LOWER_MOTHER_NATURE;
        return EMPTY_MOTHER_NATURE;
    }

    private String getTower(int towerConfig) {
        return switch (towerConfig) {
            case 1 -> WHITE_TOWER;
            case 2 -> BLACK_TOWER;
            case 3 -> GREY_TOWER;
            default -> EMPTY_TOWER_ISLAND;
        };
    }

    private String getColorIsland(int colorIndex, int amount) {
        return switch (colorIndex) {
            case 0 -> Constants.ANSI_GREEN + "G" + Constants.ANSI_RESET + "x" + amount + " ";
            case 1 -> Constants.ANSI_RED + "R" + Constants.ANSI_RESET + "x" + amount + " ";
            case 2 -> Constants.ANSI_YELLOW + "Y" + Constants.ANSI_RESET + "x" + amount + " ";
            case 3 -> Constants.ANSI_PINK + "P" + Constants.ANSI_RESET + "x" + amount + " ";
            case 4 -> Constants.ANSI_BLUE + "B" + Constants.ANSI_RESET + "x" + amount + " ";
            case 5 -> Constants.ANSI_GREEN + "G" + Constants.ANSI_RESET + "x" + amount;
            case 6 -> Constants.ANSI_RED + "R" + Constants.ANSI_RESET + "x" + amount;
            case 7 -> Constants.ANSI_YELLOW + "Y" + Constants.ANSI_RESET + "x" + amount;
            case 8 -> Constants.ANSI_PINK + "P" + Constants.ANSI_RESET + "x" + amount;
            case 9 -> Constants.ANSI_BLUE + "B" + Constants.ANSI_RESET + "x" + amount;
            default -> "    ";
        };
    }

    private String getIslandId(Integer islandId) {
        if (islandId<10)
            return "ISLE_"+ islandId +" ";
        return "ISLE_"+ islandId;
    }

    /**
     * Method getArchipelago prints a long string for the CLI view with all the islands containing students, towers,
     * mother nature (and for expert mode no entry tile).
     *
     * @return String - all the 12 islands of the game board.
     */
    private String getArchipelago(HashMap<Integer, int[]> archipelagoConfiguration) {
        return ASCII_201 + BOX_LINE_TWO + BOX_LINE_TWO + BOX_LINE_ONE + ASCII_187 + "    "+
                "-Coin redeem on school board->@" + " (EXPERT MODE)" + "\n"+
                //ISLAND 1,2,3,4,5
                "║    ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────    ║"+
                "  ║" + " -Mother Nature: " + UPPER_MOTHER_NATURE + "+" + MIDDLE_MOTHER_NATURE + "+" + LOWER_MOTHER_NATURE + "                    ║" + "\n"+
                "║  /" + getUpperMotherNature(archipelagoConfiguration.get(0)[1]) + "      \\\t\t" + "   /"+
                getUpperMotherNature(archipelagoConfiguration.get(1)[1]) + "      \\\t\t" + "   /" + getUpperMotherNature(archipelagoConfiguration.get(2)[1])+
                "      \\\t\t" + "   /" + getUpperMotherNature(archipelagoConfiguration.get(3)[1]) + "      \\\t\t" + "   /"+
                getUpperMotherNature(archipelagoConfiguration.get(4)[1]) + "      \\\t\t" + "   /" + getUpperMotherNature(archipelagoConfiguration.get(5)[1])+
                "      \\  ║" + "    -MOVELIST:" + "\n" +
                "║ / " + getMiddleMotherNature(archipelagoConfiguration.get(0)[1]) + "  " + getTower(archipelagoConfiguration.get(0)[2])+
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(1)[1]) + "  " + getTower(archipelagoConfiguration.get(1)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(2)[1]) + "  " + getTower(archipelagoConfiguration.get(2)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(3)[1]) + "  " + getTower(archipelagoConfiguration.get(3)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(4)[1]) + "  " + getTower(archipelagoConfiguration.get(4)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(5)[1]) + "  " + getTower(archipelagoConfiguration.get(5)[2]) +
                "   \\ ║" + "    1.MOVEMOTHERNATURE <integer num_steps>" + "\n" +
                "║|  " + getLowerMotherNature(archipelagoConfiguration.get(0)[1]) + "  "+getColorIsland(0, archipelagoConfiguration.get(0)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(1)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(1)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(2)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(2)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(3)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(3)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(4)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(4)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(5)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(5)[4])+
                "  |║" + "  ║ 2.MOVESTUDENTTO <ISLAND/DINING ROOM>" + "           " +ASCII_185+ "\n" +
                "║ \\"+getColorIsland(1, archipelagoConfiguration.get(0)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(0)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(1)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(1)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(2)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(2)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(3)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(3)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(4)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(4)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(5)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(5)[6])+
                "  / ║"+"           <CHOOSE STUDENT FROM SCHOOL ENTRANCE>"+"\n"+
                "║  \\"+getColorIsland(3, archipelagoConfiguration.get(0)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(0)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(1)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(1)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(2)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(2)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(3)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(3)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(4)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(4)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(5)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(5)[8])+"/  ║"+
                "    3.PICKCLOUD <integer cloud ID number>"+"\n"+
                "║    ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" +
                "     ───────    ║"+"\n"+
                "║    "+getIslandId(archipelagoConfiguration.get(0)[0])+"             "+getIslandId(archipelagoConfiguration.get(1)[0])+
                "             "+getIslandId(archipelagoConfiguration.get(2)[0])+"             "+getIslandId(archipelagoConfiguration.get(3)[0])+
                "             "+getIslandId(archipelagoConfiguration.get(4)[0])+"             "+getIslandId(archipelagoConfiguration.get(5)[0])+
                "    " + "║" + "\n" +
                "║" + EMPTY_LINE_TWO + EMPTY_LINE_TWO + EMPTY_LINE_ONE + "║" +"\n"+

                //ISLAND 7,8,9,10,11,12
                "║    ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────    ║\n"+
                "║  /" + getUpperMotherNature(archipelagoConfiguration.get(11)[1]) + "      \\\t\t" + "   /" +
                getUpperMotherNature(archipelagoConfiguration.get(10)[1]) + "      \\\t\t" + "   /" + getUpperMotherNature(archipelagoConfiguration.get(9)[1])+
                "      \\\t\t" + "   /" + getUpperMotherNature(archipelagoConfiguration.get(8)[1]) + "      \\\t\t" + "   /"+
                getUpperMotherNature(archipelagoConfiguration.get(7)[1]) + "      \\\t\t" + "   /" + getUpperMotherNature(archipelagoConfiguration.get(6)[1])+
                "      \\  ║"+"\n"
                +"║ / " + getMiddleMotherNature(archipelagoConfiguration.get(11)[1]) + "  " + getTower(archipelagoConfiguration.get(11)[2])+
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(10)[1]) + "  " + getTower(archipelagoConfiguration.get(10)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(9)[1]) + "  " + getTower(archipelagoConfiguration.get(9)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(8)[1]) + "  " + getTower(archipelagoConfiguration.get(8)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(7)[1]) + "  " + getTower(archipelagoConfiguration.get(7)[2]) +
                "   \\\t\t" + "  / " + getMiddleMotherNature(archipelagoConfiguration.get(6)[1]) + "  " + getTower(archipelagoConfiguration.get(6)[2]) +
                "   \\ ║"+"\n"+
                "║|  " + getLowerMotherNature(archipelagoConfiguration.get(11)[1]) + "  "+getColorIsland(0, archipelagoConfiguration.get(11)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(10)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(10)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(9)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(9)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(8)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(8)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(7)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(7)[4])+
                "  |\t" + " |  " + getLowerMotherNature(archipelagoConfiguration.get(6)[1])+"  "+getColorIsland(0, archipelagoConfiguration.get(6)[4])+"  |║\n"+
                "║ \\"+getColorIsland(1, archipelagoConfiguration.get(11)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(11)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(10)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(10)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(9)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(9)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(8)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(8)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(7)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(7)[6])+
                "  /\t\t" + "  \\"+getColorIsland(1, archipelagoConfiguration.get(6)[5])+" "+getColorIsland(2, archipelagoConfiguration.get(6)[6])+
                "  / ║\n" +
                "║  \\"+getColorIsland(3, archipelagoConfiguration.get(11)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(11)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(10)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(10)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(9)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(9)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(8)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(8)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(7)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(7)[8])+"/\t\t"+
                "   \\"+getColorIsland(3, archipelagoConfiguration.get(6)[7])+" "+getColorIsland(4, archipelagoConfiguration.get(6)[8])+"/  ║\n"+
                "║    ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" + "     ───────  \t\t" +
                "     ───────    ║\n" +
                "║    "+getIslandId(archipelagoConfiguration.get(11)[0])+"             "+getIslandId(archipelagoConfiguration.get(10)[0])+
                "             "+getIslandId(archipelagoConfiguration.get(9)[0])+"             "+getIslandId(archipelagoConfiguration.get(8)[0])+
                "             "+getIslandId(archipelagoConfiguration.get(7)[0])+"             "+getIslandId(archipelagoConfiguration.get(6)[0])+
                "    " + "║" + "\n" +
                "║" + EMPTY_LINE_TWO + EMPTY_LINE_TWO + EMPTY_LINE_ONE + "║" +"\n"+
                ASCII_200 + BOX_LINE_TWO + BOX_LINE_TWO + BOX_LINE_ONE + ASCII_188 + "\n";
    }

    private String getColorFromHall(Color color) {
        String printable = "";
        switch (color) {
            case GREEN_FROGS -> printable += GREEN_FROG;
            case RED_DRAGONS -> printable += RED_DRAGON;
            case YELLOW_GNOMES -> printable += YELLOW_GNOME;
            case PINK_FAIRIES -> printable += PINK_FAIRY;
            case BLUE_UNICORNS -> printable += BLUE_UNICORN;
            default -> printable += EMPTY_STUDENT_SLOT;
        }

        return printable;
    }

    private String getStudentHall(int row, List<Color> entrance, int numberOfPlayers) {
        int amount = entrance.size();
        String emptyCells = Constants.ANSI_BACKGROUND_GREY + Constants.ANSI_WHITE + EMPTY_STUDENT_SLOT +
                Constants.ANSI_RESET + " " + Constants.ANSI_BACKGROUND_GREY + Constants.ANSI_WHITE +
                EMPTY_STUDENT_SLOT + Constants.ANSI_RESET;

        switch (row) {
            case 0 -> {
                if (amount == 0)
                    return Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET;
                else
                    return getColorFromHall(entrance.get(0));
            }
            case 1 -> {
                if (amount <= 1)
                    return emptyCells;
                else if (amount == 2)
                    return getColorFromHall(entrance.get(1)) + " "+Constants.ANSI_BACKGROUND_GREY+EMPTY_STUDENT_SLOT+
                            " "+Constants.ANSI_RESET;
                else
                    return getColorFromHall(entrance.get(1)) + " " + getColorFromHall(entrance.get(2));
            }
            case 2 -> {
                if (amount <= 3)
                    return emptyCells;
                else if (amount == 4)
                    return getColorFromHall(entrance.get(3)) + " "+Constants.ANSI_BACKGROUND_GREY+EMPTY_STUDENT_SLOT+
                        " "+Constants.ANSI_RESET;
                else
                    return getColorFromHall(entrance.get(3)) + " " + getColorFromHall(entrance.get(4));
            }
            case 3 -> {
                if (amount <= 5)
                    return emptyCells;
                else if (amount == 6)
                    return getColorFromHall(entrance.get(5))+" "+Constants.ANSI_BACKGROUND_GREY+
                            Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET;
                else
                    return getColorFromHall(entrance.get(5))+" "+getColorFromHall(entrance.get(6));
            }
            case 4 -> {
                if (numberOfPlayers == 3) {
                    if (amount <= 7)
                        return emptyCells;
                    else if (amount == 8)
                        return getColorFromHall(entrance.get(7))+" "+Constants.ANSI_BACKGROUND_GREY+
                                Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET;
                    else
                        return getColorFromHall(entrance.get(7))+" "+getColorFromHall(entrance.get(8));
                }
            }
        }

        return emptyCells;
    }

    private String getStudentDining(int colorID, int amount) {
        StringBuilder printable= new StringBuilder();

        switch (colorID) {
            case 0 -> {
                for (int i = 0; i < amount; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append(Constants.ANSI_GREEN + "@" + Constants.ANSI_RESET + "  ");
                    else
                        printable.append(GREEN_FROG + "  ");
                }

                for (int i = amount; i < 10; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append("@  ");
                    else
                        printable.append(EMPTY_STUDENT_SLOT + "  ");
                }
            }
            case 1 -> {
                for (int i = 0; i < amount; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append(Constants.ANSI_RED + "@" + Constants.ANSI_RESET + "  ");
                    else
                        printable.append(RED_DRAGON + "  ");
                }

                for (int i = amount; i < 10; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append("@  ");
                    else
                        printable.append(EMPTY_STUDENT_SLOT + "  ");
                }
            }
            case 2 -> {
                for (int i = 0; i < amount; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append(Constants.ANSI_YELLOW + "@" + Constants.ANSI_RESET + "  ");
                    else
                        printable.append(YELLOW_GNOME + "  ");
                }

                for (int i = amount; i < 10; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append("@  ");
                    else
                        printable.append(EMPTY_STUDENT_SLOT + "  ");
                }
            }
            case 3 -> {
                for (int i = 0; i < amount; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append(Constants.ANSI_PINK + "@" + Constants.ANSI_RESET + "  ");
                    else
                        printable.append(PINK_FAIRY + "  ");
                }

                for (int i = amount; i < 10; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append("@  ");
                    else
                        printable.append(EMPTY_STUDENT_SLOT + "  ");
                }
            }
            case 4 -> {
                for (int i = 0; i < amount; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append(Constants.ANSI_BLUE + "@" + Constants.ANSI_RESET + "  ");
                    else
                        printable.append(BLUE_UNICORN + "  ");
                }

                for (int i = amount; i < 10; i++) {
                    if (i==2 || i==5 || i==8)
                        printable.append("@  ");
                    else
                        printable.append(EMPTY_STUDENT_SLOT + "  ");
                }
            }
            default -> printable.append(EMPTY_STUDENT_SLOT + "  " + EMPTY_STUDENT_SLOT + "  @  " +
                    EMPTY_STUDENT_SLOT + "  " + EMPTY_STUDENT_SLOT + "  @  " + EMPTY_STUDENT_SLOT + "  " +
                    EMPTY_STUDENT_SLOT + "  @  " + EMPTY_STUDENT_SLOT + "  ");
        }
        return printable.toString();
    }

    private String getProfessor(int colorID, boolean isOwned) {
        if(isOwned)
            switch (colorID){
                case 0: return GREEN_PROFESSOR;
                case 1: return RED_PROFESSOR;
                case 2: return YELLOW_PROFESSOR;
                case 3: return PINK_PROFESSOR;
                case 4: return BLUE_PROFESSOR;
            }

        return EMPTY_PROFESSOR;
    }

    private String getUsedTowers(int row, int remaining, int towerID, int numberOfPlayers){
        String printable = "";
        switch (row) {
            case 0: {
                if(remaining==1) {
                    printable += TOWERS[towerID]+"  "+TOWERS[4]+"  ";
                } else if (remaining==0) {
                    printable += TOWERS[4]+"  "+TOWERS[4]+"  ";
                } else {
                    printable += TOWERS[towerID]+"  "+TOWERS[towerID]+"  ";
                }
                break;
            }
            case 1: {
                if(remaining==3) {
                    printable += TOWERS[towerID]+"  "+TOWERS[4]+"  ";
                } else if (remaining==2) {
                    printable += TOWERS[4]+"  "+TOWERS[4]+"  ";
                } else {
                    printable += TOWERS[towerID]+"  "+TOWERS[towerID]+"  ";
                }
                break;
            }
            case 2: {
                if(remaining==5) {
                    printable += TOWERS[towerID]+"  "+TOWERS[4]+"  ";
                } else if (remaining==4) {
                    printable += TOWERS[4]+"  "+TOWERS[4]+"  ";
                } else {
                    printable += TOWERS[towerID]+"  "+TOWERS[towerID]+"  ";
                }
                break;
            }
            case 3: {
                if (numberOfPlayers==2) {
                    if (remaining == 7) {
                        printable += TOWERS[towerID] + "  " + TOWERS[4] + "  ";
                    } else if (remaining == 6) {
                        printable += TOWERS[4] + "  " + TOWERS[4] + "  ";
                    } else {
                        printable += TOWERS[towerID] + "  " + TOWERS[towerID] + "  ";
                    }
                    break;
                }
            }
            default: printable += TOWERS[4]+"  "+TOWERS[4]+"  ";
        }

        return printable;
    }
    public String getSchoolBoardMinPlayers(HashMap<Integer, SchoolGrid> schools) {
        return " ───────────────────────────────────────────────────────────"+"        "+
                " ───────────────────────────────────────────────────────────"+"\n"+
                ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+
                " "+ getStudentHall(0,schools.get(0).getEntrance(), 2)+" "+ASCII_179+Constants.ANSI_GREEN+
                " (G)"+Constants.ANSI_RESET+" "+getStudentDining(0,schools.get(0).getDiningRoom()[0])+ASCII_179+" "+
                getProfessor(0, schools.get(0).checkProfessor(Color.GREEN_FROGS))+" "+ASCII_179+"  "+
                getUsedTowers(0, schools.get(0).getTowers(), 0, 2)+
                ASCII_179+"       "+ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+
                Constants.ANSI_RESET+" "+ getStudentHall(0, schools.get(1).getEntrance(),2)+" "+ASCII_179+
                Constants.ANSI_GREEN+ " (G)"+Constants.ANSI_RESET+" "+
                getStudentDining(0, schools.get(1).getDiningRoom()[0])+ASCII_179+" "+
                getProfessor(0, schools.get(1).checkProfessor(Color.GREEN_FROGS))+" "+ASCII_179+"  "+
                getUsedTowers(0, schools.get(1).getTowers(), 1, 2)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(1, schools.get(0).getEntrance(), 2)+" "+ASCII_179+Constants.ANSI_RED+
                " (R)"+Constants.ANSI_RESET+" "+getStudentDining(1, schools.get(0).getDiningRoom()[1])+ASCII_179+" "+
                getProfessor(1, schools.get(0).checkProfessor(Color.RED_DRAGONS))+" "+ASCII_179+"  "+
                getUsedTowers(1, schools.get(0).getTowers(), 0,2)+ASCII_179+"       "+ASCII_179+
                " "+ getStudentHall(1, schools.get(1).getEntrance(),2)+" "+ASCII_179+
                Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+getStudentDining(1,schools.get(1).getDiningRoom()[1])+ASCII_179+
                " "+ getProfessor(1, schools.get(1).checkProfessor(Color.RED_DRAGONS))+" "+ASCII_179+"  "+
                getUsedTowers(1, schools.get(1).getTowers(), 1, 2)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(2, schools.get(0).getEntrance(), 2)+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+
                Constants.ANSI_RESET+" "+getStudentDining(2, schools.get(0).getDiningRoom()[2])+ASCII_179+" "+
                getProfessor(2, schools.get(0).checkProfessor(Color.YELLOW_GNOMES))+" "+ASCII_179+"  "+
                getUsedTowers(2, schools.get(0).getTowers(), 0, 2)+ASCII_179+"       "+ASCII_179+
                " "+ getStudentHall(2, schools.get(1).getEntrance(), 2)+" "+ASCII_179+
                Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+getStudentDining(2, schools.get(1).getDiningRoom()[2])+ASCII_179+" "+
                getProfessor(2, schools.get(1).checkProfessor(Color.YELLOW_GNOMES))+" "+ASCII_179+"  "+
                getUsedTowers(2, schools.get(1).getTowers(), 1, 2)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(3, schools.get(0).getEntrance(), 2)+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+
                Constants.ANSI_RESET+" "+getStudentDining(3, schools.get(0).getDiningRoom()[3])+ASCII_179+" "+
                getProfessor(3, schools.get(0).checkProfessor(Color.PINK_FAIRIES))+" "+ASCII_179+"  "+
                getUsedTowers(3, schools.get(0).getTowers(), 0, 2)+ASCII_179+"       "+ ASCII_179+
                " "+ getStudentHall(3, schools.get(1).getEntrance(), 2)+" "+ASCII_179+Constants.ANSI_PINK+
                " (P)"+Constants.ANSI_RESET+" "+getStudentDining(3, schools.get(1).getDiningRoom()[3])+ASCII_179+" "+
                getProfessor(3, schools.get(1).checkProfessor(Color.PINK_FAIRIES))+" "+ASCII_179+"  "+
                getUsedTowers(3, schools.get(1).getTowers(), 1, 2)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(4, schools.get(0).getEntrance(), 2)+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+
                Constants.ANSI_RESET+" "+getStudentDining(4, schools.get(0).getDiningRoom()[4])+ASCII_179+" "+
                getProfessor(4, schools.get(0).checkProfessor(Color.BLUE_UNICORNS))+" "+ASCII_179+"  "+
                getUsedTowers(4, schools.get(0).getTowers(), 0, 2)+ASCII_179+"       "+ASCII_179+" "+ getStudentHall(4, schools.get(1).getEntrance(),2)+" "+ASCII_179+Constants.ANSI_BLUE+
                " (B)"+Constants.ANSI_RESET+" "+getStudentDining(4, schools.get(1).getDiningRoom()[4])+ASCII_179+" "+
                getProfessor(4, schools.get(1).checkProfessor(Color.BLUE_UNICORNS))+" "+ASCII_179+"  "+
                getUsedTowers(4, schools.get(1).getTowers(), 1, 2)+ASCII_179+"\n"+
                " ───────────────────────────────────────────────────────────"+"        "+
                " ───────────────────────────────────────────────────────────"+"\n"+
                "                   "+Constants.RECTANGLE+" "+schools.get(0).getNickname()+"'s SCHOOL "+Constants.ANSI_RESET+
                "                                                      "+
                Constants.RECTANGLE+" "+schools.get(1).getNickname()+"'s SCHOOL "+Constants.ANSI_RESET+"\n\n";
    }

    public String getSchoolBoardMaxPlayers(HashMap<Integer, SchoolGrid> schools) {
        return " ───────────────────────────────────────────────────────────"+"        "+
                " ───────────────────────────────────────────────────────────"+"\n"+
                ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+
                " "+ getStudentHall(0,schools.get(0).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_GREEN+
                " (G)"+Constants.ANSI_RESET+" "+getStudentDining(0,schools.get(0).getDiningRoom()[0])+ASCII_179+" "+
                getProfessor(0, schools.get(0).checkProfessor(Color.GREEN_FROGS))+" "+ASCII_179+"  "+
                getUsedTowers(0, schools.get(0).getTowers(), 0, 3)+ASCII_179+"       "+ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+
                Constants.ANSI_RESET+" "+ getStudentHall(0, schools.get(1).getEntrance(),2)+" "+ASCII_179+
                Constants.ANSI_GREEN+" (G)"+Constants.ANSI_RESET+" "+ getStudentDining(0,schools.get(1).getDiningRoom()[0])+ASCII_179+" "+
                getProfessor(0, schools.get(1).checkProfessor(Color.GREEN_FROGS))+" "+ASCII_179+"  "+
                getUsedTowers(0, schools.get(1).getTowers(), 1, 3)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(1, schools.get(0).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_RED+
                " (R)"+Constants.ANSI_RESET+" "+getStudentDining(1, schools.get(0).getDiningRoom()[1])+ASCII_179+" "+
                getProfessor(1, schools.get(0).checkProfessor(Color.RED_DRAGONS))+" "+ASCII_179+"  "+
                getUsedTowers(1, schools.get(0).getTowers(), 0,3)+ASCII_179+"       "+ASCII_179+" "+ getStudentHall(1, schools.get(1).getEntrance(),3)+" "+ASCII_179+
                Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+getStudentDining(1,schools.get(1).getDiningRoom()[1])+ASCII_179+
                " "+ getProfessor(1, schools.get(1).checkProfessor(Color.RED_DRAGONS))+" "+ASCII_179+"  "+
                getUsedTowers(1, schools.get(1).getTowers(), 1, 3)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(2, schools.get(0).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+
                Constants.ANSI_RESET+" "+getStudentDining(2, schools.get(0).getDiningRoom()[2])+ASCII_179+" "+
                getProfessor(2, schools.get(0).checkProfessor(Color.YELLOW_GNOMES))+" "+ASCII_179+"  "+
                getUsedTowers(2, schools.get(0).getTowers(), 0, 3)+ASCII_179+"       "+ASCII_179+" "+ getStudentHall(2, schools.get(1).getEntrance(), 3)+" "+ASCII_179+
                Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+getStudentDining(2, schools.get(1).getDiningRoom()[2])+ASCII_179+" "+
                getProfessor(2, schools.get(1).checkProfessor(Color.YELLOW_GNOMES))+" "+ASCII_179+"  "+
                getUsedTowers(2, schools.get(1).getTowers(), 1, 3)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(3, schools.get(0).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+
                Constants.ANSI_RESET+" "+getStudentDining(3, schools.get(0).getDiningRoom()[3])+ASCII_179+" "+
                getProfessor(3, schools.get(0).checkProfessor(Color.PINK_FAIRIES))+" "+ASCII_179+"  "+
                getUsedTowers(3, schools.get(0).getTowers(), 0, 3)+ASCII_179+"       "+ ASCII_179+" "+ getStudentHall(3, schools.get(1).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_PINK+
                " (P)"+Constants.ANSI_RESET+" "+getStudentDining(3, schools.get(1).getDiningRoom()[3])+ASCII_179+" "+
                getProfessor(3, schools.get(1).checkProfessor(Color.PINK_FAIRIES))+" "+ASCII_179+"  "+
                getUsedTowers(3, schools.get(1).getTowers(), 1, 3)+ASCII_179+"\n"+
                ASCII_179+" "+ getStudentHall(4, schools.get(0).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+
                Constants.ANSI_RESET+" "+getStudentDining(4, schools.get(0).getDiningRoom()[4])+ASCII_179+" "+
                getProfessor(4, schools.get(0).checkProfessor(Color.BLUE_UNICORNS))+" "+ASCII_179+"  "+
                getUsedTowers(4, schools.get(0).getTowers(), 0, 3)+ASCII_179+"       "+ASCII_179+" "+ getStudentHall(4, schools.get(1).getEntrance(),3)+" "+ASCII_179+Constants.ANSI_BLUE+
                " (B)"+Constants.ANSI_RESET+" "+getStudentDining(4, schools.get(1).getDiningRoom()[4])+ASCII_179+" "+
                getProfessor(4, schools.get(1).checkProfessor(Color.BLUE_UNICORNS))+" "+ASCII_179+"  "+
                getUsedTowers(4, schools.get(1).getTowers(), 1, 3)+ASCII_179+"\n"+
                " ───────────────────────────────────────────────────────────"+"        "+
                " ───────────────────────────────────────────────────────────"+"\n"+
                "                  "+Constants.RECTANGLE+" "+schools.get(0).getNickname()+"'s SCHOOL "+Constants.ANSI_RESET+
                "                                                      "+
                Constants.RECTANGLE+" "+schools.get(1).getNickname()+"'s SCHOOL "+Constants.ANSI_RESET+"\n\n\n"+
                " ──────────────────────────────────────────────────────────"+"\n"+
                ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+" "+
                getStudentHall(0, schools.get(2).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_GREEN+" (G)"+
                Constants.ANSI_RESET+" "+getStudentDining(0, schools.get(2).getDiningRoom()[0])+ASCII_179+" "+
                getProfessor(0, schools.get(2).checkProfessor(Color.GREEN_FROGS))+" "+ASCII_179+"  "+
                getUsedTowers(0, schools.get(2).getTowers(), 2, 3)+ASCII_179+"\n"+
                ASCII_179+" "+getStudentHall(1, schools.get(2).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_RED+
                " (R)"+Constants.ANSI_RESET+" "+getStudentDining(1, schools.get(2).getDiningRoom()[1])+ASCII_179+" "+
                getProfessor(1, schools.get(2).checkProfessor(Color.RED_DRAGONS))+" "+ASCII_179+"  "+
                getUsedTowers(1, schools.get(2).getTowers(), 2, 3)+ASCII_179+"\n"+
                ASCII_179+" "+getStudentHall(2, schools.get(2).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_YELLOW+
                " (Y)"+Constants.ANSI_RESET+" "+getStudentDining(2, schools.get(2).getDiningRoom()[2])+ASCII_179+" "+
                getProfessor(2, schools.get(2).checkProfessor(Color.YELLOW_GNOMES))+" "+ASCII_179+"  "+
                getUsedTowers(2, schools.get(2).getTowers(), 2, 3)+ASCII_179+"\n"+
                ASCII_179+" "+getStudentHall(3, schools.get(2).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_PINK+
                " (P)"+Constants.ANSI_RESET+" "+getStudentDining(3, schools.get(2).getDiningRoom()[3])+ASCII_179+" "+
                getProfessor(3, schools.get(2).checkProfessor(Color.PINK_FAIRIES))+" "+ASCII_179+"  "+
                getUsedTowers(3, schools.get(2).getTowers(), 2, 3)+ASCII_179+"\n"+
                ASCII_179+" "+getStudentHall(4, schools.get(2).getEntrance(), 3)+" "+ASCII_179+Constants.ANSI_BLUE+
                " (B)"+Constants.ANSI_RESET+" "+getStudentDining(4, schools.get(2).getDiningRoom()[4])+ASCII_179+" "+
                getProfessor(4, schools.get(2).checkProfessor(Color.BLUE_UNICORNS))+" "+ASCII_179+"  "+
                getUsedTowers(4, schools.get(2).getTowers(), 2, 3)+ASCII_179+"\n"+
                " ──────────────────────────────────────────────────────────"+"\n"+
                "                  "+Constants.RECTANGLE+" "+schools.get(2).getNickname()+"'s SCHOOL "+Constants.ANSI_RESET+"\n\n";
    }

    private final String[] CHARACTERS = new String[] { "MONK CARD", "INNKEEPER CARD", "PRINCE CARD", "HERALD CARD", "GROCER CARD",
            "CENTAUR CARD", "JESTER CARD", "KNIGHT CARD", "MERCHANT CARD", "MINSTREL CARD", "W_PRINCESS CARD", "THIEF CARD"};

    private String getCharacterCards(int[] costEffect, int[] characterId) {
        return ASCII_218+"────────────────────"+ASCII_191+"          "+ASCII_218+"────────────────────"+ASCII_191+
                "          "+ASCII_218+"────────────────────"+ASCII_191+"\n"+
                ASCII_179+" "+Constants.RECTANGLE+" COST──>"+costEffect[0]+" "+Constants.ANSI_RESET+"         "+ASCII_179+
                "          "+ASCII_179+" "+Constants.RECTANGLE+" COST──>"+costEffect[1]+" "+Constants.ANSI_RESET+"         "+ASCII_179+
                "          "+ASCII_179+" "+Constants.RECTANGLE+" COST──>"+costEffect[2]+" "+Constants.ANSI_RESET+"         "+ASCII_179+
                "\n"+ASCII_179+"                    "+ASCII_179+"          "+ASCII_179+"                    "+ASCII_179+
                "          "+ASCII_179+"                    "+ASCII_179+"\n"+
                ASCII_179+"                    "+ASCII_179+"          "+ASCII_179+"                    "+ASCII_179+
                "          "+ASCII_179+"                    "+ASCII_179+"\n"+
                ASCII_179+"                    "+ASCII_179+"          "+ASCII_179+"                    "+ASCII_179+
                "          "+ASCII_179+"                    "+ASCII_179+"\n"+
                ASCII_179+"                    "+ASCII_179+"          "+ASCII_179+"                    "+ASCII_179+
                "          "+ASCII_179+"                    "+ASCII_179+"\n"+
                ASCII_179+"                    "+ASCII_179+"          "+ASCII_179+"                    "+ASCII_179+
                "          "+ASCII_179+"                    "+ASCII_179+"\n"+

                ASCII_179+"                    "+ASCII_179+"          "+ASCII_179+"                    "+ASCII_179+
                "          "+ASCII_179+"                    "+ASCII_179+"\n"+
                ASCII_192+"────────────────────"+ASCII_217+"          "+ASCII_192+"────────────────────"+ASCII_217+"          "+
                ASCII_192+"────────────────────"+ASCII_217+"          "+"\n"+
                "  "+CHARACTERS[characterId[0]]+"\t\t\t\t\t "+CHARACTERS[characterId[1]]+"\t\t\t\t\t\t "+CHARACTERS[characterId[2]];
    }

    public String printBoard(int numberOfPlayers, GameBoard gameBoard/*, boolean isExpert*/){
        String printable = "";

        if (numberOfPlayers==2) {
            printable+=getCloudsMinPlayers(gameBoard.getClouds().getAllStudents());
            printable+=getArchipelago(gameBoard.getArchipelago().getConfiguration());
            printable+=getSchoolBoardMinPlayers(gameBoard.getSchools());
        } else {
            printable+=getCloudsMaxPlayers(gameBoard.getClouds().getAllStudents());
            printable+=getArchipelago(gameBoard.getArchipelago().getConfiguration());
            printable+=getSchoolBoardMaxPlayers(gameBoard.getSchools());
        }

        return printable;
    }

    /*
    public static void main(String[] args) {
        /*List<IslandTile> islands = new ArrayList<>();
        islands.add(new IslandTile(-1));
        islands.add(new IslandTile(2));
        islands.add(new IslandTile(0));
        islands.add(new IslandTile(1));
        islands.add(new IslandTile(4));
        islands.add(new IslandTile(3));
        islands.add(new IslandTile(1));
        islands.add(new IslandTile(4));
        islands.add(new IslandTile(0));
        islands.add(new IslandTile(3));
        islands.add(new IslandTile(2));
        islands.add(new IslandTile(-1));

        Blueprint test = new Blueprint();
        HashMap<Integer, SchoolBoard> schools = new LinkedHashMap<>();
        SchoolBoard school_1 = new SchoolBoard("lory", 3, false);
        SchoolBoard school_2 = new SchoolBoard("andre", 3, false);
        SchoolBoard school_3 = new SchoolBoard("ANNE", 3, false);


        school_2.addStudentToEntrance(0);
        school_2.addStudentToEntrance(1);
        school_2.addStudentToEntrance(2);
        school_2.addStudentToEntrance(3);
        school_2.addStudentToEntrance(4);
        school_2.addStudentToEntrance(1);
        school_2.addStudentToEntrance(1);
        school_2.addStudentToEntrance(1);
        school_2.addStudentToEntrance(1);

        school_3.addStudentToEntrance(0);
        school_3.addStudentToEntrance(1);
        school_3.addStudentToEntrance(2);
        school_3.addStudentToEntrance(3);
        school_3.addStudentToEntrance(4);
        school_3.addStudentToEntrance(1);
        school_3.addStudentToEntrance(4);
        school_3.addStudentToEntrance(0);
        school_3.addStudentToEntrance(2);

        schools.put(0, school_1);
        schools.put(1, school_2);
        schools.put(2, school_3);
        //System.out.println(test.getCloudsMaxPlayers(new int[] {0,0,0,0,0,0,0,0,0,0,0,0}));

        System.out.println(test.getArchipelago());

    }

     */
}
