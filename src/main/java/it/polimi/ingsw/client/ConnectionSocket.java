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
 */
public class ConnectionSocket {
    private final String serverip;
    private final int port;
    private Listener_FromServer listener;
    private ObjectOutputStream out;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());


    public ConnectionSocket() {
        this.serverip = Constants.getAddress();
        this.port = Constants.getPort();
    }

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

    public boolean registration1(String nickname, CommandHandler handler, ClientView view) throws DuplicateNicknameException {
        try{
            System.out.println("Hi, Opening a communication socket on Port: ");
            System.out.print(Constants.getPort());
            Socket socket;
            try {
                socket = new Socket(Constants.getAddress(), Constants.getPort());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while(true){
                try{
                    send(new SetupConnection(nickname));
                    SerializedAnswer answer = (SerializedAnswer) in.readObject();
                    LOGGER.log(Level.INFO, answer.getAnswer().toString());
                    if(answer.getAnswer() instanceof ConnectionMessage && ((ConnectionMessage)answer.getAnswer()).isCheck()){
                        System.out.println("\nRegistration (conn.socket): " + answer.getAnswer().getMessage());
                        break;
                    } else if (answer.getAnswer() instanceof GameError /*&& ((GameError)answer.getAnswer()).getError().equals(Errors.DUPLICATENICKNAME)*/) {
                        System.out.println("The nickname you chose is already in use, please pick another one");
                        throw new DuplicateNicknameException();
                    } else if (answer.getAnswer() instanceof GameError && ((GameError)answer.getAnswer()).getError().equals(Errors.SERVER_IS_FULL)) {
                        System.err.println("The server will not accept more players because is full");
                        System.exit(0);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            listener = new Listener_FromServer(socket,view,handler,in);
            Thread thread = new Thread(listener);
            thread.start();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Socket didn't start properly, application closing");
            System.exit(12);
            return false;
        }

    }

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

    private boolean nicknameChecker(Object readObject) throws DuplicateNicknameException{
        SerializedAnswer answer = (SerializedAnswer) readObject;
        LOGGER.log(Level.INFO, answer.getAnswer().getMessage().toString());
        if (answer.getAnswer() instanceof ConnectionMessage && ((ConnectionMessage) answer.getAnswer()).isCheck()) {
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

    private void send(UserCommand command){
        SerializedMessage serializedMessage = new SerializedMessage(command);
        try {
            out.reset();
            out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Command couldn't be sent...");
        }
    }


}
