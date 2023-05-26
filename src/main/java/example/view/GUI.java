package example.view;

import example.controller.Controller;
import example.model.Food;
import example.model.Snake;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GUI extends Application {
    public static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = PANEL_WIDTH;
    public static final int BOARD_ROWS = 20;
    public static final int BOARD_COLS = BOARD_ROWS;
    private static final int CELL_SIZE = PANEL_WIDTH / BOARD_COLS;
    private GraphicsContext gc;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SNAKE");
        Group root = new Group();
        Canvas canvas = new Canvas(PANEL_WIDTH, PANEL_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.gc = canvas.getGraphicsContext2D();
        Controller c = new Controller(this);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                c.handleKeyEvent(keyEvent);
            }
        });
    }

    public void endGame() {
        System.exit(0);
    }

    public void drawBackground() {
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

    public void drawFood(Food food) {
        int[] foodPosition = food.getPosition();
        this.gc.setFill(Color.web("000000"));
        this.gc.fillRect(foodPosition[1]*CELL_SIZE, foodPosition[0]*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawSnake(Snake snake) {
        for (int[] bodyPosition : snake.getBodyPartPositions()) {
            this.gc.setFill(Color.web("999999"));
            this.gc.fillRect(bodyPosition[1]*CELL_SIZE, bodyPosition[0]*CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}