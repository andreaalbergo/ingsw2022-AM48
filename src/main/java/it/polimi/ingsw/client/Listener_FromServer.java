package it.polimi.ingsw.client;

import it.polimi.ingsw.server.messages.SerializedAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener_FromServer implements Runnable{
    private final Socket socket;
    private final ClientView view;
    private final CommandHandler commandHandler;
    private final ObjectInputStream in;

    private final Logger logger = Logger.getLogger(getClass().getName());

    public Listener_FromServer(Socket socket, ClientView view, CommandHandler commandHandler, ObjectInputStream input) {
        this.socket = socket;
        this.view = view;
        this.commandHandler = commandHandler;
        this.in = input;
    }

    @Override
    public void run() {
        try{
            do{

                SerializedAnswer answer = (SerializedAnswer) in.readObject();
                System.out.println(answer.getAnswer().getMessage());
                view.getCli().logger.log(Level.SEVERE, "HOLAAA"+answer.getAnswer().toString());
                view.setAnswer(answer.getAnswer());
                commandHandler.answerHandler();
            }while (view.getCli() == null || view.getCli().isActiveGame());
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Connection closed by the server. Quitting...");
            System.exit(0);

        }finally {
            try{
                System.out.println("SEI USCITO DAL WHILE NEL RUN DEL LISTENER" + view.getCli().isActiveGame() + view.getCli());
                in.close();
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE,e.getMessage(),e);
            }
        }
    }
}
