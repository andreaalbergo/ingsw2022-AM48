package it.polimi.ingsw.server;

import it.polimi.ingsw.client.messages.SerializedMessage;
import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.client.messages.*;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.System.exit;

public class ClientHandler implements Runnable {

    private final  Socket socket;
    private final MultiplayerServer server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Integer idClient;
    private boolean active;

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

    public void sendSocketMessage(CustomMessage message) {
        try {
            outputStream.reset();
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            close();
        }

    }

    public boolean isActive() {
        return active;
    }
    public Socket getSocket() {
        return socket;
    }
    public Integer getIdClient() {
        return idClient;
    }

    public synchronized void readFromStream() throws IOException, ClassNotFoundException{
        SerializedMessage input = (SerializedMessage) inputStream.readObject();
        if ( input.message != null){
            System.out.println("\nSei nel ReadFromStream e ho letto questo messaggio: " + input.message + "\n");
            Message command = input.message;
            commandLinker(command);
        }else if (input.command!= null){
            System.out.println("\nSei nel ReadFromStream e ho letto: " + input.command + "\n");
            UserCommand command = input.command;
            commandLinker(command);
        }
    }

    public void commandLinker(UserCommand command) {

        //ancora da implementare

    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
            server.getBoard().getController();//.Setmode
            server.setMode(((ChooseMode) command).getMode());
        }else if (command instanceof ChooseWizard){
            if(Wizard.notChosen(((ChooseWizard) command).getWizard())){
                server.getBoard().getController().setWizard(((ChooseWizard)command).getWizard(), idClient);//Bisogna aggiungere in GameController;
                server.getIdtoClientMap().get(idClient).send(new CustomMessage(server.getIdtoClientMap().get(idClient).getNickname() + ": You chose " + ((ChooseWizard)command).getWizard()));
                Wizard.choose(((ChooseWizard) command).getWizard());
            }else
            {
                server.getIdtoClientMap().get(idClient).send(new GameError( Errors.ALREADYCHOSEN , "The Wizard you chose is already taken, choose one of these: " + Wizard.getAvailable()));
            }
        }else if (command instanceof ChooseTowerColor){
            if(Tower.available().contains(((ChooseTowerColor) command).getTower())){
                server.getBoard().getController().setTower(((ChooseTowerColor) command).getTower(),idClient);
                server.getIdtoClientMap().get(idClient).send(new CustomMessage(server.getIdtoClientMap().get(idClient).getNickname() + " :You chose  " + ((ChooseTowerColor)command).getTower().toString() ));
                Tower.choose(((ChooseTowerColor)command).getTower());
            }else{
                server.getIdtoClientMap().get(idClient).send(new GameError(Errors.ALREADYCHOSEN,"The Wizard you chose is already taken, choose one of these: " + Tower.available));
            }

        }else if( command instanceof QuitMessage){
            server.getBoard().sendAll(new CustomMessage(server.getIdNameMap().get(idClient) + " has quit the game, Game ending... "));
            server.getBoard().endGame(server.getIdNameMap().get(idClient));
            close();
        }
    }


    public void sendSocketMessage(SerializedAnswer message) {
        try{
            System.out.println("SENDSOCKET IN CLIENT HANDLER -> system answer: " + message.getAnswer().getMessage().toString());
            outputStream.reset();
            outputStream.writeObject(message);
            outputStream.flush();

        } catch (IOException e) {
            close();
        }
    }

    public void close(){
        server.removeClientFromGame(this.getIdClient());
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


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
                    server.getIdtoClientMap().get(idClient).send(new CustomMessage("The number of players was set to " + ((NumberOfPlayers) m).NumberOfPlayers));
                    break;
                    }else {
                        server.getIdtoClientMap().get(idClient).send(new GameError(Errors.DUPLICATENICKNAME));
                        server.getIdtoClientMap().get(idClient).send(new SetPlayersRequest("Choose a number of Players [2 or 3]"));
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                exit(-1);
            }

        }
    }



    @Override
    public void run() {
        try{
            while (isActive()) {
                readFromStream();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            BoardHandler board = server.getBoard();
            server.removeClientFromGame(idClient);
            if (board.isStarted()){
                board.endGame(server.getIdNameMap().get(idClient));
            }
            System.err.println(e.getMessage());
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
