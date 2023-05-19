package example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    private int length;
    private List<int[]> bodyPartPositions;
    private int[] headPosition;
    private Direction movingDirection;
    private int boardHeightBound;
    private int boardWidthBound;
    public Snake(int boardHeightBound, int boardWidthBound) {
        this.boardHeightBound = boardHeightBound;
        this.boardWidthBound = boardWidthBound;
        this.bodyPartPositions = new ArrayList();
        this.headPosition = new int[]{new Random().nextInt(this.boardHeightBound), new Random().nextInt(this.boardWidthBound)};
        this.bodyPartPositions.add(this.headPosition);
        this.movingDirection = null;
        this.length = 1;
    }

    public boolean move() {
        int[] oldPosition = this.headPosition.clone();
        int[] newHeadPosition = oldPosition.clone();
        if (this.movingDirection == Direction.UP && newHeadPosition[0] - 1 >= 0) {
            newHeadPosition[0]--;
        } else if (this.movingDirection == Direction.RIGHT && newHeadPosition[1] + 1 <= this.boardWidthBound - 1) {
            newHeadPosition[1]++;
        } else if (this.movingDirection == Direction.DOWN && newHeadPosition[0] + 1 <= this.boardHeightBound - 1) {
            newHeadPosition[0]++;
        } else if (this.movingDirection == Direction.LEFT && newHeadPosition[1] - 1 >= 0) {
            newHeadPosition[1]--;
        } else if (this.movingDirection == null){
            return true;
        } else{
            return false;
        }
        this.bodyPartPositions.set(0, newHeadPosition);
        this.headPosition = newHeadPosition;
        for (int i = 1; i < this.length; i++) {
            int[] temp = this.bodyPartPositions.get(i).clone();
            this.bodyPartPositions.set(i, oldPosition);
            oldPosition = temp;
        }
        return true;
    }

    public boolean grow() {
        System.out.println(this);
        int[] tailPosition = this.bodyPartPositions.get(this.bodyPartPositions.size() - 1).clone();
        boolean success = this.move();
        System.out.println(this);
        System.out.println(tailPosition[0] + " " + tailPosition[1]);
        this.bodyPartPositions.add(tailPosition);
        this.length++;
        return success;
    }

    public boolean checkSelfCollision() {
        for (int i = 1; i < this.length; i++) {
            if (this.headPosition[0] == this.bodyPartPositions.get(i)[0] &&
                    this.headPosition[1] == this.bodyPartPositions.get(i)[1]) {
                return true;
            }
        }
        return false;
    }


    public List<int[]> getBodyPartPositions() {
        return this.bodyPartPositions;
    }

    public int[] getHeadPosition() {
        return this.headPosition;
    }

    public void changeMovingDirection(Direction direction) {
        if (direction == Direction.UP && this.movingDirection == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.movingDirection == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.movingDirection == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.movingDirection == Direction.UP) {
            return;
        }
        this.movingDirection = direction;
    }

    @Override
    public String toString() {
        String result  = "";
        for (int[] bodyPosition : this.bodyPartPositions) {
            result = result + bodyPosition[0] + " " + bodyPosition[1] + ", ";
        }
        return result;
    }
}
