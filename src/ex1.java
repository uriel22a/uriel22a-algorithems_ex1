import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ex1 {
    public static void main(String[] args) {
        int gridRows = 5;
        int gridCols = 5;
        int k = 2; // the robots number
        //black tiles ratio in Board class

        List<Robot> robots = new ArrayList<>();
        Board board = new Board(gridRows, gridCols);
        List<Cell> allWalkableCells = new ArrayList<>(board.getAllWalkableCells());
        Collections.shuffle(allWalkableCells);


        Graph<Cell> graph = new Graph<>(false);

        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        for (int row = 0; row < gridRows; row++) {
            for (int colum = 0; colum < gridCols; colum++) {
                Cell cell = board.getCell(row, colum);
                if (cell != null && cell.isWalkable()) {
                    graph.addVertex(cell);
                }
            }
        }

        for (int row = 0; row < gridRows; row++) {
            for (int colum = 0; colum < gridCols; colum++) {
                Cell currentCell = board.getCell(row, colum);
                if (currentCell != null && currentCell.isWalkable()) {
                    for (int[] dir : directions) {
                        int neighborR = row + dir[0];
                        int neighborC = colum + dir[1];

                        Cell neighborCell = board.getCell(neighborR, neighborC);
                        if (neighborCell != null && neighborCell.isWalkable()) {
                            graph.addEdge(currentCell, neighborCell);
                        }
                    }
                }
            }
        }
        Cell robot_pos[] = new Cell[k];
        int robot_index=0;
        for (int i = 0; i < k; i++) {
            if (i < allWalkableCells.size()) {
                robots.add(new Robot(i + 1, allWalkableCells.get(i)));
                robot_pos[robot_index]=robots.get(robot_index).getCurrentCell();
                robot_index++;
            } else {
                System.out.println("Not enough unique walkable cells to place " + k + " robots. Placed " + i + " robots.");
                break;
            }
        }

        try {
            boolean isRobot = false;
            System.out.println("B -black tile");
            System.out.println(" W - white tile");
            System.out.println(" R - robot");
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCols(); j++) {
                    for (int r = 0; r < k; r++) {
                        if (robot_pos[r].getRow() == i && robot_pos[r].getCol() == j) {
                            System.out.print("R ");
                            isRobot = true;
                        }
                    }

                    if (!isRobot) {
                        if (board.getCell(i, j).isWalkable()) {
                            System.out.print(("W "));
                        } else {
                            System.out.print("B ");
                        }
                    }
                    isRobot = false;

                }
                System.out.println();
            }
        }catch (Exception e){
            System.out.println("not enouth walkable tiles"); }


        if (robots.isEmpty()) {
            System.out.println("No robots placed on valid cells. Exiting.");
            return;
        }

        System.out.println("Robots initialized: " + robots);

        List<Map<Vertex<Cell>, Integer>> robotBfsResults = new ArrayList<>();

        for (Robot robot : robots) {
            Map<Vertex<Cell>, Integer> distances = graph.bfs(robot.getCurrentCell());
            robotBfsResults.add(distances);
        }

        long minTotalSteps = Long.MAX_VALUE;
        Cell optimalTargetCell = null;

        for (Vertex<Cell> targetVertex : graph.getAllVertices()) {
            long currentSumOfSteps = 0;
            boolean reachableByAll = true;

            for (Map<Vertex<Cell>, Integer> bfsResult : robotBfsResults) {
                Integer dist = bfsResult.get(targetVertex);
                if (dist == null) {
                    reachableByAll = false;
                    break;
                }
                currentSumOfSteps += dist;
            }

            if (reachableByAll && currentSumOfSteps < minTotalSteps) {
                minTotalSteps = currentSumOfSteps;
                optimalTargetCell = targetVertex.getData();
            }
        }

        System.out.println("Board Size: " + gridRows + "x" + gridCols);
        System.out.println("Board Type: Randomly generated with " + (int)(board.getObstacleProbability() * 100) + "% obstacle probability.");
        System.out.println("Number of Robots (k): " + k +"\n");
        if (optimalTargetCell != null) {
            System.out.println("Optimal meeting cell: " + optimalTargetCell);
            System.out.println("Minimal total steps: " + minTotalSteps);
        } else {
            System.out.println("No common meeting cell found for all robots.");
        }
    }
}