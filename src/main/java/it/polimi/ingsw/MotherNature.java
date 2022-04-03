package it.polimi.ingsw;

public class MotherNature {
    private int position;


    public MotherNature() {
        this.position = 1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int steps) throws IllegalStateException{
        if (steps <= 5 && steps >= 1)
        {
            this.position = getPosition() + steps;
        }else{
            throw new IllegalStateException("The steps selection is Invalid");
        }
    }
}
