package example.view;

import example.model.Board;
import example.model.Direction;
import example.model.Food;
import example.model.Snake;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;


public class GUI extends Application {
    Board board;
    boolean gameOver = false;
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = PANEL_WIDTH;
    private static final int BOARD_ROWS = 20;
    private static final int BOARD_COLS = BOARD_ROWS;
    private static final int CELL_SIZE = PANEL_WIDTH / BOARD_COLS;
    private GraphicsContext gc;
    @Override
    public void start(Stage primaryStage) {
        Snake snake = new Snake(BOARD_ROWS, BOARD_COLS);
        Food food = new Food(BOARD_ROWS, BOARD_COLS);
        this.board = new Board(BOARD_ROWS, BOARD_COLS, snake, food);

        primaryStage.setTitle("SNAKE");
        Group root = new Group();
        Canvas canvas = new Canvas(PANEL_WIDTH, PANEL_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.LEFT) {
                    board.getSnake().changeMovingDirection(Direction.LEFT);
                } else if (code == KeyCode.RIGHT) {
                    board.getSnake().changeMovingDirection(Direction.RIGHT);
                } else if (code == KeyCode.DOWN) {
                    board.getSnake().changeMovingDirection(Direction.DOWN);
                } else if (code == KeyCode.UP) {
                    board.getSnake().changeMovingDirection(Direction.UP);
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        this.gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> run()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run() {
        if (this.board.getSnake().getHeadPosition()[0] == this.board.getFood().getPosition()[0] &&
                this.board.getSnake().getHeadPosition()[1] == this.board.getFood().getPosition()[1]) {
            if (!this.board.getSnake().grow()) {
                endGame();
            } else {
                this.board.addFoodToBoard();
            }
        } else {
            if (!this.board.getSnake().move()) {
                endGame();
            }
        }
        if (this.board.getSnake().checkSelfCollision()) {
            endGame();
        }
        drawBackground();
        drawFood();
        drawSnake();
    }

    private void endGame() {
        System.exit(0);
    }

    private void drawBackground() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                if ((i+j) % 2 == 0) {
                    this.gc.setFill(Color.web("AAD751"));
                } else {
                    this.gc.setFill(Color.web("A2d149"));
                }
                this.gc.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawFood() {
        int[] foodPosition = this.board.getFood().getPosition();
        this.gc.setFill(Color.web("000000"));
        this.gc.fillRect(foodPosition[1]*CELL_SIZE, foodPosition[0]*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void drawSnake() {
        for (int[] bodyPosition : this.board.getSnake().getBodyPartPositions()) {
            this.gc.setFill(Color.web("999999"));
            this.gc.fillRect(bodyPosition[1]*CELL_SIZE, bodyPosition[0]*CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}