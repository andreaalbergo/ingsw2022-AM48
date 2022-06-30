package it.polimi.ingsw.model;

public class MotherNature {
    private int position;

    private int numberofIslands;

    public MotherNature() {
        this.position = 0;
        numberofIslands = 12;
    }

    public int getPosition() {
        return position;
    }

    /**
     * @param steps INTEGER -> represents the number of steps we want Mothernature to make
     * @throws IllegalStateException in case the number of steps is different from the ones available from the card

     */
    public void move(int steps, int effect){

    }


    public void setPosition(int position){
        this.position = position;
    }
    public void move(int steps) throws IllegalStateException{
        //if effect = 0 --> nothing change
        //if effect = 2 --> 2 more steps (herald characterCard)

        if (steps <= 5 && steps >= 1)
        {
            if(this.position + steps > numberofIslands - 1){
                this.position = (this.position + steps) - numberofIslands;
            }else
                this.position = getPosition() + steps;

        }else{
            throw new IllegalStateException("The steps selection is Invalid");
        }
    }

    public int getNumberofIslands() {
        return numberofIslands;
    }

    public void setNumberofIslands(int numberofIslands) {
        this.numberofIslands = numberofIslands;
    }
}

