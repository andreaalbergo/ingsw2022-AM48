package it.polimi.ingsw.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

public class SocketServer implements Runnable{

    private final int port;
    private final MultiplayerServer server;
    private boolean active;
    private final ExecutorService executorService;

    public SocketServer(int port, MultiplayerServer server) {
        this.port = port;
        this.server = server;
        this.active = true;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void setActiveStatus(boolean status) {
        this.active = status;
    }

    public void acceptNewConnections(ServerSocket serverSocket){
        while(active){
            try{
            ClientHandler clientHandler = new ClientHandler(serverSocket.accept(),server);
            executorService.submit(clientHandler);
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }

    }

    @Override
    public void run() {
        try{
            ServerSocket socketServer = new ServerSocket(port);
            System.out.println("Il Server ha aperto un socket alla porta: " + port);
            acceptNewConnections(socketServer);
        }catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }


}