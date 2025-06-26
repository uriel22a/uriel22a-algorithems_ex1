import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

public class ex3 {

    public static void main(String[] args) {
        Graph<Integer> graph1 = new Graph<>(true);

        graph1.addVertex(0);
        graph1.addVertex(1);
        graph1.addVertex(2);

        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 0);

        System.out.println("--- Testing Graph 1  ---");
        System.out.println("Is graph 1 strongly connected? " + isStronglyConnected(graph1));
        Graph<Integer> graph2 = new Graph<>(true);

        graph2.addVertex(0);
        graph2.addVertex(1);
        graph2.addVertex(2);

        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);

        System.out.println("\n--- Testing Graph 2 ---");
        System.out.println("Is graph 2 strongly connected? " + isStronglyConnected(graph2));

        Graph<Integer> graph3 = new Graph<>(true);

        graph3.addVertex(0);
        graph3.addVertex(1);
        graph3.addVertex(2);
        graph3.addVertex(3);

        graph3.addEdge(0, 1);
        graph3.addEdge(2, 3);

        System.out.println("\n--- Testing Graph 3  ---");
        System.out.println("Is graph 3 strongly connected? " + isStronglyConnected(graph3));

     Graph<Integer> graph4 = new Graph<>(true);

            graph4.addVertex(0);
            graph4.addVertex(1);
            graph4.addVertex(2);

            graph4.addEdge(0, 1);
            graph4.addEdge(2, 1);

            System.out.println("\n--- Testing Graph 4  ---");
            System.out.println("Is graph 4 strongly connected? " + isStronglyConnected(graph4));
        }

    public static <T> boolean isStronglyConnected(Graph<T> graph) {
        if (graph.getAllVertices().isEmpty()) {
            System.out.println("Graph is empty, considered strongly connected.");
            return true;
        }

        Collection<Vertex<T>> allVertices = graph.getAllVertices();
        int totalVertices = allVertices.size();
        System.out.println("Total vertices in graph: " + totalVertices);


        T arbitraryNodeData = allVertices.iterator().next().getData();
        System.out.println("Arbitrary starting node: " + arbitraryNodeData);


        Map<Vertex<T>, Integer> bfs1Distances = graph.bfs(arbitraryNodeData);
        System.out.println("BFS1 (from " + arbitraryNodeData + ") reached " + bfs1Distances.size() + " vertices.");

        if (bfs1Distances.size() != totalVertices) {
            System.out.println("  --> Graph is NOT strongly connected (BFS1 failed to reach all vertices).");
            return false;
        }

        Graph<T> transposedGraph = new Graph<>(true);

        for (Vertex<T> vertex : allVertices) {
            transposedGraph.addVertex(vertex.getData());
        }
        System.out.println("Transposed graph built with " + transposedGraph.getAllVertices().size() + " vertices.");


        for (Vertex<T> u : allVertices) {
            for (Vertex<T> v : u.getNeighbors()) {
                transposedGraph.addEdge(v.getData(), u.getData());
            }
        }
        System.out.println("Edges transposed.");


        Map<Vertex<T>, Integer> bfs2Distances = transposedGraph.bfs(arbitraryNodeData);
        System.out.println("BFS2 (from " + arbitraryNodeData + " on transposed graph) reached " + bfs2Distances.size() + " vertices.");

        if (bfs2Distances.size() != totalVertices) {
            System.out.println("  --> Graph is NOT strongly connected (BFS2 failed to reach all vertices).");
            return false;
        }

        System.out.println("  --> Graph IS strongly connected (both BFS traversals reached all vertices).");
        return true;
    }
}