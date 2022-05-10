package it.polimi.ingsw;

import java.util.List;
import java.util.Random;

public enum Color {
    YELLOW_GNOMES(0), BLUE_UNICORNS(1), GREEN_FROGS(2),
    RED_DRAGONS(3),PINK_FAIRIES(4);

    private final int colorIndex;
    //MADE BY BARB, I NEED TO GET A RANDOM COLOR:
    private static final List<Color> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    //MADE BY BARB, SKIP TO LINE 25

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
     * Utility Method used to pick a Random Color form the values of the enum
     *
     * @return one of the Color values in the enum
     */
    //MADE BY BARB:
    public static Color getRandomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE));
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

