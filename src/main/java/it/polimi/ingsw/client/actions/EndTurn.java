package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.Cloud;

/**
 * EndTurn class is used to create an action of end turn when the active player (client) chooses a cloud. After that or
 * a new player is set active or a new game round is starting.
 *
 * @author Andrea Albergo
 */
public class EndTurn implements UserCommand{
    private final Cloud cloud;
    private final boolean lastPlayer;

    /**
     * Constructor EndTurn creates its instance given a single cloud. It means that another player chose a cloud and has
     * ended its turn.
     *
     * @param cloud of type Cloud.
     */
    public EndTurn(Cloud cloud) {
        this.cloud = cloud;
        this.lastPlayer = false;
    }

    /**
     * Constructor EndTurn creates its instance given a single cloud and a boolean input called enabler which makes the
     * game and the client aware of the ending of the game's round when the last player of the turn chose a cloud.
     *
     * @param cloud of type Cloud.
     * @param enabler of type boolean.
     */
    public EndTurn(Cloud cloud, boolean enabler){
        this.cloud = cloud;
        this.lastPlayer = enabler;
    }

    /**
     * Method isLastPlayer is a getter.
     *
     * @return of type boolean - the last player of the current game round.
     */
    public boolean isLastPlayer() {
        return lastPlayer;
    }

    /**
     * Method getCloud is a getter.
     *
     * @return of type Cloud - the chosen cloud.
     */
    public Cloud getCloud() {
        return cloud;
    }

    /**
     * Method toString translates this method into String form, useful to handle actions in another client classes.
     *
     * @return of type String.
     */
    public String toString(){
        return "EndTurn";
    }
}
