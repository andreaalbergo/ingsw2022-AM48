package it.polimi.ingsw.client;

import it.polimi.ingsw.server.servermessages.SerializedAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Listener_FromServer is the listener in the socket connection, it is unique for every player.
 */
public class Listener_FromServer implements Runnable{
    private final Socket socket;
    private final ClientView view;
    private final CommandHandler commandHandler;
    private final ObjectInputStream in;
    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Constructor Listener_FromServer that creates its instances.
     *
     * @param socket of type Socket.
     * @param view of type ClientView.
     * @param commandHandler of type CommandHandler.
     * @param input of type ObjectInputStream.
     */
    public Listener_FromServer(Socket socket, ClientView view, CommandHandler commandHandler, ObjectInputStream input) {
        this.socket = socket;
        this.view = view;
        this.commandHandler = commandHandler;
        this.in = input;
    }

    /**
     * Method run is needed to run the listener and keeping it active and ready to listen.
     */
    @Override
    public void run() {
        try{
            do{
                SerializedAnswer answer = (SerializedAnswer) in.readObject();
                System.out.println(answer.getAnswer().getMessage());
                view.setAnswer(answer.getAnswer());
                commandHandler.answerHandler();
            }while (view.getCli() == null || view.getCli().isActiveGame());
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Connection closed by the server. Quitting...");
            System.exit(0);

        }finally {
            try{
                in.close();
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE,e.getMessage(),e);
            }
        }
    }
}
