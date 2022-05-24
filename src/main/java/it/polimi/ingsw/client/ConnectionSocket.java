package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.client.messages.Message;
import it.polimi.ingsw.client.messages.SerializedMessage;
import it.polimi.ingsw.client.messages.SetupConnection;
import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.server.messages.ConnectionMessage;
import it.polimi.ingsw.server.messages.Errors;
import it.polimi.ingsw.server.messages.GameError;
import it.polimi.ingsw.server.messages.SerializedAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionSocket {

    private final String serverip;
    private final int port;

    private Listener_FromServer listener;

    private ObjectOutputStream out;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public ConnectionSocket(String serverip, int port) {
        this.serverip = serverip;
        this.port = port;
    }

    public ConnectionSocket() {
        this.serverip = Constants.getAddress();
        this.port = Constants.getPort();
    }


    public boolean registration(String nickname, CommandHandler handler, ClientView view){
        try{
            System.out.println("Hi, Opening a communication socket on Port: ");
            System.out.print(Constants.getPort());
            Socket socket;
            try {
                socket = new Socket(serverip, port);

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
                    if(answer.getAnswer() instanceof ConnectionMessage && ((ConnectionMessage)answer.getAnswer()).isCheck()){
                        break;
                    } else if (answer.getAnswer() instanceof GameError && ((GameError)answer.getAnswer()).getError().equals(Errors.DUPLICATENICKNAME)) {
                        System.err.println("The nickname you chose is already in use, please pick another one");
                        //potremmo creare delle eccezioni...
                    } else if (answer.getAnswer() instanceof GameError && ((GameError)answer.getAnswer()).getError().equals(Errors.SERVER_IS_FULL)) {
                        System.err.println("The server will not accept more players because is full");
                        System.exit(0);
                        //potremmo creare delle eccezioni...
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
            System.exit(0);
            return false;
        }

    }

    private void send(Message message) {
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
