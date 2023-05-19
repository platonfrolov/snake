package example.model;

import java.util.Random;

public class Food {
    int[] position;
    public Food(int boardHeightBound, int boardWidthBound) {
        this.position = new int[]{new Random().nextInt(boardHeightBound), new Random().nextInt(boardWidthBound)};
    }

    public int[] getPosition() {
        return this.position;
    }
}
