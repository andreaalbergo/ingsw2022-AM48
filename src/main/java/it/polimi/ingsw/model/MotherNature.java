package it.polimi.ingsw.model;

public class MotherNature {
    private int position;

    public MotherNature() {
        this.position = 0;
    }

    public int getPosition() {
        return position;
    }

    /**
     * @param steps INTEGER -> represents the number of steps we want Mothernature to make
     * @throws IllegalStateException in case the number of steps is different from the ones available from the card
     */
    public void move(int steps, int effect) throws IllegalStateException{
        //if effect = 0 --> nothing change
        //if effect = 2 --> 2 more steps (herald characterCard)

        if (steps <= (5 + effect) && steps >= 1)
        {
            if(this.position + steps > 11){
                this.position = (this.position+steps)-12;
            }else
                this.position = getPosition() + steps;

        }else{
            throw new IllegalStateException("The steps selection is Invalid");
        }
    }

}

