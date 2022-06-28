package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.CommandHandler;
import it.polimi.ingsw.client.ConnectionSocket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GUI extends Application implements PropertyChangeListener {

    private final ClientView clientView;
    private final CommandHandler handler;
    private boolean active;
    private final PropertyChangeSupport listener = new PropertyChangeSupport(this);
    private ConnectionSocket connectionSocket = null;
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader test = new FXMLLoader(getClass().getResource("/Fxml_files/Game/GameMenu.fxml"));
            Parent root = test.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    //in newCommandHandler, this is set to cli, not GUI
    public GUI(){

        clientView = new ClientView(this);
        handler = new CommandHandler(clientView,this);
        active = true;

    }


    @Override
    public void stop(){
        System.exit(0);
    }

    public PropertyChangeSupport getListener() {
        return listener;
    }

    public ConnectionSocket getConnectionSocket() {
        return connectionSocket;
    }

    public void setConnectionSocket(ConnectionSocket connectionSocket){
        if(this.connectionSocket == null){
            this.connectionSocket = connectionSocket;
        }
    }

    public ClientView getModelView() {
        return clientView;
    }

    public CommandHandler getCommandHandler(){
        return handler;
    }

    private void newPlayer(){
        Platform.runLater(() -> {
            //<GUI_HOMEPAGE_PLAYER> controller = (<GUI_HOMEPAGE_PLAYER>) getControllerFromUsername()
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}


