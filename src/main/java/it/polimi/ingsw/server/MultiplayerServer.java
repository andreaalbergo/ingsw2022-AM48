package it.polimi.ingsw.server;


import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class MultiplayerServer {
    private final int port;
    private BoardHandler board;
    private int number_of_Players = 0;
    private final Map<Integer, Client> idtoClientMap;
    private final Map<String, Integer> nametoIdMap;
    private final Map<Integer, String> idNameMap;
    private final Map<Client, ClientHandler> clientConnectionMap;
    private int assigned_clientID = -1;
    private final List<ClientHandler> waiting = new ArrayList<>();
    public static final Logger LOGGER = Logger.getLogger(MultiplayerServer.class.getName());

    public SocketServer getSocketServer() {
        return socketServer;
    }

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
                getSocketServer().setActiveStatus(false);
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

    public void lobby(ClientHandler client){
        waiting.add(client);
        if(waiting.size() == 1){
            client.setNumberofPlayers(new SetPlayersRequest("Select the number of Players [2 or 3] -> "));//gli passeremo il tipo di messaggio da mandare

        }else if (waiting.size() == number_of_Players){
            waiting.clear();
            Wizard.clear();
            Tower.clear();
            board.setup();

        }else
            board.sendAll(new CustomMessage("There are still" + (number_of_Players - waiting.size()) + "slots left"));

    }

    public int update_assignedId(){
        int id = assigned_clientID;
        assigned_clientID++;
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
            if(waiting.isEmpty()){
                board = new BoardHandler();
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
                client.sendSocketMessage(new CustomMessage("Server is FULL"));
                return null;
            }

            idNameMap.put(IDclient,nickname);
            nametoIdMap.put(nickname, IDclient);
            clientConnectionMap.put(virtualClient, client);
            idtoClientMap.put(IDclient, virtualClient);



        }else
        {
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
            exit(-1);
        }

        if (port < Constants.MAX_PORT && port > Constants.MIN_PORT) {
            System.out.println("The port selected is reserved to other tasks, choose a port higher than 1024");
            main(null);
        }
        Constants.setPort(port);
        System.out.println("Initiating Server on port:"  + Constants.getPort());
        MultiplayerServer server = new MultiplayerServer(Constants.getPort());
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new SocketServer(Constants.getPort(),server));


    }

}

