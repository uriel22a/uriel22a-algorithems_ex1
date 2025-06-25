import java.util.*;

public class Vertex<T> {
    private final T data;
    private final List<Vertex<T>> neighbors;

    public Vertex(T data) {
        this.data = data;
        this.neighbors = new ArrayList<>();
    }
    public T getData() {
        return data;
    }
    public List<Vertex<T>> getNeighbors() {
        return neighbors;
    }
    public void addNeighbor(Vertex<T> neighbor) {
        if (!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
        }
    }
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
