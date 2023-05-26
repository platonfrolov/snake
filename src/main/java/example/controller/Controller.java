package example.controller;

import example.model.Board;
import example.model.Direction;
import example.model.Food;
import example.model.Snake;
import example.view.GUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Controller {
    private Board board;
    private GUI view;
    private static final int MILLISPERFRAME = 100;
    public Controller(GUI view) {
        this.view = view;
        Snake snake = new Snake(GUI.BOARD_ROWS, GUI.BOARD_COLS);
        Food food = new Food(GUI.BOARD_ROWS, GUI.BOARD_COLS);
        this.board = new Board(GUI.BOARD_ROWS, GUI.BOARD_COLS, snake, food);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(MILLISPERFRAME), actionEvent -> run()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (code == KeyCode.LEFT) {
            this.board.getSnake().changeMovingDirection(Direction.LEFT);
        } else if (code == KeyCode.RIGHT) {
            this.board.getSnake().changeMovingDirection(Direction.RIGHT);
        } else if (code == KeyCode.DOWN) {
            this.board.getSnake().changeMovingDirection(Direction.DOWN);
        } else if (code == KeyCode.UP) {
            this.board.getSnake().changeMovingDirection(Direction.UP);
        }
    }


    private void run() {
        if (this.board.getSnake().getHeadPosition()[0] == this.board.getFood().getPosition()[0] &&
                this.board.getSnake().getHeadPosition()[1] == this.board.getFood().getPosition()[1]) {
            if (!this.board.getSnake().grow()) {
                this.view.endGame();
            } else {
                this.board.addFoodToBoard();
            }
        } else {
            if (!this.board.getSnake().move()) {
                this.view.endGame();
            }
        }
        if (this.board.getSnake().checkSelfCollision()) {
            this.view.endGame();
        }
        this.view.drawBackground();
        this.view.drawFood(this.board.getFood());
        this.view.drawSnake(this.board.getSnake());
    }


}