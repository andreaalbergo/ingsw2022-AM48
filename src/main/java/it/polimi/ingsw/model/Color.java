package it.polimi.ingsw.model;

/**
 * Color enumeration that...
 *
 * @author Andrea
 */
public enum Color {
    GREEN_FROGS(0), RED_DRAGONS(1), YELLOW_GNOMES(2),
    PINK_FAIRIES(3),BLUE_UNICORNS(4);

    private final int colorIndex;

    Color(int colorIndex){
        this.colorIndex = colorIndex;
    }


    /**
     * Utility method that helps us manage the index knowing the color
     *
     * @return the index of one Color
     */
    public int getColorIndex() {
        return colorIndex;
    }

    /**
     * Utility method to associate the Color, given a number that represents one of the colors' index.
     *
     * @param colorIndex is an integer from which I want do derive the respective Color
     * @return the Color that has colorIndex parameter as its own index
     */
    public static Color colorFromIndex(int colorIndex){
        Color toBeReturned = null;
        for(Color c: Color.values()){
            if(colorIndex == c.getColorIndex()){
                toBeReturned = c;
            }
        }
        return toBeReturned;
    }
}

