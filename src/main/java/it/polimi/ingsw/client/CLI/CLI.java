package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.CommandHandler;
import it.polimi.ingsw.client.ConnectionSocket;
import it.polimi.ingsw.costanti.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class CLI implements Runnable{

    private final Scanner in;

    private final ClientView clientView;
    private final CommandHandler handler;
    private final PrintStream out;
    private boolean active;

    private ConnectionSocket socket;




    public CLI() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
        clientView = new ClientView(this);
        handler = new CommandHandler();

    }

    public static void main(String[] args) {
        System.out.println("WELCOME TO ERYANTIS");
        System.out.println("The following game was developed by:");
        System.out.println("Andrea Albergo, Loredan Barb, Edoardo Bozzini\n\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the server's IP address (es. 127.0.0.1) -> ");
        String ipAddress = scanner.nextLine();
        System.out.println("Insert the port that the server is listening on (es. 1234) -> ");
        int port = scanner.nextInt();
        Constants.setPort(port);
        Constants.setAddress(ipAddress);
        CLI cli = new CLI();
        cli.run();
    }

    @Override
    public void run() {
        startCLI();
        while(isActiveGame()){
            if(clientView.getPhase() == 0 /* >  fase dopo aver fatto le scelte che differenziano il player dagl'altri, lo zero l'ho messo solo per ora*/){
                in.reset();
                String received = in.nextLine();

            }
        }
    }

    public boolean isActiveGame() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void startCLI() {
        String player = null;
        while (player == null){
            System.out.println("Insert your Nickname here ->");
            player = in.nextLine();
        }
        socket = new ConnectionSocket();
        clientView.setName(player);
        try{
            if(!socket.registration(player, handler, clientView)){
                System.err.println("The is no server with those specifications...");
                CLI.main(null);
            }else System.out.println("> Socket Connection completed succesully < ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
