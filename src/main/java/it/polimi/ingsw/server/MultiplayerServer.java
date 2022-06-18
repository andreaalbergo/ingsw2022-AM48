package it.polimi.ingsw.server;


import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class MultiplayerServer {
    private final int port;
    private BoardHandler board;

    private Boolean mode;

    public Boolean isExpert() {
        return mode;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }

    private int number_of_Players;
    private final Map<Integer, Client> idtoClientMap;
    private final Map<String, Integer> nametoIdMap;
    private final Map<Integer, String> idNameMap;
    private final Map<Client, ClientHandler> clientConnectionMap;
    private int assigned_clientID = -1;
    private final List<ClientHandler> waiting = new ArrayList<>();
    public static final Logger LOGGER = Logger.getLogger(MultiplayerServer.class.getName());


    private final SocketServer socketServer;

    public MultiplayerServer(int port) {
        this.port = port;
        clientConnectionMap = new HashMap<>();
        nametoIdMap = new HashMap<>();
        idtoClientMap = new HashMap<>();
        idNameMap = new HashMap<>();
        this.socketServer = new SocketServer(Constants.getPort(),this);
        Thread thread = new Thread(this::quitter);
        thread.start();

    }

    public Map<Integer, Client> getIdtoClientMap() {
        return idtoClientMap;
    }

    public Map<String, Integer> getNametoIdMap() {
        return nametoIdMap;
    }

    public Map<Integer, String> getIdNameMap() {
        return idNameMap;
    }

    public Map<Client, ClientHandler> getClientConnectionMap() {
        return clientConnectionMap;
    }


    public void quitter(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(scanner.next().equalsIgnoreCase("Killserver")){
                socketServer.setActiveStatus(false);
                System.out.println("The server is now closing...");
                System.exit(0);
                break;
            }
        }
    }

    public void setNumber_of_Players(int number_of_Players) {
        this.number_of_Players = number_of_Players;
    }

    public BoardHandler getBoard() {
        return board;
    }

    public void lobby(ClientHandler client) throws InterruptedException {
        waiting.add(client);
        System.out.println("\nNella lobby ci sono " + waiting.size() + " giocatori\n");
        if(waiting.size() == 1){
            System.out.println("COMMAND: Select the number of Players [2 or 3] -> ");
            client.setNumberofPlayers(new SetPlayersRequest( idtoClientMap.get(client.getIdClient()).getNickname()
                    + " please select the number of Players [2 or 3] -> "));
            if(number_of_Players > 0){
                client.setMode( new SetMode(idtoClientMap.get(client.getIdClient()).getNickname() + " as the host select a mode between EXPERT and NORMAL"));
            }


        }else if (waiting.size() == number_of_Players){
            for (int i = 3; i > 0; i++) {
                board.sendAll(new CustomMessage("Match is starting in " + i));
                TimeUnit.SECONDS.sleep(1);
            }
            board.sendAll(new CustomMessage("Match started"));
            waiting.clear();
            Wizard.clear();
            Tower.clear();
            board.setup();

        }else
            board.sendAll(new CustomMessage("There are still" + (number_of_Players - waiting.size()) + "slots left"));

    }

    public int update_assignedId(){
        int id;
        if(assigned_clientID == -1){
            id = 0;
            assigned_clientID = 1;
        }else{
        id = assigned_clientID;
        assigned_clientID++;
        }
        return id;
    }

/*
    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); // Porta non disponibile
            return;
        }
        System.out.println("Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket, this));
            } catch (IOException e) {
                break; // Entrerei qui se serverSocket venisse chiuso
            }
        }
        executor.shutdown();
    }


 */
    public void removeClientFromGame(Integer idClient) {
        board.unregisterPlayer(idClient);
        Client client = idtoClientMap.get(idClient);
        System.out.println("Removing the player: " + idtoClientMap.get(idClient).getNickname() + "...\n");
        nametoIdMap.remove(client.getNickname());
        idtoClientMap.remove(idClient);
        waiting.remove(clientConnectionMap.get(client));
        idNameMap.remove(idClient);
        clientConnectionMap.remove(client);
        System.out.println("Player removed");

    }

    public Integer addClientToGame(String nickname, ClientHandler client) {
        Integer IDclient = nametoIdMap.get(nickname);
        if(IDclient == null) //player
        {
            System.out.println("This is the first player!!");
            if(waiting.isEmpty()){
                board = new BoardHandler(this);
            }
            if (nametoIdMap.keySet().stream().anyMatch(nickname::equalsIgnoreCase)) {
                SerializedAnswer error = new SerializedAnswer();
                error.setSerializedAnswer(new GameError(Errors.DUPLICATENICKNAME));
                client.sendSocketMessage(error);
                return null;
            }
            IDclient = update_assignedId();
            board.setupPlayer(nickname, IDclient);
            Client virtualClient = new Client(IDclient, nickname, client, board);
            if (number_of_Players != 0 && waiting.size() > number_of_Players){
                SerializedAnswer error = new SerializedAnswer();
                error.setSerializedAnswer(new GameError(Errors.SERVER_IS_FULL));
                client.sendSocketMessage(error);
                return null;
            }

            idNameMap.put(IDclient,nickname);
            nametoIdMap.put(nickname, IDclient);
            clientConnectionMap.put(virtualClient, client);
            idtoClientMap.put(IDclient, virtualClient);
            System.out.println( "Client "+ virtualClient.getNickname() + ", identified by ID "+ virtualClient.getIdClient() + ", has successfully connected!");
            virtualClient.send(new ConnectionMessage(true,"Connection was successfully set-up! You are now connected."));



        }else
        {
            System.out.println("This is not the first player with that name, maybe you are reconnecting...!!");
            Client virtualClient = idtoClientMap.get(IDclient);
            if(virtualClient.isConnected()){
                client.sendSocketMessage(new CustomMessage("The current nickname is already taken"));
                return null;
            }
        }
        return IDclient;
    }


    public static void main(String[] args) {

        int port = 0;
        System.out.println("Hey, Welcome to the Eryantis Server!\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which port do you want the server to work on? ->");
        try {
            port = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("The input is invalid try again, try writing a Number");
            exit(-1); // magari metterei main(null)
        }

        if (port < Constants.MAX_PORT && port > Constants.MIN_PORT) {
            System.out.println("The port selected is reserved to other tasks, choose a port higher than 1024");

        }
        Constants.setPort(port);
        System.out.println("Initiating Server on port:"  + Constants.getPort());
        MultiplayerServer server = new MultiplayerServer(Constants.getPort());
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new SocketServer(Constants.getPort(),server));


    }

}

