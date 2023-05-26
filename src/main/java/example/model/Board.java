package example.model;

public class Board {
    private int width;
    private int height;
    private int[][] matrix;
    private Food food;
    private Snake snake;
    public Board(int width, int height, Snake snake, Food food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.snake = snake;
        this.matrix = new int[height][width];
//        Arrays.fill(this.matrix, 0);
    }

    public void clearSnakeFromBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.matrix[i][j] == 1) {
                    this.matrix[i][j] = 0;
                }
            }
        }
    }

    public void clearFoodFromBoard() {
        this.matrix[this.food.position[0]][this.food.position[1]] = 0;
    }

    public void addSnakeToBoard() {
        for (int[] bodyPartPosition : this.snake.getBodyPartPositions()) {
            this.matrix[bodyPartPosition[0]][bodyPartPosition[1]] = 1;
        }
    }

    public void addFoodToBoard() {
        this.food = new Food(this.height, this.width);
        int[] foodPosition = this.food.position;
        this.matrix[foodPosition[0]][foodPosition[1]] = 2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Food getFood() {
        return this.food;
    }
}
