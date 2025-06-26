public class Robot {
    private final int id;
    private Cell currentCell;

    public Robot(int id, Cell initialCell) {
        this.id = id;
        this.currentCell = initialCell;
    }

    public int getId() {
        return id;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    @Override
    public String toString() {
        return "Robot(" + id + " at " + currentCell + ")";
    }
}