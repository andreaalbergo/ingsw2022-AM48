package it.polimi.ingsw.model;

public class MotherNature {
    private int position;


    private int[] numberofIslands;

    public MotherNature() {
        this.position = 0;
        numberofIslands = new int[12];
        for (int i = 0; i < 12; i++) {
            numberofIslands[i] = i;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    /**
     * @param steps INTEGER -> represents the number of steps we want Mothernature to make
     * @throws IllegalStateException in case the number of steps is different from the ones available from the card

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

    public void setNewID(int islandbefore, int merged) {
        this.numberofIslands[merged] = islandbefore;
    }
}

