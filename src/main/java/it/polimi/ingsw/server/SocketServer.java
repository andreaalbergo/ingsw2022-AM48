package it.polimi.ingsw.server;
/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;

public class SocketServer implements Runnable{

    private final int port;
    private final Server server;
    private boolean active;

    private static ServerSocket openServerSocket(int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return serverSocket;
    }

    static boolean startServer(int port){

        System.out.println("Starting server on port: ");
        ServerSocket serverSocket = openServerSocket(port);
        if(serverSocket == null){
            System.out.println("cannot start server on port: "+port);
            exit(-1);
        }

        System.out.println("Accepted");


        Socket clientSocket = null;
        System.out.println("Accepting");
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void run() {

    }

}
*/