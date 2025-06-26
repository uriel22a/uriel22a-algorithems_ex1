import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class Board {
    private final int rows,cols;
    private final Cell[][] cells;
    private final double obstacleProbability = 0.20;

    public Board(int size) {
        this.rows = size;
        this.cols = size;
        this.cells = new Cell[size][size];
        initializeBoard();
    }

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        initializeBoard();
    }
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean walkable = ThreadLocalRandom.current().nextDouble() > obstacleProbability;
                cells[i][j] = new Cell(i, j, walkable);
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return null;
        return cells[row][col];
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public double getObstacleProbability() {
        return obstacleProbability;
    }
    public List<Cell> getAllWalkableCells() {
        List<Cell> result = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (cells[i][j].isWalkable())
                    result.add(cells[i][j]);
        return result;
    }
}