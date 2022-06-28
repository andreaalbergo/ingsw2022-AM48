package it.polimi.ingsw.costanti;

/**
 * Blueprint class contains all grid assets for our CLI view.
 *
 * @author David Barb
 */
public class Blueprint {
    private static final String ASCII_157 = "Ø";
    private static final String ASCII_179 = "│";
    private static final String ASCII_185 = "║";
    private static final String ASCII_187 = "╗";
    private static final String ASCII_188 = "╝";
    private static final String ASCII_191 = "┐";
    private static final String ASCII_192 = "└";
    private static final String ASCII_200 = "╚";
    private static final String ASCII_201 = "╔";
    private static final String ASCII_217 ="┘";
    private static final String ASCII_218 ="┌";
    private static final String ASCII_219 = "█";

    private static String getUpperBoxCloud(Integer cloudId) {
        return "┌──────"+Constants.RECTANGLE+" CLOUD_"+cloudId.toString()+" "+Constants.ANSI_RESET+"──────┐";
    }
    private static final String LOWER_BOX_CLOUD = "└─────────────────────┘";


    private static final String BOX_LINE_ONE = "═══════════════";
    private static final String BOX_LINE_TWO = "══════════════════════════════════════════════════";
    private static final String EMPTY_LINE_ONE = "               ";
    private static final String EMPTY_LINE_TWO = "                                                  ";

    private static final String BLUE_PROFESSOR = Constants.RECTANGLE+Constants.ANSI_CYAN+"◯"+Constants.ANSI_RESET;
    private static final String RED_PROFESSOR= Constants.RECTANGLE+Constants.ANSI_RED+"◯"+Constants.ANSI_RESET;
    private static final String PINK_PROFESSOR= Constants.RECTANGLE+Constants.ANSI_PINK+"◯"+Constants.ANSI_RESET;
    private static final String YELLOW_PROFESSOR = Constants.RECTANGLE+Constants.ANSI_YELLOW+"◯"+Constants.ANSI_RESET;
    private static final String GREEN_PROFESSOR = Constants.RECTANGLE+Constants.ANSI_GREEN+"◯"+Constants.ANSI_RESET;
    private static final String EMPTY_PROFESSOR = "◯";
    private static final String[] PROFESSORS = new String[] {YELLOW_PROFESSOR, BLUE_PROFESSOR, GREEN_PROFESSOR,
            RED_PROFESSOR, PINK_PROFESSOR, EMPTY_PROFESSOR};

    private static final String BLUE_UNICORN = Constants.RECTANGLE+Constants.ANSI_CYAN+"◍"+Constants.ANSI_RESET;
    private static final String RED_DRAGON = Constants.RECTANGLE+Constants.ANSI_RED+"◍"+Constants.ANSI_RESET;
    private static final String PINK_FAIRY = Constants.RECTANGLE+Constants.ANSI_PINK+"◍"+Constants.ANSI_RESET;
    private static final String YELLOW_GNOME = Constants.RECTANGLE+Constants.ANSI_YELLOW+"◍"+Constants.ANSI_RESET;
    private static final String GREEN_FROG = Constants.RECTANGLE+Constants.ANSI_GREEN+"◍"+Constants.ANSI_RESET;
    private static final String EMPTY_STUDENT_SLOT = "◍";
    private static final String NO_ENTRY_TILE = Constants.ANSI_RED+ASCII_157+Constants.ANSI_RESET;
    private static final String[] STUDENTS = new String[] {YELLOW_GNOME, BLUE_UNICORN, GREEN_FROG, RED_DRAGON,
            PINK_FAIRY, EMPTY_STUDENT_SLOT};

    private static final String MENU_TAB_MIN_PLAYERS = "══════════════════════════════"+"\n";

    private static final String MENU_TAB_MAX_PLAYERS = "══════════════════════════════"+"\n";

    public static String getCloudsMinPlayers(int[] studentsRandom) throws IllegalArgumentException{
        if(studentsRandom.length!=6) throw new IllegalArgumentException();
        return getUpperBoxCloud(1) +"\t\t"+ getUpperBoxCloud(2)+
                "                                                                     "+
                "═════════════════════"+Constants.RECTANGLE+" LEGEND "+Constants.ANSI_RESET+"═══════════════════"+"\n"+
                "|       _______       |\t\t|       _______       |"+"                                                                    "+
                ASCII_185+" -Empty student cell: "+EMPTY_STUDENT_SLOT+"                        "+ASCII_185+"\n"+
                "     _(   "+STUDENTS[studentsRandom[0]]+"    )___  \t\t     _(   "+STUDENTS[studentsRandom[3]]+"    )___  "+
                "                                                                        "+"-Students: UNICORN->"+BLUE_UNICORN+
                "\\"+Constants.ANSI_BLUE+"(B)"+Constants.ANSI_RESET+"  GNOME->"+YELLOW_GNOME+ "\\"+Constants.ANSI_YELLOW+
                "(Y)"+Constants.ANSI_RESET+"\n"+
                "   _(  "+STUDENTS[studentsRandom[1]]+"     "+STUDENTS[studentsRandom[2]]+"     ) \t\t   _(  "+
                STUDENTS[studentsRandom[4]]+"     "+STUDENTS[studentsRandom[5]]+"     ) "+
                "                                                                        "+"   FAIRY->"+PINK_FAIRY+"\\"+
                Constants.ANSI_PINK+"(P)"+Constants.ANSI_RESET+"  FROG->"+GREEN_FROG+"\\"+Constants.ANSI_GREEN+
                "(G)"+Constants.ANSI_RESET+"  DRAGON->"+RED_DRAGON+"\\"+Constants.ANSI_RED+"(R)"+Constants.ANSI_RESET+"\n"+
                "| (_________________) |\t\t| (_________________) |"+"                                                                    "+
                ASCII_185+" -Empty professor cell: "+EMPTY_PROFESSOR+"                       "+ASCII_185+"\n"+
                LOWER_BOX_CLOUD+"\t\t"+LOWER_BOX_CLOUD+"                                                                      "+
                "-Towers: "+" WHITE->"+WHITE_TOWER+" BLACK->"+BLACK_TOWER+" (for 3p)GREY->"+GREY_TOWER+"\n";
    }

    public static String getCloudsMaxPlayers(int[] studentsRandom) throws IllegalArgumentException{
        if(studentsRandom.length!=9) throw new IllegalArgumentException();
        return getUpperBoxCloud(1) +"\t\t"+ getUpperBoxCloud(2) +"\t\t"+ getUpperBoxCloud(3)+
                "                                         "+"═════════════════════"+Constants.RECTANGLE+" LEGEND "+Constants.ANSI_RESET+"═══════════════════"+" \n" +
                "|       _______       |\t\t|       _______       |\t\t|       _______       |"+"                                        "+
                ASCII_185+" -Empty student cell: "+EMPTY_STUDENT_SLOT+"                        "+ASCII_185+"\n"+
                "     _(   "+STUDENTS[studentsRandom[0]]+"    )___  \t\t     _(   "+STUDENTS[studentsRandom[3]]+
                "    )___  \t\t     _(   "+STUDENTS[studentsRandom[6]]+"    )___  "+"                                            "+
                "-Students: UNICORN->"+BLUE_UNICORN+"\\"+Constants.ANSI_BLUE+"(B)"+Constants.ANSI_RESET+"  GNOME->"+YELLOW_GNOME+
                "\\"+Constants.ANSI_YELLOW+"(Y)"+Constants.ANSI_RESET+"\n"+
                "   _(  "+STUDENTS[studentsRandom[1]]+"     "+STUDENTS[studentsRandom[2]]+"     ) \t\t   _(  "+
                STUDENTS[studentsRandom[4]]+"     "+STUDENTS[studentsRandom[5]]+"     ) \t\t   _(  "+
                STUDENTS[studentsRandom[7]]+"     "+STUDENTS[studentsRandom[8]]+"     ) "+"                                            "+
                "   FAIRY->"+PINK_FAIRY+"\\"+Constants.ANSI_PINK+"(P)"+Constants.ANSI_RESET+"  FROG->"+GREEN_FROG+"\\"+Constants.ANSI_GREEN+
                "(G)"+Constants.ANSI_RESET+"  DRAGON->"+RED_DRAGON+"\\"+Constants.ANSI_RED+"(R)"+Constants.ANSI_RESET+"\n"+
                "| (_________________) |\t\t| (_________________) |\t\t| (_________________) |"+"                                        "+
                ASCII_185+" -Empty professor cell: "+EMPTY_PROFESSOR+"                       "+ASCII_185+"\n"+
                LOWER_BOX_CLOUD+"\t\t"+LOWER_BOX_CLOUD+"\t\t"+LOWER_BOX_CLOUD+"                                          "+
                "-Towers: "+" WHITE->"+WHITE_TOWER+" BLACK->"+BLACK_TOWER+" (for 3p)GREY->"+GREY_TOWER+"\n";
    }
    private static final String BLACK_TOWER = Constants.ANSI_BACKGROUND_BLACK+"╦╦"+Constants.ANSI_RESET;
    private static final String GREY_TOWER = Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_BLACK +"╬╬" +
            Constants.ANSI_RESET;
    private static final String WHITE_TOWER = Constants.RECTANGLE+"╦╦"+Constants.ANSI_RESET;
    private static final String EMPTY_TOWER_ISLAND = "  ";
    private static final String EMPTY_TOWER_SCHOOL = "ØØ";
    private static final String[] TOWERS = new String[]{WHITE_TOWER, BLACK_TOWER, GREY_TOWER, EMPTY_TOWER_ISLAND,
    EMPTY_TOWER_SCHOOL};

    private static final String UPPER_MOTHER_NATURE = Constants.ANSI_BLACK+Constants.ANSI_BACKGROUND_YELLOW+"╔═╗"+
            Constants.ANSI_RESET;
    private static final String MIDDLE_MOTHER_NATURE = Constants.ANSI_BLACK+Constants.ANSI_BACKGROUND_YELLOW+"║M║"+
            Constants.ANSI_RESET;
    private static final String LOWER_MOTHER_NATURE = Constants.ANSI_BLACK+Constants.ANSI_BACKGROUND_YELLOW+"╚═╝"+
            Constants.ANSI_RESET;
    private static final String EMPTY_MOTHER_NATURE = "   ";

    private static final String ARCHIPELAGO_START_GAME =
            ASCII_201 + BOX_LINE_TWO + BOX_LINE_TWO + BOX_LINE_ONE + ASCII_187 +"    "+
            "-Coin redeem on school board->@"+" (EXPERT MODE)"+"\n"+
            "║    ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────    ║"+
            "  ║"+" -Mother Nature: "+UPPER_MOTHER_NATURE+"+"+MIDDLE_MOTHER_NATURE+"+"+LOWER_MOTHER_NATURE+"                    ║"+"\n"+
            "║  /"+UPPER_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+
            "   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\  ║"+
            "    -MOVELIST:"+"\n"+
            "║ / "+MIDDLE_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+"  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+
            "  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+"  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+
            "  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+"  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\ ║"+
            "    1.MOVEMOTHERNATURE <int num_steps>"+"\n"+
            "║|  "+LOWER_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |\t"+
            " |  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |║"+
            "  ║ 2."+"   "+ASCII_185+"\n"+
            "║ \\           /\t\t"+"  \\           /\t\t"+"  \\           /\t\t"+"  \\           /\t\t"+"  \\           /\t\t"+
            "  \\           / ║\n"+
            "║  \\         /\t\t"+"   \\         /\t\t"+"   \\         /\t\t"+"   \\         /\t\t"+"   \\         /\t\t"+
            "   \\         /  ║\n"+
            "║    ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+
            "     ───────    ║\n"+
            "║    "+"ISLE_1              "+"ISLE_2              "+"ISLE_3              "+"ISLE_4              "+
            "ISLE_5              "+"ISLE_6     "+"║"+"\n"+
            "║"+EMPTY_LINE_TWO+EMPTY_LINE_TWO+EMPTY_LINE_ONE+"║"+"\n"+
            "║    ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────    ║\n"+
            "║  /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+
            "   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\\t\t"+"   /"+EMPTY_MOTHER_NATURE+"      \\  ║\n"+
            "║ / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+"  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+
            "  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+"  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+
            "  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\\t\t"+"  / "+EMPTY_MOTHER_NATURE+"  "+TOWERS[3]+"   \\ ║\n"+
            "║|  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |\t"+
            " |  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |\t"+" |  "+EMPTY_MOTHER_NATURE+"        |║\n"+
            "║ \\           /\t\t"+"  \\           /\t\t"+"  \\           /\t\t"+"  \\           /\t\t"+"  \\           /\t\t"+
            "  \\           / ║\n"+
            "║  \\         /\t\t"+"   \\         /\t\t"+"   \\         /\t\t"+"   \\         /\t\t"+"   \\         /\t\t"+
            "   \\         /  ║\n"+
            "║    ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+"     ───────  \t\t"+
            "     ───────    ║\n"+
            "║    "+"ISLE_12             "+"ISLE_11             "+"ISLE_10             "+"ISLE_9              "+
                    "ISLE_8              "+"ISLE_7     "+"║"+"\n"+
            "║"+EMPTY_LINE_TWO+EMPTY_LINE_TWO+EMPTY_LINE_ONE+"║"+"\n"+
                    ASCII_200 +BOX_LINE_TWO+BOX_LINE_TWO+BOX_LINE_ONE+ ASCII_188 +"\n";

    public static String getArchipelago() {
        return "";
    }

    private static final String SCHOOLS_MIN_PLAYERS = " ──────────────────────────────────────────────────────────"+"        "+" ──────────────────────────────────────────────────────────"+"\n"+
            ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_GREEN+" (G)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+  ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_GREEN+" (G)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+      ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+   ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+ ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            " ──────────────────────────────────────────────────────────"+"        "+" ──────────────────────────────────────────────────────────"+"\n"+
            "                   "+Constants.RECTANGLE+" PLAYER_1's SCHOOL "+Constants.ANSI_RESET+"                                                   "+
            Constants.RECTANGLE+" PLAYER_2's SCHOOL "+Constants.ANSI_RESET+"\n\n";

    private static final String SCHOOLS_MAX_PLAYERS = " ──────────────────────────────────────────────────────────"+"        "+" ──────────────────────────────────────────────────────────"+"\n"+
            ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_GREEN+" (G)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+  ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_GREEN+" (G)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+      ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+   ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+ ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+WHITE_TOWER+"  "+WHITE_TOWER+"  "+ASCII_179+"       "+ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+BLACK_TOWER+"  "+BLACK_TOWER+"  "+ASCII_179+"\n"+
            " ──────────────────────────────────────────────────────────"+"        "+" ──────────────────────────────────────────────────────────"+"\n"+
            "                   "+Constants.RECTANGLE+" PLAYER_1's SCHOOL "+Constants.ANSI_RESET+"                                                   "+
            Constants.RECTANGLE+" PLAYER_2's SCHOOL "+Constants.ANSI_RESET+"\n\n\n"+
            " ──────────────────────────────────────────────────────────"+"\n"+
            ASCII_179+" "+Constants.ANSI_BACKGROUND_GREY+Constants.ANSI_WHITE+EMPTY_STUDENT_SLOT+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_GREEN+" (G)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+GREY_TOWER+"  "+GREY_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_RED+" (R)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+GREY_TOWER+"  "+GREY_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_YELLOW+" (Y)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+GREY_TOWER+"  "+GREY_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_PINK+" (P)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+GREY_TOWER+"  "+GREY_TOWER+"  "+ASCII_179+"\n"+
            ASCII_179+" "+EMPTY_STUDENT_SLOT+" "+EMPTY_STUDENT_SLOT+" "+ASCII_179+Constants.ANSI_BLUE+" (B)"+Constants.ANSI_RESET+" "+EMPTY_STUDENT_SLOT+"  "+
            EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+"  "+EMPTY_STUDENT_SLOT+"  @  "+EMPTY_STUDENT_SLOT+
            " "+ASCII_179+" "+EMPTY_PROFESSOR+" "+ASCII_179+"  "+GREY_TOWER+"  "+GREY_TOWER+"  "+ASCII_179+"\n"+
            " ──────────────────────────────────────────────────────────"+"\n"+
            "                   "+Constants.RECTANGLE+" PLAYER_3's SCHOOL "+Constants.ANSI_RESET+"\n\n";

    private static final String TAB_MIN_PLAYERS_NORMAL = Constants.RECTANGLE+Constants.ANSI_BLUE+" PLAYER_1's HAND: "+Constants.ANSI_RESET+"                                 "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" PLAYER_2's HAND: "+Constants.ANSI_RESET+"\n"+
            Constants.RECTANGLE+Constants.ANSI_BLUE+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"   "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"\n";

    private static final String TAB_MAX_PLAYERS_NORMAL = Constants.RECTANGLE+Constants.ANSI_BLUE+" PLAYER_1's HAND: "+Constants.ANSI_RESET+"                                 "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" PLAYER_2's HAND: "+Constants.ANSI_RESET+"                                 "+
            Constants.RECTANGLE+Constants.ANSI_RED+" PLAYER_3's HAND: "+Constants.ANSI_RESET+"\n"+
            Constants.RECTANGLE+Constants.ANSI_BLUE+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"   "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"   "+
            Constants.RECTANGLE+Constants.ANSI_RED+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"\n";

    private static final String TAB_MIN_PLAYERS_EXPERT = Constants.RECTANGLE+Constants.ANSI_BLUE+" PLAYER_1's HAND: "+Constants.ANSI_RESET+"                                 "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" PLAYER_2's HAND: "+Constants.ANSI_RESET+"\n"+
            Constants.RECTANGLE+Constants.ANSI_BLUE+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"   "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"\n"+
            Constants.RECTANGLE+Constants.ANSI_BLUE+" COIN BAG ──>"+" N "+"COINS "+Constants.ANSI_RESET+"                             "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" COIN BAG ──>"+" N "+"COINS "+Constants.ANSI_RESET+"\n";

    private static final String TAB_MAX_PLAYERS_EXPERT = Constants.RECTANGLE+Constants.ANSI_BLUE+" PLAYER_1's HAND: "+Constants.ANSI_RESET+"                                 "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" PLAYER_2's HAND: "+Constants.ANSI_RESET+"                                 "+
            Constants.RECTANGLE+Constants.ANSI_RED+" PLAYER_3's HAND: "+Constants.ANSI_RESET+"\n"+
            Constants.RECTANGLE+Constants.ANSI_BLUE+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"   "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"   "+
            Constants.RECTANGLE+Constants.ANSI_RED+" Assistant cards not used: "+"1,2,3,4,5,6,7,8,9,10 "+Constants.ANSI_RESET+"\n"+
            Constants.RECTANGLE+Constants.ANSI_BLUE+" COIN BAG ──>"+" N "+"COINS "+Constants.ANSI_RESET+"                             "+
            Constants.RECTANGLE+Constants.ANSI_YELLOW+" COIN BAG ──>"+" N "+"COINS "+Constants.ANSI_RESET+"                             "+
            Constants.RECTANGLE+Constants.ANSI_RED+" COIN BAG ──>"+" N "+"COINS "+Constants.ANSI_RESET+"\n";

    private static final String[] CHARACTERS = new String[] { "MONK CARD", "INNKEEPER CARD", "PRINCE CARD", "HERALD CARD", "GROCER CARD",
            "CENTAUR CARD", "JESTER CARD", "KNIGHT CARD", "MERCHANT CARD", "MINSTREL CARD", "W_PRINCESS CARD", "THIEF CARD"};

    private static String getCharacterCards(int[] costEffect, int[] characterId) {
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
    public static final String CLI_BOARD = getCharacterCards(new int[] {3,6,2}, new int[] {11,4,0});


    public static void main(String[] args) {
        System.out.print(Blueprint.CLI_BOARD);
    }
}
