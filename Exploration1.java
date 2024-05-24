import java.util.ArrayList;
import java.util.List;

public class Exploration1 {

    public static void main(String[] args) {
        int mazeSize = 20;
        boolean display = false;
        int delay = 0;

        // Define obstacle densities
        double[] obstacleDensities = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};

        // Store results for each density
        List<Double> probabilitiesDFS = new ArrayList<>();
        List<Double> probabilitiesBFS = new ArrayList<>();
        List<Double> probabilitiesAStar = new ArrayList<>();

        for (double obstacleDensity : obstacleDensities) {
            int totalTrials = 1000;

            double probabilityDFS = conductDFSExperiment(mazeSize, obstacleDensity, totalTrials, display, delay);
            double probabilityBFS = conductBFSExperiment(mazeSize, obstacleDensity, totalTrials, display, delay);
            double probabilityAStar = conductAStarExperiment(mazeSize, obstacleDensity, totalTrials, display, delay);

            probabilitiesDFS.add(probabilityDFS);
            probabilitiesBFS.add(probabilityBFS);
            probabilitiesAStar.add(probabilityAStar);

            System.out.println("Obstacle Density: " + obstacleDensity);
            System.out.println("Probability of reaching target (DFS): " + probabilityDFS);
            System.out.println("Probability of reaching target (BFS): " + probabilityBFS);
            System.out.println("Probability of reaching target (A*): " + probabilityAStar);
            System.out.println();
        }

        // Output the results
        System.out.println("DFS Probabilities: " + probabilitiesDFS);
        System.out.println("BFS Probabilities: " + probabilitiesBFS);
        System.out.println("A* Probabilities: " + probabilitiesAStar);
    }

    private static double conductDFSExperiment(int mazeSize, double obstacleDensity, int totalTrials, boolean display, int delay) {
        int successfulTrialsDFS = 0;

        for (int i = 0; i < totalTrials; i++) {
            Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);
            Cell start = maze.get(0, 0);
            Cell target = maze.get(mazeSize - 1, mazeSize - 1);

            AbstractMazeSearch dfs = new MazeDepthFirstSearch(maze);
            LinkedList<Cell> dfsPath = dfs.search(start, target, display, delay);

            if (dfsPath != null) {
                successfulTrialsDFS++;
            }
        }

        return (double) successfulTrialsDFS / totalTrials;
    }

    private static double conductBFSExperiment(int mazeSize, double obstacleDensity, int totalTrials, boolean display, int delay) {
        int successfulTrialsBFS = 0;

        for (int i = 0; i < totalTrials; i++) {
            Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);
            Cell start = maze.get(0, 0);
            Cell target = maze.get(mazeSize - 1, mazeSize - 1);

            AbstractMazeSearch bfs = new MazeBreadthFirstSearch(maze);
            LinkedList<Cell> bfsPath = bfs.search(start, target, display, delay);

            if (bfsPath != null) {
                successfulTrialsBFS++;
            }
        }

        return (double) successfulTrialsBFS / totalTrials;
    }

    private static double conductAStarExperiment(int mazeSize, double obstacleDensity, int totalTrials, boolean display, int delay) {
        int successfulTrialsAStar = 0;

        for (int i = 0; i < totalTrials; i++) {
            Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);
            Cell start = maze.get(0, 0);
            Cell target = maze.get(mazeSize - 1, mazeSize - 1);

            AbstractMazeSearch astar = new MazeAStarSearch(maze);
            LinkedList<Cell> astarPath = astar.search(start, target, display, delay);

            if (astarPath != null) {
                successfulTrialsAStar++;
            }
        }

        return (double) successfulTrialsAStar / totalTrials;
    }
}
