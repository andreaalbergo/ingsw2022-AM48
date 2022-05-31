package it.polimi.ingsw.client;

import it.polimi.ingsw.server.messages.SerializedAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener_FromServer implements Runnable{
    private final Socket socket;
    private final ClientView view;
    private final CommandHandler commandHandler;
    private final ObjectInputStream in;

    public Listener_FromServer(Socket socket, ClientView view, CommandHandler commandHandler, ObjectInputStream in) {
        this.socket = socket;
        this.view = view;
        this.commandHandler = commandHandler;
        this.in = in;
    }

    public void inputLinker(SerializedAnswer message){
        view.setAnswer(message.getAnswer());
        commandHandler.answerHandler();
    }

    @Override
    public void run() {
        try{
            do{
                SerializedAnswer answer = (SerializedAnswer) in.readObject();
                System.out.println("\nSERVER: " + answer.getAnswer().getMessage());
                inputLinker(answer);
            }while (view.getCli() == null || view.getCli().isActiveGame());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            try{
                in.close();
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
