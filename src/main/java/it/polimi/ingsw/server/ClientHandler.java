package it.polimi.ingsw.server;

import it.polimi.ingsw.client.actions.ChoiceAssistantCard;
import it.polimi.ingsw.client.messages.SerializedMessage;
import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.client.messages.*;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

/**
 *
 * handles a connection between client and server
 *
 * @author andrea albergo
 */
public class ClientHandler implements Runnable {

    private final  Socket socket;
    private final MultiplayerServer server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Integer idClient;
    private boolean active;

    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * constructor
     *
     * @param socket Socket
     * @param server MultiplayerServer
     */
    public ClientHandler(Socket socket, MultiplayerServer server) {
        this.server = server;
        this.socket = socket;
        MultiplayerServer.LOGGER.info("A new client has connected from : " + socket.getInetAddress());
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            active = true;
            idClient = -1;
        } catch (IOException e) {
            System.err.println("ERROR INITIALIZING Client");
            System.err.println(e.getMessage());
        }

    }


    /**
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return Socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @return Integer
     */
    public Integer getIdClient() {
        return idClient;
    }

    /**
     * Listens what comes through the input stream and calls methods upon reception
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized void readFromStream() throws IOException, ClassNotFoundException{
        SerializedMessage input = (SerializedMessage) inputStream.readObject();
        System.out.println("\nSei nel ReadFromStream e ho letto: " + input.command + "\n");
        if (input.message != null){
            System.out.println("\nSei nel ReadFromStream e ho letto questo messaggio: " + input.message + "\n");
            Message command = input.message;
            commandLinker(command);
        }else if (input.command != null){
            System.out.println("\nSei nel ReadFromStream e ho letto il comando: " + input.command + "\n");
            UserCommand command = input.command;
            commandLinker(command);
        }
    }

    /**
     * Links a player command to a corrispondant method
     *
     * @param command User Command
     */
    public void commandLinker(UserCommand command) {
        Board game = server.getBoard().game();
        if (game.getCurrentPlayerIndex() != idClient){
            GameError message = new GameError(Errors.NOTYOURTURN,"It's not your turn...be patient");
            System.out.println(message.getMessage());
            server.getBoard().sendtoPlayer(message, idClient);
            return;
        }
        if(command instanceof ChoiceAssistantCard) {
            logger.log(Level.INFO, "Ho ricevuto la scelta da " + server.getIdNameMap().get(idClient) + ": " + ((ChoiceAssistantCard) command).card + ", La board è in fase " + server.getBoard().getPhase() );
            if (server.getBoard().getPhase() > 2 && game.isStartedRound()) {
                server.getBoard().sendtoPlayer(new GameError(
                                Errors.INVALIDINPUT,
                                "Not in correct game phase to perform this command!"),
                        idClient);
                return;
            }
            server.getBoard().makeAction(command, "RoundDecider");
        }else server.getBoard().makeAction(command,"turnController");
    }

    /**
     * Sets to active the connection
     *
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * used to link messages to correspondant methods
     *
     * @param command Message command
     */
    public void commandLinker(Message command) {
        if (command instanceof SetupConnection) {
            try{
            idClient = server.addClientToGame(((SetupConnection) command).getNickname(), this);
            System.out.println("STO REGISTRANDO IL "+ idClient + " CLIENT");
            if (idClient == null) {
                setActive(false);
                return;
            }
            server.lobby(this);
            }catch (InterruptedException e)
            {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        } else if (command instanceof ChooseMode) {
            server.getBoard().getController().setMode(((ChooseMode) command).getMode());
            server.setMode(((ChooseMode) command).getMode());
        }else if (command instanceof ChooseDetails){
            System.out.println("Ha selezionato la torre: " + ((ChooseDetails) command).getTower() + " e il mago: " + ((ChooseDetails) command).getWizard());
            if(Tower.isAlreadyPicked(((ChooseDetails) command).getTower()) || Wizard.isAlreadyPicked(((ChooseDetails) command).getWizard())){
                server.getIdtoClientMap().get(idClient).send(new SetDetails(server.getBoard().getNumberOfPlayers(), "This selection is invalid, check if one of the choices isn't already picked"));
                return;
            }
            server.getBoard().getController().setTower(((ChooseDetails) command).getTower(),idClient);
            server.getBoard().getController().setWizard(((ChooseDetails) command).getWizard(),idClient);
            Wizard.choose(((ChooseDetails) command).getWizard());
            Tower.choose(((ChooseDetails) command).getTower());
            System.out.println("Ho settato la scelta");
            String name = server.getIdNameMap().get(idClient);
            System.out.println(name);
            SetDetails message = new SetDetails(((ChooseDetails) command).getWizard(),((ChooseDetails) command).getTower(),name, server.getBoard().getNumberOfPlayers());
            server.getBoard().sendAll(message);
            System.out.println("Sto mandando che ha selezionato la torre: " + ((ChooseDetails) command).getTower() + " e il mago: " + ((ChooseDetails) command).getWizard());
            server.getBoard().sendtoPlayer(new SetDetails("Sono destinati alla view",((ChooseDetails) command).getWizard(),((ChooseDetails) command).getTower()),idClient);
            server.getBoard().setup();


        }else if( command instanceof QuitMessage){
            server.getBoard().sendAll(new CustomMessage(server.getIdNameMap().get(idClient) + " has quit the game, Game ending... ",false));
            server.getBoard().endGame(server.getIdNameMap().get(idClient));
            close();
        }
    }


    /**
     * used to send a message through the socket
     *
     * @param message SerializedAnswer
     */
    public void sendSocketMessage(SerializedAnswer message) {
        try{
            System.out.println("SENDSOCKET IN CLIENT HANDLER -> system answer: " + message.getAnswer().getMessage().toString());
            outputStream.reset();
            outputStream.writeObject(message);
            outputStream.flush();

        } catch (IOException e) {
            System.out.println("ERRORE SENDSOCKET");
            System.err.println(e.getMessage());
            e.printStackTrace();
            close();
        }
    }

    /**
     * closes connection
     */
    public void close(){
        server.removeClientFromGame(this.getIdClient());
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Sets the number of players
     * @param message SetPlayersRequest
     */
    public void setNumberofPlayers(SetPlayersRequest message){
        SerializedAnswer answer = new SerializedAnswer();
        answer.setSerializedAnswer(message);
        System.out.println("The prepared message is: " + answer.getAnswer().toString());
        sendSocketMessage(answer);
        while(true){
            try{
                SerializedMessage input = (SerializedMessage) inputStream.readObject();
                Message m = input.message;
                if( m instanceof NumberOfPlayers){
                    if ( ((NumberOfPlayers) m).NumberOfPlayers ==2 || ((NumberOfPlayers) m).NumberOfPlayers == 3)
                    {
                    server.getBoard().setNumberOfPlayers(((NumberOfPlayers) m).NumberOfPlayers);
                    server.setNumber_of_Players(((NumberOfPlayers) m).NumberOfPlayers);
                    server.getIdtoClientMap().get(idClient).send(new CustomMessage("The number of players was set to " + ((NumberOfPlayers) m).NumberOfPlayers,false));
                    break;
                    }else {
                        server.getIdtoClientMap().get(idClient).send(new GameError(Errors.DUPLICATENICKNAME,"The nickname is already chosen"));
                        server.getIdtoClientMap().get(idClient).send(new SetPlayersRequest("Choose a number of Players [2 or 3]"));
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                exit(-1);
            }

        }
    }


    /**
     * Runnable
     */
    @Override
    public void run() {
        try{
            while (isActive()) {
                readFromStream();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Sei nel run del CH");
            e.printStackTrace();
            BoardHandler board = server.getBoard();
            server.removeClientFromGame(idClient);
            if (board.isStarted()){
                board.endGame(server.getIdNameMap().get(idClient));
            }
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }


    public void setMode(SetMode message) {
        SerializedAnswer answer = new SerializedAnswer();
        answer.setSerializedAnswer(message);
        System.out.println("The prepared message is: " + answer.getAnswer().toString());
        sendSocketMessage(answer);
        while(true){
            try{
                SerializedMessage input = (SerializedMessage) inputStream.readObject();
                System.out.println("Dalla SetMode è arrivato questo...." + input);
                Message m = input.message;
                if( m instanceof ChooseMode)
                    {
                        server.getBoard().setExpertMode(((ChooseMode) m).getMode());
                        System.out.println("The mode was set to "+ ((ChooseMode) m).getMode() + " by " + server.getIdNameMap().get(0));
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }








}
