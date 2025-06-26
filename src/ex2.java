import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ex2 {

    public static void main(String[] args) {
        Graph<Integer> tree = new Graph<>(false);

        for (int i = 0; i < 5; i++) {
            tree.addVertex(i);
        }

        tree.addEdge(0, 1);
        tree.addEdge(1, 2);
        tree.addEdge(2, 3);
        tree.addEdge(3, 4);

        System.out.println("Tree built with vertices: " + tree.getAllVertices().size());

        Integer startNodeData = 0;
        Map<Vertex<Integer>, Integer> distancesFromS = tree.bfs(startNodeData);

        Vertex<Integer> nodeX = null;
        int maxDistFromS = -1;

        for (Map.Entry<Vertex<Integer>, Integer> entry : distancesFromS.entrySet()) {
            if (entry.getValue() > maxDistFromS) {
                maxDistFromS = entry.getValue();
                nodeX = entry.getKey();
            }
        }

        if (nodeX == null) {
            System.out.println("No reachable nodes from starting point. Tree might be empty or disconnected.");
            return;
        }

        System.out.println("Node X (farthest from " + startNodeData + "): " + nodeX.getData() + " with distance " + maxDistFromS);

        Map<Vertex<Integer>, Integer> distancesFromX = tree.bfs(nodeX.getData());

        int diameter = 0;
        for (Map.Entry<Vertex<Integer>, Integer> entry : distancesFromX.entrySet()) {
            if (entry.getValue() > diameter) {
                diameter = entry.getValue();
            }
        }

        System.out.println("The diameter of the tree is: " + diameter);

        System.out.println("\n--- Testing with a different tree structure ---");
        Graph<Integer> complexTree = new Graph<>(false);
        for(int i = 0; i < 9; i++) complexTree.addVertex(i);

        complexTree.addEdge(0, 1);
        complexTree.addEdge(1, 2);
        complexTree.addEdge(2, 3);
        complexTree.addEdge(3, 4);
        complexTree.addEdge(1, 5);
        complexTree.addEdge(5, 6);
        complexTree.addEdge(6, 7);
        complexTree.addEdge(6, 8);
        System.out.println("Complex tree built with vertices: " + complexTree.getAllVertices().size());

        startNodeData = 0;
        distancesFromS = complexTree.bfs(startNodeData);

        nodeX = null;
        maxDistFromS = -1;
        for (Map.Entry<Vertex<Integer>, Integer> entry : distancesFromS.entrySet()) {
            if (entry.getValue() > maxDistFromS) {
                maxDistFromS = entry.getValue();
                nodeX = entry.getKey();
            }
        }

        if (nodeX == null) {
            System.out.println("No reachable nodes from starting point for complex tree.");
            return;
        }

        System.out.println("Node X (farthest from " + startNodeData + "): " + nodeX.getData() + " with distance " + maxDistFromS);

        distancesFromX = complexTree.bfs(nodeX.getData());

        diameter = 0;
        for (Map.Entry<Vertex<Integer>, Integer> entry : distancesFromX.entrySet()) {
            if (entry.getValue() > diameter) {
                diameter = entry.getValue();
            }
        }

        System.out.println("The diameter of the complex tree is: " + diameter);
    }
}