package it.polimi.ingsw.server;

import it.polimi.ingsw.server.servermessages.Answer;
import it.polimi.ingsw.server.servermessages.LoseMessage;
import it.polimi.ingsw.server.servermessages.SerializedAnswer;

/**
 * Client simulates a virtual client in the connection
 * phase waiting to be admitted into the game
 *
 * @author andrea albergo
 */
public class Client {
    private String nickname;
    private Integer idClient;
    private ClientHandler clientConnection;
    private BoardHandler board;

    /**
     * @param idClient Integer
     * @param nickname String
     * @param clientConnection ClientHandler
     * @param board BoardHandler
     */
    public Client(Integer idClient, String nickname, ClientHandler clientConnection, BoardHandler board) {
        this.idClient = idClient;
        this.nickname = nickname;
        this.clientConnection = clientConnection;
        this.board = board;

    }

    /**
     * @return String
     */
    public String getNickname(){
        return this.nickname;
    }

    /**
     * @return Integer
     */
    public Integer getIdClient() {
        return idClient;
    }


    /**
     * @return BoardHandler
     */
    public BoardHandler getBoard() {
        return board;
    }

    /**
     * @return Integer
     */
    public Integer getId() {
        return this.idClient;
    }

    /**
     * signals if the player is connected or not
     *
     * @return boolean
     */
    public boolean isConnected() {
        return clientConnection != null;
    }

    /**
     * sends message thorough the socket connection
     *
     * @param serverAnswer Answer
     */
    public void send(Answer serverAnswer) {
        SerializedAnswer message = new SerializedAnswer();
        message.setSerializedAnswer(serverAnswer);
        clientConnection.sendSocketMessage(message);
    }

}

