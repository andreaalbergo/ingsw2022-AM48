package it.polimi.ingsw;

public class MotherNature {
    private int position;

    public MotherNature() {
        this.position = 1;
    }

    public int getPosition() {
        return position;
    }

    public void move(int steps) throws IllegalStateException{
        if (steps <= 5 && steps >= 1)
        {
            if(this.position + steps > 12){
                this.position = (this.position + steps) -12;
            }else
                this.position = getPosition() + steps;

        }else{
            throw new IllegalStateException("The steps selection is Invalid");
        }
    }

}

