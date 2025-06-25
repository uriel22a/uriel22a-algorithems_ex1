import java.util.*;

public class Graph<T> {
    private final Map<T, Vertex<T>> vertices = new HashMap<>();
    private final boolean directed;

     public Graph(boolean directed) {
        this.directed = directed;
    }


    public Vertex<T> addVertex(T data) {
        return vertices.computeIfAbsent(data, d -> new Vertex<>(d));
    }

    public void addEdge(T from, T to) {
        Vertex<T> vFrom = addVertex(from);
        Vertex<T> vTo   = addVertex(to);
        vFrom.addNeighbor(vTo);
        if (!directed) {
            vTo.addNeighbor(vFrom);
        }
    }


    public Collection<Vertex<T>> getAllVertices() {
        return vertices.values();
    }


    public Map<Vertex<T>, Integer> bfs(T startData) {
        Vertex<T> start = vertices.get(startData);
        if (start == null) {
            throw new IllegalArgumentException("Start vertex not found: " + startData);
        }

        Map<Vertex<T>, Integer> dist = new HashMap<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        dist.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            int d = dist.get(u);
            for (Vertex<T> nbr : u.getNeighbors()) {
                if (!dist.containsKey(nbr)) {
                    dist.put(nbr, d + 1);
                    queue.add(nbr);
                }
            }
        }
        return dist;
    }
}
