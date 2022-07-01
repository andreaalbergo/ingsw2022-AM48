package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;

/**
 * Class ChooseDetails is a message sent to the server socket from every single client with the chosen
 * available towers and wizards.
 */
public class ChooseDetails implements Message{
    private final Tower tower;
    private final Wizard wizard;

    /**
     * Constructor ChooseDetails creates its instance.
     *
     * @param tower of type Tower - the chosen tower.
     * @param wizard of type Wizard - the chosen wizard.
     */
    public ChooseDetails(Tower tower, Wizard wizard) {
        this.tower = tower;
        this.wizard = wizard;
    }

    /**
     * Method getTower is a getter.
     *
     * @return of type Tower.
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Method getWizard is a getter.
     *
     * @return of type Wizard.
     */
    public Wizard getWizard() {
        return wizard;
    }
}
