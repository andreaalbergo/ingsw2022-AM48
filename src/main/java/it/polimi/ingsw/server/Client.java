package it.polimi.ingsw.server;

import it.polimi.ingsw.server.messages.Answer;
import it.polimi.ingsw.server.messages.LoseMessage;
import it.polimi.ingsw.server.messages.SerializedAnswer;

public class Client {
    private String nickname;
    private Integer idClient;
    private ClientHandler clientConnection;
    private BoardHandler board;

    public String getNickname(){
         return this.nickname;
    }

    public Client(Integer idClient, String nickname, ClientHandler clientConnection, BoardHandler board) {
        this.idClient = idClient;
        this.nickname = nickname;
        this.clientConnection = clientConnection;
        this.board = board;

    }

    public Integer getIdClient() {
        return idClient;
    }

    public ClientHandler getClientConnection() {
        return clientConnection;
    }

    public BoardHandler getBoard() {
        return board;
    }

    public Integer getId() {
        return this.idClient;
    }

    public boolean isConnected() {
        return true;
    }

    public void send(Answer serverAnswer) {
        SerializedAnswer message = new SerializedAnswer();
        message.setSerializedAnswer(serverAnswer);
        clientConnection.sendSocketMessage(message);
    }

    public void win(Answer win){
        SerializedAnswer winMessage = new SerializedAnswer();
        winMessage.setSerializedAnswer(win);
        clientConnection.sendSocketMessage(winMessage);
        board.sendAllExcept(new LoseMessage(nickname), idClient);
        board.endGame(idClient);
    }

}

