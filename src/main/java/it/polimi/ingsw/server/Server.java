package it.polimi.ingsw.server;

import it.polimi.ingsw.Costants.Constants;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;
/*
public class Server {
    private final SocketServer socketServer;
    private int number_of_Players = 0;
    private final Map<Integer, VirtualClient> idtoClientMap;
    private final Map<String, Integer> nametoIdMap;
    private final Map<Integer, String> idNameMap;
    private final Map<VirtualClient, ClientHandler>  clientConnectionMap;
    private int assigned_clientID;
    private GameHandler game;
    private final List<ClientHandler> waiting = new ArrayList<>();


    public void endGame(){
        Scanner scanner = new Scanner(System.in);
        while( true ){
            if(scanner.next().equalsIgnoreCase("KILLSERVER")){
                socketServer.setActiveStatus(false);
                exit(-1);
                break;
            }
        }
    }

    public void setNumber_of_Players(int number_of_Players) {
        this.number_of_Players = number_of_Players;
    }

    public SocketServer getSocketServer() {
        return socketServer;
    }

    public VirtualClient getClientbyID(int id){
        return idtoClientMap.get(id);
    }

    public String getnicknamebyID(int id){
        return idNameMap.get(id);
    }


    public Server() {
        socketServer = new SocketServer(Constants.getPort(),this);
        idtoClientMap = new HashMap<>();
        nametoIdMap = new HashMap<>();
        idNameMap = new HashMap<>();
        clientConnectionMap = new HashMap<>();
        number_of_Players = 0;
        Thread thread = new Thread(this::endGame);
        thread.start();
        this.assigned_clientID = 1;


    }

    public synchronized void waitingRoom(ClientHandler client) throws InterruptedException{
        waiting.add(client);
        if (waiting.size() == 1){
            System.out.println("Choose the desired number of players ->");
            client.setNumberofPlayers();//gli passeremo un messaggio che gli fa decidere il numero di players


        }// se invece aggiungendo il player si raggiunge il quorum scelto dall'host in precedenza faccio partire la
        // partita e svuoto la lobby ( basta clearAll)
    }

    public synchronized Integer addClientToGame(ClientHandler socketClientConnection, String nickname){
        Integer idClient = nametoIdMap.get(nickname);

        if (idClient == null){
            if(waiting.isEmpty()){
                this.game = new GameHandler(this);
            }

            if (nametoIdMap.keySet().stream().anyMatch(nickname::equalsIgnoreCase)){
                System.out.println("\nThis nickname is already taken, pick another one\n");
                return null;
            }

            idClient = assigned_clientID;
            assigned_clientID++; //Made by Lollo
            game.setupPlayer(nickname, idClient);
            VirtualClient client = new VirtualClient(idClient,nickname,socketClientConnection,game);
            if (number_of_Players != 0 && waiting.size()>number_of_Players){
                client.send();// Need to implement messages, here you say
                return null;
            }

            idtoClientMap.put(idClient,client);
            nametoIdMap.put(nickname,idClient);
            idNameMap.put(idClient,nickname);
            clientConnectionMap.put(client,socketClientConnection);
            System.out.println(client.getNickname() + " (ID:" + client.getId() + "): has connected to the Game" );
            client.send();// Dobbiamo fare i messaggi qua gli diciamo che si è connesso correttamente


        }else {
            VirtualClient client = idtoClientMap.get(idClient);
            if(client.isConnected()){
                //creation of ServerAnswer
                return null;
            }
        }


        return idClient;
    }

    public void removeClientFromGame(Integer idClient) {
        game.unregisterPlayer(idClient);
        VirtualClient client = idtoClientMap.get(idClient);
        System.out.println("Removing the player: " + idtoClientMap.get(idClient).getNickname() + "...\n");
        nametoIdMap.remove(client.getNickname());
        idtoClientMap.remove(idClient);
        waiting.remove(clientConnectionMap.get(client));
        idNameMap.remove(idClient);
        clientConnectionMap.remove(client);
        System.out.println("Player removed");

    }

    public static void main(String[] args) {

        int port = 0;

        System.out.println("Hey, Welcome to the Eryantis Server!\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which port do you want the server to work on? ->");
        try{
            port = scanner.nextInt();
        }catch (InputMismatchException exception){
            System.out.println("The input is invalid try again, try writing a Number");
            exit(-1);
        }

        if(port < Constants.MAX_PORT && port > Constants.MIN_PORT){
            System.out.println("The port selected is reserved to other tasks, choose a port higher than 1024");
            main(null);
        }
        Constants.setPort(port);
        System.out.println("Initiating Server on port:");
        Server server = new Server();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(server.socketServer);
    }




}
*/