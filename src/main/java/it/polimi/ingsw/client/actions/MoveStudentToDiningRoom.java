package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.Color;

/**
 * MoveStudentToDiningRoom is used to create an action for the client in order to input the command of moving a student
 * from hall to dining room by given color chosen by the player.
 *
 * @author Andrea Albergo
 */
public class MoveStudentToDiningRoom implements UserCommand {
    private final Color color;

    /**
     * Method getColor is used to get the chosen color of the student to move to dining from hall.
     *
     * @return of type Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Constructor MoveStudentToDiningRoom to create its instance.
     *
     * @param color of type Color.
     */
    public MoveStudentToDiningRoom(Color color) {
        this.color = color;
    }

    /**
     * Method toString translates this method into String form, useful to handle actions in another client classes.
     *
     * @return of type String.
     */
    public String toString(){
        return "MoveStudentToDiningRoom";
    }
}
