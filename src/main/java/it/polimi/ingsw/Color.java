package it.polimi.ingsw;

public enum Color {
    YELLOW_GNOMES(0), BLUE_UNICORNS(1), GREEN_FROGS(2), RED_DRAGONS(3),PINK_FAIRIES(4);

    private int index;

    Color(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /*

    public Color colorFromIndex(int index){
        Color toBeReturned = null;
        for(Color c: Color.values()){
            if(index == c.getIndex()){
                toBeReturned = c;
            }
        }
        return toBeReturned;
    }


     */
}
