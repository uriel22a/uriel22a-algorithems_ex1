import java.util.*;
public class Cell {
    private final int row;
    private final int col;
    private final boolean walkable;

    public Cell(int row, int col, boolean walkable) {
        this.row = row;
        this.col = col;
        this.walkable = walkable;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean isWalkable() { return walkable; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) return false;
        Cell other = (Cell) o;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
