package it.polimi.ingsw;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.server.MultiplayerServer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Eryantis
{
        /**
         * Method main selects CLI, GUI or Server based on the arguments provided.
         *
         * @param args of type String[]
         */
        public static void main(String[] args){
        System.out.println("Welcome to Eryantis!\nPlease choose one of the following options to launch");
        System.out.println("0.SERVER\n1.CLIENT CLI (COMMAND LINE INTERFACE)\n2.CLIENT GUI (GRAPHIC USER INTERFACE)");
        System.out.println("Type the option's number you desire to launch:");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Numeric format requested, application will now close...");
            System.exit(-1);
        }

        switch (input) {
            case 0 -> {
                System.out.println("You chose server, opening server instance...");
                MultiplayerServer.main(null);
            }
            case 1 -> {
                System.out.println("You chose CLI, opening instance...");
                CLI.main(null);
            }
            case 2 -> System.out.println("You chose GUI, opening window...");//GUI.main(null);
            default -> System.err.println("""
                    Invalid argument, try again with valid input:
                    0.Server
                    1.Client CLI
                    2.Client GUI
                    Terminating process""");
        }
    }
}
