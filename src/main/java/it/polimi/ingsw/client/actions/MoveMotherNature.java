package it.polimi.ingsw.client.actions;

/**
 * MoveMotherNature class is used to create an action for the client in order to input the command of moving mother
 * nature by given max steps after choosing the assistant card at the beginning of the turn.
 *
 * @author Andrea Albergo
 */
public class MoveMotherNature implements UserCommand {

    private final int number_of_steps;

    /**
     * Constructor MoveMotherNature to create its instance.
     *
     * @param in of type String[] - in[0] is "MOVEMOTHERNATURE" and in[1] is the number of steps.
     */
    public MoveMotherNature(String[] in) {
        this.number_of_steps = Integer.parseInt(in[1]);
    }

    /**
     * Method getNumber_of_steps is used to get the number of chosen steps.
     *
     * @return of type int.
     */
    public int getNumber_of_steps() {
        return number_of_steps;
    }

    /**
     * Method toString translates this method into String form, useful to handle actions in another client classes.
     *
     * @return of type String.
     */
    public String toString(){
        return "MoveMotherNature";
    }
}
