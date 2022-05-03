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

    public int getColorIndex() {
        return colorIndex;
    }

    //MADE BY BARB:
    public static Color getRandomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }


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

