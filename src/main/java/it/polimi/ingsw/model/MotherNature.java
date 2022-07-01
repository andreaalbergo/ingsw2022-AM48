package it.polimi.ingsw.model;

/**
 * MotherNature is a class used to keep notice of the MotherNature pawn and its movements throughout the game
 *
 * @author Andrea Albergo
 */
public class MotherNature {
    private int position;


    private int[] numberofIslands;

    /**
     * MotherNature Constructor instantiates the islands in the game and keeps notice of their merge
     *
     */
    public MotherNature() {
        this.position = 0;
        numberofIslands = new int[12];
        for (int i = 0; i < 12; i++) {
            numberofIslands[i] = i;
        }
    }

    /**
     * Getter for the actual position of the pawn
     *
     * @return int
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the position of the pawn from a given int
     *
     * @param position
     */
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * Method move changes the position of mother nature moving through the islands. (Overloading for expert mode)
     *
     * @param steps of type int - represents the number of steps we want Mothernature to make.
     * @param effect of type int - the effect of character card.
     * @throws IllegalStateException in case the number of steps is different from the ones available from the card.
     */
    public void move(int steps, int effect){
        //if effect = 0 --> nothing change
        //if effect = 2 --> 2 more steps (herald characterCard)

        if (steps <= 5 + effect && steps >= 1)
        {
            if(this.position + steps > 11){
                this.position = (this.position + steps) - 12;
            }else
                this.position = getPosition() + steps;

        }else{
            throw new IllegalStateException("The steps selection is Invalid");
        }
    }

    /**
     * Method move changes the position of mother nature moving through the islands. (Overloading for expert mode)
     *
     * @param steps of type int - represents the number of steps we want Mothernature to make.
     * @throws IllegalStateException in case the number of steps is different from the ones available from the card.
     */
    public void move(int steps) throws IllegalStateException{
        //if effect = 0 --> nothing change
        //if effect = 2 --> 2 more steps (herald characterCard)

        if (steps <= 5 && steps >= 1)
        {
            for(int i = 0; i < steps; i++){
                if(position == 11 && numberofIslands[position] == numberofIslands[0])
                    position = 0;
                if(position == 11){
                    position = 0;
                    i++;
                }
                do{
                    position++;
                }while(numberofIslands[position] == numberofIslands[position+1]);
            }
        }else{
            throw new IllegalStateException("The steps selection is Invalid");
        }
    }

    public int getNumberofIslands() {
        return 12;
    }

    /**
     * Method setNewID is a setter.
     *
     * @param islandbefore of type int - the island with ID less than one from the current one.
     * @param merged of type int - index of island merged.
     */
    public void setNewID(int islandbefore, int merged) {
        this.numberofIslands[merged] = islandbefore;
    }
}

