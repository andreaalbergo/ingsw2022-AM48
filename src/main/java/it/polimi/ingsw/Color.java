package it.polimi.ingsw;

public enum Color {
    YELLOW_GNOMES(1), BLUE_UNICORNS(2), GREEN_FROGS(3), RED_DRAGONS(4),PINK_FAIRIES(5);

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
