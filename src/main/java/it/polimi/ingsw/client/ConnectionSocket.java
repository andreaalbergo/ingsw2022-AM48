package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.client.messages.Message;
import it.polimi.ingsw.client.messages.SerializedMessage;
import it.polimi.ingsw.client.messages.SetupConnection;
import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.exceptions.DuplicateNicknameException;
import it.polimi.ingsw.exceptions.FullserverException;
import it.polimi.ingsw.server.servermessages.ConnectionMessage;
import it.polimi.ingsw.server.servermessages.Errors;
import it.polimi.ingsw.server.servermessages.GameError;
import it.polimi.ingsw.server.servermessages.SerializedAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConnectionSocket class creates a server socket that accepts connections from new clients and instantiates a new
 * thread to work with it.
 *
 * @author Andrea Albergo, Barb David
 */
public class ConnectionSocket {
    private final String serverip;
    private final int port;
    private Listener_FromServer listener;
    private ObjectOutputStream out;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());


    /**
     * Constructor ConnectionSocket creates its own instance.
     */
    public ConnectionSocket() {
        this.serverip = Constants.getAddress();
        this.port = Constants.getPort();
    }

    /**
     * Method registration registers and creates a socket between new registered player and server.
     * @param nickname of type String.
     * @param handler of type CommandHandler.
     * @param view of type ClientView.
     * @return of type boolean.
     * @throws DuplicateNicknameException when a new player chooses a nickname already picked.
     */
    public boolean registration(String nickname, CommandHandler handler, ClientView view) throws DuplicateNicknameException {
        try {
            Socket socket1;
            try {
                socket1 = new Socket(serverip, port);
            } catch (SocketException | UnknownHostException e) {
                return false;
            }
            out = new ObjectOutputStream(socket1.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket1.getInputStream());
            while (true) {
                if(readInput(nickname,inputStream)){
                    break;
                }
            }
            listener = new Listener_FromServer(socket1,view,handler,inputStream);
            Thread thread = new Thread(listener);
            thread.start();
            return true;
            } catch (IOException e) {
                System.err.println("Error during config");
                LOGGER.log(Level.SEVERE, e.getMessage(),e);
                System.exit(0);
                return false;
            }
    }

    /**
     * Method readInput reads input from new player that chooses a nickname.
     * @param nickname of type String.
     * @param input of type ObjectInputStrean
     * @return of type boolean.
     * @throws DuplicateNicknameException when nickname already chosen.
     */
    private boolean readInput(String nickname, ObjectInputStream input)
        throws DuplicateNicknameException{
            try {
                send(new SetupConnection(nickname));
                if (nicknameChecker(input.readObject())) {
                    return true;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
                return false;
            }
            return true;
    }

    /**
     * Method nicknameChecker checks for duplicate nickname.
     * @param readObject of type Object.
     * @return of type boolean
     * @throws DuplicateNicknameException when nickname is already chosen.
     */
    private boolean nicknameChecker(Object readObject) throws DuplicateNicknameException{
        SerializedAnswer answer = (SerializedAnswer) readObject;
        LOGGER.log(Level.INFO, answer.getAnswer().getMessage().toString());
        if (answer.getAnswer() instanceof ConnectionMessage message && ((ConnectionMessage) answer.getAnswer()).isCheck()) {
            System.out.println(message.getMessage());
            return true;
        } else if (answer.getAnswer() instanceof GameError) {
            if (((GameError) answer.getAnswer()).getError().equals(Errors.DUPLICATENICKNAME)) {
                System.err.println("This nickname is already in use! Please choose one other.");
                throw new DuplicateNicknameException();
            }
            if (((GameError) answer.getAnswer()).getError().equals(Errors.SERVER_IS_FULL)) {
                System.err.println(
                        "This match is already full, please try again later!\nApplication will now close...");
                System.exit(0);
            }
        }
        return false;
    }

    /**
     * Method send sends a message in output stream to socket.
     *
     * @param message of type Message.
     */
    public void send(Message message) {
        SerializedMessage serializedMessage = new SerializedMessage(message);
        try {
            out.reset();
            out.writeObject(serializedMessage);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("\nMessage couldn't be sent...");
        }
    }

    /**
     * Method sends a message in output stream to socket.
     * @param command of type UserCommand.
     */
    public void send(UserCommand command){
        SerializedMessage serializedMessage = new SerializedMessage(command);
        try {
            out.reset();
            out.writeObject(serializedMessage);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Command couldn't be sent...");
        }
    }


}
